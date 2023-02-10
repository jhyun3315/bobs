package com.b304.bobs.db.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="refrige")
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class Refrige {
    @Id
    @Column(name="refrige_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long refrige_id;

    @Column(name="refrige_ingredient_prior",columnDefinition = "BOOLEAN", nullable = false)
    private Boolean refrige_ingredient_prior;

    @Column(name="refrige_ingredient_delete",columnDefinition = "BOOLEAN", nullable = false)
    private Boolean refrige_ingredient_delete;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="ingredient_id")
    private Ingredient ingredient;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User user;

    @Builder
    public Refrige(Long refrige_id, Boolean refrige_ingredient_prior, Boolean refrige_ingredient_delete, User user) {
        this.refrige_id = refrige_id;
        this.refrige_ingredient_prior = refrige_ingredient_prior;
        this.refrige_ingredient_delete = refrige_ingredient_delete;
        this.user = user;
    }
}
