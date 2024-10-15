package io.ggogit.ggogit.domain.leaf.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "LEAF_TAG_MAP")
public class LeafTagMap {
    @EmbeddedId
    private LeafTagMapId id;

    @MapsId("leafId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "LEAF_ID", nullable = false)
    private Leaf leaf;

    @MapsId("leafTagId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "LEAF_TAG_ID", nullable = false)
    private LeafTag leafTag;

    @NotNull
    @Builder.Default
    @ColumnDefault("1")
    @Column(name = "ACTIVE", nullable = false)
    private Boolean active = false;

    @Version
    @Column(name = "VERSION", nullable = false)
    private Long version;

    public static LeafTagMap of(Leaf leaf, LeafTag leafTag) {
        return LeafTagMap.builder()
                .id(LeafTagMapId.of(leaf.getId(), leafTag.getId()))
                .leaf(leaf)
                .leafTag(leafTag)
                .build();
    }
}