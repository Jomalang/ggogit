package Recorders.ggogit.domain.leaf.service;

import Recorders.ggogit.domain.leaf.view.LeafCardView;
import Recorders.ggogit.domain.leaf.view.LeafBookView;
import Recorders.ggogit.type.SearchType;
import Recorders.ggogit.type.SortType;

import java.util.List;

public interface LeafBookService {

    boolean register(LeafBookView leafBookView);
    boolean registerRoot(LeafBookView leafBookView);
    boolean registerNode(LeafBookView leafBookView);
    boolean modify(LeafBookView leafBookView);
    boolean remove(Long leafId);

    LeafBookView get(Long leafBookId);
    List<LeafBookView> leafBooks(Long treeId);
    List<LeafBookView> leafBooks(Long treeId, SearchType searchType, String search);
    List<LeafBookView> leafBooks(Long treeId, SearchType searchType, String search, SortType sortType, Long page, Long size);
    List<LeafCardView> leafBookCards(Long bookId);
}