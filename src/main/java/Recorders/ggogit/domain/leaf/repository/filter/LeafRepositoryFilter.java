package Recorders.ggogit.domain.leaf.repository.filter;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@Builder
public class LeafRepositoryFilter {
    private final List<Long> ids;
    private final List<Long> treeIds;
    private final List<Long> parentLeafIds;
    private final List<Boolean> visibilities;
    private final List<Integer> viewCounts;
    private final List<Integer> likeCounts;
    private final List<String> titles;
    private final List<String> contents;
    private final List<Integer> childLeafCounts;
    private final List<Long> bookMarks;
    private final List<LocalDateTime> updateTimes;
    private final List<LocalDateTime> createTimes;

    public LeafRepositoryFilter() {
        this.ids = new ArrayList<>();
        this.treeIds = new ArrayList<>();
        this.parentLeafIds = new ArrayList<>();
        this.visibilities = new ArrayList<>();
        this.viewCounts = new ArrayList<>();
        this.likeCounts = new ArrayList<>();
        this.titles = new ArrayList<>();
        this.contents = new ArrayList<>();
        this.childLeafCounts = new ArrayList<>();
        this.bookMarks = new ArrayList<>();
        this.updateTimes = new ArrayList<>();
        this.createTimes = new ArrayList<>();
    }

    public void addId(Long id) {
        this.ids.add(id);
    }

    public void addTreeId(Long treeId) {
        this.treeIds.add(treeId);
    }

    public void addParentLeafId(Long parentLeafId) {
        this.parentLeafIds.add(parentLeafId);
    }

    public void addVisibility(Boolean visibility) {
        this.visibilities.add(visibility);
    }

    public void addViewCount(Integer viewCount) {
        this.viewCounts.add(viewCount);
    }

    public void addLikeCount(Integer likeCount) {
        this.likeCounts.add(likeCount);
    }

    public void addTitle(String title) {
        this.titles.add(title);
    }

    public void addContent(String content) {
        this.contents.add(content);
    }

    public void addChildLeafCount(Integer childLeafCount) {
        this.childLeafCounts.add(childLeafCount);
    }

    public void addBookMark(Long bookMark) {
        this.bookMarks.add(bookMark);
    }

    public void addUpdateTime(LocalDateTime updateTime) {
        this.updateTimes.add(updateTime);
    }

    public void addCreateTime(LocalDateTime createTime) {
        this.createTimes.add(createTime);
    }
}