package com.pirtol.lab.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.pirtol.lab.SenfoncierApp;
import com.pirtol.lab.domain.CategorieBatie;
import com.pirtol.lab.repository.CategorieBatieRepository;
import com.pirtol.lab.service.CategorieBatieService;
import com.pirtol.lab.service.dto.CategorieBatieDTO;
import com.pirtol.lab.service.mapper.CategorieBatieMapper;
import java.util.List;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link CategorieBatieResource} REST controller.
 */
@SpringBootTest(classes = SenfoncierApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class CategorieBatieResourceIT {
    private static final String DEFAULT_LIBELLE = "AAAAAAAAAA";
    private static final String UPDATED_LIBELLE = "BBBBBBBBBB";

    private static final Double DEFAULT_PRIX_METRE_CARE = 1D;
    private static final Double UPDATED_PRIX_METRE_CARE = 2D;

    @Autowired
    private CategorieBatieRepository categorieBatieRepository;

    @Autowired
    private CategorieBatieMapper categorieBatieMapper;

    @Autowired
    private CategorieBatieService categorieBatieService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restCategorieBatieMockMvc;

    private CategorieBatie categorieBatie;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CategorieBatie createEntity(EntityManager em) {
        CategorieBatie categorieBatie = new CategorieBatie().libelle(DEFAULT_LIBELLE).prixMetreCare(DEFAULT_PRIX_METRE_CARE);
        return categorieBatie;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CategorieBatie createUpdatedEntity(EntityManager em) {
        CategorieBatie categorieBatie = new CategorieBatie().libelle(UPDATED_LIBELLE).prixMetreCare(UPDATED_PRIX_METRE_CARE);
        return categorieBatie;
    }

    @BeforeEach
    public void initTest() {
        categorieBatie = createEntity(em);
    }

    @Test
    @Transactional
    public void createCategorieBatie() throws Exception {
        int databaseSizeBeforeCreate = categorieBatieRepository.findAll().size();
        // Create the CategorieBatie
        CategorieBatieDTO categorieBatieDTO = categorieBatieMapper.toDto(categorieBatie);
        restCategorieBatieMockMvc
            .perform(
                post("/api/categorie-baties")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(categorieBatieDTO))
            )
            .andExpect(status().isCreated());

        // Validate the CategorieBatie in the database
        List<CategorieBatie> categorieBatieList = categorieBatieRepository.findAll();
        assertThat(categorieBatieList).hasSize(databaseSizeBeforeCreate + 1);
        CategorieBatie testCategorieBatie = categorieBatieList.get(categorieBatieList.size() - 1);
        assertThat(testCategorieBatie.getLibelle()).isEqualTo(DEFAULT_LIBELLE);
        assertThat(testCategorieBatie.getPrixMetreCare()).isEqualTo(DEFAULT_PRIX_METRE_CARE);
    }

    @Test
    @Transactional
    public void createCategorieBatieWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = categorieBatieRepository.findAll().size();

        // Create the CategorieBatie with an existing ID
        categorieBatie.setId(1L);
        CategorieBatieDTO categorieBatieDTO = categorieBatieMapper.toDto(categorieBatie);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCategorieBatieMockMvc
            .perform(
                post("/api/categorie-baties")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(categorieBatieDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the CategorieBatie in the database
        List<CategorieBatie> categorieBatieList = categorieBatieRepository.findAll();
        assertThat(categorieBatieList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllCategorieBaties() throws Exception {
        // Initialize the database
        categorieBatieRepository.saveAndFlush(categorieBatie);

        // Get all the categorieBatieList
        restCategorieBatieMockMvc
            .perform(get("/api/categorie-baties?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(categorieBatie.getId().intValue())))
            .andExpect(jsonPath("$.[*].libelle").value(hasItem(DEFAULT_LIBELLE)))
            .andExpect(jsonPath("$.[*].prixMetreCare").value(hasItem(DEFAULT_PRIX_METRE_CARE.doubleValue())));
    }

    @Test
    @Transactional
    public void getCategorieBatie() throws Exception {
        // Initialize the database
        categorieBatieRepository.saveAndFlush(categorieBatie);

        // Get the categorieBatie
        restCategorieBatieMockMvc
            .perform(get("/api/categorie-baties/{id}", categorieBatie.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(categorieBatie.getId().intValue()))
            .andExpect(jsonPath("$.libelle").value(DEFAULT_LIBELLE))
            .andExpect(jsonPath("$.prixMetreCare").value(DEFAULT_PRIX_METRE_CARE.doubleValue()));
    }

    @Test
    @Transactional
    public void getNonExistingCategorieBatie() throws Exception {
        // Get the categorieBatie
        restCategorieBatieMockMvc.perform(get("/api/categorie-baties/{id}", Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCategorieBatie() throws Exception {
        // Initialize the database
        categorieBatieRepository.saveAndFlush(categorieBatie);

        int databaseSizeBeforeUpdate = categorieBatieRepository.findAll().size();

        // Update the categorieBatie
        CategorieBatie updatedCategorieBatie = categorieBatieRepository.findById(categorieBatie.getId()).get();
        // Disconnect from session so that the updates on updatedCategorieBatie are not directly saved in db
        em.detach(updatedCategorieBatie);
        updatedCategorieBatie.libelle(UPDATED_LIBELLE).prixMetreCare(UPDATED_PRIX_METRE_CARE);
        CategorieBatieDTO categorieBatieDTO = categorieBatieMapper.toDto(updatedCategorieBatie);

        restCategorieBatieMockMvc
            .perform(
                put("/api/categorie-baties")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(categorieBatieDTO))
            )
            .andExpect(status().isOk());

        // Validate the CategorieBatie in the database
        List<CategorieBatie> categorieBatieList = categorieBatieRepository.findAll();
        assertThat(categorieBatieList).hasSize(databaseSizeBeforeUpdate);
        CategorieBatie testCategorieBatie = categorieBatieList.get(categorieBatieList.size() - 1);
        assertThat(testCategorieBatie.getLibelle()).isEqualTo(UPDATED_LIBELLE);
        assertThat(testCategorieBatie.getPrixMetreCare()).isEqualTo(UPDATED_PRIX_METRE_CARE);
    }

    @Test
    @Transactional
    public void updateNonExistingCategorieBatie() throws Exception {
        int databaseSizeBeforeUpdate = categorieBatieRepository.findAll().size();

        // Create the CategorieBatie
        CategorieBatieDTO categorieBatieDTO = categorieBatieMapper.toDto(categorieBatie);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCategorieBatieMockMvc
            .perform(
                put("/api/categorie-baties")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(categorieBatieDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the CategorieBatie in the database
        List<CategorieBatie> categorieBatieList = categorieBatieRepository.findAll();
        assertThat(categorieBatieList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteCategorieBatie() throws Exception {
        // Initialize the database
        categorieBatieRepository.saveAndFlush(categorieBatie);

        int databaseSizeBeforeDelete = categorieBatieRepository.findAll().size();

        // Delete the categorieBatie
        restCategorieBatieMockMvc
            .perform(delete("/api/categorie-baties/{id}", categorieBatie.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<CategorieBatie> categorieBatieList = categorieBatieRepository.findAll();
        assertThat(categorieBatieList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
