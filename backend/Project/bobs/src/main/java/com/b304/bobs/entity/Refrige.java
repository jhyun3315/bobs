package com.b304.bobs.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="refrige")
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class Refrige {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="refrige_id", nullable = false)
    private Long refrige_id;

    @Column(name="refrige_ingredient_prior",columnDefinition = "BOOLEAN", nullable = false)
    private boolean refrige_ingredient_prior;

    @Column(name="refrige_ingredient_delete",columnDefinition = "BOOLEAN", nullable = false)
    private boolean refrige_ingredient_delete;

    @Builder
    public Refrige(Long refrige_id, boolean refrige_ingredient_prior, boolean refrige_ingredient_delete, Ingredient ingredient) {
        this.refrige_id = refrige_id;
        this.refrige_ingredient_prior = refrige_ingredient_prior;
        this.refrige_ingredient_delete = refrige_ingredient_delete;
        this.ingredient = ingredient;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="ingredient_id")
    private Ingredient ingredient;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User user;
}
