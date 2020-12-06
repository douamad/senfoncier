package com.pirtol.lab.web.rest;

import com.pirtol.lab.service.CommuneService;
import com.pirtol.lab.web.rest.errors.BadRequestAlertException;
import com.pirtol.lab.service.dto.CommuneDTO;

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
 * REST controller for managing {@link com.pirtol.lab.domain.Commune}.
 */
@RestController
@RequestMapping("/api")
public class CommuneResource {

    private final Logger log = LoggerFactory.getLogger(CommuneResource.class);

    private static final String ENTITY_NAME = "commune";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CommuneService communeService;

    public CommuneResource(CommuneService communeService) {
        this.communeService = communeService;
    }

    /**
     * {@code POST  /communes} : Create a new commune.
     *
     * @param communeDTO the communeDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new communeDTO, or with status {@code 400 (Bad Request)} if the commune has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/communes")
    public ResponseEntity<CommuneDTO> createCommune(@RequestBody CommuneDTO communeDTO) throws URISyntaxException {
        log.debug("REST request to save Commune : {}", communeDTO);
        if (communeDTO.getId() != null) {
            throw new BadRequestAlertException("A new commune cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CommuneDTO result = communeService.save(communeDTO);
        return ResponseEntity.created(new URI("/api/communes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /communes} : Updates an existing commune.
     *
     * @param communeDTO the communeDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated communeDTO,
     * or with status {@code 400 (Bad Request)} if the communeDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the communeDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/communes")
    public ResponseEntity<CommuneDTO> updateCommune(@RequestBody CommuneDTO communeDTO) throws URISyntaxException {
        log.debug("REST request to update Commune : {}", communeDTO);
        if (communeDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CommuneDTO result = communeService.save(communeDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, communeDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /communes} : get all the communes.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of communes in body.
     */
    @GetMapping("/communes")
    public ResponseEntity<List<CommuneDTO>> getAllCommunes(Pageable pageable) {
        log.debug("REST request to get a page of Communes");
        Page<CommuneDTO> page = communeService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /communes/:id} : get the "id" commune.
     *
     * @param id the id of the communeDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the communeDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/communes/{id}")
    public ResponseEntity<CommuneDTO> getCommune(@PathVariable Long id) {
        log.debug("REST request to get Commune : {}", id);
        Optional<CommuneDTO> communeDTO = communeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(communeDTO);
    }

    /**
     * {@code DELETE  /communes/:id} : delete the "id" commune.
     *
     * @param id the id of the communeDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/communes/{id}")
    public ResponseEntity<Void> deleteCommune(@PathVariable Long id) {
        log.debug("REST request to delete Commune : {}", id);
        communeService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
