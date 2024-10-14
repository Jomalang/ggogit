package io.ggogit.ggogit.domain.tree.entity;

import io.ggogit.ggogit.domain.book.entity.Book;
import io.ggogit.ggogit.domain.book.entity.BookCategory;
import io.ggogit.ggogit.domain.member.entity.Member;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "TREE_SAVE_TMP")
public class TreeSaveTmp {
    @Id
    @Column(name = "ID", nullable = false)
    private Long id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "MEMBER_ID", nullable = false)
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BOOK_CATEGORY_ID")
    private BookCategory bookCategory;

    @Size(max = 255)
    @Column(name = "BOOK_TITLE")
    private String bookTitle;

    @Size(max = 255)
    @Column(name = "AUTHOR")
    private String author;

    @Size(max = 255)
    @Column(name = "PUBLISHER")
    private String publisher;

    @Column(name = "TOTAL_PAGE")
    private Integer totalPage;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BOOK_ID")
    private Book book;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "SEED_ID", nullable = false)
    private Seed seed;

    @Size(max = 3000)
    @NotNull
    @Column(name = "TREE_TITLE", nullable = false, length = 3000)
    private String treeTitle;

    @Size(max = 3000)
    @NotNull
    @Column(name = "DESCRIPTION", nullable = false, length = 3000)
    private String description;

    @Size(max = 255)
    @Column(name = "IMAGE_FILE")
    private String imageFile;

    @NotNull
    @ColumnDefault("1")
    @Column(name = "VISIBILITY", nullable = false)
    private Boolean visibility = false;

    @NotNull
    @CreatedDate
    @Column(name = "CREATE_TIME", nullable = false)
    private LocalDateTime createTime;

    @Version
    @Column(name = "VERSION", nullable = false)
    private Long version;
}