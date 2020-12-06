package com.pirtol.lab.web.rest;

import com.pirtol.lab.SenfoncierApp;
import com.pirtol.lab.domain.CategorieCloture;
import com.pirtol.lab.repository.CategorieClotureRepository;
import com.pirtol.lab.service.CategorieClotureService;
import com.pirtol.lab.service.dto.CategorieClotureDTO;
import com.pirtol.lab.service.mapper.CategorieClotureMapper;

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
 * Integration tests for the {@link CategorieClotureResource} REST controller.
 */
@SpringBootTest(classes = SenfoncierApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class CategorieClotureResourceIT {

    private static final String DEFAULT_LIBELLE = "AAAAAAAAAA";
    private static final String UPDATED_LIBELLE = "BBBBBBBBBB";

    private static final Double DEFAULT_PRIX_METRE_CARE = 1D;
    private static final Double UPDATED_PRIX_METRE_CARE = 2D;

    @Autowired
    private CategorieClotureRepository categorieClotureRepository;

    @Autowired
    private CategorieClotureMapper categorieClotureMapper;

    @Autowired
    private CategorieClotureService categorieClotureService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restCategorieClotureMockMvc;

    private CategorieCloture categorieCloture;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CategorieCloture createEntity(EntityManager em) {
        CategorieCloture categorieCloture = new CategorieCloture()
            .libelle(DEFAULT_LIBELLE)
            .prixMetreCare(DEFAULT_PRIX_METRE_CARE);
        return categorieCloture;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CategorieCloture createUpdatedEntity(EntityManager em) {
        CategorieCloture categorieCloture = new CategorieCloture()
            .libelle(UPDATED_LIBELLE)
            .prixMetreCare(UPDATED_PRIX_METRE_CARE);
        return categorieCloture;
    }

    @BeforeEach
    public void initTest() {
        categorieCloture = createEntity(em);
    }

    @Test
    @Transactional
    public void createCategorieCloture() throws Exception {
        int databaseSizeBeforeCreate = categorieClotureRepository.findAll().size();
        // Create the CategorieCloture
        CategorieClotureDTO categorieClotureDTO = categorieClotureMapper.toDto(categorieCloture);
        restCategorieClotureMockMvc.perform(post("/api/categorie-clotures")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(categorieClotureDTO)))
            .andExpect(status().isCreated());

        // Validate the CategorieCloture in the database
        List<CategorieCloture> categorieClotureList = categorieClotureRepository.findAll();
        assertThat(categorieClotureList).hasSize(databaseSizeBeforeCreate + 1);
        CategorieCloture testCategorieCloture = categorieClotureList.get(categorieClotureList.size() - 1);
        assertThat(testCategorieCloture.getLibelle()).isEqualTo(DEFAULT_LIBELLE);
        assertThat(testCategorieCloture.getPrixMetreCare()).isEqualTo(DEFAULT_PRIX_METRE_CARE);
    }

    @Test
    @Transactional
    public void createCategorieClotureWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = categorieClotureRepository.findAll().size();

        // Create the CategorieCloture with an existing ID
        categorieCloture.setId(1L);
        CategorieClotureDTO categorieClotureDTO = categorieClotureMapper.toDto(categorieCloture);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCategorieClotureMockMvc.perform(post("/api/categorie-clotures")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(categorieClotureDTO)))
            .andExpect(status().isBadRequest());

        // Validate the CategorieCloture in the database
        List<CategorieCloture> categorieClotureList = categorieClotureRepository.findAll();
        assertThat(categorieClotureList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllCategorieClotures() throws Exception {
        // Initialize the database
        categorieClotureRepository.saveAndFlush(categorieCloture);

        // Get all the categorieClotureList
        restCategorieClotureMockMvc.perform(get("/api/categorie-clotures?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(categorieCloture.getId().intValue())))
            .andExpect(jsonPath("$.[*].libelle").value(hasItem(DEFAULT_LIBELLE)))
            .andExpect(jsonPath("$.[*].prixMetreCare").value(hasItem(DEFAULT_PRIX_METRE_CARE.doubleValue())));
    }
    
    @Test
    @Transactional
    public void getCategorieCloture() throws Exception {
        // Initialize the database
        categorieClotureRepository.saveAndFlush(categorieCloture);

        // Get the categorieCloture
        restCategorieClotureMockMvc.perform(get("/api/categorie-clotures/{id}", categorieCloture.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(categorieCloture.getId().intValue()))
            .andExpect(jsonPath("$.libelle").value(DEFAULT_LIBELLE))
            .andExpect(jsonPath("$.prixMetreCare").value(DEFAULT_PRIX_METRE_CARE.doubleValue()));
    }
    @Test
    @Transactional
    public void getNonExistingCategorieCloture() throws Exception {
        // Get the categorieCloture
        restCategorieClotureMockMvc.perform(get("/api/categorie-clotures/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCategorieCloture() throws Exception {
        // Initialize the database
        categorieClotureRepository.saveAndFlush(categorieCloture);

        int databaseSizeBeforeUpdate = categorieClotureRepository.findAll().size();

        // Update the categorieCloture
        CategorieCloture updatedCategorieCloture = categorieClotureRepository.findById(categorieCloture.getId()).get();
        // Disconnect from session so that the updates on updatedCategorieCloture are not directly saved in db
        em.detach(updatedCategorieCloture);
        updatedCategorieCloture
            .libelle(UPDATED_LIBELLE)
            .prixMetreCare(UPDATED_PRIX_METRE_CARE);
        CategorieClotureDTO categorieClotureDTO = categorieClotureMapper.toDto(updatedCategorieCloture);

        restCategorieClotureMockMvc.perform(put("/api/categorie-clotures")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(categorieClotureDTO)))
            .andExpect(status().isOk());

        // Validate the CategorieCloture in the database
        List<CategorieCloture> categorieClotureList = categorieClotureRepository.findAll();
        assertThat(categorieClotureList).hasSize(databaseSizeBeforeUpdate);
        CategorieCloture testCategorieCloture = categorieClotureList.get(categorieClotureList.size() - 1);
        assertThat(testCategorieCloture.getLibelle()).isEqualTo(UPDATED_LIBELLE);
        assertThat(testCategorieCloture.getPrixMetreCare()).isEqualTo(UPDATED_PRIX_METRE_CARE);
    }

    @Test
    @Transactional
    public void updateNonExistingCategorieCloture() throws Exception {
        int databaseSizeBeforeUpdate = categorieClotureRepository.findAll().size();

        // Create the CategorieCloture
        CategorieClotureDTO categorieClotureDTO = categorieClotureMapper.toDto(categorieCloture);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCategorieClotureMockMvc.perform(put("/api/categorie-clotures")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(categorieClotureDTO)))
            .andExpect(status().isBadRequest());

        // Validate the CategorieCloture in the database
        List<CategorieCloture> categorieClotureList = categorieClotureRepository.findAll();
        assertThat(categorieClotureList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteCategorieCloture() throws Exception {
        // Initialize the database
        categorieClotureRepository.saveAndFlush(categorieCloture);

        int databaseSizeBeforeDelete = categorieClotureRepository.findAll().size();

        // Delete the categorieCloture
        restCategorieClotureMockMvc.perform(delete("/api/categorie-clotures/{id}", categorieCloture.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<CategorieCloture> categorieClotureList = categorieClotureRepository.findAll();
        assertThat(categorieClotureList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
