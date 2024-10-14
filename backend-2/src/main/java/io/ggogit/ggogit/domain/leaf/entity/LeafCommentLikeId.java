package io.ggogit.ggogit.domain.leaf.entity;

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
public class LeafCommentLikeId implements java.io.Serializable {
    private static final long serialVersionUID = -3659886522042579768L;
    @NotNull
    @Column(name = "MEMBER_ID", nullable = false)
    private Long memberId;

    @NotNull
    @Column(name = "LEAF_COMMENT_ID", nullable = false)
    private Long leafCommentId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        LeafCommentLikeId entity = (LeafCommentLikeId) o;
        return Objects.equals(this.leafCommentId, entity.leafCommentId) &&
                Objects.equals(this.memberId, entity.memberId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(leafCommentId, memberId);
    }

}