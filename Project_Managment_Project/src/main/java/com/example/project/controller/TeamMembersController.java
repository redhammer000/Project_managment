package com.example.project.controller;

import java.sql.Date;
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
import com.example.project.services.*;

@RestController
@RequestMapping("team")
public class TeamMembersController {

	private final TeamsService  TeamServ; 
	private final MemberService membersServ;
	
	
	public TeamMembersController(TeamsService teamServ, MemberService membersServ) {
		super();
		TeamServ = teamServ;
		
		this.membersServ = membersServ;
	}
	
	
	
	@GetMapping("Get_Teams")
	public List<Teams> getTeams()
	{
		return TeamServ.get_team_details();
		
	}
	
	
	
	@GetMapping("Get_MyTeam")
	public Teams getMyTeam(@RequestParam (required = true) Long TeamId)
	{
		return TeamServ.get_My_team_details(TeamId);
		
	}
	
	
	@GetMapping("Get_proj_team")
	public List<Teams> getMyprojTeam(@RequestParam (required = true) Long project_id)
	{
		return TeamServ.get_My_team_details_for_the_project(project_id);
		
	}
	
	
	@GetMapping("Get_members")
	public List<Members> getMembers()
	{
		return membersServ.get_Members_details();
		
	}
	
	
	@GetMapping("Get_My_member")
	public Members get_My_Members(@RequestParam (required = true) Long memberid)
	{
		return membersServ.get_My_Members_details(memberid);
		
	}
	
	
	@PostMapping("register_team")
	public void Register_team(@RequestBody Teams team) {
		
		TeamServ.Register_team(team);
		
	}
	
	@PostMapping("register_member")
	public void Register_team(@RequestBody Members member) {
		
		membersServ.Register_member(member);
		
	}
	
	@PatchMapping(path = "update_team/{teamid}")
	public void updateTeam(
			@PathVariable ("teamid") Long teamid,
			@RequestBody Teams team
			)	 
	{
		
		String TeamName = team.getteam_name() ;
		String TeamDiscription = team.getteam_description();  
		Long projectid = team.getproject_id(); 
		TeamServ.updateTeam(teamid,TeamName,TeamDiscription,projectid);
		
	}
	
	
	@PatchMapping(path = "update_member/{memberid}")
	public void updatemember(
			@PathVariable ("memberid") Long memberid,
			@RequestParam (required = false) Date JoiningDate,
			@RequestParam (required = false) Boolean isTeamLead,
			@RequestParam (required = false) User userMember,
			@RequestParam (required = false) Teams teamMembers
			)	 
	{
		
		membersServ.updatemember(memberid,JoiningDate,isTeamLead,userMember,teamMembers);
		
	}
	
	
	@DeleteMapping(path = "deletemember/{memberid}")
	
	public void deletemember (@PathVariable ("memberid") Long memberid)
	{
		membersServ.deletemember(memberid);
	
	}
	
	@DeleteMapping(path = "deleteTeam/{teamid}")
	
	public void deleteteam (@PathVariable ("teamid") Long teamid)
	{
		TeamServ.deleteteam(teamid);
	
	}
}
