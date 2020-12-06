package com.pirtol.lab.web.rest;

import com.pirtol.lab.SenfoncierApp;
import com.pirtol.lab.domain.Arrondissement;
import com.pirtol.lab.repository.ArrondissementRepository;
import com.pirtol.lab.service.ArrondissementService;
import com.pirtol.lab.service.dto.ArrondissementDTO;
import com.pirtol.lab.service.mapper.ArrondissementMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link ArrondissementResource} REST controller.
 */
@SpringBootTest(classes = SenfoncierApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class ArrondissementResourceIT {

    private static final String DEFAULT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_LIBELLE = "AAAAAAAAAA";
    private static final String UPDATED_LIBELLE = "BBBBBBBBBB";

    @Autowired
    private ArrondissementRepository arrondissementRepository;

    @Autowired
    private ArrondissementMapper arrondissementMapper;

    @Autowired
    private ArrondissementService arrondissementService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restArrondissementMockMvc;

    private Arrondissement arrondissement;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Arrondissement createEntity(EntityManager em) {
        Arrondissement arrondissement = new Arrondissement()
            .code(DEFAULT_CODE)
            .libelle(DEFAULT_LIBELLE);
        return arrondissement;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Arrondissement createUpdatedEntity(EntityManager em) {
        Arrondissement arrondissement = new Arrondissement()
            .code(UPDATED_CODE)
            .libelle(UPDATED_LIBELLE);
        return arrondissement;
    }

    @BeforeEach
    public void initTest() {
        arrondissement = createEntity(em);
    }

    @Test
    @Transactional
    public void createArrondissement() throws Exception {
        int databaseSizeBeforeCreate = arrondissementRepository.findAll().size();
        // Create the Arrondissement
        ArrondissementDTO arrondissementDTO = arrondissementMapper.toDto(arrondissement);
        restArrondissementMockMvc.perform(post("/api/arrondissements")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(arrondissementDTO)))
            .andExpect(status().isCreated());

        // Validate the Arrondissement in the database
        List<Arrondissement> arrondissementList = arrondissementRepository.findAll();
        assertThat(arrondissementList).hasSize(databaseSizeBeforeCreate + 1);
        Arrondissement testArrondissement = arrondissementList.get(arrondissementList.size() - 1);
        assertThat(testArrondissement.getCode()).isEqualTo(DEFAULT_CODE);
        assertThat(testArrondissement.getLibelle()).isEqualTo(DEFAULT_LIBELLE);
    }

    @Test
    @Transactional
    public void createArrondissementWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = arrondissementRepository.findAll().size();

        // Create the Arrondissement with an existing ID
        arrondissement.setId(1L);
        ArrondissementDTO arrondissementDTO = arrondissementMapper.toDto(arrondissement);

        // An entity with an existing ID cannot be created, so this API call must fail
        restArrondissementMockMvc.perform(post("/api/arrondissements")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(arrondissementDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Arrondissement in the database
        List<Arrondissement> arrondissementList = arrondissementRepository.findAll();
        assertThat(arrondissementList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllArrondissements() throws Exception {
        // Initialize the database
        arrondissementRepository.saveAndFlush(arrondissement);

        // Get all the arrondissementList
        restArrondissementMockMvc.perform(get("/api/arrondissements?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(arrondissement.getId().intValue())))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE)))
            .andExpect(jsonPath("$.[*].libelle").value(hasItem(DEFAULT_LIBELLE)));
    }
    
    @Test
    @Transactional
    public void getArrondissement() throws Exception {
        // Initialize the database
        arrondissementRepository.saveAndFlush(arrondissement);

        // Get the arrondissement
        restArrondissementMockMvc.perform(get("/api/arrondissements/{id}", arrondissement.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(arrondissement.getId().intValue()))
            .andExpect(jsonPath("$.code").value(DEFAULT_CODE))
            .andExpect(jsonPath("$.libelle").value(DEFAULT_LIBELLE));
    }
    @Test
    @Transactional
    public void getNonExistingArrondissement() throws Exception {
        // Get the arrondissement
        restArrondissementMockMvc.perform(get("/api/arrondissements/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateArrondissement() throws Exception {
        // Initialize the database
        arrondissementRepository.saveAndFlush(arrondissement);

        int databaseSizeBeforeUpdate = arrondissementRepository.findAll().size();

        // Update the arrondissement
        Arrondissement updatedArrondissement = arrondissementRepository.findById(arrondissement.getId()).get();
        // Disconnect from session so that the updates on updatedArrondissement are not directly saved in db
        em.detach(updatedArrondissement);
        updatedArrondissement
            .code(UPDATED_CODE)
            .libelle(UPDATED_LIBELLE);
        ArrondissementDTO arrondissementDTO = arrondissementMapper.toDto(updatedArrondissement);

        restArrondissementMockMvc.perform(put("/api/arrondissements")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(arrondissementDTO)))
            .andExpect(status().isOk());

        // Validate the Arrondissement in the database
        List<Arrondissement> arrondissementList = arrondissementRepository.findAll();
        assertThat(arrondissementList).hasSize(databaseSizeBeforeUpdate);
        Arrondissement testArrondissement = arrondissementList.get(arrondissementList.size() - 1);
        assertThat(testArrondissement.getCode()).isEqualTo(UPDATED_CODE);
        assertThat(testArrondissement.getLibelle()).isEqualTo(UPDATED_LIBELLE);
    }

    @Test
    @Transactional
    public void updateNonExistingArrondissement() throws Exception {
        int databaseSizeBeforeUpdate = arrondissementRepository.findAll().size();

        // Create the Arrondissement
        ArrondissementDTO arrondissementDTO = arrondissementMapper.toDto(arrondissement);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restArrondissementMockMvc.perform(put("/api/arrondissements")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(arrondissementDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Arrondissement in the database
        List<Arrondissement> arrondissementList = arrondissementRepository.findAll();
        assertThat(arrondissementList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteArrondissement() throws Exception {
        // Initialize the database
        arrondissementRepository.saveAndFlush(arrondissement);

        int databaseSizeBeforeDelete = arrondissementRepository.findAll().size();

        // Delete the arrondissement
        restArrondissementMockMvc.perform(delete("/api/arrondissements/{id}", arrondissement.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Arrondissement> arrondissementList = arrondissementRepository.findAll();
        assertThat(arrondissementList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
