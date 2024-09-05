package Recorders.ggogit.domain.tree.service;

import Recorders.ggogit.domain.tree.view.TreeInfoView;
import Recorders.ggogit.domain.tree.view.MyTreeView;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class MemTreeServiceImpl implements TreeService {

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
                    .bookTotalPage(400 + (int) i)
                    .treeId(i)
                    .memberId(memberId)
                    .seedId(i % 5)
                    .title("Title " + i)
                    .description("Description " + i)
                    .visibility(true)
                    .createdAt(new Date())
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
    public List<MyTreeView> getMyTreeView(Long memberId){
        MyTreeView treeLists;
        List<MyTreeView> treeListsViews = new ArrayList<>();

        for(long i = 0L; i < 10L; i++) {
            boolean bookComplete;
            bookComplete = (((319 + (int) i) * 100.0) / (400 + (int) i)) >= 80.0;
            treeLists = MyTreeView.builder()
                    .bookId(i)
                    .bookCategory("Test "+ i  +" Category")
                    .bookTitle("Book " + i)
                    .bookAuthor("Author " + i)
                    .bookTranslator("Translator " + i)
                    .bookPublisher("Publisher " + i)
                    .bookPublishedYear(Calendar.YEAR)
                    .bookTotalPage(400 + (int) i)
                    .bookComplete(bookComplete)
                    .treeId(i)
                    .seedId(memberId)
                    .seedId(i % 5)
                    .title("Title " + i)
                    .visibility(true)
                    .leafCreatedAt(new Date())
                    .readingPage(319 + (int) i)
                    .coverImageName("book-cover-dummy1.svg")
                    .treeLeafCnt(100 * i)
                    .treeLikeCnt(100 * i)
                    .treeViewCnt(100 * i)
                    .build();

            treeListsViews.add(treeLists);
        }
        return  treeListsViews;
    }
}
