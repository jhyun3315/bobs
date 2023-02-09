package com.b304.bobs.db.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@Table(name="ingredient")
@AllArgsConstructor
@NoArgsConstructor
public class Ingredient {
    @Id
    @Column(name="ingredient_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ingredient_id;

    @Column(name="ingredient_name",columnDefinition = "VARCHAR(20)", nullable = false)
    private String ingredient_name;

    @Builder
    public Ingredient(Long ingredient_id, String ingredient_name) {
        this.ingredient_id = ingredient_id;
        this.ingredient_name = ingredient_name;
    }

    @OneToMany(mappedBy = "ingredient")
    private List<Allergy> allergies = new ArrayList<Allergy>();

    public void addAllergy(Allergy allergy){
        allergies.add(allergy);
        allergy.setIngredient(this);
    }

}
