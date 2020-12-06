package com.pirtol.lab.service;

import com.pirtol.lab.service.dto.LotissementDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.pirtol.lab.domain.Lotissement}.
 */
public interface LotissementService {

    /**
     * Save a lotissement.
     *
     * @param lotissementDTO the entity to save.
     * @return the persisted entity.
     */
    LotissementDTO save(LotissementDTO lotissementDTO);

    /**
     * Get all the lotissements.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<LotissementDTO> findAll(Pageable pageable);


    /**
     * Get the "id" lotissement.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<LotissementDTO> findOne(Long id);

    /**
     * Delete the "id" lotissement.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
