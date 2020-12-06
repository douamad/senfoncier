package com.pirtol.lab.service.mapper;

import com.pirtol.lab.domain.*;
import com.pirtol.lab.service.dto.CategorieBatieDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link CategorieBatie} and its DTO {@link CategorieBatieDTO}.
 */
@Mapper(componentModel = "spring", uses = { DossierMapper.class })
public interface CategorieBatieMapper extends EntityMapper<CategorieBatieDTO, CategorieBatie> {
    @Mapping(source = "dossier.id", target = "dossierId")
    CategorieBatieDTO toDto(CategorieBatie categorieBatie);

    @Mapping(source = "dossierId", target = "dossier")
    CategorieBatie toEntity(CategorieBatieDTO categorieBatieDTO);

    default CategorieBatie fromId(Long id) {
        if (id == null) {
            return null;
        }
        CategorieBatie categorieBatie = new CategorieBatie();
        categorieBatie.setId(id);
        return categorieBatie;
    }
}
