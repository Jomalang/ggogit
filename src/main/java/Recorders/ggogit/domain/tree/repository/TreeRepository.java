package Recorders.ggogit.domain.tree.repository;

import Recorders.ggogit.domain.tree.entity.Tree;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TreeRepository {

    //Create:  트리 입력
    void saveTree(Tree tree);

    //Read:  트리 리스트 출력
    List<Tree> findTreeListAll();
    //Read:  트리id로 트리 조회
    Tree findTreeByTreeId(long id);
    //Read:  회원id로 트리 조회
    List<Tree> findTreeByMemberId(long memberId);
    //Read:  트리제목으로 트리 조회
    List<Tree> findTreeByTitle(String title);
    //Read:  설명글으로 트리 조회
    List<Tree> findTreeByDescription(String description);
    //Read:  공개성으로 트리 조회
    List<Tree> findTreeByVisibility(boolean visibility);

    //Update: 제목수정
    void updateTreeTitle(long id, String title);
    //Update: 설명문 수정
    void updateTreeDescription(long id, String description);
    //Update: 공개성 수정
    void updateTreeVisibility(long id, boolean visibility);

    //Delete: 트리 삭제
    void deleteTree(long id);
}
