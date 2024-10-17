package io.ggogit.ggogit.domain.memoir.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.ggogit.ggogit.domain.tree.entity.Tree;
import jakarta.persistence.*;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.*;
import org.hibernate.annotations.Generated;
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
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Memoir {
    @Id
    @Column(name = "ID", nullable = false)
    //TODO: 사용중인 DB에 맞춰 식별자 생성 전략 수정해야 함.
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY, optional = false, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "TREE_ID")
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

    @ColumnDefault("1")
    @Column(name = "VISIBILITY", nullable = false)
    private Boolean visibility = true;

    @ColumnDefault("0")
    @Column(name = "IS_DELETED")
    @Generated
    private Boolean isDeleted = false;

    @CreatedDate
    @ColumnDefault("current_timestamp()")
    @Column(name = "CREATE_TIME", updatable = false)
    private LocalDateTime createTime;

    @LastModifiedDate
    @ColumnDefault("current_timestamp()")
    @Column(name = "UPDATE_TIME")
    private LocalDateTime updateTime;

    @Version
    @Column(name = "VERSION")
    private Long version;

    //------------연관관계 편의 메서드-----------------
    //--------연관관계의 주인에만 만들어야 한다----------
    public void changeTree(Tree tree) {
        this.tree = tree;

        if(tree != null)
            tree.setMemoir(this);
    }
}