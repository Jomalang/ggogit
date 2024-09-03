package Recorders.ggogit.domain.tree.service;

import Recorders.ggogit.domain.tree.view.FindTreeInfoView;
import Recorders.ggogit.domain.tree.view.MyTreeListsView;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class MemTreeServiceImpl implements TreeService {

    @Override
    public List<FindTreeInfoView> treeInfoLists(Long memberId) {

        FindTreeInfoView treeInfo;
        List<FindTreeInfoView> treeInfos = new ArrayList<>();

        for(long i = 0L; i < 10L; i++) {
            treeInfo = FindTreeInfoView.builder()
                    .bookId(i)
                    .bookCategory("Test "+ i  +"Category")
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
                    .readingPage(320 + (int) i)
                    .coverImageName("book-cover-dummy1.svg")
                    .treeLeafCnt(100 * i)
                    .treeLikeCnt(100 * i)
                    .treeViewCnt(100 * i)
                    .build();
            treeInfos.add(treeInfo);
        }
        return treeInfos;
    }

    @Override
    public List<MyTreeListsView> treeAllLists(Long memberId){
        MyTreeListsView treeLists;
        List<MyTreeListsView> treeListsViews = new ArrayList<>();

        for(long i = 0L; i < 10L; i++) {
            treeLists = MyTreeListsView.builder()
                    .bookId(i)
                    .bookCategory("Test "+ i  +"Category")
                    .bookTitle("Book " + i)
                    .bookAuthor("Author " + i)
                    .bookTranslator("Translator " + i)
                    .bookPublisher("Publisher " + i)
                    .bookPublishedYear(Calendar.YEAR)
                    .bookTotalPage(400 + (int) i)
                    .treeId(i)
                    .seedId(memberId)
                    .seedId(i % 5)
                    .title("Title " + i)
                    .visibility(true)
                    .leafCreatedAt(new Date())
                    .readingPage(320 + (int) i)
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
