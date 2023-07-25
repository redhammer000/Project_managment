package com.example.project.entity;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
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




	public Sprints(Long sprintId, String name, Date startDate, Date endDate, Project projectSprints) {
		super();
		SprintId = sprintId;
		Name = name;
		StartDate = startDate;
		EndDate = endDate;
		ProjectSprints = projectSprints;
	}




	public Long getSprintId() {
		return SprintId;
	}




	public void setSprintId(Long sprintId) {
		SprintId = sprintId;
	}




	public String getName() {
		return Name;
	}




	public void setName(String name) {
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




	@OneToOne(targetEntity = Project.class)
	@JoinColumn(name = "ProjectId" , referencedColumnName = "projectId")
    private Project ProjectSprints;
	
	
	
	
}
