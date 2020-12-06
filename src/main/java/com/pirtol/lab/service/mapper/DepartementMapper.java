package com.pirtol.lab.service.mapper;

import com.pirtol.lab.domain.*;
import com.pirtol.lab.service.dto.DepartementDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Departement} and its DTO {@link DepartementDTO}.
 */
@Mapper(componentModel = "spring", uses = { RegionMapper.class })
public interface DepartementMapper extends EntityMapper<DepartementDTO, Departement> {
    @Mapping(source = "region.id", target = "regionId")
    DepartementDTO toDto(Departement departement);

    @Mapping(target = "arrondissements", ignore = true)
    @Mapping(target = "removeArrondissement", ignore = true)
    @Mapping(source = "regionId", target = "region")
    Departement toEntity(DepartementDTO departementDTO);

    default Departement fromId(Long id) {
        if (id == null) {
            return null;
        }
        Departement departement = new Departement();
        departement.setId(id);
        return departement;
    }
}
