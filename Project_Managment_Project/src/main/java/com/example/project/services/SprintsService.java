package com.example.project.services;

import java.sql.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.project.entity.*;
import com.example.project.repository.*;

import jakarta.transaction.Transactional;

@Service
public class SprintsService {
	
	
	private final SprintsRepository SprintRepo;

	private final ProjectRepository projRepo;
	
	public SprintsService(SprintsRepository sprintRepo, ProjectRepository projRepo) {
		super();
		SprintRepo = sprintRepo;
		this.projRepo = projRepo;
	}

	
	public List<Sprints> getsprintdetails() {
		
		return SprintRepo.findAll();
		
	}

	public Sprints getMysprintdetails(Long sprintid) {
		
		Sprints sprint = SprintRepo.findById(sprintid).orElseThrow(() -> new IllegalStateException("user with id " + sprintid + " does not exist"));
		return sprint;
	}


	public void Register_Sprint(Sprints sprint) {
		
		sprint.setProjectSprints(projRepo.findById(sprint.getProjectSprints().getprojectId()).orElseThrow(() -> new IllegalStateException("project with id " + sprint.getProjectSprints().getprojectId() + " does not exist")));
		
		SprintRepo.save(sprint);
	
	}


	public void deletesprint(Long sprintid) {
		
		Sprints sprint = SprintRepo.findById(sprintid).orElseThrow(() -> new IllegalStateException("sprint with id " + sprintid + " does not exist"));
		
		SprintRepo.delete(sprint);
		
	}


	
	
	@Transactional
	public void updateSprint(Long sprintid, String name, Date startDate, Date endDate, Long project_id) {
		
		Sprints sprint = SprintRepo.findById(sprintid).orElseThrow(() -> new IllegalStateException("sprint with id " + sprintid + " does not exist"));
		
		if (project_id != null)
		{
			Project projectSprints = projRepo.findById(project_id).get();
		
			if(projectSprints != null)
				if (projectSprints.getprojectId() != sprint.getProjectSprints().getprojectId())
					{
						sprint.setProjectSprints(projRepo.findById(projectSprints.getprojectId()).orElseThrow(() -> new IllegalStateException("Project with id " + projectSprints.getprojectId() + " does not exist")));
					}
		
		}
		
		

		if (name != null)
			sprint.setsprints_name(name);
		if (startDate != null)
			sprint.setStartDate(startDate);
		if (endDate != null)
			sprint.setEndDate(endDate);

		
	}


	public List<Sprints> getSprintsByProjects(Long projectId) {
		
		return SprintRepo.findByProjectSprintsProjectId(projectId);
		
	}


	
	
	
	
	
	
}
