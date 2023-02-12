package com.b304.bobs.db.repository;

import com.b304.bobs.db.entity.Allergy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface AllergyRepository extends JpaRepository<Allergy, Long> {

    @Query(value ="SELECT * FROM allergy WHERE allergy_id =:allergyId", nativeQuery = true)
    Optional<Allergy> findByAllergyId(@Param("allergyId") Long allergyId);

}
