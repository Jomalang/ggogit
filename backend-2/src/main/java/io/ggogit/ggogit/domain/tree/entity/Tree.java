package io.ggogit.ggogit.domain.tree.entity;

import io.ggogit.ggogit.domain.book.entity.Book;
import io.ggogit.ggogit.domain.leaf.entity.Leaf;
import io.ggogit.ggogit.domain.member.entity.Member;
import io.ggogit.ggogit.domain.memoir.entity.Memoir;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@SQLDelete(sql = "update tree set is_deleted = true where id = ? and version = ?")
@SQLRestriction("is_deleted = false")
@EntityListeners(AuditingEntityListener.class)
@Table(name = "TREE")
public class Tree {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Long id;

    @NotNull
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "MEMBER_ID", nullable = false)
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BOOK_ID")
    private Book book;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TREEBOOK_ID")
    private TreeBook treeBook;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "LEAF_ID")
    private Leaf leaf;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "SEED_ID", nullable = false)
    private Seed seed;

    @Size(max = 3000)
    @NotNull
    @Column(name = "TITLE", nullable = false, length = 3000)
    private String title;

    @Size(max = 3000)
    @NotNull
    @Column(name = "DESCRIPTION", nullable = false, length = 3000)
    private String description;

    @NotNull
    @Builder.Default
    @ColumnDefault("0")
    @Column(name = "BOOK_MARK_COUNT", nullable = false)
    private Integer bookMarkCount = 0;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "tree")
    private Memoir memoir;

    @NotNull
    @Builder.Default
    @ColumnDefault("1")
    @Column(name = "VISIBILITY", nullable = false)
    private Boolean visibility = false;

    @NotNull
    @Builder.Default
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

    public static Tree of(TreeSaveTmp treeSaveTmp, Book book, Member member, Seed seed) {
        return Tree.builder()
                .seed(seed)
                .book(book)
                .member(member)
                .bookMarkCount(0)
                .title(treeSaveTmp.getTreeTitle())
                .description(treeSaveTmp.getDescription())
                .visibility(treeSaveTmp.getVisibility())
                .build();
    }

    public static Tree of(TreeSaveTmp treeSaveTmp, Member member, Seed seed) {
        return Tree.builder()
                .seed(seed)
                .member(member)
                .bookMarkCount(0)
                .title(treeSaveTmp.getTreeTitle())
                .description(treeSaveTmp.getDescription())
                .visibility(treeSaveTmp.getVisibility())
                .build();
    }
}