package com.pirtol.lab.repository;

import com.pirtol.lab.domain.Dossier;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data  repository for the Dossier entity.
 */
@Repository
public interface DossierRepository extends JpaRepository<Dossier, Long> {

    @Query(value = "select distinct dossier from Dossier dossier left join fetch dossier.parcelles",
        countQuery = "select count(distinct dossier) from Dossier dossier")
    Page<Dossier> findAllWithEagerRelationships(Pageable pageable);

    @Query("select distinct dossier from Dossier dossier left join fetch dossier.parcelles")
    List<Dossier> findAllWithEagerRelationships();

    @Query("select dossier from Dossier dossier left join fetch dossier.parcelles where dossier.id =:id")
    Optional<Dossier> findOneWithEagerRelationships(@Param("id") Long id);
}
