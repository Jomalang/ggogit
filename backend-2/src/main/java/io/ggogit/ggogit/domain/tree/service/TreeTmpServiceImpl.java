package io.ggogit.ggogit.domain.tree.service;

import io.ggogit.ggogit.domain.book.entity.BookCategory;
import io.ggogit.ggogit.domain.book.repository.BookCategoryRepository;
import io.ggogit.ggogit.domain.image.repository.ImageRepository;
import io.ggogit.ggogit.domain.member.entity.Member;
import io.ggogit.ggogit.domain.member.repository.MemberRepository;
import io.ggogit.ggogit.domain.tree.entity.Seed;
import io.ggogit.ggogit.domain.tree.entity.TreeTmp;
import io.ggogit.ggogit.domain.tree.repository.SeedRepository;
import io.ggogit.ggogit.domain.tree.repository.TreeTmpRepository;
import io.ggogit.ggogit.type.UploadFolderType;
import jakarta.annotation.Nullable;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TreeTmpServiceImpl implements TreeTmpService {

    private final TreeTmpRepository treeTmpRepository;
    private final BookCategoryRepository bookCategoryRepository;
    private final MemberRepository memberRepository;
    private final SeedRepository seedRepository;
    private final ImageRepository imageRepository;

    @Override
    public void deleteTmpById(Long memberId) {
        if (treeTmpRepository.findByMemberId(memberId).isPresent())
            treeTmpRepository.deleteById(memberId);
    }

    @Override
    @Transactional // 직접 등록 도서
    public Long save(TreeTmp treeTmp, Long memberId, Long seedId, @Nullable Long bookCategoryId, @Nullable byte[] img, @Nullable String imgFileName) {

        // 기존에 있는 임시 트리 삭제
        TreeTmp savedTreeTmp = treeTmpRepository.findByMemberId(memberId).orElse(null);
        if (savedTreeTmp != null) {
            if (savedTreeTmp.getImageFile() != null) {
                imageRepository.deleteImage(savedTreeTmp.getImageFile(), UploadFolderType.TMP);
            }
            treeTmpRepository.delete(savedTreeTmp);
        }

        Member member = memberRepository.findById(memberId) // 회원이 존재하는지 확인 TODO: 나중에 제거 해야함
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다."));

        treeTmp.setMember(member);

        if (bookCategoryId != null) {  // 도서 카테고리를 선택한 경우
            BookCategory bookCategory = bookCategoryRepository.findById(bookCategoryId)
                    .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 카테고리입니다."));
            treeTmp.setBookCategory(bookCategory);
        }

        Seed seed = seedRepository.findById(seedId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 씨앗입니다."));
        treeTmp.setSeed(seed);

        if (imgFileName != null) { // 이미지 파일 존재하면 저장
            String fileName = imageRepository.changeFileNameToUUID(imgFileName);
            treeTmp.setImageFile(fileName);
        }

        treeTmpRepository.save(treeTmp);

        if (img != null) { // 이미지 저장
            imageRepository.saveImage(treeTmp.getImageFile(), img, UploadFolderType.TMP);
        }

        return treeTmp.getId();
    }
}
