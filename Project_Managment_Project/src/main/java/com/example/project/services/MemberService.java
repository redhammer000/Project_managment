package com.example.project.services;

import java.sql.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.project.entity.Members;
import com.example.project.entity.Tasks;
import com.example.project.entity.Teams;
import com.example.project.entity.User;
import com.example.project.repository.*;

import jakarta.transaction.Transactional;

@Service

public class MemberService {
	
	
	private final MembersRepository memberRepo;
	
	private final TasksRepository TaskRepo;
	
	private final TeamsRepository TeamsRepo;
	
	private final UserRepository userRepo;
	
	private final TeamsService Teamserv;


	public MemberService(MembersRepository memberRepo, TasksRepository taskRepo, TeamsRepository teamsRepo,
			UserRepository userRepo , TeamsService Teamserv) {
		super();
		this.memberRepo = memberRepo;
		TaskRepo = taskRepo;
		TeamsRepo = teamsRepo;
		this.userRepo = userRepo;
		this.Teamserv = Teamserv;
	}




	public void Register_member(Members member) {
		
		member.setUserMember((userRepo.findById(member.getUserMember().getUserId()).orElseThrow(() -> new IllegalStateException("User with id " + member.getUserMember().getUserId() + " does not exist"))));
		
		member.setTeamMembers((TeamsRepo.findById(member.getTeamMembers().getTeamId()).orElseThrow(() -> new IllegalStateException("Team with id " + member.getTeamMembers().getTeamId() + " does not exist"))));
		
		member.setTask((TaskRepo.findById(member.getTask().getTaskId()).orElseThrow(() -> new IllegalStateException("Task with id " + member.getTask().getTaskId() + " does not exist"))));
		
		Teamserv.increment(member.getTeamMembers().getTeamId());
		memberRepo.save(member);
		
		
	}




	public List<Members> get_Members_details() {
		
		return memberRepo.findAll();
		
	}




	public Members get_My_Members_details(Long memberid) {
		
		Members member = memberRepo.findById(memberid).orElseThrow(() -> new IllegalStateException("Team with id " + memberid + " does not exist"));
		return member;
		
	}



	@Transactional
	public void updatemember(Long memberid, Date joiningDate, String role, Boolean isTeamLead, Tasks task,
			User userMember, Teams teamMembers) 
	
	{
		Members member= memberRepo.findById(memberid).orElseThrow(() -> new IllegalStateException("Team with id " + memberid + " does not exist"));
		
		if (userMember != null)
		if ( userMember.getUserId() != member.getUserMember().getUserId())
		member.setUserMember((userRepo.findById(member.getUserMember().getUserId()).orElseThrow(() -> new IllegalStateException("Branch with id " + member.getUserMember().getUserId() + " does not exist"))));
		
		
		if (teamMembers != null)
		if (teamMembers.getTeamId() != member.getTeamMembers().getTeamId())
		member.setTeamMembers((TeamsRepo.findById(member.getTeamMembers().getTeamId()).orElseThrow(() -> new IllegalStateException("Branch with id " + member.getTeamMembers().getTeamId() + " does not exist"))));
		
		
		
		if (task != null)
		if (task.getTaskId() != member.getTask().getTaskId())
		member.setTask((TaskRepo.findById(member.getTask().getTaskId()).orElseThrow(() -> new IllegalStateException("Branch with id " + member.getTask().getTaskId() + " does not exist"))));
		
			if (joiningDate != null)
			member.setJoiningDate(joiningDate);
			if (role != null)
			member.setRole(role);
			if (isTeamLead != null)
			member.setIsTeamLead(isTeamLead);

		
	}




	public void deletemember(Long memberid) {
			
		Members member = memberRepo.findById(memberid).orElseThrow(() -> new IllegalStateException("Team with id " + memberid + " does not exist"));
		
		Teamserv.decremental(member.getTeamMembers().getTeamId());
		memberRepo.delete(member);
		
	}
	
	

}
