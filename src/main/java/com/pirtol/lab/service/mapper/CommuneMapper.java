package com.pirtol.lab.service.mapper;


import com.pirtol.lab.domain.*;
import com.pirtol.lab.service.dto.CommuneDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Commune} and its DTO {@link CommuneDTO}.
 */
@Mapper(componentModel = "spring", uses = {ArrondissementMapper.class})
public interface CommuneMapper extends EntityMapper<CommuneDTO, Commune> {

    @Mapping(source = "arrondissement.id", target = "arrondissementId")
    CommuneDTO toDto(Commune commune);

    @Mapping(target = "quartiers", ignore = true)
    @Mapping(target = "removeQuartier", ignore = true)
    @Mapping(source = "arrondissementId", target = "arrondissement")
    Commune toEntity(CommuneDTO communeDTO);

    default Commune fromId(Long id) {
        if (id == null) {
            return null;
        }
        Commune commune = new Commune();
        commune.setId(id);
        return commune;
    }
}
