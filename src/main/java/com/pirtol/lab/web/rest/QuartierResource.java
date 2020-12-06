package com.pirtol.lab.web.rest;

import com.pirtol.lab.service.QuartierService;
import com.pirtol.lab.web.rest.errors.BadRequestAlertException;
import com.pirtol.lab.service.dto.QuartierDTO;

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
 * REST controller for managing {@link com.pirtol.lab.domain.Quartier}.
 */
@RestController
@RequestMapping("/api")
public class QuartierResource {

    private final Logger log = LoggerFactory.getLogger(QuartierResource.class);

    private static final String ENTITY_NAME = "quartier";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final QuartierService quartierService;

    public QuartierResource(QuartierService quartierService) {
        this.quartierService = quartierService;
    }

    /**
     * {@code POST  /quartiers} : Create a new quartier.
     *
     * @param quartierDTO the quartierDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new quartierDTO, or with status {@code 400 (Bad Request)} if the quartier has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/quartiers")
    public ResponseEntity<QuartierDTO> createQuartier(@RequestBody QuartierDTO quartierDTO) throws URISyntaxException {
        log.debug("REST request to save Quartier : {}", quartierDTO);
        if (quartierDTO.getId() != null) {
            throw new BadRequestAlertException("A new quartier cannot already have an ID", ENTITY_NAME, "idexists");
        }
        QuartierDTO result = quartierService.save(quartierDTO);
        return ResponseEntity.created(new URI("/api/quartiers/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /quartiers} : Updates an existing quartier.
     *
     * @param quartierDTO the quartierDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated quartierDTO,
     * or with status {@code 400 (Bad Request)} if the quartierDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the quartierDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/quartiers")
    public ResponseEntity<QuartierDTO> updateQuartier(@RequestBody QuartierDTO quartierDTO) throws URISyntaxException {
        log.debug("REST request to update Quartier : {}", quartierDTO);
        if (quartierDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        QuartierDTO result = quartierService.save(quartierDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, quartierDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /quartiers} : get all the quartiers.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of quartiers in body.
     */
    @GetMapping("/quartiers")
    public ResponseEntity<List<QuartierDTO>> getAllQuartiers(Pageable pageable) {
        log.debug("REST request to get a page of Quartiers");
        Page<QuartierDTO> page = quartierService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /quartiers/:id} : get the "id" quartier.
     *
     * @param id the id of the quartierDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the quartierDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/quartiers/{id}")
    public ResponseEntity<QuartierDTO> getQuartier(@PathVariable Long id) {
        log.debug("REST request to get Quartier : {}", id);
        Optional<QuartierDTO> quartierDTO = quartierService.findOne(id);
        return ResponseUtil.wrapOrNotFound(quartierDTO);
    }

    /**
     * {@code DELETE  /quartiers/:id} : delete the "id" quartier.
     *
     * @param id the id of the quartierDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/quartiers/{id}")
    public ResponseEntity<Void> deleteQuartier(@PathVariable Long id) {
        log.debug("REST request to delete Quartier : {}", id);
        quartierService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
