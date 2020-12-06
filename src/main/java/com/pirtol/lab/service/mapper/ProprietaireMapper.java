package com.pirtol.lab.service.mapper;

import com.pirtol.lab.domain.*;
import com.pirtol.lab.service.dto.ProprietaireDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Proprietaire} and its DTO {@link ProprietaireDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ProprietaireMapper extends EntityMapper<ProprietaireDTO, Proprietaire> {
    @Mapping(target = "dossiers", ignore = true)
    @Mapping(target = "removeDossier", ignore = true)
    Proprietaire toEntity(ProprietaireDTO proprietaireDTO);

    default Proprietaire fromId(Long id) {
        if (id == null) {
            return null;
        }
        Proprietaire proprietaire = new Proprietaire();
        proprietaire.setId(id);
        return proprietaire;
    }
}
