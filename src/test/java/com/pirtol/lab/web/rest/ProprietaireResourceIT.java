package com.pirtol.lab.web.rest;

import com.pirtol.lab.SenfoncierApp;
import com.pirtol.lab.domain.Proprietaire;
import com.pirtol.lab.repository.ProprietaireRepository;
import com.pirtol.lab.service.ProprietaireService;
import com.pirtol.lab.service.dto.ProprietaireDTO;
import com.pirtol.lab.service.mapper.ProprietaireMapper;

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
 * Integration tests for the {@link ProprietaireResource} REST controller.
 */
@SpringBootTest(classes = SenfoncierApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class ProprietaireResourceIT {

    private static final String DEFAULT_PRENOM = "AAAAAAAAAA";
    private static final String UPDATED_PRENOM = "BBBBBBBBBB";

    private static final String DEFAULT_NOM = "AAAAAAAAAA";
    private static final String UPDATED_NOM = "BBBBBBBBBB";

    private static final String DEFAULT_RAISON_SOCIAL = "AAAAAAAAAA";
    private static final String UPDATED_RAISON_SOCIAL = "BBBBBBBBBB";

    private static final Boolean DEFAULT_PERSONNE_MORALE = false;
    private static final Boolean UPDATED_PERSONNE_MORALE = true;

    private static final String DEFAULT_LIEU_NAISSANCE = "AAAAAAAAAA";
    private static final String UPDATED_LIEU_NAISSANCE = "BBBBBBBBBB";

    private static final String DEFAULT_NUM_CN_II = "AAAAAAAAAA";
    private static final String UPDATED_NUM_CN_II = "BBBBBBBBBB";

    private static final String DEFAULT_NINEA = "AAAAAAAAAA";
    private static final String UPDATED_NINEA = "BBBBBBBBBB";

    private static final String DEFAULT_ADRESSE = "AAAAAAAAAA";
    private static final String UPDATED_ADRESSE = "BBBBBBBBBB";

    private static final String DEFAULT_TELEPHONE = "AAAAAAAAAA";
    private static final String UPDATED_TELEPHONE = "BBBBBBBBBB";

    private static final String DEFAULT_TELEPHONE_2 = "AAAAAAAAAA";
    private static final String UPDATED_TELEPHONE_2 = "BBBBBBBBBB";

    private static final String DEFAULT_TELEPHONE_3 = "AAAAAAAAAA";
    private static final String UPDATED_TELEPHONE_3 = "BBBBBBBBBB";

    private static final String DEFAULT_AQUISITION = "AAAAAAAAAA";
    private static final String UPDATED_AQUISITION = "BBBBBBBBBB";

    @Autowired
    private ProprietaireRepository proprietaireRepository;

    @Autowired
    private ProprietaireMapper proprietaireMapper;

    @Autowired
    private ProprietaireService proprietaireService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restProprietaireMockMvc;

    private Proprietaire proprietaire;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Proprietaire createEntity(EntityManager em) {
        Proprietaire proprietaire = new Proprietaire()
            .prenom(DEFAULT_PRENOM)
            .nom(DEFAULT_NOM)
            .raisonSocial(DEFAULT_RAISON_SOCIAL)
            .personneMorale(DEFAULT_PERSONNE_MORALE)
            .lieuNaissance(DEFAULT_LIEU_NAISSANCE)
            .numCNIi(DEFAULT_NUM_CN_II)
            .ninea(DEFAULT_NINEA)
            .adresse(DEFAULT_ADRESSE)
            .telephone(DEFAULT_TELEPHONE)
            .telephone2(DEFAULT_TELEPHONE_2)
            .telephone3(DEFAULT_TELEPHONE_3)
            .aquisition(DEFAULT_AQUISITION);
        return proprietaire;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Proprietaire createUpdatedEntity(EntityManager em) {
        Proprietaire proprietaire = new Proprietaire()
            .prenom(UPDATED_PRENOM)
            .nom(UPDATED_NOM)
            .raisonSocial(UPDATED_RAISON_SOCIAL)
            .personneMorale(UPDATED_PERSONNE_MORALE)
            .lieuNaissance(UPDATED_LIEU_NAISSANCE)
            .numCNIi(UPDATED_NUM_CN_II)
            .ninea(UPDATED_NINEA)
            .adresse(UPDATED_ADRESSE)
            .telephone(UPDATED_TELEPHONE)
            .telephone2(UPDATED_TELEPHONE_2)
            .telephone3(UPDATED_TELEPHONE_3)
            .aquisition(UPDATED_AQUISITION);
        return proprietaire;
    }

    @BeforeEach
    public void initTest() {
        proprietaire = createEntity(em);
    }

    @Test
    @Transactional
    public void createProprietaire() throws Exception {
        int databaseSizeBeforeCreate = proprietaireRepository.findAll().size();
        // Create the Proprietaire
        ProprietaireDTO proprietaireDTO = proprietaireMapper.toDto(proprietaire);
        restProprietaireMockMvc.perform(post("/api/proprietaires")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(proprietaireDTO)))
            .andExpect(status().isCreated());

        // Validate the Proprietaire in the database
        List<Proprietaire> proprietaireList = proprietaireRepository.findAll();
        assertThat(proprietaireList).hasSize(databaseSizeBeforeCreate + 1);
        Proprietaire testProprietaire = proprietaireList.get(proprietaireList.size() - 1);
        assertThat(testProprietaire.getPrenom()).isEqualTo(DEFAULT_PRENOM);
        assertThat(testProprietaire.getNom()).isEqualTo(DEFAULT_NOM);
        assertThat(testProprietaire.getRaisonSocial()).isEqualTo(DEFAULT_RAISON_SOCIAL);
        assertThat(testProprietaire.isPersonneMorale()).isEqualTo(DEFAULT_PERSONNE_MORALE);
        assertThat(testProprietaire.getLieuNaissance()).isEqualTo(DEFAULT_LIEU_NAISSANCE);
        assertThat(testProprietaire.getNumCNIi()).isEqualTo(DEFAULT_NUM_CN_II);
        assertThat(testProprietaire.getNinea()).isEqualTo(DEFAULT_NINEA);
        assertThat(testProprietaire.getAdresse()).isEqualTo(DEFAULT_ADRESSE);
        assertThat(testProprietaire.getTelephone()).isEqualTo(DEFAULT_TELEPHONE);
        assertThat(testProprietaire.getTelephone2()).isEqualTo(DEFAULT_TELEPHONE_2);
        assertThat(testProprietaire.getTelephone3()).isEqualTo(DEFAULT_TELEPHONE_3);
        assertThat(testProprietaire.getAquisition()).isEqualTo(DEFAULT_AQUISITION);
    }

    @Test
    @Transactional
    public void createProprietaireWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = proprietaireRepository.findAll().size();

        // Create the Proprietaire with an existing ID
        proprietaire.setId(1L);
        ProprietaireDTO proprietaireDTO = proprietaireMapper.toDto(proprietaire);

        // An entity with an existing ID cannot be created, so this API call must fail
        restProprietaireMockMvc.perform(post("/api/proprietaires")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(proprietaireDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Proprietaire in the database
        List<Proprietaire> proprietaireList = proprietaireRepository.findAll();
        assertThat(proprietaireList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllProprietaires() throws Exception {
        // Initialize the database
        proprietaireRepository.saveAndFlush(proprietaire);

        // Get all the proprietaireList
        restProprietaireMockMvc.perform(get("/api/proprietaires?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(proprietaire.getId().intValue())))
            .andExpect(jsonPath("$.[*].prenom").value(hasItem(DEFAULT_PRENOM)))
            .andExpect(jsonPath("$.[*].nom").value(hasItem(DEFAULT_NOM)))
            .andExpect(jsonPath("$.[*].raisonSocial").value(hasItem(DEFAULT_RAISON_SOCIAL)))
            .andExpect(jsonPath("$.[*].personneMorale").value(hasItem(DEFAULT_PERSONNE_MORALE.booleanValue())))
            .andExpect(jsonPath("$.[*].lieuNaissance").value(hasItem(DEFAULT_LIEU_NAISSANCE)))
            .andExpect(jsonPath("$.[*].numCNIi").value(hasItem(DEFAULT_NUM_CN_II)))
            .andExpect(jsonPath("$.[*].ninea").value(hasItem(DEFAULT_NINEA)))
            .andExpect(jsonPath("$.[*].adresse").value(hasItem(DEFAULT_ADRESSE)))
            .andExpect(jsonPath("$.[*].telephone").value(hasItem(DEFAULT_TELEPHONE)))
            .andExpect(jsonPath("$.[*].telephone2").value(hasItem(DEFAULT_TELEPHONE_2)))
            .andExpect(jsonPath("$.[*].telephone3").value(hasItem(DEFAULT_TELEPHONE_3)))
            .andExpect(jsonPath("$.[*].aquisition").value(hasItem(DEFAULT_AQUISITION)));
    }
    
    @Test
    @Transactional
    public void getProprietaire() throws Exception {
        // Initialize the database
        proprietaireRepository.saveAndFlush(proprietaire);

        // Get the proprietaire
        restProprietaireMockMvc.perform(get("/api/proprietaires/{id}", proprietaire.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(proprietaire.getId().intValue()))
            .andExpect(jsonPath("$.prenom").value(DEFAULT_PRENOM))
            .andExpect(jsonPath("$.nom").value(DEFAULT_NOM))
            .andExpect(jsonPath("$.raisonSocial").value(DEFAULT_RAISON_SOCIAL))
            .andExpect(jsonPath("$.personneMorale").value(DEFAULT_PERSONNE_MORALE.booleanValue()))
            .andExpect(jsonPath("$.lieuNaissance").value(DEFAULT_LIEU_NAISSANCE))
            .andExpect(jsonPath("$.numCNIi").value(DEFAULT_NUM_CN_II))
            .andExpect(jsonPath("$.ninea").value(DEFAULT_NINEA))
            .andExpect(jsonPath("$.adresse").value(DEFAULT_ADRESSE))
            .andExpect(jsonPath("$.telephone").value(DEFAULT_TELEPHONE))
            .andExpect(jsonPath("$.telephone2").value(DEFAULT_TELEPHONE_2))
            .andExpect(jsonPath("$.telephone3").value(DEFAULT_TELEPHONE_3))
            .andExpect(jsonPath("$.aquisition").value(DEFAULT_AQUISITION));
    }
    @Test
    @Transactional
    public void getNonExistingProprietaire() throws Exception {
        // Get the proprietaire
        restProprietaireMockMvc.perform(get("/api/proprietaires/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateProprietaire() throws Exception {
        // Initialize the database
        proprietaireRepository.saveAndFlush(proprietaire);

        int databaseSizeBeforeUpdate = proprietaireRepository.findAll().size();

        // Update the proprietaire
        Proprietaire updatedProprietaire = proprietaireRepository.findById(proprietaire.getId()).get();
        // Disconnect from session so that the updates on updatedProprietaire are not directly saved in db
        em.detach(updatedProprietaire);
        updatedProprietaire
            .prenom(UPDATED_PRENOM)
            .nom(UPDATED_NOM)
            .raisonSocial(UPDATED_RAISON_SOCIAL)
            .personneMorale(UPDATED_PERSONNE_MORALE)
            .lieuNaissance(UPDATED_LIEU_NAISSANCE)
            .numCNIi(UPDATED_NUM_CN_II)
            .ninea(UPDATED_NINEA)
            .adresse(UPDATED_ADRESSE)
            .telephone(UPDATED_TELEPHONE)
            .telephone2(UPDATED_TELEPHONE_2)
            .telephone3(UPDATED_TELEPHONE_3)
            .aquisition(UPDATED_AQUISITION);
        ProprietaireDTO proprietaireDTO = proprietaireMapper.toDto(updatedProprietaire);

        restProprietaireMockMvc.perform(put("/api/proprietaires")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(proprietaireDTO)))
            .andExpect(status().isOk());

        // Validate the Proprietaire in the database
        List<Proprietaire> proprietaireList = proprietaireRepository.findAll();
        assertThat(proprietaireList).hasSize(databaseSizeBeforeUpdate);
        Proprietaire testProprietaire = proprietaireList.get(proprietaireList.size() - 1);
        assertThat(testProprietaire.getPrenom()).isEqualTo(UPDATED_PRENOM);
        assertThat(testProprietaire.getNom()).isEqualTo(UPDATED_NOM);
        assertThat(testProprietaire.getRaisonSocial()).isEqualTo(UPDATED_RAISON_SOCIAL);
        assertThat(testProprietaire.isPersonneMorale()).isEqualTo(UPDATED_PERSONNE_MORALE);
        assertThat(testProprietaire.getLieuNaissance()).isEqualTo(UPDATED_LIEU_NAISSANCE);
        assertThat(testProprietaire.getNumCNIi()).isEqualTo(UPDATED_NUM_CN_II);
        assertThat(testProprietaire.getNinea()).isEqualTo(UPDATED_NINEA);
        assertThat(testProprietaire.getAdresse()).isEqualTo(UPDATED_ADRESSE);
        assertThat(testProprietaire.getTelephone()).isEqualTo(UPDATED_TELEPHONE);
        assertThat(testProprietaire.getTelephone2()).isEqualTo(UPDATED_TELEPHONE_2);
        assertThat(testProprietaire.getTelephone3()).isEqualTo(UPDATED_TELEPHONE_3);
        assertThat(testProprietaire.getAquisition()).isEqualTo(UPDATED_AQUISITION);
    }

    @Test
    @Transactional
    public void updateNonExistingProprietaire() throws Exception {
        int databaseSizeBeforeUpdate = proprietaireRepository.findAll().size();

        // Create the Proprietaire
        ProprietaireDTO proprietaireDTO = proprietaireMapper.toDto(proprietaire);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restProprietaireMockMvc.perform(put("/api/proprietaires")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(proprietaireDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Proprietaire in the database
        List<Proprietaire> proprietaireList = proprietaireRepository.findAll();
        assertThat(proprietaireList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteProprietaire() throws Exception {
        // Initialize the database
        proprietaireRepository.saveAndFlush(proprietaire);

        int databaseSizeBeforeDelete = proprietaireRepository.findAll().size();

        // Delete the proprietaire
        restProprietaireMockMvc.perform(delete("/api/proprietaires/{id}", proprietaire.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Proprietaire> proprietaireList = proprietaireRepository.findAll();
        assertThat(proprietaireList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
