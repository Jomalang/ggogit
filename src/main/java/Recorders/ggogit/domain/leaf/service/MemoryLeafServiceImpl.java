package Recorders.ggogit.domain.leaf.service;

import Recorders.ggogit.Type.LeafDirectionType;
import Recorders.ggogit.domain.leaf.entity.LeafTag;
import Recorders.ggogit.domain.leaf.view.*;
import jakarta.annotation.Nullable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MemoryLeafServiceImpl implements LeafService {

    @Override
    public List<LeafItemView> getLeafItems(Long treeId, @Nullable Long leafId) {

        List<LeafTag> tags = List.of(
                LeafTag.builder().id(1L).name("태그1").build(),
                LeafTag.builder().id(2L).name("태그2").build(),
                LeafTag.builder().id(3L).name("태그3").build()
        );

        return List.of(
                LeafItemView.builder().id(1L).parentLeafId(null).title("리프제목1").tags(tags).focused(false).direction(LeafDirectionType.START).createTime(LocalDateTime.now()).build(),
                LeafItemView.builder().id(2L).parentLeafId(1L).title("리프제목2").tags(tags).focused(false).direction(LeafDirectionType.RIGHT).createTime(LocalDateTime.now()).build(),
                LeafItemView.builder().id(3L).parentLeafId(2L).title("리프제목3").tags(tags).focused(false).direction(LeafDirectionType.SIDE).createTime(LocalDateTime.now()).build(),
                LeafItemView.builder().id(4L).parentLeafId(3L).title("리프제목4").tags(tags).focused(false).direction(LeafDirectionType.DOWN).createTime(LocalDateTime.now()).build(),
                LeafItemView.builder().id(5L).parentLeafId(4L).title("리프제목5").tags(tags).focused(false).direction(LeafDirectionType.LEFT).createTime(LocalDateTime.now()).build(),
                LeafItemView.builder().id(6L).parentLeafId(5L).title("리프제목6").tags(tags).focused(true).direction(LeafDirectionType.END).createTime(LocalDateTime.now()).build()
        );
    }

    @Override
    public LeafBreadcrumbView getBreadcrumb(Long leafId) {
        return getBreadcrumb(null, leafId);
    }

    @Override
    public LeafBreadcrumbView getBreadcrumb(Long treeId, Long leafId) {
        return LeafBreadcrumbView.builder().treeName("트리이름").branchName("브랜치명(말단로그)").build();
    }

    @Override
    public LeafRecentBranchView getRecentBranch(Long treeId) {
        return LeafRecentBranchView.builder().branchName("최근 수정 브랜치명").leafCount(10L).likeCount(5L).viewCount(100L).updateTime(LocalDateTime.now()).build();
    }

    @Override
    public LeafImageCardView LeafImageCardView(Long memberId) {
        return null;
    }
}