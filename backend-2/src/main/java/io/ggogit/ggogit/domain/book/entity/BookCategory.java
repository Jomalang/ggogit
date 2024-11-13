package io.ggogit.ggogit.domain.book.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "BOOK_CATEGORY")
public class BookCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Long id;

    @Size(max = 255)
    @NotNull
    @Column(name = "NAME", nullable = false)
    private String name;

    @Version
    @Builder.Default
    @Column(name = "VERSION", nullable = false)
    private Long version = 0L;

    public static BookCategory of(Long categoryId, String categoryName) {
        return BookCategory.builder()
                .id(categoryId)
                .name(categoryName)
                .build();
    }
}