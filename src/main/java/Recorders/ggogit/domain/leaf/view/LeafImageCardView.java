package Recorders.ggogit.domain.leaf.view;

import Recorders.ggogit.Type.SeedCategoryType;

import java.time.LocalDateTime;

public class LeafImageCardView {
    String imageFileName;
    SeedCategoryType seedCategoryType;
    String title;
    String content;
    LocalDateTime updateTime;
    Long viewCount;
    Boolean likeActive;
}