package com.example.project.services;

import java.sql.Date;
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
	
	

	
	
	
	
	public ProjectService(ProjectRepository proj_repo, CompanyRepository comp_repo) {
		super();
		Proj_repo = proj_repo;
		this.comp_repo = comp_repo;
	}

	
	
	
	
	public List<Project> getprojdetails() {
		
		return Proj_repo.findAll();
	}

	
	
	
	
	public Optional<Project> getMyProjdetails(Long projectid) {
		
		return Proj_repo.findById(projectid);
	}

	
	
	
	
	
	public void Register_project(Project project) {
		
		project.setCompanyProject((comp_repo.findById(project.getCompanyProject().getBranch_no()).orElseThrow(() -> new IllegalStateException("Branch with id " + project.getCompanyProject().getBranch_no() + " does not exist"))));
		Proj_repo.save(project);
	}

	
	
	
	
	public void deleteProject(Long projectid) {
		
		Project project = Proj_repo.findById(projectid).orElseThrow(() -> new IllegalStateException("project with id " + projectid + " does not exist"));
		
		Proj_repo.delete(project);
		
	}


	
	
	
	@Transactional
	public void UpdateProject(Long projectid, String projectName, String projectStatus, Date projectStartDate,
			Date projectEndDate, Long budget, String description, String client, String projectManager,
			Company companyProject) {
		
		
		
		Project project = Proj_repo.findById(projectid).orElseThrow(() -> new IllegalStateException("project with id " + projectid + " does not exist"));
		
		if (companyProject != null)
			if(project.getCompanyProject().getBranch_no() != companyProject.getBranch_no())
				project.setCompanyProject(comp_repo.findById(companyProject.getBranch_no()).orElseThrow(() -> new IllegalStateException("branch with id " + companyProject.getBranch_no() + " does not exist")));
		
		if (projectName != null)
			project.setProjectName(projectName);
		if (projectStatus != null)
			project.setProjectStatus(projectStatus);
		if (projectStartDate != null)
			project.setProjectStartDate(projectStartDate);
		if (projectEndDate != null)
			project.setProjectEndDate(projectEndDate);
		if (budget != null)
			project.setBudget(budget);
		if (description != null)
			project.setDescription(description);
		if (client != null)
			project.setClient(client);
		if (projectManager != null)
			project.setProjectManager(projectManager);
		
	}
	
	
	
	

}
