package com.pirtol.lab.web.rest;

import com.pirtol.lab.service.DossierService;
import com.pirtol.lab.web.rest.errors.BadRequestAlertException;
import com.pirtol.lab.service.dto.DossierDTO;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.pirtol.lab.domain.Dossier}.
 */
@RestController
@RequestMapping("/api")
public class DossierResource {

    private final Logger log = LoggerFactory.getLogger(DossierResource.class);

    private static final String ENTITY_NAME = "dossier";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DossierService dossierService;

    public DossierResource(DossierService dossierService) {
        this.dossierService = dossierService;
    }

    /**
     * {@code POST  /dossiers} : Create a new dossier.
     *
     * @param dossierDTO the dossierDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new dossierDTO, or with status {@code 400 (Bad Request)} if the dossier has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/dossiers")
    public ResponseEntity<DossierDTO> createDossier(@RequestBody DossierDTO dossierDTO) throws URISyntaxException {
        log.debug("REST request to save Dossier : {}", dossierDTO);
        if (dossierDTO.getId() != null) {
            throw new BadRequestAlertException("A new dossier cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DossierDTO result = dossierService.save(dossierDTO);
        return ResponseEntity.created(new URI("/api/dossiers/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /dossiers} : Updates an existing dossier.
     *
     * @param dossierDTO the dossierDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated dossierDTO,
     * or with status {@code 400 (Bad Request)} if the dossierDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the dossierDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/dossiers")
    public ResponseEntity<DossierDTO> updateDossier(@RequestBody DossierDTO dossierDTO) throws URISyntaxException {
        log.debug("REST request to update Dossier : {}", dossierDTO);
        if (dossierDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        DossierDTO result = dossierService.save(dossierDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, dossierDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /dossiers} : get all the dossiers.
     *
     * @param pageable the pagination information.
     * @param eagerload flag to eager load entities from relationships (This is applicable for many-to-many).
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of dossiers in body.
     */
    @GetMapping("/dossiers")
    public ResponseEntity<List<DossierDTO>> getAllDossiers(Pageable pageable, @RequestParam(required = false, defaultValue = "false") boolean eagerload) {
        log.debug("REST request to get a page of Dossiers");
        Page<DossierDTO> page;
        if (eagerload) {
            page = dossierService.findAllWithEagerRelationships(pageable);
        } else {
            page = dossierService.findAll(pageable);
        }
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /dossiers/:id} : get the "id" dossier.
     *
     * @param id the id of the dossierDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the dossierDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/dossiers/{id}")
    public ResponseEntity<DossierDTO> getDossier(@PathVariable Long id) {
        log.debug("REST request to get Dossier : {}", id);
        Optional<DossierDTO> dossierDTO = dossierService.findOne(id);
        return ResponseUtil.wrapOrNotFound(dossierDTO);
    }

    /**
     * {@code DELETE  /dossiers/:id} : delete the "id" dossier.
     *
     * @param id the id of the dossierDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/dossiers/{id}")
    public ResponseEntity<Void> deleteDossier(@PathVariable Long id) {
        log.debug("REST request to delete Dossier : {}", id);
        dossierService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
