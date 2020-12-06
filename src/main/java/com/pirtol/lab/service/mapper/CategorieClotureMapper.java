package com.pirtol.lab.service.mapper;


import com.pirtol.lab.domain.*;
import com.pirtol.lab.service.dto.CategorieClotureDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link CategorieCloture} and its DTO {@link CategorieClotureDTO}.
 */
@Mapper(componentModel = "spring", uses = {DossierMapper.class})
public interface CategorieClotureMapper extends EntityMapper<CategorieClotureDTO, CategorieCloture> {

    @Mapping(source = "dossier.id", target = "dossierId")
    @Mapping(source = "dossier.id", target = "dossierId")
    CategorieClotureDTO toDto(CategorieCloture categorieCloture);

    @Mapping(source = "dossierId", target = "dossier")
    @Mapping(source = "dossierId", target = "dossier")
    CategorieCloture toEntity(CategorieClotureDTO categorieClotureDTO);

    default CategorieCloture fromId(Long id) {
        if (id == null) {
            return null;
        }
        CategorieCloture categorieCloture = new CategorieCloture();
        categorieCloture.setId(id);
        return categorieCloture;
    }
}
