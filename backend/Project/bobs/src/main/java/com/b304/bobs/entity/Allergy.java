package com.b304.bobs.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="allergy")
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class Allergy {
    @Id
    @Column(name="allergy_name",columnDefinition = "VARCHAR(10)", nullable = false)
    private String allergy_name;

    @Column(name = "is_deleted",columnDefinition = "BOOLEAN", nullable = false)
    private boolean is_deleted;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User user;

    @OneToMany(mappedBy = "allergy")
    private List<Ingredient> ingredients = new ArrayList<Ingredient>();

    @Builder
    public Allergy(String allergy_name, boolean is_deleted, User user) {
        this.allergy_name = allergy_name;
        this.is_deleted = is_deleted;
        this.user = user;
    }

    public void addIngredient(Ingredient ingredient){
        ingredients.add(ingredient);
        ingredient.setAllergy(this);
    }
}
