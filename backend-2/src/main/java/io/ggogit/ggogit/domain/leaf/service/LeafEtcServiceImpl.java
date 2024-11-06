package io.ggogit.ggogit.domain.leaf.service;

import io.ggogit.ggogit.domain.image.repository.ImageRepositoryImpl;
import io.ggogit.ggogit.domain.leaf.entity.*;
import io.ggogit.ggogit.domain.leaf.repository.LeafImageRepository;
import io.ggogit.ggogit.domain.leaf.repository.LeafRepository;
import io.ggogit.ggogit.domain.leaf.repository.LeafTagMapRepository;
import io.ggogit.ggogit.domain.leaf.repository.LeafTagRepository;
import io.ggogit.ggogit.domain.leaf.util.ImageSaveUtil;
import io.ggogit.ggogit.domain.member.entity.Member;
import io.ggogit.ggogit.domain.member.repository.MemberRepository;
import io.ggogit.ggogit.domain.tree.entity.Seed;
import io.ggogit.ggogit.domain.tree.entity.Tree;
import io.ggogit.ggogit.domain.tree.entity.TreeImage;
import io.ggogit.ggogit.domain.tree.entity.TreeTmp;
import io.ggogit.ggogit.domain.tree.repository.TreeImageRepository;
import io.ggogit.ggogit.domain.tree.repository.TreeRepository;
import io.ggogit.ggogit.domain.tree.repository.TreeTmpRepository;
import io.ggogit.ggogit.type.UploadFolderType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class LeafEtcServiceImpl implements LeafEtcService {

    private final static int LEAF_MAX_CHILD_COUNT = 3;

    private final MemberRepository memberRepository;

    private final TreeTmpRepository treeTmpRepository;
    private final TreeRepository treeRepository;
    private final TreeImageRepository treeImageRepository;

    private final LeafTagMapRepository leafTagMapRepository;
    private final LeafImageRepository leafImageRepository;
    private final LeafRepository leafRepository;
    private final LeafTagRepository leafTagRepository;

    private final ImageRepositoryImpl imageRepository;

    @Override
    @Transactional
    public Leaf createFirstLeafEtc(Long memberId, Leaf leaf, List<Long> leafTagIds) {

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("Member 데이터가 없습니다."));

        // `System`은 `TreeTmp`에 데이터를 조회한다.
        TreeTmp treeTmp = treeTmpRepository.findByMemberId(memberId)
                .orElseThrow(() -> new IllegalArgumentException("TreeTmp 데이터가 없습니다."));

        Seed seed = treeTmp.getSeed();
        Tree tree = Tree.of(treeTmp, member, seed);
        treeRepository.save(tree);
        leaf.setTree(tree);

        Leaf savedLeaf = createLogic(memberId, leaf, leafTagIds);

        // `System`은 `TreeTmp` 데이터를 삭제한다.
        treeTmpRepository.delete(treeTmp);

        String treeImagePath = treeTmp.getImageFile();
        if (treeImagePath != null) { // 트리 이미지가 있으면 이동
            imageRepository.moveImage(treeImagePath, UploadFolderType.TMP, UploadFolderType.TREE);
        }

        // 리프 이미지 모두 이동
        imageRepository.moveAllImages(leaf.getContent(), UploadFolderType.TMP, UploadFolderType.LEAF);

        return savedLeaf;
    }

    private Leaf createLogic(Long memberId, Leaf leaf, List<Long> leafTagIds) {

        // `System`은 입력받은 데이터에서 `LeafTag` 데이터를 조회 후 `LeafTag` 데이터를 생성후 저장한다.
        List<LeafTag> leafTags = new ArrayList<>();
        for (Long leafTagId : leafTagIds) {
            LeafTag leafTag = leafTagRepository.findById(leafTagId)
                    .orElseThrow(() -> new IllegalArgumentException("LeafTag 데이터가 없습니다."));
            if (!Objects.equals(leafTag.getMember().getId(), memberId)) {
                throw new IllegalArgumentException("LeafTag 데이터의 MemberId와 LeafBook 데이터의 MemberId가 일치하지 않습니다.");
            }
            leafTags.add(leafTag);
        }

        // `System`은 `Leaf` 데이터 저장
        leafRepository.save(leaf);

        // `System`은 `LeafTagMap` 데이터를 생성후 저장한다.
        for (LeafTag leafTag : leafTags) {
            LeafTagMap leafTagMap = LeafTagMap.of(leaf, leafTag);
            leafTagMapRepository.save(leafTagMap);
        }

        return leaf;
    }

    @Override
    public Leaf createLeafEtc(Long memberId, Long parentLeafId, Leaf leaf, List<Long> leafTagIds) {

        // `System`은 부모 `Leaf` 데이터를 조회한다.
        Leaf parentLeaf = leafRepository.findById(parentLeafId)
                .orElseThrow(() -> new IllegalArgumentException("Leaf 데이터가 없습니다."));

        // `System`은 부모 `Leaf` 데이터의 자식 개수를 확인한다.
        if (LEAF_MAX_CHILD_COUNT <= parentLeaf.getChildLeafCount()) {
            throw new IllegalArgumentException("부모 Leaf의 자식 개수가 최대치 3개를 초과했습니다.");
        }

        leaf.setParentLeaf(parentLeaf);
        leaf.setTree(parentLeaf.getTree());

        Leaf savedLeaf = createLogic(memberId, leaf, leafTagIds);

        // `System`은 부모 `Leaf` 데이터의 자식 개수를 증가시킨다.
        parentLeaf.setChildLeafCount(parentLeaf.getChildLeafCount() + 1);
        leafRepository.save(parentLeaf);

        // 리프 이미지 모두 이동
        imageRepository.moveAllImages(leaf.getContent(), UploadFolderType.TMP, UploadFolderType.LEAF);

        return savedLeaf;
    }

    @Override
    public Leaf updateLeafEtc(Long memberId, Long leafId, Leaf toLeaf, List<Long> toLeafTagIds) {

        Leaf leaf = leafRepository.findById(leafId)
                .orElseThrow(() -> new IllegalArgumentException("Leaf 데이터가 없습니다."));

        // 리프 수정
        leaf.setTitle(toLeaf.getTitle());
        leaf.setContent(toLeaf.getContent()); // TODO: 이미지 저장 및 경로 변경
        leaf.setVisibility(toLeaf.getVisibility());
        leafRepository.save(leaf);

        List<LeafTagMap> leafTagMaps = leafTagMapRepository.findByLeaf(leaf);
        for (LeafTagMap leafTagMap : leafTagMaps) {
            leafTagMap.setActive(false);
            leafTagMapRepository.save(leafTagMap);
        }

        for (Long leafTagId : toLeafTagIds) {
            LeafTag leafTag = leafTagRepository.findById(leafTagId)
                    .orElseThrow(() -> new IllegalArgumentException("LeafTag 데이터가 없습니다."));

            if (!Objects.equals(leafTag.getMember().getId(), memberId)) {
                throw new IllegalArgumentException("LeafTag 데이터의 권한이 없습니다.");
            }

            // 리프 태그 맵핑 수정
            LeafTagMapId leafTagMapId = LeafTagMapId.of(leaf.getId(), leafTag.getId());
            leafTagMapRepository.findById(leafTagMapId).ifPresentOrElse(
                    leafTagMap -> { // 이미 있는 경우 활성화
                        leafTagMap.setActive(true);
                        leafTagMapRepository.save(leafTagMap);
                    },
                    () -> { // 없는 경우 생성
                        LeafTagMap leafTagMap = LeafTagMap.of(leaf, leafTag);
                        leafTagMapRepository.save(leafTagMap);
                    }
            );

        }

        return leaf;
    }

    @Override
    public void deleteLeafEtc(Long leafId) {

        Leaf leaf = leafRepository.findById(leafId)
                .orElseThrow(() -> new IllegalArgumentException("Leaf 데이터가 없습니다."));

        // LeafTagMap 비활성화
        List<LeafTagMap> leafTagMaps = leafTagMapRepository.findByLeaf(leaf);
        for (LeafTagMap leafTagMap : leafTagMaps) {
            leafTagMap.setActive(false);
            leafTagMapRepository.save(leafTagMap);
        }

        // LeafImage 삭제
        leafImageRepository.findById(leafId)
                .ifPresent(leafImageRepository::delete);

        // Leaf 삭제
        leafRepository.delete(leaf);
    }

    @Override
    public boolean isOwner(Long memberId, Long parentLeafId) {

        Leaf leaf = leafRepository.findById(parentLeafId)
                .orElseThrow(() -> new IllegalArgumentException("해당 리프를 찾을 수 없습니다."));

        return leaf.getTree().getMember().getId().equals(memberId);
    }
}
