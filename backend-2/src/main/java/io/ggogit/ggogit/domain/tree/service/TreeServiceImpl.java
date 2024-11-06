package io.ggogit.ggogit.domain.tree.service;

import io.ggogit.ggogit.api.tree.dto.TreeCardResponse;
import io.ggogit.ggogit.api.tree.dto.TreeInfoResponse;
import io.ggogit.ggogit.domain.book.entity.Book;
import io.ggogit.ggogit.domain.book.repository.BookRepository;
import io.ggogit.ggogit.domain.leaf.entity.Leaf;
import io.ggogit.ggogit.domain.leaf.repository.LeafRepository;
import io.ggogit.ggogit.domain.member.entity.Member;
import io.ggogit.ggogit.domain.member.repository.MemberRepository;
import io.ggogit.ggogit.domain.tree.entity.Seed;
import io.ggogit.ggogit.domain.tree.entity.Tree;
import io.ggogit.ggogit.domain.tree.entity.TreeBook;
import io.ggogit.ggogit.domain.tree.entity.TreeImage;
import io.ggogit.ggogit.domain.tree.repository.SeedRepository;
import io.ggogit.ggogit.domain.tree.repository.TreeBookRepository;
import io.ggogit.ggogit.domain.tree.repository.TreeImageRepository;
import io.ggogit.ggogit.domain.tree.repository.TreeRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;


@Service
@RequiredArgsConstructor
public class TreeServiceImpl implements TreeService {

    private final TreeRepository treeRepository;
    private final BookRepository bookRepository;
    private final TreeBookRepository treeBookRepository;
    private final SeedRepository seedRepository;
    private final TreeImageRepository treeImageRepository;
    private final MemberRepository memberRepository;
    private final LeafRepository leafRepository;
    private final ModelMapper modelMapper;

    @Override
    public void register(Tree tree) {
        if (tree != null)
            treeRepository.save(tree);
    }
    @Override
    public void update(Tree tree) {
        if (tree != null)
            treeRepository.save(tree);
    }
    @Override
    public void delete(Long treeId) {
        Tree tree = treeRepository.findById(treeId).orElse(null);
        if (tree != null) {
            tree.setIsDeleted(true);
            treeRepository.save(tree);
        }
    }
    @Override
    public Tree get(Long treeId) {return treeRepository.findById(treeId).orElse(null);}

    @Override
    public List<Tree> findAllByMemberId(Long memberId) { return  treeRepository.findByMemberId(memberId); }

    @Override
    public Page<Tree> findAllPages(Long memberId, Pageable pageable){
        return treeRepository.findTreeByMemberIdFetch(memberId, pageable);
    }

    @Override
    public Boolean getComplate(Long treeId) {
        Tree tree = treeRepository.findById(treeId).orElse(null);
        Book book = tree.getBook();

        if (book == null) return false;

        Integer totalPage = tree.getBook().getTotalPage();
        Integer readingPage = tree.getTreeBook().getReadingPage();

        if (totalPage != null && readingPage != null) {
            return (readingPage * 100.0) / totalPage >= 80;
        }
        else return false;
    }


    @Override

    public Boolean isOwner(Long treeId, Long memberId) {
        Tree tree = treeRepository.findById(treeId).orElseThrow(()-> new IllegalArgumentException("tree not found"));
        return memberId.equals(tree.getMember().getId());
    }

    @Override
    public Integer getTreeCount(Long memberId) { return (treeRepository.findByMemberId(memberId)).size(); }

    @Override
    public Integer getLeafCount(Long treeId) {
        return 0;
    }

    @Override
    public Seed getSeedByTreeId(Long treeId) {
        return (treeRepository.findById(treeId)
                .orElseThrow(()-> new  IllegalArgumentException("해당하는 Tree가 없습니다."))).getSeed(); }


    @Override
    public Long getMemberId(Long treeId) {
        return (treeRepository.findById(treeId)
                .orElseThrow(()-> new IllegalArgumentException("해당하는 Tree가 없습니다."))).getMember().getId(); }


    public Page<TreeCardResponse> findTreeCardRequestList(Long seedId, Long memberId, Pageable pageable) {
        Page<Tree> treeList = treeRepository.findByMemberIdAndSeedId(memberId, seedId, pageable);
        return treeList.map(tree -> {
            if (tree.getSeed().getId() == 1){
                Book tmpBook =  bookRepository.findById(tree.getBook().getId())
                        .orElseThrow(()-> new IllegalArgumentException("해당하는 Book이 없습니다."));
//                BookInfoResponse book = BookInfoResponse.of(tmpBook);
                TreeBook treeBook = treeBookRepository.findById(tree.getId())
                        .orElseThrow(()-> new IllegalArgumentException("해당하는 TreeBook이 없습니다."));
                Seed tmpSeed = seedRepository.findById(tree.getSeed().getId())
                        .orElseThrow(()-> new IllegalArgumentException("해당하는 Seed가 없습니다."));

                Integer totalPage = tmpBook.getTotalPage();
                Integer readingPage = treeBook.getReadingPage();
                String seedKorName = tmpSeed.getKorName();

                if(readingPage == null)
                    readingPage = 0;
                boolean complateBook = (readingPage * 100.0 / totalPage) >= 80;

                return TreeCardResponse.toEntity(tmpBook, complateBook, tree, tmpSeed, memberId);

            }else {
                TreeImage treeImage = treeImageRepository.findById(tree.getId())
                        .orElseThrow(()-> new IllegalArgumentException("TreeImage가 없습니다."));
                Seed tmpSeed = seedRepository.findById(tree.getSeed().getId())
                        .orElseThrow(()-> new IllegalArgumentException("해당하는 Seed가 없습니다."));
                Member tmpMember = memberRepository.findById(tree.getMember().getId())
                        .orElseThrow(()-> new IllegalArgumentException("해당하는 Member가 없습니다."));
                String coverImage = treeImage.getName();
                String seedKorName = tmpSeed.getKorName();
                String nickname = tmpMember.getNickname();
                return TreeCardResponse.toEntity(coverImage, tmpMember, tree, tmpSeed, nickname);
            }
        });
    }

    @Override
    public TreeInfoResponse findTreeInfoResponse(Long memberId, Long treeId) {
        Tree tree = treeRepository.findById(treeId).orElseThrow(() -> new IllegalArgumentException("해당하는 Tree가 없습니다."));
        if (!treeRepository.findByMemberId(memberId).contains(tree)) return null;

        List<Leaf> leafList = leafRepository.findByTreeId(tree.getId());
        LocalDateTime lastestLeafTime = null;
        Long viewCount = 0L;
        Long likeCount = 0L;
        Long leafCount = 0L;
        for (Leaf leaf : leafList) {
            viewCount += leaf.getViewCount();
            likeCount += leaf.getLikeCount();
            leafCount++;
            if(lastestLeafTime == null || lastestLeafTime.isBefore(leaf.getUpdateTime()))
                lastestLeafTime = leaf.getUpdateTime();
        }
        System.out.println("============================================");
        System.out.println("============================================");
        System.out.println(tree.getTreeBook());
        System.out.println("============================================");
        System.out.println("============================================");

        return TreeInfoResponse.of(tree, lastestLeafTime != null ? lastestLeafTime : LocalDateTime.now(), leafCount, likeCount, viewCount);
    }

    @Override
    public Page<TreeInfoResponse> findTreeInfoResponseList(Long memberId, Pageable pageable) {
        Page<Tree> treeList = treeRepository.findByMemberId(memberId, pageable);
        return treeList.map(tree -> {
                List<Leaf> leafList = leafRepository.findByTreeId(tree.getId());
                LocalDateTime lastestLeafTime = null;
                Long viewCount = 0L;
                Long likeCount = 0L;
                Long leafCount = 0L;
                for (Leaf leaf : leafList) {
                    viewCount += leaf.getViewCount();
                    likeCount += leaf.getLikeCount();
                    leafCount++;
                    if(lastestLeafTime == null || lastestLeafTime.isBefore(leaf.getUpdateTime() != null ? leaf.getUpdateTime() : LocalDateTime.now()))
                        lastestLeafTime = leaf.getUpdateTime();
                }
                return TreeInfoResponse.of(tree, lastestLeafTime != null ? lastestLeafTime : LocalDateTime.now(), leafCount, likeCount, viewCount);
        });
    }

    @Override
    public List<TreeInfoResponse> findTreeInfoResponseList(Long memberId) {
        List<Tree> trees = treeRepository.findTreeByMemberIdFetch(memberId);

        return trees.stream().map(tree -> {
            LocalDateTime lastestLeafTime = null;
            Long viewCount = 0L;
            Long likeCount = 0L;
            Long leafCount = 0L;
            List<Leaf> leafs = tree.getLeaf();
            for (Leaf leaf : leafs) {
                viewCount += leaf.getViewCount();
                likeCount += leaf.getLikeCount();
                leafCount++;
                if(lastestLeafTime == null || lastestLeafTime.isBefore(leaf.getUpdateTime() != null ? leaf.getUpdateTime() : LocalDateTime.now()))
                    lastestLeafTime = leaf.getUpdateTime();
            }
            return TreeInfoResponse.of(tree, lastestLeafTime != null ? lastestLeafTime : LocalDateTime.now(), leafCount, likeCount, viewCount);

        }).toList();
    }
    @Override
    public List<TreeInfoResponse> findTreeInfoResponseList(Long memberId, Long seedId) {
        List<Tree> trees = treeRepository.findTreeByMemberIdFetch(memberId, seedId);

        return trees.stream().map(tree -> {
            LocalDateTime lastestLeafTime = null;
            Long viewCount = 0L;
            Long likeCount = 0L;
            Long leafCount = 0L;
            List<Leaf> leafs = tree.getLeaf();
            for (Leaf leaf : leafs) {
                viewCount += leaf.getViewCount();
                likeCount += leaf.getLikeCount();
                leafCount++;
                if(lastestLeafTime == null || lastestLeafTime.isBefore(leaf.getUpdateTime() != null ? leaf.getUpdateTime() : LocalDateTime.now()))
                    lastestLeafTime = leaf.getUpdateTime();
            }
            return TreeInfoResponse.of(tree, lastestLeafTime != null ? lastestLeafTime : LocalDateTime.now(), leafCount, likeCount, viewCount);

        }).toList();
    }


}
