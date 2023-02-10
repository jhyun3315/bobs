package com.b304.bobs.db.repository;

import com.b304.bobs.db.entity.Allergy;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AllergyRepository extends JpaRepository<Allergy, Long> {
}
