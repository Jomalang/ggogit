package Recorders.ggogit.domain.tree.repository;

import Recorders.ggogit.domain.tree.entity.TreeBook;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TreeBookRepository {

    //Create: 도서 정보 저장
    void save(TreeBook treeBook);
    //Read: 도서 정보 조회
    TreeBook findById(Long treeId);
    //Update: 도서 정보 수정
    void update(TreeBook treeBook);
    //Delete: 도서 정보 삭제
    void delete(Long treeId);
}
