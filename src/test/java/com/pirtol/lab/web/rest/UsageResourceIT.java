package com.pirtol.lab.web.rest;

import com.pirtol.lab.SenfoncierApp;
import com.pirtol.lab.domain.Usage;
import com.pirtol.lab.repository.UsageRepository;
import com.pirtol.lab.service.UsageService;
import com.pirtol.lab.service.dto.UsageDTO;
import com.pirtol.lab.service.mapper.UsageMapper;

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
 * Integration tests for the {@link UsageResource} REST controller.
 */
@SpringBootTest(classes = SenfoncierApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class UsageResourceIT {

    private static final String DEFAULT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_LIBELLE = "AAAAAAAAAA";
    private static final String UPDATED_LIBELLE = "BBBBBBBBBB";

    @Autowired
    private UsageRepository usageRepository;

    @Autowired
    private UsageMapper usageMapper;

    @Autowired
    private UsageService usageService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restUsageMockMvc;

    private Usage usage;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Usage createEntity(EntityManager em) {
        Usage usage = new Usage()
            .code(DEFAULT_CODE)
            .libelle(DEFAULT_LIBELLE);
        return usage;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Usage createUpdatedEntity(EntityManager em) {
        Usage usage = new Usage()
            .code(UPDATED_CODE)
            .libelle(UPDATED_LIBELLE);
        return usage;
    }

    @BeforeEach
    public void initTest() {
        usage = createEntity(em);
    }

    @Test
    @Transactional
    public void createUsage() throws Exception {
        int databaseSizeBeforeCreate = usageRepository.findAll().size();
        // Create the Usage
        UsageDTO usageDTO = usageMapper.toDto(usage);
        restUsageMockMvc.perform(post("/api/usages")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(usageDTO)))
            .andExpect(status().isCreated());

        // Validate the Usage in the database
        List<Usage> usageList = usageRepository.findAll();
        assertThat(usageList).hasSize(databaseSizeBeforeCreate + 1);
        Usage testUsage = usageList.get(usageList.size() - 1);
        assertThat(testUsage.getCode()).isEqualTo(DEFAULT_CODE);
        assertThat(testUsage.getLibelle()).isEqualTo(DEFAULT_LIBELLE);
    }

    @Test
    @Transactional
    public void createUsageWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = usageRepository.findAll().size();

        // Create the Usage with an existing ID
        usage.setId(1L);
        UsageDTO usageDTO = usageMapper.toDto(usage);

        // An entity with an existing ID cannot be created, so this API call must fail
        restUsageMockMvc.perform(post("/api/usages")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(usageDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Usage in the database
        List<Usage> usageList = usageRepository.findAll();
        assertThat(usageList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllUsages() throws Exception {
        // Initialize the database
        usageRepository.saveAndFlush(usage);

        // Get all the usageList
        restUsageMockMvc.perform(get("/api/usages?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(usage.getId().intValue())))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE)))
            .andExpect(jsonPath("$.[*].libelle").value(hasItem(DEFAULT_LIBELLE)));
    }
    
    @Test
    @Transactional
    public void getUsage() throws Exception {
        // Initialize the database
        usageRepository.saveAndFlush(usage);

        // Get the usage
        restUsageMockMvc.perform(get("/api/usages/{id}", usage.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(usage.getId().intValue()))
            .andExpect(jsonPath("$.code").value(DEFAULT_CODE))
            .andExpect(jsonPath("$.libelle").value(DEFAULT_LIBELLE));
    }
    @Test
    @Transactional
    public void getNonExistingUsage() throws Exception {
        // Get the usage
        restUsageMockMvc.perform(get("/api/usages/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateUsage() throws Exception {
        // Initialize the database
        usageRepository.saveAndFlush(usage);

        int databaseSizeBeforeUpdate = usageRepository.findAll().size();

        // Update the usage
        Usage updatedUsage = usageRepository.findById(usage.getId()).get();
        // Disconnect from session so that the updates on updatedUsage are not directly saved in db
        em.detach(updatedUsage);
        updatedUsage
            .code(UPDATED_CODE)
            .libelle(UPDATED_LIBELLE);
        UsageDTO usageDTO = usageMapper.toDto(updatedUsage);

        restUsageMockMvc.perform(put("/api/usages")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(usageDTO)))
            .andExpect(status().isOk());

        // Validate the Usage in the database
        List<Usage> usageList = usageRepository.findAll();
        assertThat(usageList).hasSize(databaseSizeBeforeUpdate);
        Usage testUsage = usageList.get(usageList.size() - 1);
        assertThat(testUsage.getCode()).isEqualTo(UPDATED_CODE);
        assertThat(testUsage.getLibelle()).isEqualTo(UPDATED_LIBELLE);
    }

    @Test
    @Transactional
    public void updateNonExistingUsage() throws Exception {
        int databaseSizeBeforeUpdate = usageRepository.findAll().size();

        // Create the Usage
        UsageDTO usageDTO = usageMapper.toDto(usage);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restUsageMockMvc.perform(put("/api/usages")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(usageDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Usage in the database
        List<Usage> usageList = usageRepository.findAll();
        assertThat(usageList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteUsage() throws Exception {
        // Initialize the database
        usageRepository.saveAndFlush(usage);

        int databaseSizeBeforeDelete = usageRepository.findAll().size();

        // Delete the usage
        restUsageMockMvc.perform(delete("/api/usages/{id}", usage.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Usage> usageList = usageRepository.findAll();
        assertThat(usageList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
