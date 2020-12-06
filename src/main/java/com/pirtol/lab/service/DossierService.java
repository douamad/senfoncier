package com.pirtol.lab.service;

import com.pirtol.lab.service.dto.DossierDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.pirtol.lab.domain.Dossier}.
 */
public interface DossierService {

    /**
     * Save a dossier.
     *
     * @param dossierDTO the entity to save.
     * @return the persisted entity.
     */
    DossierDTO save(DossierDTO dossierDTO);

    /**
     * Get all the dossiers.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<DossierDTO> findAll(Pageable pageable);

    /**
     * Get all the dossiers with eager load of many-to-many relationships.
     *
     * @return the list of entities.
     */
    Page<DossierDTO> findAllWithEagerRelationships(Pageable pageable);


    /**
     * Get the "id" dossier.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<DossierDTO> findOne(Long id);

    /**
     * Delete the "id" dossier.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
