package Recorders.ggogit.domain.memoir.repository;

import Recorders.ggogit.domain.memoir.entity.Memoir;
import Recorders.ggogit.domain.memoir.vIew.MemoirBookView;
import Recorders.ggogit.domain.memoir.vIew.MemoirCommentLikeView;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MemoirRepository {
    //INSERT문은 삽입된 행의 수를 반환한다.
    int save(Memoir memoir);

    Memoir findById(long id);

    //삭제한 회고록의 id 반환
    Long delete(long treeId);

    int update(Memoir memoir);

    List<Memoir> findAll();

    Memoir findByTreeId(long treeId);

    Memoir findByTitle(String title);

    //고려해보기
    //Memoir findByBookId(long bookId);

    //뷰를 이용하는 메서드
    MemoirCommentLikeView findCntById(long id);


    List<MemoirBookView> findMemoirBookViews(long memberId);
}
