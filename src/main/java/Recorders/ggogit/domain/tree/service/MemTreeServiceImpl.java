package Recorders.ggogit.domain.tree.service;

import Recorders.ggogit.domain.tree.view.FindTreeInfoView;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class MemTreeServiceImpl implements TreeService {

    @Override
    public List<FindTreeInfoView> treeInfoLists(long memberId) {

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
                    .bookPage(400 + (int) i)
                    .treeId(i)
                    .memberId(memberId)
                    .seedId(i % 5)
                    .title("Title " + i)
                    .description("Description " + i)
                    .visibility(true)
                    .updateTime(new Date())
                    .createTime(new Date())
                    .readingPage(320 + (int) i)
                    .coverImageName("")
                    .treeLeafCnt(100 * i)
                    .treeLikeCnt(100 * i)
                    .treeViewCnt(100 * i)
                    .build();
            treeInfos.add(treeInfo);
        }
        return treeInfos;
    }
}
