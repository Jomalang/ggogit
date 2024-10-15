package io.ggogit.ggogit.domain.memoir.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.ggogit.ggogit.domain.tree.entity.Tree;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
//TODO: SQLDelte내의 JPQL에서, bool<->int 타입컨버팅 지원하는지 확인
@SQLDelete(sql = "update memoir m set m.is_deleted = true where m.id = ? and version = ?") //위치 기반 바인딩..
@SQLRestriction("is_deleted = false")
@EntityListeners(AuditingEntityListener.class)
@Table(name = "MEMOIR")
public class Memoir {
    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue
    private Long id;

    @NotNull
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "TREE_ID", nullable = false)
    @JsonIgnore
    private Tree tree;

    @Size(max = 255)
    @NotNull
    @Column(name = "TITLE", nullable = false)
    private String title;

    @Size(max = 3000)
    @NotNull
    @Column(name = "TEXT", nullable = false, length = 3000)
    private String text;

    @NotNull
    @ColumnDefault("1")
    @Column(name = "VISIBILITY", nullable = false)
    private Boolean visibility = false;

    @NotNull
    @ColumnDefault("0")
    @Column(name = "IS_DELETED", nullable = false)
    private Boolean isDeleted = false;

    @NotNull
    @CreatedDate
    @Column(name = "CREATE_TIME", nullable = false)
    private LocalDateTime createTime;

    @NotNull
    @LastModifiedDate
    @Column(name = "UPDATE_TIME", nullable = false)
    private LocalDateTime updateTime;

    @Version
    @Column(name = "VERSION", nullable = false)
    private Long version;
}