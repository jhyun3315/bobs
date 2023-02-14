package com.b304.bobs.db.entity;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Entity
@Table(name="allergy")
@Getter @Setter
@NoArgsConstructor
public class Allergy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="allergy_id", nullable = false)
    private Long allergy_id;

    @ColumnDefault("false")
    @Column(name = "is_deleted",columnDefinition = "BOOLEAN", nullable = false)
    private boolean is_deleted;

    @Column(name = "allergy_name",columnDefinition = "VARCHAR(20)", nullable = false)
    private String allergy_name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="ingredient_id")
    private Ingredient ingredient;

    @Builder
    public Allergy(Long allergy_id, boolean is_deleted, User user, Ingredient ingredient) {
        this.allergy_id = allergy_id;
        this.is_deleted = is_deleted;
        this.user = user;
        this.ingredient = ingredient;
    }
}
