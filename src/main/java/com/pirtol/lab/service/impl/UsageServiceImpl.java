package com.pirtol.lab.service.impl;

import com.pirtol.lab.domain.Usage;
import com.pirtol.lab.repository.UsageRepository;
import com.pirtol.lab.service.UsageService;
import com.pirtol.lab.service.dto.UsageDTO;
import com.pirtol.lab.service.mapper.UsageMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Usage}.
 */
@Service
@Transactional
public class UsageServiceImpl implements UsageService {
    private final Logger log = LoggerFactory.getLogger(UsageServiceImpl.class);

    private final UsageRepository usageRepository;

    private final UsageMapper usageMapper;

    public UsageServiceImpl(UsageRepository usageRepository, UsageMapper usageMapper) {
        this.usageRepository = usageRepository;
        this.usageMapper = usageMapper;
    }

    @Override
    public UsageDTO save(UsageDTO usageDTO) {
        log.debug("Request to save Usage : {}", usageDTO);
        Usage usage = usageMapper.toEntity(usageDTO);
        usage = usageRepository.save(usage);
        return usageMapper.toDto(usage);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<UsageDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Usages");
        return usageRepository.findAll(pageable).map(usageMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<UsageDTO> findOne(Long id) {
        log.debug("Request to get Usage : {}", id);
        return usageRepository.findById(id).map(usageMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Usage : {}", id);
        usageRepository.deleteById(id);
    }
}
