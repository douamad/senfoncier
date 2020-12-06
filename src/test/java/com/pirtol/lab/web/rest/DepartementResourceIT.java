package com.pirtol.lab.web.rest;

import com.pirtol.lab.SenfoncierApp;
import com.pirtol.lab.domain.Departement;
import com.pirtol.lab.repository.DepartementRepository;
import com.pirtol.lab.service.DepartementService;
import com.pirtol.lab.service.dto.DepartementDTO;
import com.pirtol.lab.service.mapper.DepartementMapper;

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
 * Integration tests for the {@link DepartementResource} REST controller.
 */
@SpringBootTest(classes = SenfoncierApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class DepartementResourceIT {

    private static final String DEFAULT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_LIBELLE = "AAAAAAAAAA";
    private static final String UPDATED_LIBELLE = "BBBBBBBBBB";

    @Autowired
    private DepartementRepository departementRepository;

    @Autowired
    private DepartementMapper departementMapper;

    @Autowired
    private DepartementService departementService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restDepartementMockMvc;

    private Departement departement;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Departement createEntity(EntityManager em) {
        Departement departement = new Departement()
            .code(DEFAULT_CODE)
            .libelle(DEFAULT_LIBELLE);
        return departement;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Departement createUpdatedEntity(EntityManager em) {
        Departement departement = new Departement()
            .code(UPDATED_CODE)
            .libelle(UPDATED_LIBELLE);
        return departement;
    }

    @BeforeEach
    public void initTest() {
        departement = createEntity(em);
    }

    @Test
    @Transactional
    public void createDepartement() throws Exception {
        int databaseSizeBeforeCreate = departementRepository.findAll().size();
        // Create the Departement
        DepartementDTO departementDTO = departementMapper.toDto(departement);
        restDepartementMockMvc.perform(post("/api/departements")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(departementDTO)))
            .andExpect(status().isCreated());

        // Validate the Departement in the database
        List<Departement> departementList = departementRepository.findAll();
        assertThat(departementList).hasSize(databaseSizeBeforeCreate + 1);
        Departement testDepartement = departementList.get(departementList.size() - 1);
        assertThat(testDepartement.getCode()).isEqualTo(DEFAULT_CODE);
        assertThat(testDepartement.getLibelle()).isEqualTo(DEFAULT_LIBELLE);
    }

    @Test
    @Transactional
    public void createDepartementWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = departementRepository.findAll().size();

        // Create the Departement with an existing ID
        departement.setId(1L);
        DepartementDTO departementDTO = departementMapper.toDto(departement);

        // An entity with an existing ID cannot be created, so this API call must fail
        restDepartementMockMvc.perform(post("/api/departements")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(departementDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Departement in the database
        List<Departement> departementList = departementRepository.findAll();
        assertThat(departementList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllDepartements() throws Exception {
        // Initialize the database
        departementRepository.saveAndFlush(departement);

        // Get all the departementList
        restDepartementMockMvc.perform(get("/api/departements?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(departement.getId().intValue())))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE)))
            .andExpect(jsonPath("$.[*].libelle").value(hasItem(DEFAULT_LIBELLE)));
    }
    
    @Test
    @Transactional
    public void getDepartement() throws Exception {
        // Initialize the database
        departementRepository.saveAndFlush(departement);

        // Get the departement
        restDepartementMockMvc.perform(get("/api/departements/{id}", departement.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(departement.getId().intValue()))
            .andExpect(jsonPath("$.code").value(DEFAULT_CODE))
            .andExpect(jsonPath("$.libelle").value(DEFAULT_LIBELLE));
    }
    @Test
    @Transactional
    public void getNonExistingDepartement() throws Exception {
        // Get the departement
        restDepartementMockMvc.perform(get("/api/departements/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateDepartement() throws Exception {
        // Initialize the database
        departementRepository.saveAndFlush(departement);

        int databaseSizeBeforeUpdate = departementRepository.findAll().size();

        // Update the departement
        Departement updatedDepartement = departementRepository.findById(departement.getId()).get();
        // Disconnect from session so that the updates on updatedDepartement are not directly saved in db
        em.detach(updatedDepartement);
        updatedDepartement
            .code(UPDATED_CODE)
            .libelle(UPDATED_LIBELLE);
        DepartementDTO departementDTO = departementMapper.toDto(updatedDepartement);

        restDepartementMockMvc.perform(put("/api/departements")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(departementDTO)))
            .andExpect(status().isOk());

        // Validate the Departement in the database
        List<Departement> departementList = departementRepository.findAll();
        assertThat(departementList).hasSize(databaseSizeBeforeUpdate);
        Departement testDepartement = departementList.get(departementList.size() - 1);
        assertThat(testDepartement.getCode()).isEqualTo(UPDATED_CODE);
        assertThat(testDepartement.getLibelle()).isEqualTo(UPDATED_LIBELLE);
    }

    @Test
    @Transactional
    public void updateNonExistingDepartement() throws Exception {
        int databaseSizeBeforeUpdate = departementRepository.findAll().size();

        // Create the Departement
        DepartementDTO departementDTO = departementMapper.toDto(departement);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDepartementMockMvc.perform(put("/api/departements")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(departementDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Departement in the database
        List<Departement> departementList = departementRepository.findAll();
        assertThat(departementList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteDepartement() throws Exception {
        // Initialize the database
        departementRepository.saveAndFlush(departement);

        int databaseSizeBeforeDelete = departementRepository.findAll().size();

        // Delete the departement
        restDepartementMockMvc.perform(delete("/api/departements/{id}", departement.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Departement> departementList = departementRepository.findAll();
        assertThat(departementList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
