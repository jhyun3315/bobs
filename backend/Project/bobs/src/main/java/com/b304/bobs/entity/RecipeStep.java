package com.b304.bobs.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="recipe_step")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RecipeStep {
    @Id
    @Column(name="recipe_step_id", nullable = false)
    private Long recipe_step_id;

    @Column(name="recipe_step_num",columnDefinition = "TINYINT(20)", nullable = false)
    private int recipe_step_num;

    @Column(name="recipe_step_content",columnDefinition = "VARCHAR(150)")
    private String recipe_step_content;

    @Column(name="recipe_step_img",columnDefinition = "VARCHAR(100)")
    private String recipe_step_img;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="recipe_id")
    private Recipe recipe;
}
