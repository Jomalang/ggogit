package Recorders.ggogit.domain.leaf.repository;

import Recorders.ggogit.domain.leaf.entity.LeafBook;
import Recorders.ggogit.domain.leaf.view.LeafBookView;
import Recorders.ggogit.type.SearchType;
import Recorders.ggogit.type.SortType;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface LeafBookRepository {
    Long save(LeafBook leafBook);
    Long update(LeafBook leafBook);
    void delete(LeafBook leafBook);
    LeafBook findByLeafId(Long leafId);
    LeafBook findById(Long leafId);
    LeafBookView findLeafBookViewByLeafId(Long leafBookId);
    List<LeafBookView> findLeafBookViewByTreeId(Long treeId);
    List<LeafBookView> findLeafBookViewByTreeId(Long treeId, SearchType searchType, String search, SortType sortType, Long page, Long size);
    List<LeafBook> findByTreeId(Long treeId);
}