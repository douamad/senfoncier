package com.pirtol.lab.service.impl;

import com.pirtol.lab.domain.Parcelle;
import com.pirtol.lab.repository.ParcelleRepository;
import com.pirtol.lab.service.ParcelleService;
import com.pirtol.lab.service.dto.ParcelleDTO;
import com.pirtol.lab.service.mapper.ParcelleMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Parcelle}.
 */
@Service
@Transactional
public class ParcelleServiceImpl implements ParcelleService {
    private final Logger log = LoggerFactory.getLogger(ParcelleServiceImpl.class);

    private final ParcelleRepository parcelleRepository;

    private final ParcelleMapper parcelleMapper;

    public ParcelleServiceImpl(ParcelleRepository parcelleRepository, ParcelleMapper parcelleMapper) {
        this.parcelleRepository = parcelleRepository;
        this.parcelleMapper = parcelleMapper;
    }

    @Override
    public ParcelleDTO save(ParcelleDTO parcelleDTO) {
        log.debug("Request to save Parcelle : {}", parcelleDTO);
        Parcelle parcelle = parcelleMapper.toEntity(parcelleDTO);
        parcelle = parcelleRepository.save(parcelle);
        return parcelleMapper.toDto(parcelle);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ParcelleDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Parcelles");
        return parcelleRepository.findAll(pageable).map(parcelleMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ParcelleDTO> findOne(Long id) {
        log.debug("Request to get Parcelle : {}", id);
        return parcelleRepository.findById(id).map(parcelleMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Parcelle : {}", id);
        parcelleRepository.deleteById(id);
    }
}
