package com.example.project.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.project.entity.Project;
import com.example.project.entity.Teams;
import com.example.project.repository.*;

import jakarta.transaction.Transactional;

@Service
public class TeamsService {

	private final TeamsRepository teamrepo;
	
	private final ProjectRepository Projectrepo;
	
	public TeamsService(TeamsRepository teamrepo , ProjectRepository projectrepo) {
		super();
		this.teamrepo = teamrepo;
		this.Projectrepo = projectrepo;
	}

	
	
	
	
	
	public List<Teams> get_team_details() {
		
		return teamrepo.findAll();
	}

	
	
	
	
	
	public Teams get_My_team_details(Long TeamId) {
		
		Teams team = teamrepo.findById(TeamId).orElseThrow(() -> new IllegalStateException("Team with id " + TeamId + " does not exist"));
		return team;
	}

	
	
	
	
	public void Register_team(Teams team) {
		team.setProjTeam((Projectrepo.findById(team.getProjTeam().getprojectId()).orElseThrow(() -> new IllegalStateException("Branch with id " + team.getProjTeam().getprojectId() + " does not exist"))));
		
		teamrepo.save(team);
	}





	@Transactional
	public void updateTeam(Long teamid,String teamName, String teamDiscription, Long project_id) {
		
		    Teams team = teamrepo.findById(teamid).orElseThrow(() -> new IllegalStateException("Team with id " + teamid + " does not exist"));
		    
		    
		    if (project_id != null)
		    {
		    Project projTeam = Projectrepo.findById(project_id).get();
		    if(projTeam != null)
				if (projTeam.getprojectId() != team.getProjTeam().getprojectId())
				{
					team.setProjTeam(Projectrepo.findById(team.getProjTeam().getprojectId()).orElseThrow(() -> new IllegalStateException("Branch with id " + team.getProjTeam().getprojectId() + " does not exist")));
				}
		    }
		    
			if (teamName != null)
				team.setteam_name(teamName);
			if (teamDiscription != null)
				team.setteam_description(teamDiscription);
			
	}






	public void deleteteam(Long teamid) {
		
		Teams team = teamrepo.findById(teamid).orElseThrow(() -> new IllegalStateException("Team with id " + teamid + " does not exist"));
		
		teamrepo.delete(team);
		
	}
	
	@Transactional
	public void increment(Long teamid)
	{
		Teams team = teamrepo.findById(teamid).orElseThrow(() -> new IllegalStateException("Team with id " + teamid + " does not exist"));
		team.setMembersCount(team.incrementMembersCount());
	}
	
	@Transactional
	public void decremental(Long teamid)
	{
		Teams team = teamrepo.findById(teamid).orElseThrow(() -> new IllegalStateException("Team with id " + teamid + " does not exist"));
		team.setMembersCount(team.decrementMembersCount());
	}






	public List<Teams> get_My_team_details_for_the_project(Long projId) {
		List<Teams> team = teamrepo.findByProjectid(projId);
		
		return team;
		
	}

	
	
}
