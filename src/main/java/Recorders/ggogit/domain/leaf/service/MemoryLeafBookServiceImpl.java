package Recorders.ggogit.domain.leaf.service;
import Recorders.ggogit.Type.SearchType;
import Recorders.ggogit.domain.leaf.view.LeafBookView;
import Recorders.ggogit.domain.leaf.view.LeafItemView;
import Recorders.ggogit.domain.leaf.view.LeafTagView;
import jakarta.annotation.Nullable;
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
    public List<LeafBookView> list(Long treeId) {
        return List.of();
    }

    @Override
    public List<LeafBookView> list(Long treeId, SearchType searchType, String search) {
        return List.of();
    }

    @Override
    public List<LeafItemView> getLeafItemList(Long treeId, @Nullable Long leafId) {

        List<LeafTagView> tags = List.of(
            LeafTagView.builder().id(1L).name("태그1").build(),
            LeafTagView.builder().id(2L).name("태그2").build(),
            LeafTagView.builder().id(3L).name("태그3").build()
        );

        return List.of(
                LeafItemView.builder().id(1L).parentLeafId(null).title("리프제목1").tags(tags).focused(false).createTime(LocalDateTime.MAX).build(),
                LeafItemView.builder().id(2L).parentLeafId(1L).title("리프제목2").tags(tags).focused(false).createTime(LocalDateTime.MAX).build(),
                LeafItemView.builder().id(3L).parentLeafId(2L).title("리프제목3").tags(tags).focused(false).createTime(LocalDateTime.MAX).build(),
                LeafItemView.builder().id(4L).parentLeafId(3L).title("리프제목4").tags(tags).focused(false).createTime(LocalDateTime.MAX).build(),
                LeafItemView.builder().id(5L).parentLeafId(4L).title("리프제목5").tags(tags).focused(false).createTime(LocalDateTime.MAX).build(),
                LeafItemView.builder().id(6L).parentLeafId(5L).title("리프제목6").tags(tags).focused(false).createTime(LocalDateTime.MAX).build()
        );
    }
}
