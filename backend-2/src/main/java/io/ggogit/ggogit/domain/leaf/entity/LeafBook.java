package io.ggogit.ggogit.domain.leaf.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@SQLDelete(sql = "update leaf_book set is_deleted = true where leaf_id = ?")
@SQLRestriction("is_deleted = false")
@EntityListeners(AuditingEntityListener.class)
@Table(name = "LEAF_BOOK")
public class LeafBook {
    @Id
    @Column(name = "LEAF_ID", nullable = false)
    private Long id;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "LEAF_ID", nullable = false)
    private Leaf leaf;

    @NotNull
    @Column(name = "START_PAGE", nullable = false)
    private Integer startPage;

    @NotNull
    @Column(name = "END_PAGE", nullable = false)
    private Integer endPage;

    @NotNull
    @Builder.Default
    @ColumnDefault("0")
    @Column(name = "IS_DELETED", nullable = false)
    private Boolean isDeleted = false;

    @Version
    @Column(name = "VERSION", nullable = false)
    private Long version;
}