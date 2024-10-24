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
public class MemoirLikeId implements java.io.Serializable {
    private static final long serialVersionUID = 1696808674320850503L;
    @NotNull
    @Column(name = "MEMBER_ID", nullable = false)
    private Long memberId;

    @NotNull
    @Column(name = "MEMOIR_ID", nullable = false)
    private Long memoirId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        MemoirLikeId entity = (MemoirLikeId) o;
        return Objects.equals(this.memoirId, entity.memoirId) &&
                Objects.equals(this.memberId, entity.memberId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(memoirId, memberId);
    }

}