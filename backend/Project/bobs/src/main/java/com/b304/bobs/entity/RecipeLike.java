package com.b304.bobs.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="recipe_like")
@Getter @Setter
public class RecipeLike {
    @Id
    @GeneratedValue
    @Column(name="recipe_like_id")
    private Long recipe_like_id;

    @Column(name="recipe_like_created")
    @CreationTimestamp
    private LocalDateTime recipe_like_created = LocalDateTime.now();

    @Column(name="recipe_like_is_deleted")
    private boolean recipe_like_is_deleted;

    @ManyToOne
    @JoinColumn(name="recipe_id")
    private Recipe recipe;

    @OneToOne
    @JoinColumn(name="user_id")
    private User user;

}
