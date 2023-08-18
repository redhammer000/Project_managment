package com.example.project.controller;

import java.sql.Date;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.project.entity.*;
import com.example.project.services.*;

@RestController

@RequestMapping("sprint")
public class SprintTaskController {

	private final SprintsService SprintServ;
	private final TaskService TaskServe;

	
	
	
	


	public SprintTaskController(SprintsService sprintServ, TaskService taskServe) {
		super();
		SprintServ = sprintServ;
		TaskServe = taskServe;
	}


	@GetMapping("get_sprints")
	public List<Sprints> getSprints()
	{
		
		return SprintServ.getsprintdetails();
		
	}
	
	
	@GetMapping("get_My_sprints")
	public Sprints getMySprints(@RequestParam (required = true) Long Sprintid)
	{
		
		return SprintServ.getMysprintdetails(Sprintid);
		
	}
	
	
	@GetMapping("gettask")
	public List<Tasks> gettasks()
	{
		
		return TaskServe.getMytasks();
		
	}
	
	
	@GetMapping("get_My_task")
	public Tasks get_My_tasks(@RequestParam (required = true) Long taskId)
	{
		
		return TaskServe.gettasks(taskId);
		
	}
	
	@GetMapping("get_Tasks_by_Sprint")
	public List<Tasks> getTasksBySprints(@RequestParam (required = true) Long Sprintid)
	{
		
		return TaskServe.getTasksBySprint(Sprintid);
		
	}
	
	@GetMapping("get_Sprints_by_Projects")
	public List<Sprints> getSprintsByProjects(@RequestParam (required = true) Long project_id)
	{
		
		return SprintServ.getSprintsByProjects(project_id);
		
	}
	
	@PostMapping("Register_sprint")
	public void register_Sprint(@RequestBody Sprints sprint)
	{
		
		SprintServ.Register_Sprint(sprint);
		
	}
	
	
	@PostMapping("Register_task")
	public void register_task(@RequestBody Tasks task)
	{
		
		TaskServe.TaskServe(task);
		
	}
	
	@DeleteMapping(path = "deletesprint/{sprintid}")
	
	public void deleteSprint (@PathVariable ("sprintid") Long sprintid)
	{
		SprintServ.deletesprint(sprintid);
	
	}
	
	@DeleteMapping(path = "deletetask/{taskid}")
	
	public void deleteTask (@PathVariable ("taskid") Long taskid)
	{
		TaskServe.deleteTask(taskid);
	
	}
	
	@PatchMapping(path = "update_Sprint/{id}")
	
	public void updatesprint(
	        @PathVariable("id") Long sprintid,
	        @RequestBody Sprints sprint
	) {
		
	String sprints_name	= sprint.getsprints_name();
	Date startDate = sprint.getStartDate();
	Date endDate = sprint.getEndDate();
	Long project_id = sprint.getproject_id();
	
	
	        SprintServ.updateSprint(sprintid, sprints_name, startDate, endDate, project_id);

	}
	
	
	@PatchMapping(path = "update_task/{taskid}")
	
	public void updatetask(
			@PathVariable ("taskid") Long taskid,
			@RequestBody Tasks task
			)	 
	{
		
		
		String taskTitle = task.gettask_title();
		String taskDiscription = task.gettask_description();
		Date startDate = task.getstartDate();
		Date endDate = task.getendDate();
		String taskStatus = task.gettaskStatus();
		Long sprint_id = task.getsprint_id();
		
		
		TaskServe.updateTask(taskid,taskTitle,taskDiscription,startDate,endDate,taskStatus,sprint_id);
		
	}
	
}
