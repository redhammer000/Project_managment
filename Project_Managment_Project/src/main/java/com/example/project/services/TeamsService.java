package com.example.project.services;

import java.util.List;

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
		team.setProjTeam((Projectrepo.findById(team.getProjTeam().getProjectId()).orElseThrow(() -> new IllegalStateException("Branch with id " + team.getProjTeam().getProjectId() + " does not exist"))));
		
		teamrepo.save(team);
	}





	@Transactional
	public void updateTeam(Long teamid,String teamName, String teamDiscription, Project projTeam) {
		
		    Teams team = teamrepo.findById(teamid).orElseThrow(() -> new IllegalStateException("Team with id " + teamid + " does not exist"));
		    
		    if(projTeam != null)
				if (projTeam.getProjectId() != team.getProjTeam().getProjectId())
				{
					team.setProjTeam(Projectrepo.findById(team.getProjTeam().getProjectId()).orElseThrow(() -> new IllegalStateException("Branch with id " + team.getProjTeam().getProjectId() + " does not exist")));
				}
		    
		    
			if (teamName != null)
				team.setTeamName(teamName);
			if (teamDiscription != null)
				team.setTeamDiscription(teamDiscription);
			
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

	
	
}
