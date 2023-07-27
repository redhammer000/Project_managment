package com.example.project.entity;

import java.sql.Date;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table

@EntityListeners(AuditingEntityListener.class)
public class Project {

	@Id
	
	
	@SequenceGenerator(
			name = "Project_sequence" , 
			sequenceName = "Project_sequence" , 
			allocationSize = 1 
			)
	@GeneratedValue(
			
			strategy = GenerationType.SEQUENCE,
			generator = "Project_sequence"
			
			)
	
	private Long ProjectId;
	
	private String ProjectName;
	
	private String ProjectStatus;
	
	private Date ProjectStartDate;
	
	private Date ProjectEndDate;
	
	
	private Long Budget;
	
	private String Description;
	
	private String client;
	
	private String ProjectManager;

	
	
	public Project() {
		super();
	}


	
	
	public Project(Long projectId, String projectName, String projectStatus, Date projectStartDate, Date projectEndDate,
			Long budget, String description, String client, String projectManager, Company companyProject) {
		super();
		ProjectId = projectId;
		ProjectName = projectName;
		ProjectStatus = projectStatus;
		ProjectStartDate = projectStartDate;
		ProjectEndDate = projectEndDate;
		Budget = budget;
		Description = description;
		this.client = client;
		ProjectManager = projectManager;
		CompanyProject = companyProject;
	}




	public Long getProjectId() {
		return ProjectId;
	}




	public void setProjectId(Long projectId) {
		ProjectId = projectId;
	}




	public String getProjectName() {
		return ProjectName;
	}




	public void setProjectName(String projectName) {
		ProjectName = projectName;
	}




	public String getProjectStatus() {
		return ProjectStatus;
	}




	public void setProjectStatus(String projectStatus) {
		ProjectStatus = projectStatus;
	}




	public Date getProjectStartDate() {
		return ProjectStartDate;
	}




	public void setProjectStartDate(Date projectStartDate) {
		ProjectStartDate = projectStartDate;
	}




	public Date getProjectEndDate() {
		return ProjectEndDate;
	}




	public void setProjectEndDate(Date projectEndDate) {
		ProjectEndDate = projectEndDate;
	}




	public Long getBudget() {
		return Budget;
	}




	public void setBudget(Long budget) {
		Budget = budget;
	}




	public String getDescription() {
		return Description;
	}




	public void setDescription(String description) {
		Description = description;
	}




	public String getClient() {
		return client;
	}




	public void setClient(String client) {
		this.client = client;
	}




	public String getProjectManager() {
		return ProjectManager;
	}




	public void setProjectManager(String projectManager) {
		ProjectManager = projectManager;
	}




	public Company getCompanyProject() {
		return CompanyProject;
	}




	public void setCompanyProject(Company companyProject) {
		CompanyProject = companyProject;
	}




	@ManyToOne(targetEntity = Company.class)
	@JoinColumn(name = "BranchNo" , referencedColumnName = "BranchNo")
	private Company CompanyProject;
	
	
	
}
