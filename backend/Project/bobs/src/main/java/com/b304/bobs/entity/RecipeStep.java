package com.b304.bobs.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="recipe_step")
@Getter @Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RecipeStep {
    @Id
    @Column(name="recipe_step_id")
    private Long recipe_step_id;

    @Column(name="recipe_step_num")
    private int recipe_step_num;

    @Column(name="recipe_step_content")
    private String recipe_step_content;

    @Column(name="recipe_step_img")
    private String recipe_step_img;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="recipe_id")
    private Recipe recipe;
}
