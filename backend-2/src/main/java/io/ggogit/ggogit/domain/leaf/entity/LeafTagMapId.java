package io.ggogit.ggogit.domain.leaf.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.Hibernate;

import java.util.Objects;

@Data
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor
@Embeddable
public class LeafTagMapId implements java.io.Serializable {
    private static final long serialVersionUID = -4006769675286114456L;
    @NotNull
    @Column(name = "LEAF_ID", nullable = false)
    private Long leafId;

    @NotNull
    @Column(name = "LEAF_TAG_ID", nullable = false)
    private Long leafTagId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        LeafTagMapId entity = (LeafTagMapId) o;
        return Objects.equals(this.leafId, entity.leafId) &&
                Objects.equals(this.leafTagId, entity.leafTagId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(leafId, leafTagId);
    }

}