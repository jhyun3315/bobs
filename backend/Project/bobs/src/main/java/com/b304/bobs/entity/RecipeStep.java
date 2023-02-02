package com.b304.bobs.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="recipe_step")
@Getter @Setter
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

    @ManyToOne
    @JoinColumn(name="recipe_id")
    private Recipe recipe;
}
