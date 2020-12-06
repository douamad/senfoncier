package com.pirtol.lab.service.mapper;


import com.pirtol.lab.domain.*;
import com.pirtol.lab.service.dto.QuartierDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Quartier} and its DTO {@link QuartierDTO}.
 */
@Mapper(componentModel = "spring", uses = {CommuneMapper.class})
public interface QuartierMapper extends EntityMapper<QuartierDTO, Quartier> {

    @Mapping(source = "communune.id", target = "commununeId")
    QuartierDTO toDto(Quartier quartier);

    @Mapping(target = "lotissements", ignore = true)
    @Mapping(target = "removeLotissement", ignore = true)
    @Mapping(source = "commununeId", target = "communune")
    Quartier toEntity(QuartierDTO quartierDTO);

    default Quartier fromId(Long id) {
        if (id == null) {
            return null;
        }
        Quartier quartier = new Quartier();
        quartier.setId(id);
        return quartier;
    }
}
