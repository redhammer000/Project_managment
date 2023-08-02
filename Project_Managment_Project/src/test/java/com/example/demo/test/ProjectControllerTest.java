package com.example.demo.test;

import com.example.project.controller.ProjectController;
import com.example.project.entity.Company;
import com.example.project.entity.Project;
import com.example.project.services.AuditService;
import com.example.project.services.ProjectService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

public class ProjectControllerTest {

    @Mock
    private ProjectService projectService;

    @Mock
    private AuditService auditService;

    @InjectMocks
    private ProjectController projectController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

	@Test
    @Autowired
    public void testGetProjects() {
        // Given
    	
    	
    	Company Comp = new Company(); 
    	Comp.setBranch_no(1L);
    	
    	Company Comp_2 = new Company(); 
    	Comp_2.setBranch_no(2L);

    	
        List<Project> projects = Arrays.asList(
                new Project(1L, "Project 1", "In Progress", Date.valueOf("2023-08-01"), Date.valueOf("2023-12-31"),
                        500000L, "Description 1", "ClientA", "ManagerA", Comp),
                new Project(2L, "Project 2", "Completed", Date.valueOf("2023-09-01"), Date.valueOf("2023-11-30"),
                        300000L, "Description 2", "ClientB", "ManagerB", Comp_2)
                );
        when(projectService.getprojdetails()).thenReturn(projects);

        // When
        List<Project> result = projectController.getProjects();

        // Then
        verify(projectService, times(1)).getprojdetails();
        // Additional assertions on the result if needed
    }

	@Test
    public void testGetMyProject() {
        // Given
    	
    	Company Comp = new Company(); 
    	Comp.setBranch_no(1L);
    	
        long projectId = 1L;
        Project project = new Project(projectId, "Project 1", "In Progress", Date.valueOf("2023-08-01"),
                Date.valueOf("2023-12-31"), 500000L, "Description 1", "ClientA", "ManagerA",Comp );
        when(projectService.getMyProjdetails(projectId)).thenReturn(Optional.of(project));

        // When
        Optional<Project> result = projectController.getMyProject(projectId);

        // Then
        verify(projectService, times(1)).getMyProjdetails(projectId);
        // Additional assertions on the result if needed
    }

    @Test
    public void testRegisterProject() {
        // Given
        Project project = new Project();
        project.setProjectName("New Project");
        project.setProjectStatus("In Progress");

        // When
        projectController.register_project(project);

        // Then
        verify(projectService, times(1)).Register_project(project);
        verify(auditService, times(1)).logAudit(any()); // Verify that auditService.logAudit() was called with the appropriate audit log details
    }

    @Test
    public void testDeleteProject() {
        // Given
        long projectId = 1L;

        // When
        projectController.deleteproject(projectId);

        // Then
        verify(projectService, times(1)).deleteProject(projectId);
        verify(auditService, times(1)).logAudit(any()); // Verify that auditService.logAudit() was called with the appropriate audit log details
    }

    @Test
    public void testUpdateProject() {
        // Given
        long projectId = 1L;
        String projectName = "Updated Project";
        // Set other attributes as needed

        // When
        projectController.updateProject(projectId, projectName,null,null,null,null,null,null,null,null);

        // Then
        verify(projectService, times(1)).UpdateProject(projectId, projectName,null,null,null,null,null,null,null,null);
    }
}
