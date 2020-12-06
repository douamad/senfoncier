package com.pirtol.lab.repository;

import com.pirtol.lab.domain.Lotissement;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Lotissement entity.
 */
@SuppressWarnings("unused")
@Repository
public interface LotissementRepository extends JpaRepository<Lotissement, Long> {}
