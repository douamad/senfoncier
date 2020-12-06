package com.pirtol.lab.web.rest;

import com.pirtol.lab.service.CategorieClotureService;
import com.pirtol.lab.service.dto.CategorieClotureDTO;
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
 * REST controller for managing {@link com.pirtol.lab.domain.CategorieCloture}.
 */
@RestController
@RequestMapping("/api")
public class CategorieClotureResource {
    private final Logger log = LoggerFactory.getLogger(CategorieClotureResource.class);

    private static final String ENTITY_NAME = "categorieCloture";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CategorieClotureService categorieClotureService;

    public CategorieClotureResource(CategorieClotureService categorieClotureService) {
        this.categorieClotureService = categorieClotureService;
    }

    /**
     * {@code POST  /categorie-clotures} : Create a new categorieCloture.
     *
     * @param categorieClotureDTO the categorieClotureDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new categorieClotureDTO, or with status {@code 400 (Bad Request)} if the categorieCloture has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/categorie-clotures")
    public ResponseEntity<CategorieClotureDTO> createCategorieCloture(@RequestBody CategorieClotureDTO categorieClotureDTO)
        throws URISyntaxException {
        log.debug("REST request to save CategorieCloture : {}", categorieClotureDTO);
        if (categorieClotureDTO.getId() != null) {
            throw new BadRequestAlertException("A new categorieCloture cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CategorieClotureDTO result = categorieClotureService.save(categorieClotureDTO);
        return ResponseEntity
            .created(new URI("/api/categorie-clotures/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /categorie-clotures} : Updates an existing categorieCloture.
     *
     * @param categorieClotureDTO the categorieClotureDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated categorieClotureDTO,
     * or with status {@code 400 (Bad Request)} if the categorieClotureDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the categorieClotureDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/categorie-clotures")
    public ResponseEntity<CategorieClotureDTO> updateCategorieCloture(@RequestBody CategorieClotureDTO categorieClotureDTO)
        throws URISyntaxException {
        log.debug("REST request to update CategorieCloture : {}", categorieClotureDTO);
        if (categorieClotureDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CategorieClotureDTO result = categorieClotureService.save(categorieClotureDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, categorieClotureDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /categorie-clotures} : get all the categorieClotures.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of categorieClotures in body.
     */
    @GetMapping("/categorie-clotures")
    public ResponseEntity<List<CategorieClotureDTO>> getAllCategorieClotures(Pageable pageable) {
        log.debug("REST request to get a page of CategorieClotures");
        Page<CategorieClotureDTO> page = categorieClotureService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /categorie-clotures/:id} : get the "id" categorieCloture.
     *
     * @param id the id of the categorieClotureDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the categorieClotureDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/categorie-clotures/{id}")
    public ResponseEntity<CategorieClotureDTO> getCategorieCloture(@PathVariable Long id) {
        log.debug("REST request to get CategorieCloture : {}", id);
        Optional<CategorieClotureDTO> categorieClotureDTO = categorieClotureService.findOne(id);
        return ResponseUtil.wrapOrNotFound(categorieClotureDTO);
    }

    /**
     * {@code DELETE  /categorie-clotures/:id} : delete the "id" categorieCloture.
     *
     * @param id the id of the categorieClotureDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/categorie-clotures/{id}")
    public ResponseEntity<Void> deleteCategorieCloture(@PathVariable Long id) {
        log.debug("REST request to delete CategorieCloture : {}", id);
        categorieClotureService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
