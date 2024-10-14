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
public class LeafLikeId implements java.io.Serializable {
    private static final long serialVersionUID = 1348945743290137231L;
    @NotNull
    @Column(name = "MEMBER_ID", nullable = false)
    private Long memberId;

    @NotNull
    @Column(name = "LEAF_ID", nullable = false)
    private Long leafId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        LeafLikeId entity = (LeafLikeId) o;
        return Objects.equals(this.leafId, entity.leafId) &&
                Objects.equals(this.memberId, entity.memberId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(leafId, memberId);
    }

}