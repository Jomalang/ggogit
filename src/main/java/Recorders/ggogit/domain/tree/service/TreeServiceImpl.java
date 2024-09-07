package Recorders.ggogit.domain.tree.service;

import Recorders.ggogit.domain.tree.entity.Tree;
import Recorders.ggogit.domain.tree.repository.TreeRepository;
import Recorders.ggogit.domain.tree.view.BookTreeView;
import Recorders.ggogit.domain.tree.view.EtcTreeView;
import Recorders.ggogit.domain.tree.view.TreeInfoView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class TreeServiceImpl implements TreeService {

    @Autowired
    TreeRepository repository;

    @Override
    public void register(Tree tree) {
        repository.save(tree);
    }

    @Override
    public List<TreeInfoView> getTreeInfoView(String str) {
        return List.of();
    }

    @Override
    public List<TreeInfoView> getTreeInfoView(Long memberId) {

        
        TreeInfoView treeInfo;
        List<TreeInfoView> treeInfos = new ArrayList<>();

        for(long i = 0L; i < 10L; i++) {
            treeInfo = TreeInfoView.builder()
                    .bookId(i)
                    .bookCategory("Test "+ i  +" Category")
                    .bookTitle("Book " + i)
                    .bookAuthor("Author " + i)
                    .bookTranslator("Translator " + i)
                    .bookPublisher("Publisher " + i)
                    .bookPublishedYear(Calendar.YEAR)
                    .bookTotalPage(400 + (int) i)
                    .treeId(i)
                    .memberId(memberId)
                    .seedId(i % 5)
                    .title("Title " + i)
                    .description("Description " + i)
                    .visibility(true)
                    .createdAt(new Date())
                    .leafCreatedAt(new Date())
                    .readingPage(315 + (int) i)
                    .coverImageName("book-cover-dummy1.svg")
                    .treeLeafCnt(100 * (i*4))
                    .treeLikeCnt(100 * (i*4))
                    .treeViewCnt(100 * (i*4))
                    .build();
            treeInfos.add(treeInfo);
        }
        return treeInfos;
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

}
