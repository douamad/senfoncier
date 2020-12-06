package com.pirtol.lab.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.pirtol.lab.SenfoncierApp;
import com.pirtol.lab.domain.Quartier;
import com.pirtol.lab.repository.QuartierRepository;
import com.pirtol.lab.service.QuartierService;
import com.pirtol.lab.service.dto.QuartierDTO;
import com.pirtol.lab.service.mapper.QuartierMapper;
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
 * Integration tests for the {@link QuartierResource} REST controller.
 */
@SpringBootTest(classes = SenfoncierApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class QuartierResourceIT {
    private static final String DEFAULT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_LIBELLE = "AAAAAAAAAA";
    private static final String UPDATED_LIBELLE = "BBBBBBBBBB";

    @Autowired
    private QuartierRepository quartierRepository;

    @Autowired
    private QuartierMapper quartierMapper;

    @Autowired
    private QuartierService quartierService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restQuartierMockMvc;

    private Quartier quartier;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Quartier createEntity(EntityManager em) {
        Quartier quartier = new Quartier().code(DEFAULT_CODE).libelle(DEFAULT_LIBELLE);
        return quartier;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Quartier createUpdatedEntity(EntityManager em) {
        Quartier quartier = new Quartier().code(UPDATED_CODE).libelle(UPDATED_LIBELLE);
        return quartier;
    }

    @BeforeEach
    public void initTest() {
        quartier = createEntity(em);
    }

    @Test
    @Transactional
    public void createQuartier() throws Exception {
        int databaseSizeBeforeCreate = quartierRepository.findAll().size();
        // Create the Quartier
        QuartierDTO quartierDTO = quartierMapper.toDto(quartier);
        restQuartierMockMvc
            .perform(post("/api/quartiers").contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(quartierDTO)))
            .andExpect(status().isCreated());

        // Validate the Quartier in the database
        List<Quartier> quartierList = quartierRepository.findAll();
        assertThat(quartierList).hasSize(databaseSizeBeforeCreate + 1);
        Quartier testQuartier = quartierList.get(quartierList.size() - 1);
        assertThat(testQuartier.getCode()).isEqualTo(DEFAULT_CODE);
        assertThat(testQuartier.getLibelle()).isEqualTo(DEFAULT_LIBELLE);
    }

    @Test
    @Transactional
    public void createQuartierWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = quartierRepository.findAll().size();

        // Create the Quartier with an existing ID
        quartier.setId(1L);
        QuartierDTO quartierDTO = quartierMapper.toDto(quartier);

        // An entity with an existing ID cannot be created, so this API call must fail
        restQuartierMockMvc
            .perform(post("/api/quartiers").contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(quartierDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Quartier in the database
        List<Quartier> quartierList = quartierRepository.findAll();
        assertThat(quartierList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllQuartiers() throws Exception {
        // Initialize the database
        quartierRepository.saveAndFlush(quartier);

        // Get all the quartierList
        restQuartierMockMvc
            .perform(get("/api/quartiers?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(quartier.getId().intValue())))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE)))
            .andExpect(jsonPath("$.[*].libelle").value(hasItem(DEFAULT_LIBELLE)));
    }

    @Test
    @Transactional
    public void getQuartier() throws Exception {
        // Initialize the database
        quartierRepository.saveAndFlush(quartier);

        // Get the quartier
        restQuartierMockMvc
            .perform(get("/api/quartiers/{id}", quartier.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(quartier.getId().intValue()))
            .andExpect(jsonPath("$.code").value(DEFAULT_CODE))
            .andExpect(jsonPath("$.libelle").value(DEFAULT_LIBELLE));
    }

    @Test
    @Transactional
    public void getNonExistingQuartier() throws Exception {
        // Get the quartier
        restQuartierMockMvc.perform(get("/api/quartiers/{id}", Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateQuartier() throws Exception {
        // Initialize the database
        quartierRepository.saveAndFlush(quartier);

        int databaseSizeBeforeUpdate = quartierRepository.findAll().size();

        // Update the quartier
        Quartier updatedQuartier = quartierRepository.findById(quartier.getId()).get();
        // Disconnect from session so that the updates on updatedQuartier are not directly saved in db
        em.detach(updatedQuartier);
        updatedQuartier.code(UPDATED_CODE).libelle(UPDATED_LIBELLE);
        QuartierDTO quartierDTO = quartierMapper.toDto(updatedQuartier);

        restQuartierMockMvc
            .perform(put("/api/quartiers").contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(quartierDTO)))
            .andExpect(status().isOk());

        // Validate the Quartier in the database
        List<Quartier> quartierList = quartierRepository.findAll();
        assertThat(quartierList).hasSize(databaseSizeBeforeUpdate);
        Quartier testQuartier = quartierList.get(quartierList.size() - 1);
        assertThat(testQuartier.getCode()).isEqualTo(UPDATED_CODE);
        assertThat(testQuartier.getLibelle()).isEqualTo(UPDATED_LIBELLE);
    }

    @Test
    @Transactional
    public void updateNonExistingQuartier() throws Exception {
        int databaseSizeBeforeUpdate = quartierRepository.findAll().size();

        // Create the Quartier
        QuartierDTO quartierDTO = quartierMapper.toDto(quartier);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restQuartierMockMvc
            .perform(put("/api/quartiers").contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(quartierDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Quartier in the database
        List<Quartier> quartierList = quartierRepository.findAll();
        assertThat(quartierList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteQuartier() throws Exception {
        // Initialize the database
        quartierRepository.saveAndFlush(quartier);

        int databaseSizeBeforeDelete = quartierRepository.findAll().size();

        // Delete the quartier
        restQuartierMockMvc
            .perform(delete("/api/quartiers/{id}", quartier.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Quartier> quartierList = quartierRepository.findAll();
        assertThat(quartierList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
