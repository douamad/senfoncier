package com.pirtol.lab.service.mapper;

import com.pirtol.lab.domain.*;
import com.pirtol.lab.service.dto.ActiviteDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Activite} and its DTO {@link ActiviteDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ActiviteMapper extends EntityMapper<ActiviteDTO, Activite> {
    @Mapping(target = "dossiers", ignore = true)
    @Mapping(target = "removeDossier", ignore = true)
    Activite toEntity(ActiviteDTO activiteDTO);

    default Activite fromId(Long id) {
        if (id == null) {
            return null;
        }
        Activite activite = new Activite();
        activite.setId(id);
        return activite;
    }
}
