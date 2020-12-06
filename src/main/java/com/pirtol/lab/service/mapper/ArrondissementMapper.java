package com.pirtol.lab.service.mapper;

import com.pirtol.lab.domain.*;
import com.pirtol.lab.service.dto.ArrondissementDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Arrondissement} and its DTO {@link ArrondissementDTO}.
 */
@Mapper(componentModel = "spring", uses = { DepartementMapper.class })
public interface ArrondissementMapper extends EntityMapper<ArrondissementDTO, Arrondissement> {
    @Mapping(source = "departement.id", target = "departementId")
    ArrondissementDTO toDto(Arrondissement arrondissement);

    @Mapping(target = "communes", ignore = true)
    @Mapping(target = "removeCommune", ignore = true)
    @Mapping(source = "departementId", target = "departement")
    Arrondissement toEntity(ArrondissementDTO arrondissementDTO);

    default Arrondissement fromId(Long id) {
        if (id == null) {
            return null;
        }
        Arrondissement arrondissement = new Arrondissement();
        arrondissement.setId(id);
        return arrondissement;
    }
}
