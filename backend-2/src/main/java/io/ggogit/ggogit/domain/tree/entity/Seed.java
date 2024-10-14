package io.ggogit.ggogit.domain.tree.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@Setter
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "SEED")
public class Seed {
    @Id
    @Column(name = "ID", nullable = false)
    private Long id;

    @Size(max = 255)
    @NotNull
    @Column(name = "KOR_NAME", nullable = false)
    private String korName;

    @Size(max = 255)
    @NotNull
    @Column(name = "ENG_NAME", nullable = false)
    private String engName;

    @Version
    @Column(name = "VERSION", nullable = false)
    private Long version;
}