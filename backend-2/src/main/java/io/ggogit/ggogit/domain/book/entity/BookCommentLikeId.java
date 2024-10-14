package io.ggogit.ggogit.domain.book.entity;

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
public class BookCommentLikeId implements java.io.Serializable {
    private static final long serialVersionUID = 2505131656719550998L;
    @NotNull
    @Column(name = "MEMBER_ID", nullable = false)
    private Long memberId;

    @NotNull
    @Column(name = "BOOK_COMMENT_ID", nullable = false)
    private Long bookCommentId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        BookCommentLikeId entity = (BookCommentLikeId) o;
        return Objects.equals(this.bookCommentId, entity.bookCommentId) &&
                Objects.equals(this.memberId, entity.memberId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bookCommentId, memberId);
    }

}