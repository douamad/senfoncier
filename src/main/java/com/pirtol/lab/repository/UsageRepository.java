package com.pirtol.lab.repository;

import com.pirtol.lab.domain.Usage;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Usage entity.
 */
@SuppressWarnings("unused")
@Repository
public interface UsageRepository extends JpaRepository<Usage, Long> {
}
