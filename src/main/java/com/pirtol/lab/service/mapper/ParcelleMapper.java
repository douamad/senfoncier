package com.pirtol.lab.service.mapper;

import com.pirtol.lab.domain.*;
import com.pirtol.lab.service.dto.ParcelleDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Parcelle} and its DTO {@link ParcelleDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ParcelleMapper extends EntityMapper<ParcelleDTO, Parcelle> {
    @Mapping(target = "dossiers", ignore = true)
    @Mapping(target = "removeDossier", ignore = true)
    Parcelle toEntity(ParcelleDTO parcelleDTO);

    default Parcelle fromId(Long id) {
        if (id == null) {
            return null;
        }
        Parcelle parcelle = new Parcelle();
        parcelle.setId(id);
        return parcelle;
    }
}
