package com.pirtol.lab.web.rest;

import com.pirtol.lab.SenfoncierApp;
import com.pirtol.lab.domain.Commune;
import com.pirtol.lab.repository.CommuneRepository;
import com.pirtol.lab.service.CommuneService;
import com.pirtol.lab.service.dto.CommuneDTO;
import com.pirtol.lab.service.mapper.CommuneMapper;

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
 * Integration tests for the {@link CommuneResource} REST controller.
 */
@SpringBootTest(classes = SenfoncierApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class CommuneResourceIT {

    private static final String DEFAULT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_LIBELLE = "AAAAAAAAAA";
    private static final String UPDATED_LIBELLE = "BBBBBBBBBB";

    @Autowired
    private CommuneRepository communeRepository;

    @Autowired
    private CommuneMapper communeMapper;

    @Autowired
    private CommuneService communeService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restCommuneMockMvc;

    private Commune commune;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Commune createEntity(EntityManager em) {
        Commune commune = new Commune()
            .code(DEFAULT_CODE)
            .libelle(DEFAULT_LIBELLE);
        return commune;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Commune createUpdatedEntity(EntityManager em) {
        Commune commune = new Commune()
            .code(UPDATED_CODE)
            .libelle(UPDATED_LIBELLE);
        return commune;
    }

    @BeforeEach
    public void initTest() {
        commune = createEntity(em);
    }

    @Test
    @Transactional
    public void createCommune() throws Exception {
        int databaseSizeBeforeCreate = communeRepository.findAll().size();
        // Create the Commune
        CommuneDTO communeDTO = communeMapper.toDto(commune);
        restCommuneMockMvc.perform(post("/api/communes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(communeDTO)))
            .andExpect(status().isCreated());

        // Validate the Commune in the database
        List<Commune> communeList = communeRepository.findAll();
        assertThat(communeList).hasSize(databaseSizeBeforeCreate + 1);
        Commune testCommune = communeList.get(communeList.size() - 1);
        assertThat(testCommune.getCode()).isEqualTo(DEFAULT_CODE);
        assertThat(testCommune.getLibelle()).isEqualTo(DEFAULT_LIBELLE);
    }

    @Test
    @Transactional
    public void createCommuneWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = communeRepository.findAll().size();

        // Create the Commune with an existing ID
        commune.setId(1L);
        CommuneDTO communeDTO = communeMapper.toDto(commune);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCommuneMockMvc.perform(post("/api/communes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(communeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Commune in the database
        List<Commune> communeList = communeRepository.findAll();
        assertThat(communeList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllCommunes() throws Exception {
        // Initialize the database
        communeRepository.saveAndFlush(commune);

        // Get all the communeList
        restCommuneMockMvc.perform(get("/api/communes?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(commune.getId().intValue())))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE)))
            .andExpect(jsonPath("$.[*].libelle").value(hasItem(DEFAULT_LIBELLE)));
    }
    
    @Test
    @Transactional
    public void getCommune() throws Exception {
        // Initialize the database
        communeRepository.saveAndFlush(commune);

        // Get the commune
        restCommuneMockMvc.perform(get("/api/communes/{id}", commune.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(commune.getId().intValue()))
            .andExpect(jsonPath("$.code").value(DEFAULT_CODE))
            .andExpect(jsonPath("$.libelle").value(DEFAULT_LIBELLE));
    }
    @Test
    @Transactional
    public void getNonExistingCommune() throws Exception {
        // Get the commune
        restCommuneMockMvc.perform(get("/api/communes/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCommune() throws Exception {
        // Initialize the database
        communeRepository.saveAndFlush(commune);

        int databaseSizeBeforeUpdate = communeRepository.findAll().size();

        // Update the commune
        Commune updatedCommune = communeRepository.findById(commune.getId()).get();
        // Disconnect from session so that the updates on updatedCommune are not directly saved in db
        em.detach(updatedCommune);
        updatedCommune
            .code(UPDATED_CODE)
            .libelle(UPDATED_LIBELLE);
        CommuneDTO communeDTO = communeMapper.toDto(updatedCommune);

        restCommuneMockMvc.perform(put("/api/communes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(communeDTO)))
            .andExpect(status().isOk());

        // Validate the Commune in the database
        List<Commune> communeList = communeRepository.findAll();
        assertThat(communeList).hasSize(databaseSizeBeforeUpdate);
        Commune testCommune = communeList.get(communeList.size() - 1);
        assertThat(testCommune.getCode()).isEqualTo(UPDATED_CODE);
        assertThat(testCommune.getLibelle()).isEqualTo(UPDATED_LIBELLE);
    }

    @Test
    @Transactional
    public void updateNonExistingCommune() throws Exception {
        int databaseSizeBeforeUpdate = communeRepository.findAll().size();

        // Create the Commune
        CommuneDTO communeDTO = communeMapper.toDto(commune);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCommuneMockMvc.perform(put("/api/communes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(communeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Commune in the database
        List<Commune> communeList = communeRepository.findAll();
        assertThat(communeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteCommune() throws Exception {
        // Initialize the database
        communeRepository.saveAndFlush(commune);

        int databaseSizeBeforeDelete = communeRepository.findAll().size();

        // Delete the commune
        restCommuneMockMvc.perform(delete("/api/communes/{id}", commune.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Commune> communeList = communeRepository.findAll();
        assertThat(communeList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
