package com.pirtol.lab.service;

import com.pirtol.lab.service.dto.CategorieClotureDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.pirtol.lab.domain.CategorieCloture}.
 */
public interface CategorieClotureService {

    /**
     * Save a categorieCloture.
     *
     * @param categorieClotureDTO the entity to save.
     * @return the persisted entity.
     */
    CategorieClotureDTO save(CategorieClotureDTO categorieClotureDTO);

    /**
     * Get all the categorieClotures.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<CategorieClotureDTO> findAll(Pageable pageable);


    /**
     * Get the "id" categorieCloture.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<CategorieClotureDTO> findOne(Long id);

    /**
     * Delete the "id" categorieCloture.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}