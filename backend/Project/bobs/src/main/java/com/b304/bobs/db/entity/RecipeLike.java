package com.b304.bobs.db.entity;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="recipe_like")
@Getter @Setter
@NoArgsConstructor
public class RecipeLike {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="recipe_like_id",  nullable = false)
    private Long recipe_like_id;

    @Column(name="recipe_like_created",columnDefinition = "DATETIME", nullable = false)
    @CreationTimestamp
    private LocalDateTime recipe_like_created;

    @ColumnDefault("false")
    @Column(name="recipe_like_is_deleted",columnDefinition = "BOOLEAN", nullable = false)
    private boolean recipe_like_is_deleted;

    @Builder
    public RecipeLike(Long recipe_like_id, LocalDateTime recipe_like_created, boolean recipe_like_is_deleted, Recipe recipe, User user) {
        this.recipe_like_id = recipe_like_id;
        this.recipe_like_created = recipe_like_created;
        this.recipe_like_is_deleted = recipe_like_is_deleted;
        this.recipe = recipe;
        this.user = user;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="recipe_id")
    private Recipe recipe;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User user;

}
