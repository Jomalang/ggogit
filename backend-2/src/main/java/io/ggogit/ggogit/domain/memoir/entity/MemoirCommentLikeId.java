package io.ggogit.ggogit.domain.memoir.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.util.Objects;

@Getter
@Setter
@Embeddable
public class MemoirCommentLikeId implements java.io.Serializable {
    private static final long serialVersionUID = -5480181368882229098L;
    @NotNull
    @Column(name = "MEMBER_ID", nullable = false)
    private Long memberId;

    @NotNull
    @Column(name = "MEMOIR_COMMENT_ID", nullable = false)
    private Long memoirCommentId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        MemoirCommentLikeId entity = (MemoirCommentLikeId) o;
        return Objects.equals(this.memoirCommentId, entity.memoirCommentId) &&
                Objects.equals(this.memberId, entity.memberId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(memoirCommentId, memberId);
    }

}