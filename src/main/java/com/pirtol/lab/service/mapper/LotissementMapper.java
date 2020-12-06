package com.pirtol.lab.service.mapper;


import com.pirtol.lab.domain.*;
import com.pirtol.lab.service.dto.LotissementDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Lotissement} and its DTO {@link LotissementDTO}.
 */
@Mapper(componentModel = "spring", uses = {QuartierMapper.class})
public interface LotissementMapper extends EntityMapper<LotissementDTO, Lotissement> {

    @Mapping(source = "quartier.id", target = "quartierId")
    LotissementDTO toDto(Lotissement lotissement);

    @Mapping(target = "lotissements", ignore = true)
    @Mapping(target = "removeLotissement", ignore = true)
    @Mapping(source = "quartierId", target = "quartier")
    Lotissement toEntity(LotissementDTO lotissementDTO);

    default Lotissement fromId(Long id) {
        if (id == null) {
            return null;
        }
        Lotissement lotissement = new Lotissement();
        lotissement.setId(id);
        return lotissement;
    }
}
