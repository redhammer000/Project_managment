package com.example.project.services;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.project.entity.*;
import com.example.project.repository.*;

import jakarta.transaction.Transactional;

@Service
public class ProjectService {

	
	private final ProjectRepository Proj_repo;
	
	private final CompanyRepository comp_repo;
	
	private final AuditService auditLogService;

	
	
	
	
	public ProjectService(ProjectRepository proj_repo, CompanyRepository comp_repo , AuditService auditLogService) {
		super();
		Proj_repo = proj_repo;
		this.comp_repo = comp_repo;
		this.auditLogService = auditLogService;
	}

	
	
	
	
	public List<Project> getprojdetails() {
		
		return Proj_repo.findAll();
	}

	
	
	
	
	public Optional<Project> getMyProjdetails(Long projectid) {
		
		return Proj_repo.findById(projectid);
	}

	
	
	
	
	
	public void Register_project(Project project) {
		
		project.setCompanyProject((comp_repo.findById(project.getCompanyProject().getBranchNo()).orElseThrow(() -> new IllegalStateException("Branch with id " + project.getCompanyProject().getBranchNo() + " does not exist"))));
		Proj_repo.save(project);
	}

	
	
	
	
	public void deleteProject(Long projectid) {
		
		Project project = Proj_repo.findById(projectid).orElseThrow(() -> new IllegalStateException("project with id " + projectid + " does not exist"));
		
		Proj_repo.delete(project);
		
	}


	
	
	
	@Transactional
	public void UpdateProject(Long projectid, String projectName, String projectStatus, Date projectStartDate,
			Date projectEndDate, Long budget, String description, String client, String projectManager,
			Long Branchno) {
		
		
		Project project = Proj_repo.findById(projectid).orElseThrow(() -> new IllegalStateException("project with id " + projectid + " does not exist"));
		
		if(Branchno != null)
		{
		Company companyProject = new Company();
		companyProject.setBranchNo(Branchno);
		
		
		
		
		if (companyProject != null)
			if(project.getCompanyProject().getBranchNo() != companyProject.getBranchNo())
			{
				auditLogService.set_audit_values("UPDATE", "SomeUser", projectid , "Project", "BranchId" , String.valueOf(companyProject.getBranchNo()) , String.valueOf(project.getCompanyProject().getBranchNo()));
				project.setCompanyProject(comp_repo.findById(companyProject.getBranchNo()).orElseThrow(() -> new IllegalStateException("branch with id " + companyProject.getBranchNo() + " does not exist")));
			}
		
		}
		
		if (projectName != null)
		{
			
			auditLogService.set_audit_values("UPDATE", "SomeUser", projectid , "Project", "ProjectName" , String.valueOf(projectName) , String.valueOf(project.getProjectName()));
			project.setProjectName(projectName);
		}
		if (projectStatus != null)
		{
			auditLogService.set_audit_values("UPDATE", "SomeUser", projectid , "Project", "ProjectStatus" , String.valueOf(projectStatus) , String.valueOf(project.getProjectStatus()));
			project.setProjectStatus(projectStatus);
			
		}
		if (projectStartDate != null)
		{
			auditLogService.set_audit_values("UPDATE", "SomeUser", projectid , "Project", "ProjectStartDate" , String.valueOf(projectStartDate) , String.valueOf(project.getstartDate()));
			project.setstartDate(projectStartDate);
		}
		if (projectEndDate != null)
		{
			
			auditLogService.set_audit_values("UPDATE", "SomeUser", projectid , "Project", "ProjectEndDate" , String.valueOf(projectEndDate) , String.valueOf(project.getendDate()));
			project.setendDate(projectEndDate);
		}
		if (budget != null)
		{
			auditLogService.set_audit_values("UPDATE", "SomeUser", projectid , "Project", "Budget" , String.valueOf(budget) , String.valueOf(project.getBudget()));
			project.setBudget(budget);
		}	
		if (description != null)
		{
			auditLogService.set_audit_values("UPDATE", "SomeUser", projectid , "Project", "Description" , String.valueOf(description) , String.valueOf(project.getDescription()));
			project.setDescription(description);
		}
		if (client != null)
		{
			auditLogService.set_audit_values("UPDATE", "SomeUser", projectid , "Project", "Client" , String.valueOf(client) , String.valueOf(project.getClient()));
			project.setClient(client);
		}
		if (projectManager != null)
		{
			auditLogService.set_audit_values("UPDATE", "SomeUser", projectid , "Project", "Client" , String.valueOf(projectManager) , String.valueOf(project.getProjectManager()));
			project.setProjectManager(projectManager);
		}
	}
	
	
	
	

}
