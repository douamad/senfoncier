package com.pirtol.lab.service;

import com.pirtol.lab.service.dto.ParcelleDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.pirtol.lab.domain.Parcelle}.
 */
public interface ParcelleService {
    /**
     * Save a parcelle.
     *
     * @param parcelleDTO the entity to save.
     * @return the persisted entity.
     */
    ParcelleDTO save(ParcelleDTO parcelleDTO);

    /**
     * Get all the parcelles.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<ParcelleDTO> findAll(Pageable pageable);

    /**
     * Get the "id" parcelle.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ParcelleDTO> findOne(Long id);

    /**
     * Delete the "id" parcelle.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
