package com.pirtol.lab.service.mapper;


import com.pirtol.lab.domain.*;
import com.pirtol.lab.service.dto.UsageDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Usage} and its DTO {@link UsageDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface UsageMapper extends EntityMapper<UsageDTO, Usage> {


    @Mapping(target = "dossiers", ignore = true)
    @Mapping(target = "removeDossier", ignore = true)
    Usage toEntity(UsageDTO usageDTO);

    default Usage fromId(Long id) {
        if (id == null) {
            return null;
        }
        Usage usage = new Usage();
        usage.setId(id);
        return usage;
    }
}
