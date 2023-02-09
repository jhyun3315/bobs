package com.b304.bobs.db.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="allergy")
@Getter @Setter
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="ingredient_id")
    private Ingredient ingredient;

    @Builder
    public Allergy(String allergy_name, boolean is_deleted, User user, Ingredient ingredient) {
        this.allergy_name = allergy_name;
        this.is_deleted = is_deleted;
        this.user = user;
        this.ingredient = ingredient;
    }
}
