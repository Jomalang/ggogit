package Recorders.ggogit.domain.leaf.repository;

import Recorders.ggogit.domain.leaf.entity.LeafBook;
import Recorders.ggogit.domain.leaf.view.LeafBookView;
import Recorders.ggogit.domain.leaf.view.LeafCardView;
import Recorders.ggogit.type.SearchType;
import Recorders.ggogit.type.SortType;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface LeafBookRepository {
    Long save(LeafBook leafBook);
    LeafBook findByLeafId(Long leafId);
    LeafBook findById(Long leafId);
    Long update(LeafBook leafBook);
    void delete(LeafBook leafBook);
    LeafBookView findLeafBookViewByLeafId(Long leafBookId);
    List<LeafBookView> findLeafBookViewByTreeId(Long treeId);
    List<LeafBookView> findLeafBookViewByTreeId(Long treeId, SearchType searchType, String search, SortType sortType, Long page, Long size);
    List<LeafCardView> findLeafCardViewByBookId(Long bookId, SearchType searchType, String search, SortType sortType, Long page, Long size);
}