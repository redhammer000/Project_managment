package com.example.project.controller;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

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
import com.example.project.services.AuditService;
import com.example.project.services.ProjectService;


@RestController
@RequestMapping("project")
public class ProjectController {

	
	private final ProjectService ProjService;
	
	private final AuditService auditLogService;
	
	
	public ProjectController(ProjectService proj_service , AuditService auditLogService) {
		super();
		ProjService = proj_service;
		this.auditLogService = auditLogService;
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
		 
		    AuditLog auditLog = new AuditLog();
		    auditLog.setCreatedAt(LocalDateTime.now());
		    auditLog.setAction("CREATED");
		    auditLog.setCreatedBy("some_user");
		    auditLog.setEntityId(project.getprojectId());
		    auditLog.setEntityName("Project");
		    auditLog.setFieldName("ALL");

		    auditLogService.logAudit(auditLog);
		
		
		
	}
	
	@DeleteMapping(path = "deleteproject/{id}")
	
	public void deleteproject (@PathVariable ("id") Long projectid)
	{
		ProjService.deleteProject(projectid);
		AuditLog auditLog = new AuditLog();
	    auditLog.setAction("Deleted");
	    auditLog.setDeletedBy("some_user");
	    auditLog.setEntityId(projectid);
	    auditLog.setEntityName("Project");
	    auditLog.setFieldName("ALL");

	    auditLogService.logAudit(auditLog);
	
	}
	
	
	@PatchMapping(path = "update/{id}")
	
	public void updateProject(
			@PathVariable ("id") Long projectid,
			@RequestParam (required = false) String projectName,
			@RequestParam (required = false) String projectStatus,
			@RequestParam (required = false) Date projectStartDate,
			@RequestParam (required = false) Date projectEndDate,
			@RequestParam (required = false) Long budget,
			@RequestParam (required = false) String description,
			@RequestParam (required = false) String client,
			@RequestParam (required = false)  String projectManager,
			@RequestParam (required = false) Long branchNumber
			)	 
	{
		
		ProjService.UpdateProject(projectid,projectName,projectStatus,projectStartDate,projectEndDate,budget,description,client,projectManager,branchNumber);
	
		
	}
}
