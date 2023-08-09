package com.example.demo.test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.example.project.controller.CompanyController;
import com.example.project.entity.Company;
import com.example.project.services.CompanyService;

import jakarta.xml.bind.ValidationException;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith(MockitoExtension.class)
public class CompanyControllerTest {

    @Mock
    private CompanyService companyService;

    @InjectMocks
    private CompanyController companyController;

    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(companyController).build();
    }

    @Test
    public void testGetBranches() throws Exception {
        // Given
        Company company1 = new Company("Branch 1", "Location 1", "Industry 1");
        Company company2 = new Company("Branch 2", "Location 2", "Industry 2");
        List<Company> companies = Arrays.asList(company1, company2);
        BDDMockito.given(companyService.getcompdetails()).willReturn(companies);

        // When
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/company/Display_branches"))
                .andReturn();

        // Then
        verify(companyService, times(1)).getcompdetails();
        // Convert response JSON to a list of Company objects
        ObjectMapper objectMapper = new ObjectMapper();
        List<Company> responseCompanies = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), new TypeReference<List<Company>>() {});

        // Check if the responseCompanies list contains the correct number of companies
        assertThat(responseCompanies).hasSize(2);

        // Check properties of the first company
        Company responseCompany1 = responseCompanies.get(0);
        assertThat(responseCompany1.getBranch_name()).isEqualTo("Branch 1");
        assertThat(responseCompany1.getBranch_location()).isEqualTo("Location 1");
        assertThat(responseCompany1.getBranch_industry()).isEqualTo("Industry 1");

        // Check properties of the second company
        Company responseCompany2 = responseCompanies.get(1);
        assertThat(responseCompany2.getBranch_name()).isEqualTo("Branch 2");
        assertThat(responseCompany2.getBranch_location()).isEqualTo("Location 2");
        assertThat(responseCompany2.getBranch_industry()).isEqualTo("Industry 2");
    }

    @Test
    public void testRegister_Branch() throws Exception {
        // Given
        Company companyToSave = new Company("New Branch", "New Location", "New Industry");
        
        // Mock the add_new_branch() method to do nothing (void method)
        doNothing().when(companyService).add_new_branch(any(Company.class));

        // When
        ObjectMapper objectMapper = new ObjectMapper();
        String companyToSaveJson = objectMapper.writeValueAsString(companyToSave);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/company/save_branch")
                .contentType("application/json")
                .content(companyToSaveJson))
                .andExpect(status().isOk()) // Expecting a status code of 200 (OK) since the method returns void
                .andReturn();

        // Then
        then(companyService).should().add_new_branch(any(Company.class));

        // Additional assertions on the result if needed
        int statusCode = mvcResult.getResponse().getStatus();
        assertThat(statusCode).isEqualTo(200);

        // Verify that the add_new_branch() method was called exactly once with the correct Company object as an argument
        ArgumentCaptor<Company> companyCaptor = ArgumentCaptor.forClass(Company.class);
        verify(companyService, times(1)).add_new_branch(companyCaptor.capture());
        Company capturedCompany = companyCaptor.getValue();
        assertThat(capturedCompany.getBranch_name()).isEqualTo("New Branch");
        assertThat(capturedCompany.getBranch_location()).isEqualTo("New Location");
        assertThat(capturedCompany.getBranch_industry()).isEqualTo("New Industry");
    }



}
