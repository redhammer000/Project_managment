package com.example.project.entity;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity

@Table
public class Sprints {

	
	@Id
	
	@SequenceGenerator(
			name = "Sprints_sequence" , 
			sequenceName = "Sprints_sequence_name" , 
			allocationSize = 1 
			)
	@GeneratedValue(
			
			strategy = GenerationType.SEQUENCE,
			generator = "Sprints_sequence"
			
			)
	
	private Long SprintId;
	
	private String Name;
	
	private Date StartDate;
	
	private Date EndDate;
	
	public Sprints() {
		super();
	}




	public Sprints(String sprints_name, Date startDate, Date endDate, Long project_id) {
		super();
		Name = sprints_name;
		StartDate = startDate;
		EndDate = endDate;
		
		Project proj = new Project();
		
		proj.setprojectId(project_id);
		ProjectSprints = proj;
	}

	
	public void setproject_id(Long project_id)
	{
		Project proj = new Project();
		
		proj.setprojectId(project_id);
		ProjectSprints = proj;
	}
	

	public Long getSprintId() {
		return SprintId;
	}




	public void setSprintId(Long sprintId) {
		SprintId = sprintId;
	}








	public String getsprints_name() {
		return Name;
	}




	public void setsprints_name(String name) {
		Name = name;
	}




	public Date getStartDate() {
		return StartDate;
	}




	public void setStartDate(Date startDate) {
		StartDate = startDate;
	}




	public Date getEndDate() {
		return EndDate;
	}




	public void setEndDate(Date endDate) {
		EndDate = endDate;
	}




	public Project getProjectSprints() {
		return ProjectSprints;
	}




	public void setProjectSprints(Project projectSprints) {
		ProjectSprints = projectSprints;
	}


	public Long getproject_id()
	{
		return ProjectSprints.getprojectId();
	}

	@ManyToOne(targetEntity = Project.class)
	@JoinColumn(name = "ProjectId" , referencedColumnName = "projectId")
    private Project ProjectSprints;
	
	
	
	
}
