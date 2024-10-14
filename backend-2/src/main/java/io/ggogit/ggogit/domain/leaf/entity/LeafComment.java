package io.ggogit.ggogit.domain.leaf.entity;

import io.ggogit.ggogit.domain.member.entity.Member;
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
@SQLDelete(sql = "update leaf_comment set is_deleted = true where id = ?")
@SQLRestriction("is_deleted = false")
@EntityListeners(AuditingEntityListener.class)
@Table(name = "LEAF_COMMENT")
public class LeafComment {
    @Id
    @Column(name = "ID", nullable = false)
    private Long id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "MEMBER_ID", nullable = false)
    private Member member;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "LEAF_ID", nullable = false)
    private Leaf leaf;

    @NotNull
    @ColumnDefault("0")
    @Column(name = "LIKE_COUNT", nullable = false)
    private Integer likeCount;

    @Size(max = 1000)
    @NotNull
    @Column(name = "CONTENT", nullable = false, length = 1000)
    private String content;

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