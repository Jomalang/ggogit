package io.ggogit.ggogit.domain.book.entity;

import io.ggogit.ggogit.domain.member.entity.Member;
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
import java.time.LocalDate;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@SQLDelete(sql = "update book set is_deleted = true where id = ?")
@SQLRestriction("is_deleted = false")
@Table(name = "BOOK")
public class Book {
    @Id
    @Column(name = "ID", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "BOOK_CATEGORY_ID", nullable = false)
    private BookCategory bookCategory;

    @Size(max = 255)
    @NotNull
    @Column(name = "TITLE", nullable = false)
    private String title;

    @Size(max = 255)
    @NotNull
    @Column(name = "AUTHOR", nullable = false)
    private String author;

    @Size(max = 255)
    @Column(name = "TRANSLATOR")
    private String translator;

    @Size(max = 255)
    @Column(name = "ISBN")
    private String isbn;

    @Size(max = 255)
    @NotNull
    @Column(name = "PUBLISHER", nullable = false)
    private String publisher;

    @NotNull
    @Column(name = "PUBLISH_DATE", nullable = false)
    private LocalDate publishDate;

    @NotNull
    @Column(name = "TOTAL_PAGE", nullable = false)
    private Long totalPage;

    @Size(max = 255)
    @Column(name = "IMAGE_FILE")
    private String imageFile;

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
    @CreatedDate
    @LastModifiedDate
    @Column(name = "UPDATE_TIME", nullable = false)
    private LocalDateTime updateTime;

    @Version
    @Column(name = "VERSION", nullable = false)
    private Long version;
}