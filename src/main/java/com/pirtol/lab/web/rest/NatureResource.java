package com.pirtol.lab.web.rest;

import com.pirtol.lab.service.NatureService;
import com.pirtol.lab.web.rest.errors.BadRequestAlertException;
import com.pirtol.lab.service.dto.NatureDTO;

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
 * REST controller for managing {@link com.pirtol.lab.domain.Nature}.
 */
@RestController
@RequestMapping("/api")
public class NatureResource {

    private final Logger log = LoggerFactory.getLogger(NatureResource.class);

    private static final String ENTITY_NAME = "nature";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final NatureService natureService;

    public NatureResource(NatureService natureService) {
        this.natureService = natureService;
    }

    /**
     * {@code POST  /natures} : Create a new nature.
     *
     * @param natureDTO the natureDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new natureDTO, or with status {@code 400 (Bad Request)} if the nature has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/natures")
    public ResponseEntity<NatureDTO> createNature(@RequestBody NatureDTO natureDTO) throws URISyntaxException {
        log.debug("REST request to save Nature : {}", natureDTO);
        if (natureDTO.getId() != null) {
            throw new BadRequestAlertException("A new nature cannot already have an ID", ENTITY_NAME, "idexists");
        }
        NatureDTO result = natureService.save(natureDTO);
        return ResponseEntity.created(new URI("/api/natures/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /natures} : Updates an existing nature.
     *
     * @param natureDTO the natureDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated natureDTO,
     * or with status {@code 400 (Bad Request)} if the natureDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the natureDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/natures")
    public ResponseEntity<NatureDTO> updateNature(@RequestBody NatureDTO natureDTO) throws URISyntaxException {
        log.debug("REST request to update Nature : {}", natureDTO);
        if (natureDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        NatureDTO result = natureService.save(natureDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, natureDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /natures} : get all the natures.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of natures in body.
     */
    @GetMapping("/natures")
    public ResponseEntity<List<NatureDTO>> getAllNatures(Pageable pageable) {
        log.debug("REST request to get a page of Natures");
        Page<NatureDTO> page = natureService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /natures/:id} : get the "id" nature.
     *
     * @param id the id of the natureDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the natureDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/natures/{id}")
    public ResponseEntity<NatureDTO> getNature(@PathVariable Long id) {
        log.debug("REST request to get Nature : {}", id);
        Optional<NatureDTO> natureDTO = natureService.findOne(id);
        return ResponseUtil.wrapOrNotFound(natureDTO);
    }

    /**
     * {@code DELETE  /natures/:id} : delete the "id" nature.
     *
     * @param id the id of the natureDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/natures/{id}")
    public ResponseEntity<Void> deleteNature(@PathVariable Long id) {
        log.debug("REST request to delete Nature : {}", id);
        natureService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
