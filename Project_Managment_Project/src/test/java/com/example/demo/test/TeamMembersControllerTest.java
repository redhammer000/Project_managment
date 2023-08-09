package com.example.demo.test;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.example.project.controller.TeamMembersController;
import com.example.project.entity.Members;
import com.example.project.entity.Project;
import com.example.project.entity.Teams;
import com.example.project.entity.User;
import com.example.project.services.MemberService;
import com.example.project.services.TeamsService;
import com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith(MockitoExtension.class)
public class TeamMembersControllerTest {

    @Mock
    private TeamsService teamService;

    @Mock
    private MemberService memberService;

    @InjectMocks
    private TeamMembersController teamMembersController;

    private MockMvc mockMvc;

    @Test
    public void testGetTeams() throws Exception {
        List<Teams> teams = new ArrayList<>();
        // Populate the teams list with test data
        
        given(teamService.get_team_details()).willReturn(teams);

        mockMvc = MockMvcBuilders.standaloneSetup(teamMembersController).build();

        mockMvc.perform(get("/team/Get_Teams"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());
        // Add more assertions based on the response JSON
    }

    @Test
    public void testGetMembers() throws Exception {
        List<Members> members = new ArrayList<>();
        // Populate the members list with test data
        
        given(memberService.get_Members_details()).willReturn(members);

        mockMvc = MockMvcBuilders.standaloneSetup(teamMembersController).build();

        mockMvc.perform(get("/team/Get_members"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());
        // Add more assertions based on the response JSON
    }

    @Test
    public void testRegisterTeam() throws Exception {
        // Populate the test data
        Long teamId = 1L;
        String teamName = "Development Team";
        String teamDescription = "A team of skilled developers";
        Project projTeam = new Project();
        
        projTeam.setProjectId(1L);
        
        Teams team = new Teams(teamId, teamName, teamDescription, projTeam);

        // Set up the expected behavior of the teamService.Register_team() method
        doNothing().when(teamService).Register_team(any(Teams.class));

        mockMvc = MockMvcBuilders.standaloneSetup(teamMembersController).build();

        mockMvc.perform(post("/team/register_team")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(team)))
                .andExpect(status().isOk());

        // Verify that the teamService.Register_team() method was called with the expected team object
        verify(teamService).Register_team(any(Teams.class));
    }

    @Test
    public void testRegisterMember() throws Exception {
        // Populate the test data
        Long memberId = 1L;
        Date joiningDate = Date.valueOf("2023-08-01");
        String role = "Developer";
        Boolean isTeamLead = false;
        User userMember = new User();
        Teams teamMembers = new Teams();
        
        userMember.setUserId(1L);
        teamMembers.setTeamId(1L);
        
        Members member = new Members(memberId, joiningDate, role, isTeamLead, userMember, teamMembers);

        // Set up the expected behavior of the memberService.Register_member() method
        doNothing().when(memberService).Register_member(any(Members.class));

        mockMvc = MockMvcBuilders.standaloneSetup(teamMembersController).build();

        mockMvc.perform(post("/team/register_member")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(member)))
                .andExpect(status().isOk());

        // Verify that the memberService.Register_member() method was called with any instance of Members class
        verify(memberService).Register_member(any(Members.class));
    }
    
    @Test
    public void testUpdateTeam() throws Exception {
        Long teamId = 1L;
        String teamName = "Updated Team Name";
        String teamDescription = "Updated Team Description";
        // Other parameters
        
        mockMvc = MockMvcBuilders.standaloneSetup(teamMembersController).build();

        mockMvc.perform(patch("/team/update_team/{teamid}", teamId)
                .contentType(MediaType.APPLICATION_JSON)
                .param("TeamName", teamName)
                .param("TeamDiscription", teamDescription)
                )
                .andExpect(status().isOk());

        verify(teamService).updateTeam(eq(teamId), eq(teamName), eq(teamDescription), any());
    }

    @Test
    public void testUpdateMember() throws Exception {
        Long memberId = 1L;
        Date joiningDate = Date.valueOf("2023-08-01");
        Boolean isTeamLead = true;
       
        mockMvc = MockMvcBuilders.standaloneSetup(teamMembersController).build();
        
        mockMvc.perform(patch("/team/update_member/{memberid}", memberId)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("JoiningDate", "2023-08-01")
                .param("isTeamLead", "true")).andExpect(status().isOk());

        // Verify that the memberService.updatemember() method was called with the expected parameters
        verify(memberService).updatemember(
                eq(memberId),
                eq(joiningDate),
                eq(isTeamLead),any(),any()
        );
    }


    @Test
    public void testDeleteMember() throws Exception {
        Long memberId = 1L;

        mockMvc = MockMvcBuilders.standaloneSetup(teamMembersController).build();

        mockMvc.perform(delete("/team/deletemember/{memberid}", memberId))
                .andExpect(status().isOk());

        verify(memberService).deletemember(eq(memberId));
    }

    @Test
    public void testDeleteTeam() throws Exception {
        Long teamId = 1L;

        mockMvc = MockMvcBuilders.standaloneSetup(teamMembersController).build();

        mockMvc.perform(delete("/team/deleteTeam/{teamid}", teamId))
                .andExpect(status().isOk());

        verify(teamService).deleteteam(eq(teamId));
    }


    // Helper method to convert object to JSON string
    private static String asJsonString(Object obj) {
        try {
            final ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
