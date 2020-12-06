package com.pirtol.lab.web.rest;

import com.pirtol.lab.SenfoncierApp;
import com.pirtol.lab.domain.Parcelle;
import com.pirtol.lab.repository.ParcelleRepository;
import com.pirtol.lab.service.ParcelleService;
import com.pirtol.lab.service.dto.ParcelleDTO;
import com.pirtol.lab.service.mapper.ParcelleMapper;

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
 * Integration tests for the {@link ParcelleResource} REST controller.
 */
@SpringBootTest(classes = SenfoncierApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class ParcelleResourceIT {

    private static final String DEFAULT_NUM_SECTION = "AAAAAAAAAA";
    private static final String UPDATED_NUM_SECTION = "BBBBBBBBBB";

    private static final String DEFAULT_NUMERO_PARCELLE = "AAAAAAAAAA";
    private static final String UPDATED_NUMERO_PARCELLE = "BBBBBBBBBB";

    private static final String DEFAULT_NICAD = "AAAAAAAAAA";
    private static final String UPDATED_NICAD = "BBBBBBBBBB";

    private static final Double DEFAULT_SUPERFICI = 1D;
    private static final Double UPDATED_SUPERFICI = 2D;

    private static final String DEFAULT_TITRE_MERE = "AAAAAAAAAA";
    private static final String UPDATED_TITRE_MERE = "BBBBBBBBBB";

    private static final String DEFAULT_TITRE_CREE = "AAAAAAAAAA";
    private static final String UPDATED_TITRE_CREE = "BBBBBBBBBB";

    @Autowired
    private ParcelleRepository parcelleRepository;

    @Autowired
    private ParcelleMapper parcelleMapper;

    @Autowired
    private ParcelleService parcelleService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restParcelleMockMvc;

    private Parcelle parcelle;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Parcelle createEntity(EntityManager em) {
        Parcelle parcelle = new Parcelle()
            .numSection(DEFAULT_NUM_SECTION)
            .numeroParcelle(DEFAULT_NUMERO_PARCELLE)
            .nicad(DEFAULT_NICAD)
            .superfici(DEFAULT_SUPERFICI)
            .titreMere(DEFAULT_TITRE_MERE)
            .titreCree(DEFAULT_TITRE_CREE);
        return parcelle;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Parcelle createUpdatedEntity(EntityManager em) {
        Parcelle parcelle = new Parcelle()
            .numSection(UPDATED_NUM_SECTION)
            .numeroParcelle(UPDATED_NUMERO_PARCELLE)
            .nicad(UPDATED_NICAD)
            .superfici(UPDATED_SUPERFICI)
            .titreMere(UPDATED_TITRE_MERE)
            .titreCree(UPDATED_TITRE_CREE);
        return parcelle;
    }

    @BeforeEach
    public void initTest() {
        parcelle = createEntity(em);
    }

    @Test
    @Transactional
    public void createParcelle() throws Exception {
        int databaseSizeBeforeCreate = parcelleRepository.findAll().size();
        // Create the Parcelle
        ParcelleDTO parcelleDTO = parcelleMapper.toDto(parcelle);
        restParcelleMockMvc.perform(post("/api/parcelles")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(parcelleDTO)))
            .andExpect(status().isCreated());

        // Validate the Parcelle in the database
        List<Parcelle> parcelleList = parcelleRepository.findAll();
        assertThat(parcelleList).hasSize(databaseSizeBeforeCreate + 1);
        Parcelle testParcelle = parcelleList.get(parcelleList.size() - 1);
        assertThat(testParcelle.getNumSection()).isEqualTo(DEFAULT_NUM_SECTION);
        assertThat(testParcelle.getNumeroParcelle()).isEqualTo(DEFAULT_NUMERO_PARCELLE);
        assertThat(testParcelle.getNicad()).isEqualTo(DEFAULT_NICAD);
        assertThat(testParcelle.getSuperfici()).isEqualTo(DEFAULT_SUPERFICI);
        assertThat(testParcelle.getTitreMere()).isEqualTo(DEFAULT_TITRE_MERE);
        assertThat(testParcelle.getTitreCree()).isEqualTo(DEFAULT_TITRE_CREE);
    }

    @Test
    @Transactional
    public void createParcelleWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = parcelleRepository.findAll().size();

        // Create the Parcelle with an existing ID
        parcelle.setId(1L);
        ParcelleDTO parcelleDTO = parcelleMapper.toDto(parcelle);

        // An entity with an existing ID cannot be created, so this API call must fail
        restParcelleMockMvc.perform(post("/api/parcelles")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(parcelleDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Parcelle in the database
        List<Parcelle> parcelleList = parcelleRepository.findAll();
        assertThat(parcelleList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllParcelles() throws Exception {
        // Initialize the database
        parcelleRepository.saveAndFlush(parcelle);

        // Get all the parcelleList
        restParcelleMockMvc.perform(get("/api/parcelles?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(parcelle.getId().intValue())))
            .andExpect(jsonPath("$.[*].numSection").value(hasItem(DEFAULT_NUM_SECTION)))
            .andExpect(jsonPath("$.[*].numeroParcelle").value(hasItem(DEFAULT_NUMERO_PARCELLE)))
            .andExpect(jsonPath("$.[*].nicad").value(hasItem(DEFAULT_NICAD)))
            .andExpect(jsonPath("$.[*].superfici").value(hasItem(DEFAULT_SUPERFICI.doubleValue())))
            .andExpect(jsonPath("$.[*].titreMere").value(hasItem(DEFAULT_TITRE_MERE)))
            .andExpect(jsonPath("$.[*].titreCree").value(hasItem(DEFAULT_TITRE_CREE)));
    }
    
    @Test
    @Transactional
    public void getParcelle() throws Exception {
        // Initialize the database
        parcelleRepository.saveAndFlush(parcelle);

        // Get the parcelle
        restParcelleMockMvc.perform(get("/api/parcelles/{id}", parcelle.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(parcelle.getId().intValue()))
            .andExpect(jsonPath("$.numSection").value(DEFAULT_NUM_SECTION))
            .andExpect(jsonPath("$.numeroParcelle").value(DEFAULT_NUMERO_PARCELLE))
            .andExpect(jsonPath("$.nicad").value(DEFAULT_NICAD))
            .andExpect(jsonPath("$.superfici").value(DEFAULT_SUPERFICI.doubleValue()))
            .andExpect(jsonPath("$.titreMere").value(DEFAULT_TITRE_MERE))
            .andExpect(jsonPath("$.titreCree").value(DEFAULT_TITRE_CREE));
    }
    @Test
    @Transactional
    public void getNonExistingParcelle() throws Exception {
        // Get the parcelle
        restParcelleMockMvc.perform(get("/api/parcelles/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateParcelle() throws Exception {
        // Initialize the database
        parcelleRepository.saveAndFlush(parcelle);

        int databaseSizeBeforeUpdate = parcelleRepository.findAll().size();

        // Update the parcelle
        Parcelle updatedParcelle = parcelleRepository.findById(parcelle.getId()).get();
        // Disconnect from session so that the updates on updatedParcelle are not directly saved in db
        em.detach(updatedParcelle);
        updatedParcelle
            .numSection(UPDATED_NUM_SECTION)
            .numeroParcelle(UPDATED_NUMERO_PARCELLE)
            .nicad(UPDATED_NICAD)
            .superfici(UPDATED_SUPERFICI)
            .titreMere(UPDATED_TITRE_MERE)
            .titreCree(UPDATED_TITRE_CREE);
        ParcelleDTO parcelleDTO = parcelleMapper.toDto(updatedParcelle);

        restParcelleMockMvc.perform(put("/api/parcelles")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(parcelleDTO)))
            .andExpect(status().isOk());

        // Validate the Parcelle in the database
        List<Parcelle> parcelleList = parcelleRepository.findAll();
        assertThat(parcelleList).hasSize(databaseSizeBeforeUpdate);
        Parcelle testParcelle = parcelleList.get(parcelleList.size() - 1);
        assertThat(testParcelle.getNumSection()).isEqualTo(UPDATED_NUM_SECTION);
        assertThat(testParcelle.getNumeroParcelle()).isEqualTo(UPDATED_NUMERO_PARCELLE);
        assertThat(testParcelle.getNicad()).isEqualTo(UPDATED_NICAD);
        assertThat(testParcelle.getSuperfici()).isEqualTo(UPDATED_SUPERFICI);
        assertThat(testParcelle.getTitreMere()).isEqualTo(UPDATED_TITRE_MERE);
        assertThat(testParcelle.getTitreCree()).isEqualTo(UPDATED_TITRE_CREE);
    }

    @Test
    @Transactional
    public void updateNonExistingParcelle() throws Exception {
        int databaseSizeBeforeUpdate = parcelleRepository.findAll().size();

        // Create the Parcelle
        ParcelleDTO parcelleDTO = parcelleMapper.toDto(parcelle);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restParcelleMockMvc.perform(put("/api/parcelles")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(parcelleDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Parcelle in the database
        List<Parcelle> parcelleList = parcelleRepository.findAll();
        assertThat(parcelleList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteParcelle() throws Exception {
        // Initialize the database
        parcelleRepository.saveAndFlush(parcelle);

        int databaseSizeBeforeDelete = parcelleRepository.findAll().size();

        // Delete the parcelle
        restParcelleMockMvc.perform(delete("/api/parcelles/{id}", parcelle.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Parcelle> parcelleList = parcelleRepository.findAll();
        assertThat(parcelleList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
