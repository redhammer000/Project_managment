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
	
	@GetMapping("get_Tasks_by_Sprint")
	public List<Object[]> getTasksBySprints(@RequestParam (required = true) Long Sprintid)
	{
		
		return TaskServe.getTasksBySprint(Sprintid);
		
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
	
	@PatchMapping(path = "update_Sprint/{sprintid}")
	
	public void updatesprint(
			@PathVariable ("sprintid") Long sprintid,
			@RequestParam (required = false) String name,
			@RequestParam (required = false) Date startDate,
			@RequestParam (required = false) Date endDate,
			@RequestParam (required = false) Project projectSprints
			)	 
	{
		
	SprintServ.updateSprint(sprintid,name,startDate,endDate,projectSprints);
		
	}
	
	
	@PatchMapping(path = "update_task/{taskid}")
	
	public void updatetask(
			@PathVariable ("taskid") Long taskid,
			@RequestParam (required = false) String taskTitle,
			@RequestParam (required = false)  String taskDiscription,
			@RequestParam (required = false) Date startDate,
			@RequestParam (required = false) Date endDate,
			@RequestParam (required = false) String taskStatus,
			@RequestParam (required = false) Sprints sprintTasks
			)	 
	{
		
		TaskServe.updateTask(taskid,taskTitle,taskDiscription,startDate,endDate,taskStatus,sprintTasks);
		
	}
	
}
