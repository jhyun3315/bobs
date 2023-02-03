package com.b304.bobs.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="recipe_like")
@Getter @Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RecipeLike {
    @Id
    @GeneratedValue
    @Column(name="recipe_like_id",columnDefinition = "INT", nullable = false)
    private int recipe_like_id;

    @Column(name="recipe_like_created",columnDefinition = "DATETIME", nullable = false)
    @CreationTimestamp
    private LocalDateTime recipe_like_created = LocalDateTime.now();

    @Column(name="recipe_like_is_deleted",columnDefinition = "BOOLEAN", nullable = false)
    private boolean recipe_like_is_deleted;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="recipe_id")
    private Recipe recipe;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User user;

}
