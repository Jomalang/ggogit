package io.ggogit.ggogit.domain.leaf.entity;

import io.ggogit.ggogit.domain.tree.entity.Tree;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@SQLDelete(sql = "update leaf set is_deleted = 1 where id = ?")
@SQLRestriction("is_deleted = false")
@EntityListeners(AuditingEntityListener.class)
@Table(name = "LEAF")
public class Leaf {
    @Id
    @Column(name = "ID", nullable = false)
    private Long id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "TREE_ID", nullable = false)
    private Tree tree;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PARENT_LEAF_ID")
    private Leaf parentLeaf;

    @Size(max = 255)
    @NotNull
    @Column(name = "TITLE", nullable = false)
    private String title;

    @Size(max = 3000)
    @NotNull
    @Column(name = "CONTENT", nullable = false, length = 3000)
    private String content;

    @NotNull
    @ColumnDefault("0")
    @Column(name = "VIEW_COUNT", nullable = false)
    private Integer viewCount;

    @NotNull
    @ColumnDefault("0")
    @Column(name = "LIKE_COUNT", nullable = false)
    private Integer likeCount;

    @NotNull
    @ColumnDefault("0")
    @Column(name = "CHILD_LEAF_COUNT", nullable = false)
    private Integer childLeafCount;

    @NotNull
    @ColumnDefault("1")
    @Column(name = "VISIBILITY", nullable = false)
    private Boolean visibility = false;

    @NotNull
    @ColumnDefault("0")
    @Column(name = "BOOK_MARK", nullable = false)
    private Boolean bookMark = false;

    @NotNull
    @ColumnDefault("0")
    @Column(name = "IS_DELETED", nullable = false)
    private Boolean isDeleted = false;

    @NotNull
    @CreatedDate
    @Column(name = "CREATE_TIME", nullable = false)
    private LocalDateTime createTime;

    @NotNull
    @LastModifiedDate
    @Column(name = "UPDATE_TIME", nullable = false)
    private LocalDateTime updateTime;

    @Version
    @Column(name = "VERSION", nullable = false)
    private Long version;
}