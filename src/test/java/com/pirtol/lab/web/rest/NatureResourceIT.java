package com.pirtol.lab.web.rest;

import com.pirtol.lab.SenfoncierApp;
import com.pirtol.lab.domain.Nature;
import com.pirtol.lab.repository.NatureRepository;
import com.pirtol.lab.service.NatureService;
import com.pirtol.lab.service.dto.NatureDTO;
import com.pirtol.lab.service.mapper.NatureMapper;

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
 * Integration tests for the {@link NatureResource} REST controller.
 */
@SpringBootTest(classes = SenfoncierApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class NatureResourceIT {

    private static final String DEFAULT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_LIBELLE = "AAAAAAAAAA";
    private static final String UPDATED_LIBELLE = "BBBBBBBBBB";

    @Autowired
    private NatureRepository natureRepository;

    @Autowired
    private NatureMapper natureMapper;

    @Autowired
    private NatureService natureService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restNatureMockMvc;

    private Nature nature;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Nature createEntity(EntityManager em) {
        Nature nature = new Nature()
            .code(DEFAULT_CODE)
            .libelle(DEFAULT_LIBELLE);
        return nature;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Nature createUpdatedEntity(EntityManager em) {
        Nature nature = new Nature()
            .code(UPDATED_CODE)
            .libelle(UPDATED_LIBELLE);
        return nature;
    }

    @BeforeEach
    public void initTest() {
        nature = createEntity(em);
    }

    @Test
    @Transactional
    public void createNature() throws Exception {
        int databaseSizeBeforeCreate = natureRepository.findAll().size();
        // Create the Nature
        NatureDTO natureDTO = natureMapper.toDto(nature);
        restNatureMockMvc.perform(post("/api/natures")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(natureDTO)))
            .andExpect(status().isCreated());

        // Validate the Nature in the database
        List<Nature> natureList = natureRepository.findAll();
        assertThat(natureList).hasSize(databaseSizeBeforeCreate + 1);
        Nature testNature = natureList.get(natureList.size() - 1);
        assertThat(testNature.getCode()).isEqualTo(DEFAULT_CODE);
        assertThat(testNature.getLibelle()).isEqualTo(DEFAULT_LIBELLE);
    }

    @Test
    @Transactional
    public void createNatureWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = natureRepository.findAll().size();

        // Create the Nature with an existing ID
        nature.setId(1L);
        NatureDTO natureDTO = natureMapper.toDto(nature);

        // An entity with an existing ID cannot be created, so this API call must fail
        restNatureMockMvc.perform(post("/api/natures")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(natureDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Nature in the database
        List<Nature> natureList = natureRepository.findAll();
        assertThat(natureList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllNatures() throws Exception {
        // Initialize the database
        natureRepository.saveAndFlush(nature);

        // Get all the natureList
        restNatureMockMvc.perform(get("/api/natures?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(nature.getId().intValue())))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE)))
            .andExpect(jsonPath("$.[*].libelle").value(hasItem(DEFAULT_LIBELLE)));
    }
    
    @Test
    @Transactional
    public void getNature() throws Exception {
        // Initialize the database
        natureRepository.saveAndFlush(nature);

        // Get the nature
        restNatureMockMvc.perform(get("/api/natures/{id}", nature.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(nature.getId().intValue()))
            .andExpect(jsonPath("$.code").value(DEFAULT_CODE))
            .andExpect(jsonPath("$.libelle").value(DEFAULT_LIBELLE));
    }
    @Test
    @Transactional
    public void getNonExistingNature() throws Exception {
        // Get the nature
        restNatureMockMvc.perform(get("/api/natures/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateNature() throws Exception {
        // Initialize the database
        natureRepository.saveAndFlush(nature);

        int databaseSizeBeforeUpdate = natureRepository.findAll().size();

        // Update the nature
        Nature updatedNature = natureRepository.findById(nature.getId()).get();
        // Disconnect from session so that the updates on updatedNature are not directly saved in db
        em.detach(updatedNature);
        updatedNature
            .code(UPDATED_CODE)
            .libelle(UPDATED_LIBELLE);
        NatureDTO natureDTO = natureMapper.toDto(updatedNature);

        restNatureMockMvc.perform(put("/api/natures")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(natureDTO)))
            .andExpect(status().isOk());

        // Validate the Nature in the database
        List<Nature> natureList = natureRepository.findAll();
        assertThat(natureList).hasSize(databaseSizeBeforeUpdate);
        Nature testNature = natureList.get(natureList.size() - 1);
        assertThat(testNature.getCode()).isEqualTo(UPDATED_CODE);
        assertThat(testNature.getLibelle()).isEqualTo(UPDATED_LIBELLE);
    }

    @Test
    @Transactional
    public void updateNonExistingNature() throws Exception {
        int databaseSizeBeforeUpdate = natureRepository.findAll().size();

        // Create the Nature
        NatureDTO natureDTO = natureMapper.toDto(nature);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restNatureMockMvc.perform(put("/api/natures")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(natureDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Nature in the database
        List<Nature> natureList = natureRepository.findAll();
        assertThat(natureList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteNature() throws Exception {
        // Initialize the database
        natureRepository.saveAndFlush(nature);

        int databaseSizeBeforeDelete = natureRepository.findAll().size();

        // Delete the nature
        restNatureMockMvc.perform(delete("/api/natures/{id}", nature.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Nature> natureList = natureRepository.findAll();
        assertThat(natureList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
