package Recorders.ggogit.domain.tree.service;

import Recorders.ggogit.domain.book.entity.Book;
import Recorders.ggogit.domain.book.repository.BookRepository;
import Recorders.ggogit.domain.book.view.BookInfoView;
import Recorders.ggogit.domain.leaf.repository.LeafRepository;
import Recorders.ggogit.domain.leaf.view.LeafBranchView;
import Recorders.ggogit.domain.member.repository.MemberRepository;
import Recorders.ggogit.domain.member.view.MemberImageView;
import Recorders.ggogit.domain.tree.entity.Tree;
import Recorders.ggogit.domain.tree.repository.SeedRepository;
import Recorders.ggogit.domain.tree.repository.TreeRepository;
import Recorders.ggogit.domain.tree.repository.TreeSaveTmpRepository;
import Recorders.ggogit.domain.tree.view.*;
import Recorders.ggogit.web.tree.form.TreeEtcSaveTmpForm;
import Recorders.ggogit.web.tree.form.TreeSaveTmpForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class TreeServiceImpl implements TreeService {

    @Autowired
    TreeRepository repository;

    @Autowired
    TreeSaveTmpRepository treeSaveTmpRepository;
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private LeafRepository leafRepository;
    @Autowired
    private TreeRepository treeRepository;
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private SeedRepository seedRepository;

    @Override
    public void register(Tree tree) {
        repository.save(tree);
    }

    @Override
    public List<TreeInfoView> getTreeInfoView(String str) {
        return List.of();
    }

    @Override
    public long hasTreeNumById(Long id) {
        return repository.hasTreeNumById(id);
    }

    @Override
    public List<TreeInfoView> getTreeInfoView(Long memberId) {

        return repository.getTreeInfoBookView(null,memberId);
    }

    @Override
    public List<TreeInfoView> getTreeInfoView(Long seedId,Long memberId) {

        return repository.getTreeInfoBookView(seedId,memberId);
    }

    @Override
    public List<BookTreeView> getBookTreeView(Long memberId) {
        return List.of();
    }

    @Override
    public List<EtcTreeView> getEtcTreeview(Long memberId) {
        return List.of();
    }

    @Override
    public Boolean getComplate(Long treeId) {
        return null;
    }

    @Override
    public void delete(Long treeId) {

    }

    public void setTreeImg(File img) {

    }

    public void tmpTreeSave(TreeSaveTmpForm form){
        treeSaveTmpRepository.save(form);

    }

    @Override
    public void tmpEtcTreeSave(TreeEtcSaveTmpForm form) {
        treeSaveTmpRepository.saveEtc(form);
    }

    @Override
    public void deleteTmpFormById(Long memberId) {
        treeSaveTmpRepository.deleteByMemberId(memberId);
    }

    @Override
    public TreeInfoView getTreeInfoViewByTreeId(Long treeId) {

        return repository.getTreeInfoViewByTreeId(treeId);
    }

    @Override
    public CombineTreeView findCombineTreeView(Long memberId, Long treeId) {
        MemberImageView memberImageView = memberRepository.getMemberImageView(memberId);
        TreeInfoView treeInfoView = repository.getTreeInfoViewByTreeId(treeId);
        List<LeafBranchView> leafList = leafRepository.findLeafBranchViewByTreeId(treeId);

        System.out.println(treeInfoView.toString());

        CombineTreeView combineTreeView = CombineTreeView.builder()
                .memberImageView(memberImageView)
                .treeInfoView(treeInfoView)
                .leafList(leafList)
                .build();

        return combineTreeView;
    }

    @Override
    public Integer getTreeCount(Long id) {
        return repository.getTreeCountByMemberId(id);
    }

    @Override
    public Long getSeedId(Long treeId) {
        Tree tree = repository.findById(treeId);
        return tree.getSeedId();
    }

    @Override
    public List<TreeCardView> findTreeCardView(Long seedId, Long memberId) {

        List<TreeCardView> treeCardViews = new ArrayList<>();
        List<Tree> treeList = treeRepository.findByMemberIdAndSeeedId(seedId,memberId);

        for(Tree tree : treeList){
            if(seedId == 0 || seedId == 1){
                BookInfoView book = bookRepository.findBookCategoryViewById(tree.getBookId());

                Long totalPage = book.getTotalPage();
                Long readingPage = repository.findReadPageById(tree.getId());
                String seedKorName = seedRepository.findById(tree.getSeedId()).getKorName();

                if(readingPage == null)
                    readingPage = 0L;
                boolean complateBook = (readingPage * 100.0 / totalPage) >= 80;

                Date publishYear = book.getPublishDate();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
                TreeCardView tmp = TreeCardView.builder()
                        .bookId(book.getId())
                        .bookCategory(book.getCategory())
                        .bookTitle(book.getTitle())
                        .bookAuthor(book.getAuthor())
                        // TODO: Book 테이블에 번역가 컬럼 추가해야함.
                        .bookTranslator(book.getAuthor())
                        .bookPublisher(book.getPublisher())
                        .bookPublishedYear(sdf.format(publishYear))
                        .bookComplete(complateBook)
                        .coverImageName(book.getImageFile())
                        .treeId(tree.getId())
                        .memberId(tree.getMemberId())
                        .seedId(tree.getSeedId())
                        .seedKorName(seedKorName)
                        .title(tree.getTitle())
                        .visibility(tree.getVisibility())
                        .leafCreatedAt(tree.getUpdateTime())
                        .build();
                treeCardViews.add(tmp);
            }else {
                String coverImage = repository.findTreeImageById(tree.getId());
                String seedKorName = seedRepository.findById(seedId).getKorName();
                String nickname = memberRepository.findById(tree.getMemberId()).getNickname();
                TreeCardView tmp = TreeCardView.builder()
                        .coverImageName(coverImage)
                        .treeId(tree.getId())
                        .bookAuthor(nickname)
                        .memberId(tree.getMemberId())
                        .seedId(tree.getSeedId())
                        .seedKorName(seedKorName)
                        .title(tree.getTitle())
                        .visibility(tree.getVisibility())
                        .leafCreatedAt(tree.getUpdateTime())
                        .build();
                treeCardViews.add(tmp);
            }
        }
        return treeCardViews;
    }

}
