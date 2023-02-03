package com.b304.bobs.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="allergy")
@Getter @Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Allergy {
    @Id
    @Column(name="allergy_name")
    private Long allergy_name;

    @Column(name = "is_deleted")
    private boolean is_deleted;

    @Column(name="ingredient_id")
    private int ingredient_id;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    @OneToMany(mappedBy = "allergy")
    private List<Ingredient> ingredients = new ArrayList<Ingredient>();

    public void addIngredient(Ingredient ingredient){
        ingredients.add(ingredient);
        ingredient.setAllergy(this);
    }
}
