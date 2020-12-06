package com.pirtol.lab.service.mapper;

import com.pirtol.lab.domain.*;
import com.pirtol.lab.service.dto.DossierDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Dossier} and its DTO {@link DossierDTO}.
 */
@Mapper(
    componentModel = "spring",
    uses = {
        ParcelleMapper.class,
        LotissementMapper.class,
        NatureMapper.class,
        ActiviteMapper.class,
        UsageMapper.class,
        ProprietaireMapper.class,
    }
)
public interface DossierMapper extends EntityMapper<DossierDTO, Dossier> {
    @Mapping(source = "dossier.id", target = "dossierId")
    @Mapping(source = "nature.id", target = "natureId")
    @Mapping(source = "activite.id", target = "activiteId")
    @Mapping(source = "usage.id", target = "usageId")
    @Mapping(source = "proprietaire.id", target = "proprietaireId")
    DossierDTO toDto(Dossier dossier);

    @Mapping(target = "categorieBaties", ignore = true)
    @Mapping(target = "removeCategorieBatie", ignore = true)
    @Mapping(target = "categoriteClotures", ignore = true)
    @Mapping(target = "removeCategoriteCloture", ignore = true)
    @Mapping(target = "removeParcelle", ignore = true)
    @Mapping(source = "dossierId", target = "dossier")
    @Mapping(source = "natureId", target = "nature")
    @Mapping(source = "activiteId", target = "activite")
    @Mapping(source = "usageId", target = "usage")
    @Mapping(source = "proprietaireId", target = "proprietaire")
    Dossier toEntity(DossierDTO dossierDTO);

    default Dossier fromId(Long id) {
        if (id == null) {
            return null;
        }
        Dossier dossier = new Dossier();
        dossier.setId(id);
        return dossier;
    }
}
