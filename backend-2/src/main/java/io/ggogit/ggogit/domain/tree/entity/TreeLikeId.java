package io.ggogit.ggogit.domain.tree.entity;

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
public class TreeLikeId implements java.io.Serializable {
    private static final long serialVersionUID = 5227449366117188954L;
    @NotNull
    @Column(name = "MEMBER_ID", nullable = false)
    private Long memberId;

    @NotNull
    @Column(name = "TREE_ID", nullable = false)
    private Long treeId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        TreeLikeId entity = (TreeLikeId) o;
        return Objects.equals(this.treeId, entity.treeId) &&
                Objects.equals(this.memberId, entity.memberId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(treeId, memberId);
    }

}