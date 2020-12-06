package com.pirtol.lab.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.pirtol.lab.SenfoncierApp;
import com.pirtol.lab.domain.Lotissement;
import com.pirtol.lab.repository.LotissementRepository;
import com.pirtol.lab.service.LotissementService;
import com.pirtol.lab.service.dto.LotissementDTO;
import com.pirtol.lab.service.mapper.LotissementMapper;
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
 * Integration tests for the {@link LotissementResource} REST controller.
 */
@SpringBootTest(classes = SenfoncierApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class LotissementResourceIT {
    private static final String DEFAULT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_LIBELLE = "AAAAAAAAAA";
    private static final String UPDATED_LIBELLE = "BBBBBBBBBB";

    @Autowired
    private LotissementRepository lotissementRepository;

    @Autowired
    private LotissementMapper lotissementMapper;

    @Autowired
    private LotissementService lotissementService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restLotissementMockMvc;

    private Lotissement lotissement;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Lotissement createEntity(EntityManager em) {
        Lotissement lotissement = new Lotissement().code(DEFAULT_CODE).libelle(DEFAULT_LIBELLE);
        return lotissement;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Lotissement createUpdatedEntity(EntityManager em) {
        Lotissement lotissement = new Lotissement().code(UPDATED_CODE).libelle(UPDATED_LIBELLE);
        return lotissement;
    }

    @BeforeEach
    public void initTest() {
        lotissement = createEntity(em);
    }

    @Test
    @Transactional
    public void createLotissement() throws Exception {
        int databaseSizeBeforeCreate = lotissementRepository.findAll().size();
        // Create the Lotissement
        LotissementDTO lotissementDTO = lotissementMapper.toDto(lotissement);
        restLotissementMockMvc
            .perform(
                post("/api/lotissements").contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(lotissementDTO))
            )
            .andExpect(status().isCreated());

        // Validate the Lotissement in the database
        List<Lotissement> lotissementList = lotissementRepository.findAll();
        assertThat(lotissementList).hasSize(databaseSizeBeforeCreate + 1);
        Lotissement testLotissement = lotissementList.get(lotissementList.size() - 1);
        assertThat(testLotissement.getCode()).isEqualTo(DEFAULT_CODE);
        assertThat(testLotissement.getLibelle()).isEqualTo(DEFAULT_LIBELLE);
    }

    @Test
    @Transactional
    public void createLotissementWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = lotissementRepository.findAll().size();

        // Create the Lotissement with an existing ID
        lotissement.setId(1L);
        LotissementDTO lotissementDTO = lotissementMapper.toDto(lotissement);

        // An entity with an existing ID cannot be created, so this API call must fail
        restLotissementMockMvc
            .perform(
                post("/api/lotissements").contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(lotissementDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Lotissement in the database
        List<Lotissement> lotissementList = lotissementRepository.findAll();
        assertThat(lotissementList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllLotissements() throws Exception {
        // Initialize the database
        lotissementRepository.saveAndFlush(lotissement);

        // Get all the lotissementList
        restLotissementMockMvc
            .perform(get("/api/lotissements?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(lotissement.getId().intValue())))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE)))
            .andExpect(jsonPath("$.[*].libelle").value(hasItem(DEFAULT_LIBELLE)));
    }

    @Test
    @Transactional
    public void getLotissement() throws Exception {
        // Initialize the database
        lotissementRepository.saveAndFlush(lotissement);

        // Get the lotissement
        restLotissementMockMvc
            .perform(get("/api/lotissements/{id}", lotissement.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(lotissement.getId().intValue()))
            .andExpect(jsonPath("$.code").value(DEFAULT_CODE))
            .andExpect(jsonPath("$.libelle").value(DEFAULT_LIBELLE));
    }

    @Test
    @Transactional
    public void getNonExistingLotissement() throws Exception {
        // Get the lotissement
        restLotissementMockMvc.perform(get("/api/lotissements/{id}", Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateLotissement() throws Exception {
        // Initialize the database
        lotissementRepository.saveAndFlush(lotissement);

        int databaseSizeBeforeUpdate = lotissementRepository.findAll().size();

        // Update the lotissement
        Lotissement updatedLotissement = lotissementRepository.findById(lotissement.getId()).get();
        // Disconnect from session so that the updates on updatedLotissement are not directly saved in db
        em.detach(updatedLotissement);
        updatedLotissement.code(UPDATED_CODE).libelle(UPDATED_LIBELLE);
        LotissementDTO lotissementDTO = lotissementMapper.toDto(updatedLotissement);

        restLotissementMockMvc
            .perform(
                put("/api/lotissements").contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(lotissementDTO))
            )
            .andExpect(status().isOk());

        // Validate the Lotissement in the database
        List<Lotissement> lotissementList = lotissementRepository.findAll();
        assertThat(lotissementList).hasSize(databaseSizeBeforeUpdate);
        Lotissement testLotissement = lotissementList.get(lotissementList.size() - 1);
        assertThat(testLotissement.getCode()).isEqualTo(UPDATED_CODE);
        assertThat(testLotissement.getLibelle()).isEqualTo(UPDATED_LIBELLE);
    }

    @Test
    @Transactional
    public void updateNonExistingLotissement() throws Exception {
        int databaseSizeBeforeUpdate = lotissementRepository.findAll().size();

        // Create the Lotissement
        LotissementDTO lotissementDTO = lotissementMapper.toDto(lotissement);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restLotissementMockMvc
            .perform(
                put("/api/lotissements").contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(lotissementDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Lotissement in the database
        List<Lotissement> lotissementList = lotissementRepository.findAll();
        assertThat(lotissementList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteLotissement() throws Exception {
        // Initialize the database
        lotissementRepository.saveAndFlush(lotissement);

        int databaseSizeBeforeDelete = lotissementRepository.findAll().size();

        // Delete the lotissement
        restLotissementMockMvc
            .perform(delete("/api/lotissements/{id}", lotissement.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Lotissement> lotissementList = lotissementRepository.findAll();
        assertThat(lotissementList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
