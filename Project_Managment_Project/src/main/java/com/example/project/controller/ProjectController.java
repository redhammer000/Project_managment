package com.example.project.controller;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.project.entity.*;
import com.example.project.services.ProjectService;


@RestController
@RequestMapping("project")
public class ProjectController {

	
	private final ProjectService ProjService;
	
	public ProjectController(ProjectService proj_service) {
		super();
		ProjService = proj_service;
	}
	
	
	@GetMapping("Display_Projects")
	public List<Project> getProjects()
	{
		
		return ProjService.getprojdetails();
		
	}
	
	
	@GetMapping("Display_My_Projects")
	public Optional<Project> getMyProject(Long projectid)
	{
		
		return ProjService.getMyProjdetails(projectid);
		
	}
	
	@PostMapping("Register_Project")
	public void register_project(@RequestBody Project project)
	{
		
		ProjService.Register_project(project);
		
	}
	
	@DeleteMapping(path = "deleteproject/{projectid}")
	
	public void deleteproject (@PathVariable ("projectid") Long projectid)
	{
		ProjService.deleteProject(projectid);
	
	}
	
	
	@PutMapping(path = "update/{projectid}")
	
	public void updateProject(
			@PathVariable ("projectid") Long projectid,
			@RequestParam (required = false) String projectName,
			@RequestParam (required = false) String projectStatus,
			@RequestParam (required = false) Date projectStartDate,
			@RequestParam (required = false) Date projectEndDate,
			@RequestParam (required = false) Long budget,
			@RequestParam (required = false) String description,
			@RequestParam (required = false) String client,
			@RequestParam (required = false)  String projectManager,
			@RequestParam (required = false) Company companyProject
			)	 
	{
		
		ProjService.UpdateProject(projectid,projectName,projectStatus,projectStartDate,projectEndDate,budget,description,client,projectManager,companyProject);
		
	}
}
