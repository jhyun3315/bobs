package com.b304.bobs.db.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="recipe_step")
@Getter
@Builder
@NoArgsConstructor
public class RecipeStep {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="recipe_step_id", nullable = false)
    private Long recipe_step_id;

    @Column(name="recipe_step_num",columnDefinition = "TINYINT(20)", nullable = false)
    private int recipe_step_num;

    @Column(name="recipe_step_content",columnDefinition = "VARCHAR(150)")
    private String recipe_step_content;

    @Column(name="recipe_step_img",columnDefinition = "VARCHAR(100)")
    private String recipe_step_img;

    @Builder
    public RecipeStep(Long recipe_step_id, int recipe_step_num, String recipe_step_content, String recipe_step_img, Recipe recipe) {
        this.recipe_step_id = recipe_step_id;
        this.recipe_step_num = recipe_step_num;
        this.recipe_step_content = recipe_step_content;
        this.recipe_step_img = recipe_step_img;
        this.recipe = recipe;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="recipe_id")
    private Recipe recipe;
}
