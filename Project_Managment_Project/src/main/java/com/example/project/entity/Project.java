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


	
	
	public Project(Long projectId, String projectName, String projectStatus, Date startDate, Date endDate,
			Long budget, String description, String client, String projectManager, Long branchNumber ) {
		super();
		ProjectId = projectId;
		ProjectName = projectName;
		ProjectStatus = projectStatus;
		ProjectStartDate = startDate;
		ProjectEndDate = endDate;
		Budget = budget;
		Description = description;
		this.client = client;
		ProjectManager = projectManager;
		
		Company comp = new Company();
		comp.setBranchNo(branchNumber);
		CompanyProject = comp;
		
	}




	public void setbranchNumber(Long branchNumber) {
		Company comp = new Company();
		comp.setBranchNo(branchNumber);
		CompanyProject = comp;
	}



	public Long getprojectId() {
		return ProjectId;
	}




	public void setprojectId(Long projectId) {
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




	public Date getstartDate() {
		return ProjectStartDate;
	}




	public void setstartDate(Date projectStartDate) {
		ProjectStartDate = projectStartDate;
	}




	public Date getendDate() {
		return ProjectEndDate;
	}




	public void setendDate(Date projectEndDate) {
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

	
	public Company getbranchNumber() {
		
		return CompanyProject;
	}

	@ManyToOne(targetEntity = Company.class)
	@JoinColumn(name = "BranchNo" , referencedColumnName = "BranchNo")
	private Company CompanyProject;
	
	
	
}
