package io.ggogit.ggogit.domain.member.entity;

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
public class FollowId implements java.io.Serializable {
    private static final long serialVersionUID = 4319676048288652083L;
    @NotNull
    @Column(name = "MEMBER_ID", nullable = false)
    private Long memberId;

    @NotNull
    @Column(name = "FOLLOW_ID", nullable = false)
    private Long followId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        FollowId entity = (FollowId) o;
        return Objects.equals(this.followId, entity.followId) &&
                Objects.equals(this.memberId, entity.memberId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(followId, memberId);
    }

}