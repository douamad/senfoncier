package com.pirtol.lab.repository;

import com.pirtol.lab.domain.CategorieCloture;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the CategorieCloture entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CategorieClotureRepository extends JpaRepository<CategorieCloture, Long> {
}
