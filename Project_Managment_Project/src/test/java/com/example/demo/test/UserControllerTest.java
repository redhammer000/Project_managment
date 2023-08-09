
package com.example.demo.test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.example.project.controller.UserController;
import com.example.project.entity.Company;
import com.example.project.entity.User;
import com.example.project.services.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {

    private MockMvc mockMvc;

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    private User sampleUser;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();

        Company  comp =   new Company();
        
        comp.setBranch_no(1L);
        // Sample user for testing
        sampleUser = new User(1L, "John", "Doe", "john.doe@example.com", "1234567890",
                "Manager", "123 Main St", "Sales", "Male", true,comp);
    }

    @Test
    public void testRegisterUser() throws Exception {
        // Given
    	doNothing().when(userService).Register_user(any(User.class));

        // When
        ObjectMapper objectMapper = new ObjectMapper();
        String userJson = objectMapper.writeValueAsString(sampleUser);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/user/Register_User")
                .contentType(MediaType.APPLICATION_JSON)
                .content(userJson))
                .andReturn();

        // Then
        verify(userService, times(1)).Register_user(any(User.class));

        // Additional assertions on the result if needed
        int statusCode = mvcResult.getResponse().getStatus();
        assertThat(statusCode).isEqualTo(200); // Assuming the status code is 200 for a successful registration
    }

    @Test
    public void testDeleteUser() throws Exception {
        // When
        mockMvc.perform(MockMvcRequestBuilders.delete("/user/deleteuser/1"))
                .andReturn();

        // Then
        verify(userService, times(1)).deleteUser(1L);
    }

    @Test
    public void testUpdateUser() throws Exception {
    	
    	ArgumentCaptor<Long> userIdCaptor = ArgumentCaptor.forClass(Long.class);
        ArgumentCaptor<String> firstNameCaptor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<String> lastNameCaptor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<String> emailCaptor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<String> phoneNumberCaptor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<String> positionCaptor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<String> addressCaptor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<String> departmentCaptor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<String> genderCaptor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<Company> companyCaptor = ArgumentCaptor.forClass(Company.class);
        
        doNothing().when(userService).updateUser(
                userIdCaptor.capture(),
                firstNameCaptor.capture(),
                lastNameCaptor.capture(),
                emailCaptor.capture(),
                phoneNumberCaptor.capture(),
                positionCaptor.capture(),
                addressCaptor.capture(),
                departmentCaptor.capture(),
                genderCaptor.capture(),
                companyCaptor.capture()
        );
        // When
        ObjectMapper objectMapper = new ObjectMapper();
        String userJson = objectMapper.writeValueAsString(sampleUser);

        mockMvc.perform(MockMvcRequestBuilders.patch("/user/update/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(userJson))
                .andReturn();
        
        verify(userService, times(1)).updateUser(
                userIdCaptor.getValue(),
                firstNameCaptor.getValue(),
                lastNameCaptor.getValue(),
                emailCaptor.getValue(),
                phoneNumberCaptor.getValue(),
                positionCaptor.getValue(),
                addressCaptor.getValue(),
                departmentCaptor.getValue(),
                genderCaptor.getValue(),
                companyCaptor.getValue()
        );
    }

    @Test
    public void testAdmUpdateUser() throws Exception {
        // Given
        ArgumentCaptor<Long> userIdCaptor = ArgumentCaptor.forClass(Long.class);
        ArgumentCaptor<String> firstNameCaptor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<String> lastNameCaptor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<String> emailCaptor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<String> phoneNumberCaptor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<String> positionCaptor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<String> addressCaptor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<String> departmentCaptor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<String> genderCaptor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<Boolean> isAdminCaptor = ArgumentCaptor.forClass(Boolean.class);
        ArgumentCaptor<Company> companyCaptor = ArgumentCaptor.forClass(Company.class);

        doNothing().when(userService).adm_updateUser(
                userIdCaptor.capture(),
                firstNameCaptor.capture(),
                lastNameCaptor.capture(),
                emailCaptor.capture(),
                phoneNumberCaptor.capture(),
                positionCaptor.capture(),
                addressCaptor.capture(),
                departmentCaptor.capture(),
                genderCaptor.capture(),
                isAdminCaptor.capture(),
                companyCaptor.capture()
        );

        // When
        ObjectMapper objectMapper = new ObjectMapper();
        String userJson = objectMapper.writeValueAsString(sampleUser);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.patch("/user/adm_update/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(userJson))
                .andReturn();

        // Then
        verify(userService, times(1)).adm_updateUser(
                userIdCaptor.getValue(),
                firstNameCaptor.getValue(),
                lastNameCaptor.getValue(),
                emailCaptor.getValue(),
                phoneNumberCaptor.getValue(),
                positionCaptor.getValue(),
                addressCaptor.getValue(),
                departmentCaptor.getValue(),
                genderCaptor.getValue(),
                isAdminCaptor.getValue(),
                companyCaptor.getValue()
        );

        // Additional assertions on the result if needed
        int statusCode = mvcResult.getResponse().getStatus();
        assertThat(statusCode).isEqualTo(200); // Assuming the status code is 200 for a successful update
    }
}

