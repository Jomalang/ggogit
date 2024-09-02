package Recorders.ggogit.domain.tree.service;

import Recorders.ggogit.domain.tree.entity.TreeImage;
import Recorders.ggogit.domain.tree.repository.TreeRepository;
import Recorders.ggogit.domain.tree.view.Vm_FindTreeInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class TreeServiceImpl implements TreeService {

    @Override
    public List<Vm_FindTreeInfo> findBymemberId(long memId) {
        long bookId = 1L;
        long treeId = 1L;
        long seedId = 1L;
        String title = "TEST TITLE";
        String description = "TEST DESCRIPTION";
        Integer bookMarkCountNum = 0;
        Boolean visibility = true;
        Date updateTime = new Date();
        Date createTime = new Date();
        int readingPage = 320;
        String treeImageName = "test.jpg";
        long treeLikeCnt = 100L;

        Vm_FindTreeInfo findTreeInfo;
        List<Vm_FindTreeInfo> treeInfoList = new ArrayList<>();

        for(int i = 0; i < 10; i++){
        findTreeInfo = Vm_FindTreeInfo.builder()
                .bookId(bookId++)
                .treeId(treeId++)
                .memberId(memId++)
                .seedId(seedId++)
                .title(title+i)
                .description(description+i)
                .bookMarkCountNum(bookMarkCountNum)
                .visibility(visibility)
                .updateTime(updateTime)
                .createTime(createTime)
                .readingPage(readingPage++)
                .treeImageName(i+treeImageName)
                .treeLikeCnt(treeLikeCnt++)
                .build();

        treeInfoList.add(findTreeInfo);
        }
        return treeInfoList;
    }
}
