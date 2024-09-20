package Recorders.ggogit.domain.leaf.repository;

import Recorders.ggogit.domain.leaf.entity.Leaf;
import Recorders.ggogit.domain.leaf.view.*;
import Recorders.ggogit.type.SearchType;
import Recorders.ggogit.type.SortType;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface LeafRepository {
    Long save(Leaf leaf);
    Long update(Leaf leaf);
    void delete(Leaf leaf);
    Long existsById(Long leafId);
    Leaf findById(Long id);
    List<Leaf> findAll();
    List<LeafCardView> findLeafCardViewByBookId(Long bookId, Long memberId);
    List<LeafCardView> findLeafCardViewByBookId(Long bookId, Long memberId, SearchType searchType, String search, SortType sortType, Long page, Long size);
    List<LeafImageCardView> findLeafImageCardViewByMemberId(Long memberId, SearchType searchType, String search, SortType sortType, Long page, Long size);
    List<Leaf> findByTreeId(Long treeId);
    List<LeafEtcView> findLeafEtcViewByTreeId(Long treeId, SearchType searchType, String search, SortType sortType, Long page, Long size);
    LeafRecentSaveBranchView findLeafRecentSaveBranchViewByTreeId(Long treeId);
    LeafBreadcrumbView findLeafBreadcrumbViewByTreeIdAndLeafId(Long treeId, Long leafId);
    List<Leaf> findByTreeIdOrderByCreateTimeDesc(Long treeId);
}