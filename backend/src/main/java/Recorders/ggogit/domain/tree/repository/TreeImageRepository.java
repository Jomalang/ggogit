package Recorders.ggogit.domain.tree.repository;

import Recorders.ggogit.domain.tree.entity.TreeImage;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TreeImageRepository {
    //Create: 도서 정보 저장
    void save(TreeImage treeImage);
    //Read: 도서 정보 조회
    TreeImage findById(Long treeId);
    //Update: 도서 정보 수정
    void update(TreeImage treeImage);
    //Delete: 도서 정보 삭제
    void delete(Long id);
}
