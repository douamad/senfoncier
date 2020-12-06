package com.pirtol.lab.web.rest;

import com.pirtol.lab.service.ParcelleService;
import com.pirtol.lab.web.rest.errors.BadRequestAlertException;
import com.pirtol.lab.service.dto.ParcelleDTO;

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
 * REST controller for managing {@link com.pirtol.lab.domain.Parcelle}.
 */
@RestController
@RequestMapping("/api")
public class ParcelleResource {

    private final Logger log = LoggerFactory.getLogger(ParcelleResource.class);

    private static final String ENTITY_NAME = "parcelle";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ParcelleService parcelleService;

    public ParcelleResource(ParcelleService parcelleService) {
        this.parcelleService = parcelleService;
    }

    /**
     * {@code POST  /parcelles} : Create a new parcelle.
     *
     * @param parcelleDTO the parcelleDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new parcelleDTO, or with status {@code 400 (Bad Request)} if the parcelle has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/parcelles")
    public ResponseEntity<ParcelleDTO> createParcelle(@RequestBody ParcelleDTO parcelleDTO) throws URISyntaxException {
        log.debug("REST request to save Parcelle : {}", parcelleDTO);
        if (parcelleDTO.getId() != null) {
            throw new BadRequestAlertException("A new parcelle cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ParcelleDTO result = parcelleService.save(parcelleDTO);
        return ResponseEntity.created(new URI("/api/parcelles/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /parcelles} : Updates an existing parcelle.
     *
     * @param parcelleDTO the parcelleDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated parcelleDTO,
     * or with status {@code 400 (Bad Request)} if the parcelleDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the parcelleDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/parcelles")
    public ResponseEntity<ParcelleDTO> updateParcelle(@RequestBody ParcelleDTO parcelleDTO) throws URISyntaxException {
        log.debug("REST request to update Parcelle : {}", parcelleDTO);
        if (parcelleDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ParcelleDTO result = parcelleService.save(parcelleDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, parcelleDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /parcelles} : get all the parcelles.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of parcelles in body.
     */
    @GetMapping("/parcelles")
    public ResponseEntity<List<ParcelleDTO>> getAllParcelles(Pageable pageable) {
        log.debug("REST request to get a page of Parcelles");
        Page<ParcelleDTO> page = parcelleService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /parcelles/:id} : get the "id" parcelle.
     *
     * @param id the id of the parcelleDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the parcelleDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/parcelles/{id}")
    public ResponseEntity<ParcelleDTO> getParcelle(@PathVariable Long id) {
        log.debug("REST request to get Parcelle : {}", id);
        Optional<ParcelleDTO> parcelleDTO = parcelleService.findOne(id);
        return ResponseUtil.wrapOrNotFound(parcelleDTO);
    }

    /**
     * {@code DELETE  /parcelles/:id} : delete the "id" parcelle.
     *
     * @param id the id of the parcelleDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/parcelles/{id}")
    public ResponseEntity<Void> deleteParcelle(@PathVariable Long id) {
        log.debug("REST request to delete Parcelle : {}", id);
        parcelleService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
