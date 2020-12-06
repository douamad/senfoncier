package com.pirtol.lab.web.rest;

import com.pirtol.lab.service.ProprietaireService;
import com.pirtol.lab.service.dto.ProprietaireDTO;
import com.pirtol.lab.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

/**
 * REST controller for managing {@link com.pirtol.lab.domain.Proprietaire}.
 */
@RestController
@RequestMapping("/api")
public class ProprietaireResource {
    private final Logger log = LoggerFactory.getLogger(ProprietaireResource.class);

    private static final String ENTITY_NAME = "proprietaire";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ProprietaireService proprietaireService;

    public ProprietaireResource(ProprietaireService proprietaireService) {
        this.proprietaireService = proprietaireService;
    }

    /**
     * {@code POST  /proprietaires} : Create a new proprietaire.
     *
     * @param proprietaireDTO the proprietaireDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new proprietaireDTO, or with status {@code 400 (Bad Request)} if the proprietaire has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/proprietaires")
    public ResponseEntity<ProprietaireDTO> createProprietaire(@RequestBody ProprietaireDTO proprietaireDTO) throws URISyntaxException {
        log.debug("REST request to save Proprietaire : {}", proprietaireDTO);
        if (proprietaireDTO.getId() != null) {
            throw new BadRequestAlertException("A new proprietaire cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ProprietaireDTO result = proprietaireService.save(proprietaireDTO);
        return ResponseEntity
            .created(new URI("/api/proprietaires/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /proprietaires} : Updates an existing proprietaire.
     *
     * @param proprietaireDTO the proprietaireDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated proprietaireDTO,
     * or with status {@code 400 (Bad Request)} if the proprietaireDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the proprietaireDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/proprietaires")
    public ResponseEntity<ProprietaireDTO> updateProprietaire(@RequestBody ProprietaireDTO proprietaireDTO) throws URISyntaxException {
        log.debug("REST request to update Proprietaire : {}", proprietaireDTO);
        if (proprietaireDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ProprietaireDTO result = proprietaireService.save(proprietaireDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, proprietaireDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /proprietaires} : get all the proprietaires.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of proprietaires in body.
     */
    @GetMapping("/proprietaires")
    public ResponseEntity<List<ProprietaireDTO>> getAllProprietaires(Pageable pageable) {
        log.debug("REST request to get a page of Proprietaires");
        Page<ProprietaireDTO> page = proprietaireService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /proprietaires/:id} : get the "id" proprietaire.
     *
     * @param id the id of the proprietaireDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the proprietaireDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/proprietaires/{id}")
    public ResponseEntity<ProprietaireDTO> getProprietaire(@PathVariable Long id) {
        log.debug("REST request to get Proprietaire : {}", id);
        Optional<ProprietaireDTO> proprietaireDTO = proprietaireService.findOne(id);
        return ResponseUtil.wrapOrNotFound(proprietaireDTO);
    }

    /**
     * {@code DELETE  /proprietaires/:id} : delete the "id" proprietaire.
     *
     * @param id the id of the proprietaireDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/proprietaires/{id}")
    public ResponseEntity<Void> deleteProprietaire(@PathVariable Long id) {
        log.debug("REST request to delete Proprietaire : {}", id);
        proprietaireService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
