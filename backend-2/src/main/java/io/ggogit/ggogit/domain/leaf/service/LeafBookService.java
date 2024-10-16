package io.ggogit.ggogit.domain.leaf.service;

import io.ggogit.ggogit.domain.leaf.entity.Leaf;
import io.ggogit.ggogit.domain.leaf.entity.LeafBook;

import java.util.List;

public interface LeafBookService {
    LeafBook createFirstLeafBook(Long memberId, Leaf leaf, LeafBook leafBook, List<Long> leafTagIds);
    LeafBook createLeafBook(Long memberId, Long parentLeafId, Leaf leaf, LeafBook leafBook, List<Long> leafTagIds);
    LeafBook updateLeafBook(Long memberId, Long leafId, Leaf toLeaf, LeafBook toLeafBook, List<Long> toLeafTagIds);
    boolean isOwner(Long memberId, Long leafId);
    void deleteLeafBook(Long leafId);
//    LeafBookView register(LeafBookView leafBookView, Long seedId, Long memberId);
//    LeafBookView registerRoot(LeafBookView leafBookView, Long seedId, Long memberId);
//    LeafBookView registerNode(LeafBookView leafBookView, Long seedId, Long memberId);
//    boolean modify(LeafBookView leafBookView);
//    boolean remove(Long leafId);
//    LeafBookView getLeafBookView(Long leafBookId);
//    List<LeafBookView> getLeafBookViews(Long treeId);
//    List<LeafBookView> getLeafBookViews(Long treeId, SearchType searchType, String search);
//    List<LeafBookView> getLeafBookViews(Long treeId, SearchType searchType, String search, SortType sortType, Long page, Long size);
}