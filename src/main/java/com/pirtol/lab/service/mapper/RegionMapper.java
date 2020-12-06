package com.pirtol.lab.service.mapper;


import com.pirtol.lab.domain.*;
import com.pirtol.lab.service.dto.RegionDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Region} and its DTO {@link RegionDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface RegionMapper extends EntityMapper<RegionDTO, Region> {


    @Mapping(target = "departements", ignore = true)
    @Mapping(target = "removeDepartement", ignore = true)
    Region toEntity(RegionDTO regionDTO);

    default Region fromId(Long id) {
        if (id == null) {
            return null;
        }
        Region region = new Region();
        region.setId(id);
        return region;
    }
}
