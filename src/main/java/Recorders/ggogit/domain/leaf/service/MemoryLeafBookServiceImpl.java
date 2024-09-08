package Recorders.ggogit.domain.leaf.service;
import Recorders.ggogit.type.SearchType;
import Recorders.ggogit.domain.leaf.view.LeafCardView;
import Recorders.ggogit.domain.leaf.view.LeafBookView;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MemoryLeafBookServiceImpl implements LeafBookService {


    @Override
    public void register(LeafBookView leafBookView) {

    }

    @Override
    public void register(LeafBookView leafBookView, Long parentLeafId) {

    }

    @Override
    public boolean isOwner(LeafBookView leafBookView) {
        return false;
    }

    @Override
    public void modify(LeafBookView leafBookView) {

    }

    @Override
    public void remove(LeafBookView leafBookView) {

    }

    @Override
    public LeafBookView get(Long leafBookId) {
        return null;
    }

    @Override
    public List<LeafBookView> leafBooks(Long treeId) {
        return List.of();
    }

    @Override
    public List<LeafBookView> leafBooks(Long treeId, SearchType searchType, String search) {
        return List.of();
    }

    @Override
    public List<LeafCardView> leafBookCards(Long bookId) {
        return List.of(
                LeafCardView.builder().treeId(1L).leafId(1L).title("리프제목1").updateTime(LocalDateTime.now()).content("나뭇잎내용1").likeCount(1646L).viewCount(1646L).userNickName("한태규짱").userEmailId("gksxorb1234").likeActive(false).build(),
                LeafCardView.builder().treeId(2L).leafId(2L).title("리프제목2").updateTime(LocalDateTime.now()).content("나뭇잎내용2").likeCount(1646L).viewCount(1646L).userNickName("한태규짱").userEmailId("gksxorb1234").likeActive(true).build(),
                LeafCardView.builder().treeId(3L).leafId(3L).title("리프제목3").updateTime(LocalDateTime.now()).content("나뭇잎내용3").likeCount(1646L).viewCount(1646L).userNickName("한태규짱").userEmailId("gksxorb1234").likeActive(false).build(),
                LeafCardView.builder().treeId(4L).leafId(4L).title("리프제목4").updateTime(LocalDateTime.now()).content("나뭇잎내용4").likeCount(1646L).viewCount(1646L).userNickName("한태규짱").userEmailId("gksxorb1234").likeActive(true).build(),
                LeafCardView.builder().treeId(5L).leafId(5L).title("리프제목5").updateTime(LocalDateTime.now()).content("나뭇잎내용5").likeCount(1646L).viewCount(1646L).userNickName("한태규짱").userEmailId("gksxorb1234").likeActive(false).build(),
                LeafCardView.builder().treeId(6L).leafId(6L).title("리프제목6").updateTime(LocalDateTime.now()).content("나뭇잎내용6").likeCount(1646L).viewCount(1646L).userNickName("한태규짱").userEmailId("gksxorb1234").likeActive(false).build()
        );
    }
}
