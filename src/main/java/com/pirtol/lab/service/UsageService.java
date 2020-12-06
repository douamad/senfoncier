package com.pirtol.lab.service;

import com.pirtol.lab.service.dto.UsageDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.pirtol.lab.domain.Usage}.
 */
public interface UsageService {
    /**
     * Save a usage.
     *
     * @param usageDTO the entity to save.
     * @return the persisted entity.
     */
    UsageDTO save(UsageDTO usageDTO);

    /**
     * Get all the usages.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<UsageDTO> findAll(Pageable pageable);

    /**
     * Get the "id" usage.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<UsageDTO> findOne(Long id);

    /**
     * Delete the "id" usage.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
