package Recorders.ggogit.domain.tree.repository;

import Recorders.ggogit.domain.tree.entity.Tree;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TreeRepository {

    //Create:  트리 입력
    void save(Tree tree);

    //Read:  트리 리스트 출력
    List<Tree> findListAll();
    //Read:  트리id로 트리 조회
    Tree findByTreeId(long id);
    //Read:  회원id로 트리 조회
    List<Tree> findByMemberId(long memberId);
    //Read:  트리제목으로 트리 조회
    List<Tree> findByTitle(String title);
    //Read:  설명글으로 트리 조회
    List<Tree> findByDescription(String description);
    //Read:  공개성으로 트리 조회
    List<Tree> findByVisibility(boolean visibility);
    //Read:  id로 회원id 조회
    Long findMemberIdById(long id);
    //Read:  id로 트리제목 조회
    String  findTitleById(long id);
    //Read:  id로 설명글 조회
    String  findDescriptionById(long id);
    //Read:  id로 공개성 조회
    Boolean findVisibilityById(long id);

    //Update: 제목수정
    void updateByTitle(Long id, String title);
    //Update: 설명문 수정
    void updateByDescription(Long id, String description);
    //Update: 공개성 수정
    void updateByVisibility(Long id, boolean visibility);

    //Delete: 트리 삭제
    void delete(Long id);
}
