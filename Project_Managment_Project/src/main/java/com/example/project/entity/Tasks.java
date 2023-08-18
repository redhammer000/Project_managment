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





	public Tasks(Long taskId, String task_title, String task_description, Date startDate, Date endDate, String taskStatus,
			Long sprint_id) {
		super();
		TaskTitle = task_title;
		TaskDiscription = task_description;
		StartDate = startDate;
		EndDate = endDate;
		TaskStatus = taskStatus;
		
		Sprints sprint = new Sprints();
		
		sprint.setSprintId(sprint_id);
		sprinttasks = sprint;
	}


	
	public void setsprint_id(Long sprint_id)
	{
		
		
		Sprints sprint = new Sprints();
		
		sprint.setSprintId(sprint_id);
		sprinttasks = sprint;
		
	}
	
	public Long getsprint_id()
	{
		
		return sprinttasks.getSprintId();
		
	}

	public Long gettaskId() {
		return TaskId;
	}





	public void settaskId(Long taskId) {
		TaskId = taskId;
	}





	public String gettask_title() {
		return TaskTitle;
	}





	public void settask_title(String taskTitle) {
		TaskTitle = taskTitle;
	}





	public String gettask_description() {
		return TaskDiscription;
	}





	public void settask_description(String taskDiscription) {
		TaskDiscription = taskDiscription;
	}





	public Date getstartDate() {
		return StartDate;
	}





	public void setstartDate(Date startDate) {
		StartDate = startDate;
	}





	public Date getendDate() {
		return EndDate;
	}





	public void setendDate(Date endDate) {
		EndDate = endDate;
	}





	public String gettaskStatus() {
		return TaskStatus;
	}





	public void settaskStatus(String taskStatus) {
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
