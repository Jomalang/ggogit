package io.ggogit.ggogit.domain.memoir.entity;

import io.ggogit.ggogit.domain.member.entity.Member;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
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
@EntityListeners(AuditingEntityListener.class)
@Table(name = "MEMOIR_COMMENT_LIKE")
public class MemoirCommentLike {
    @EmbeddedId
    private MemoirCommentLikeId id;

    @MapsId("memberId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "MEMBER_ID", nullable = false)
    private Member member;

    @MapsId("memoirCommentId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "MEMOIR_COMMENT_ID", nullable = false)
    private MemoirComment memoirComment;

    @NotNull
    @ColumnDefault("1")
    @Column(name = "ACTIVE", nullable = false)
    private Boolean active = false;

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