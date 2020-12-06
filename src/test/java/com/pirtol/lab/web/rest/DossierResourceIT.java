package com.pirtol.lab.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.pirtol.lab.SenfoncierApp;
import com.pirtol.lab.domain.Dossier;
import com.pirtol.lab.repository.DossierRepository;
import com.pirtol.lab.service.DossierService;
import com.pirtol.lab.service.dto.DossierDTO;
import com.pirtol.lab.service.mapper.DossierMapper;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link DossierResource} REST controller.
 */
@SpringBootTest(classes = SenfoncierApp.class)
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@WithMockUser
public class DossierResourceIT {
    private static final String DEFAULT_NUMERO = "AAAAAAAAAA";
    private static final String UPDATED_NUMERO = "BBBBBBBBBB";

    private static final Double DEFAULT_MONTANT_LOYER = 1D;
    private static final Double UPDATED_MONTANT_LOYER = 2D;

    private static final Double DEFAULT_SUPERFICIE_BATIE = 1D;
    private static final Double UPDATED_SUPERFICIE_BATIE = 2D;

    private static final Double DEFAULT_COEFF_CORRECTIF_BATIE = 1D;
    private static final Double UPDATED_COEFF_CORRECTIF_BATIE = 2D;

    private static final Double DEFAULT_VALEUR_BATIE = 1D;
    private static final Double UPDATED_VALEUR_BATIE = 2D;

    private static final Double DEFAULT_LINEAIRE_CLOTURE = 1D;
    private static final Double UPDATED_LINEAIRE_CLOTURE = 2D;

    private static final Double DEFAULT_COEFF_CLOTURE = 1D;
    private static final Double UPDATED_COEFF_CLOTURE = 2D;

    private static final Double DEFAULT_VALEUR_CLOTURE = 1D;
    private static final Double UPDATED_VALEUR_CLOTURE = 2D;

    private static final String DEFAULT_AMENAGEMENT_SPACIAUX = "AAAAAAAAAA";
    private static final String UPDATED_AMENAGEMENT_SPACIAUX = "BBBBBBBBBB";

    private static final Double DEFAULT_VALEUR_AMENAGEMENT = 1D;
    private static final Double UPDATED_VALEUR_AMENAGEMENT = 2D;

    private static final Double DEFAULT_VALEUR_VENALE = 1D;
    private static final Double UPDATED_VALEUR_VENALE = 2D;

    private static final String DEFAULT_VALEUR_LOCATIV = "AAAAAAAAAA";
    private static final String UPDATED_VALEUR_LOCATIV = "BBBBBBBBBB";

    private static final String DEFAULT_PAYS_ADRESSE = "AAAAAAAAAA";
    private static final String UPDATED_PAYS_ADRESSE = "BBBBBBBBBB";

    private static final String DEFAULT_VILLE_ADRESSE = "AAAAAAAAAA";
    private static final String UPDATED_VILLE_ADRESSE = "BBBBBBBBBB";

    @Autowired
    private DossierRepository dossierRepository;

    @Mock
    private DossierRepository dossierRepositoryMock;

    @Autowired
    private DossierMapper dossierMapper;

    @Mock
    private DossierService dossierServiceMock;

    @Autowired
    private DossierService dossierService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restDossierMockMvc;

    private Dossier dossier;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Dossier createEntity(EntityManager em) {
        Dossier dossier = new Dossier()
            .numero(DEFAULT_NUMERO)
            .montantLoyer(DEFAULT_MONTANT_LOYER)
            .superficieBatie(DEFAULT_SUPERFICIE_BATIE)
            .coeffCorrectifBatie(DEFAULT_COEFF_CORRECTIF_BATIE)
            .valeurBatie(DEFAULT_VALEUR_BATIE)
            .lineaireCloture(DEFAULT_LINEAIRE_CLOTURE)
            .coeffCloture(DEFAULT_COEFF_CLOTURE)
            .valeurCloture(DEFAULT_VALEUR_CLOTURE)
            .amenagementSpaciaux(DEFAULT_AMENAGEMENT_SPACIAUX)
            .valeurAmenagement(DEFAULT_VALEUR_AMENAGEMENT)
            .valeurVenale(DEFAULT_VALEUR_VENALE)
            .valeurLocativ(DEFAULT_VALEUR_LOCATIV)
            .paysAdresse(DEFAULT_PAYS_ADRESSE)
            .villeAdresse(DEFAULT_VILLE_ADRESSE);
        return dossier;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Dossier createUpdatedEntity(EntityManager em) {
        Dossier dossier = new Dossier()
            .numero(UPDATED_NUMERO)
            .montantLoyer(UPDATED_MONTANT_LOYER)
            .superficieBatie(UPDATED_SUPERFICIE_BATIE)
            .coeffCorrectifBatie(UPDATED_COEFF_CORRECTIF_BATIE)
            .valeurBatie(UPDATED_VALEUR_BATIE)
            .lineaireCloture(UPDATED_LINEAIRE_CLOTURE)
            .coeffCloture(UPDATED_COEFF_CLOTURE)
            .valeurCloture(UPDATED_VALEUR_CLOTURE)
            .amenagementSpaciaux(UPDATED_AMENAGEMENT_SPACIAUX)
            .valeurAmenagement(UPDATED_VALEUR_AMENAGEMENT)
            .valeurVenale(UPDATED_VALEUR_VENALE)
            .valeurLocativ(UPDATED_VALEUR_LOCATIV)
            .paysAdresse(UPDATED_PAYS_ADRESSE)
            .villeAdresse(UPDATED_VILLE_ADRESSE);
        return dossier;
    }

    @BeforeEach
    public void initTest() {
        dossier = createEntity(em);
    }

    @Test
    @Transactional
    public void createDossier() throws Exception {
        int databaseSizeBeforeCreate = dossierRepository.findAll().size();
        // Create the Dossier
        DossierDTO dossierDTO = dossierMapper.toDto(dossier);
        restDossierMockMvc
            .perform(post("/api/dossiers").contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(dossierDTO)))
            .andExpect(status().isCreated());

        // Validate the Dossier in the database
        List<Dossier> dossierList = dossierRepository.findAll();
        assertThat(dossierList).hasSize(databaseSizeBeforeCreate + 1);
        Dossier testDossier = dossierList.get(dossierList.size() - 1);
        assertThat(testDossier.getNumero()).isEqualTo(DEFAULT_NUMERO);
        assertThat(testDossier.getMontantLoyer()).isEqualTo(DEFAULT_MONTANT_LOYER);
        assertThat(testDossier.getSuperficieBatie()).isEqualTo(DEFAULT_SUPERFICIE_BATIE);
        assertThat(testDossier.getCoeffCorrectifBatie()).isEqualTo(DEFAULT_COEFF_CORRECTIF_BATIE);
        assertThat(testDossier.getValeurBatie()).isEqualTo(DEFAULT_VALEUR_BATIE);
        assertThat(testDossier.getLineaireCloture()).isEqualTo(DEFAULT_LINEAIRE_CLOTURE);
        assertThat(testDossier.getCoeffCloture()).isEqualTo(DEFAULT_COEFF_CLOTURE);
        assertThat(testDossier.getValeurCloture()).isEqualTo(DEFAULT_VALEUR_CLOTURE);
        assertThat(testDossier.getAmenagementSpaciaux()).isEqualTo(DEFAULT_AMENAGEMENT_SPACIAUX);
        assertThat(testDossier.getValeurAmenagement()).isEqualTo(DEFAULT_VALEUR_AMENAGEMENT);
        assertThat(testDossier.getValeurVenale()).isEqualTo(DEFAULT_VALEUR_VENALE);
        assertThat(testDossier.getValeurLocativ()).isEqualTo(DEFAULT_VALEUR_LOCATIV);
        assertThat(testDossier.getPaysAdresse()).isEqualTo(DEFAULT_PAYS_ADRESSE);
        assertThat(testDossier.getVilleAdresse()).isEqualTo(DEFAULT_VILLE_ADRESSE);
    }

    @Test
    @Transactional
    public void createDossierWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = dossierRepository.findAll().size();

        // Create the Dossier with an existing ID
        dossier.setId(1L);
        DossierDTO dossierDTO = dossierMapper.toDto(dossier);

        // An entity with an existing ID cannot be created, so this API call must fail
        restDossierMockMvc
            .perform(post("/api/dossiers").contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(dossierDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Dossier in the database
        List<Dossier> dossierList = dossierRepository.findAll();
        assertThat(dossierList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllDossiers() throws Exception {
        // Initialize the database
        dossierRepository.saveAndFlush(dossier);

        // Get all the dossierList
        restDossierMockMvc
            .perform(get("/api/dossiers?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(dossier.getId().intValue())))
            .andExpect(jsonPath("$.[*].numero").value(hasItem(DEFAULT_NUMERO)))
            .andExpect(jsonPath("$.[*].montantLoyer").value(hasItem(DEFAULT_MONTANT_LOYER.doubleValue())))
            .andExpect(jsonPath("$.[*].superficieBatie").value(hasItem(DEFAULT_SUPERFICIE_BATIE.doubleValue())))
            .andExpect(jsonPath("$.[*].coeffCorrectifBatie").value(hasItem(DEFAULT_COEFF_CORRECTIF_BATIE.doubleValue())))
            .andExpect(jsonPath("$.[*].valeurBatie").value(hasItem(DEFAULT_VALEUR_BATIE.doubleValue())))
            .andExpect(jsonPath("$.[*].lineaireCloture").value(hasItem(DEFAULT_LINEAIRE_CLOTURE.doubleValue())))
            .andExpect(jsonPath("$.[*].coeffCloture").value(hasItem(DEFAULT_COEFF_CLOTURE.doubleValue())))
            .andExpect(jsonPath("$.[*].valeurCloture").value(hasItem(DEFAULT_VALEUR_CLOTURE.doubleValue())))
            .andExpect(jsonPath("$.[*].amenagementSpaciaux").value(hasItem(DEFAULT_AMENAGEMENT_SPACIAUX)))
            .andExpect(jsonPath("$.[*].valeurAmenagement").value(hasItem(DEFAULT_VALEUR_AMENAGEMENT.doubleValue())))
            .andExpect(jsonPath("$.[*].valeurVenale").value(hasItem(DEFAULT_VALEUR_VENALE.doubleValue())))
            .andExpect(jsonPath("$.[*].valeurLocativ").value(hasItem(DEFAULT_VALEUR_LOCATIV)))
            .andExpect(jsonPath("$.[*].paysAdresse").value(hasItem(DEFAULT_PAYS_ADRESSE)))
            .andExpect(jsonPath("$.[*].villeAdresse").value(hasItem(DEFAULT_VILLE_ADRESSE)));
    }

    @SuppressWarnings({ "unchecked" })
    public void getAllDossiersWithEagerRelationshipsIsEnabled() throws Exception {
        when(dossierServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        restDossierMockMvc.perform(get("/api/dossiers?eagerload=true")).andExpect(status().isOk());

        verify(dossierServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @SuppressWarnings({ "unchecked" })
    public void getAllDossiersWithEagerRelationshipsIsNotEnabled() throws Exception {
        when(dossierServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        restDossierMockMvc.perform(get("/api/dossiers?eagerload=true")).andExpect(status().isOk());

        verify(dossierServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @Test
    @Transactional
    public void getDossier() throws Exception {
        // Initialize the database
        dossierRepository.saveAndFlush(dossier);

        // Get the dossier
        restDossierMockMvc
            .perform(get("/api/dossiers/{id}", dossier.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(dossier.getId().intValue()))
            .andExpect(jsonPath("$.numero").value(DEFAULT_NUMERO))
            .andExpect(jsonPath("$.montantLoyer").value(DEFAULT_MONTANT_LOYER.doubleValue()))
            .andExpect(jsonPath("$.superficieBatie").value(DEFAULT_SUPERFICIE_BATIE.doubleValue()))
            .andExpect(jsonPath("$.coeffCorrectifBatie").value(DEFAULT_COEFF_CORRECTIF_BATIE.doubleValue()))
            .andExpect(jsonPath("$.valeurBatie").value(DEFAULT_VALEUR_BATIE.doubleValue()))
            .andExpect(jsonPath("$.lineaireCloture").value(DEFAULT_LINEAIRE_CLOTURE.doubleValue()))
            .andExpect(jsonPath("$.coeffCloture").value(DEFAULT_COEFF_CLOTURE.doubleValue()))
            .andExpect(jsonPath("$.valeurCloture").value(DEFAULT_VALEUR_CLOTURE.doubleValue()))
            .andExpect(jsonPath("$.amenagementSpaciaux").value(DEFAULT_AMENAGEMENT_SPACIAUX))
            .andExpect(jsonPath("$.valeurAmenagement").value(DEFAULT_VALEUR_AMENAGEMENT.doubleValue()))
            .andExpect(jsonPath("$.valeurVenale").value(DEFAULT_VALEUR_VENALE.doubleValue()))
            .andExpect(jsonPath("$.valeurLocativ").value(DEFAULT_VALEUR_LOCATIV))
            .andExpect(jsonPath("$.paysAdresse").value(DEFAULT_PAYS_ADRESSE))
            .andExpect(jsonPath("$.villeAdresse").value(DEFAULT_VILLE_ADRESSE));
    }

    @Test
    @Transactional
    public void getNonExistingDossier() throws Exception {
        // Get the dossier
        restDossierMockMvc.perform(get("/api/dossiers/{id}", Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateDossier() throws Exception {
        // Initialize the database
        dossierRepository.saveAndFlush(dossier);

        int databaseSizeBeforeUpdate = dossierRepository.findAll().size();

        // Update the dossier
        Dossier updatedDossier = dossierRepository.findById(dossier.getId()).get();
        // Disconnect from session so that the updates on updatedDossier are not directly saved in db
        em.detach(updatedDossier);
        updatedDossier
            .numero(UPDATED_NUMERO)
            .montantLoyer(UPDATED_MONTANT_LOYER)
            .superficieBatie(UPDATED_SUPERFICIE_BATIE)
            .coeffCorrectifBatie(UPDATED_COEFF_CORRECTIF_BATIE)
            .valeurBatie(UPDATED_VALEUR_BATIE)
            .lineaireCloture(UPDATED_LINEAIRE_CLOTURE)
            .coeffCloture(UPDATED_COEFF_CLOTURE)
            .valeurCloture(UPDATED_VALEUR_CLOTURE)
            .amenagementSpaciaux(UPDATED_AMENAGEMENT_SPACIAUX)
            .valeurAmenagement(UPDATED_VALEUR_AMENAGEMENT)
            .valeurVenale(UPDATED_VALEUR_VENALE)
            .valeurLocativ(UPDATED_VALEUR_LOCATIV)
            .paysAdresse(UPDATED_PAYS_ADRESSE)
            .villeAdresse(UPDATED_VILLE_ADRESSE);
        DossierDTO dossierDTO = dossierMapper.toDto(updatedDossier);

        restDossierMockMvc
            .perform(put("/api/dossiers").contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(dossierDTO)))
            .andExpect(status().isOk());

        // Validate the Dossier in the database
        List<Dossier> dossierList = dossierRepository.findAll();
        assertThat(dossierList).hasSize(databaseSizeBeforeUpdate);
        Dossier testDossier = dossierList.get(dossierList.size() - 1);
        assertThat(testDossier.getNumero()).isEqualTo(UPDATED_NUMERO);
        assertThat(testDossier.getMontantLoyer()).isEqualTo(UPDATED_MONTANT_LOYER);
        assertThat(testDossier.getSuperficieBatie()).isEqualTo(UPDATED_SUPERFICIE_BATIE);
        assertThat(testDossier.getCoeffCorrectifBatie()).isEqualTo(UPDATED_COEFF_CORRECTIF_BATIE);
        assertThat(testDossier.getValeurBatie()).isEqualTo(UPDATED_VALEUR_BATIE);
        assertThat(testDossier.getLineaireCloture()).isEqualTo(UPDATED_LINEAIRE_CLOTURE);
        assertThat(testDossier.getCoeffCloture()).isEqualTo(UPDATED_COEFF_CLOTURE);
        assertThat(testDossier.getValeurCloture()).isEqualTo(UPDATED_VALEUR_CLOTURE);
        assertThat(testDossier.getAmenagementSpaciaux()).isEqualTo(UPDATED_AMENAGEMENT_SPACIAUX);
        assertThat(testDossier.getValeurAmenagement()).isEqualTo(UPDATED_VALEUR_AMENAGEMENT);
        assertThat(testDossier.getValeurVenale()).isEqualTo(UPDATED_VALEUR_VENALE);
        assertThat(testDossier.getValeurLocativ()).isEqualTo(UPDATED_VALEUR_LOCATIV);
        assertThat(testDossier.getPaysAdresse()).isEqualTo(UPDATED_PAYS_ADRESSE);
        assertThat(testDossier.getVilleAdresse()).isEqualTo(UPDATED_VILLE_ADRESSE);
    }

    @Test
    @Transactional
    public void updateNonExistingDossier() throws Exception {
        int databaseSizeBeforeUpdate = dossierRepository.findAll().size();

        // Create the Dossier
        DossierDTO dossierDTO = dossierMapper.toDto(dossier);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDossierMockMvc
            .perform(put("/api/dossiers").contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(dossierDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Dossier in the database
        List<Dossier> dossierList = dossierRepository.findAll();
        assertThat(dossierList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteDossier() throws Exception {
        // Initialize the database
        dossierRepository.saveAndFlush(dossier);

        int databaseSizeBeforeDelete = dossierRepository.findAll().size();

        // Delete the dossier
        restDossierMockMvc
            .perform(delete("/api/dossiers/{id}", dossier.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Dossier> dossierList = dossierRepository.findAll();
        assertThat(dossierList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
