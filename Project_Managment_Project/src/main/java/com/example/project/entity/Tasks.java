package com.example.project.entity;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table
public class Tasks {
	
	@Id
	
	@SequenceGenerator(
			name = "Tasks_sequence" , 
			sequenceName = "Tasks_sequence_name" , 
			allocationSize = 1 
			)
	@GeneratedValue(
			
			strategy = GenerationType.SEQUENCE,
			generator = "Tasks_sequence"
			
			)
	
	private Long TaskId;
	
	private String TaskTitle;
	
	private String TaskDiscription;
	
	private Date StartDate;
	
	private Date EndDate;
	
	private String TaskStatus;


	
	
	
	public Tasks() {
		super();
	}





	public Tasks(Long taskId, String taskTitle, String taskDiscription, Date startDate, Date endDate, String taskStatus,
			Sprints sprintTasks) {
		super();
		TaskId = taskId;
		TaskTitle = taskTitle;
		TaskDiscription = taskDiscription;
		StartDate = startDate;
		EndDate = endDate;
		TaskStatus = taskStatus;
		sprinttasks = sprintTasks;
	}





	public Long getTaskId() {
		return TaskId;
	}





	public void setTaskId(Long taskId) {
		TaskId = taskId;
	}





	public String getTaskTitle() {
		return TaskTitle;
	}





	public void setTaskTitle(String taskTitle) {
		TaskTitle = taskTitle;
	}





	public String getTaskDiscription() {
		return TaskDiscription;
	}





	public void setTaskDiscription(String taskDiscription) {
		TaskDiscription = taskDiscription;
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





	public String getTaskStatus() {
		return TaskStatus;
	}





	public void setTaskStatus(String taskStatus) {
		TaskStatus = taskStatus;
	}





	public Sprints getSprintTasks() {
		return sprinttasks;
	}





	public void setSprintTasks(Sprints sprintTasks) {
		sprinttasks = sprintTasks;
	}





	@ManyToOne(targetEntity = Sprints.class)
	@JoinColumn(name = "SprintId" , referencedColumnName = "sprintId")
	private Sprints sprinttasks;
	
}
