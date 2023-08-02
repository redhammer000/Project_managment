package com.example.project.services;

import java.sql.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.project.entity.CompositeKey;
import com.example.project.entity.MemberTask;
import com.example.project.entity.Members;
import com.example.project.entity.Tasks;
import com.example.project.entity.Teams;
import com.example.project.entity.User;
import com.example.project.repository.*;

import jakarta.transaction.Transactional;

@Service

public class MemberService {
	
	
	private final MembersRepository memberRepo;	
	
	private final TeamsRepository TeamsRepo;
	
	private final UserRepository userRepo;
	
	private final TeamsService Teamserv;

	private final TasksRepository taskrepo;
	
	private final MemberTasksRepository MemberTasksrepo;
	
	public MemberService(MembersRepository memberRepo, TeamsRepository teamsRepo,
			UserRepository userRepo , TeamsService Teamserv , TasksRepository taskrepo , MemberTasksRepository MemberTasksrepo) {
		super();
		this.memberRepo = memberRepo;
		TeamsRepo = teamsRepo;
		this.userRepo = userRepo;
		this.Teamserv = Teamserv;
		this.taskrepo = taskrepo;
		this.MemberTasksrepo  = MemberTasksrepo;
	}




	public void Register_member(Members member) {
		
		member.setUserMember((userRepo.findById(member.getUserMember().getUserId()).orElseThrow(() -> new IllegalStateException("User with id " + member.getUserMember().getUserId() + " does not exist"))));
		
		member.setTeamMembers((TeamsRepo.findById(member.getTeamMembers().getTeamId()).orElseThrow(() -> new IllegalStateException("Team with id " + member.getTeamMembers().getTeamId() + " does not exist"))));
		
		
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
	public void updatemember(Long memberid, Date joiningDate, Boolean isTeamLead,User userMember, Teams teamMembers) 
	
	{
		Members member= memberRepo.findById(memberid).orElseThrow(() -> new IllegalStateException("Member with id " + memberid + " does not exist"));
		
		if (userMember != null)
		if ( userMember.getUserId() != member.getUserMember().getUserId())
		member.setUserMember((userRepo.findById(member.getUserMember().getUserId()).orElseThrow(() -> new IllegalStateException("user with id " + member.getUserMember().getUserId() + " does not exist"))));
		
		
		if (teamMembers != null)
		if (teamMembers.getTeamId() != member.getTeamMembers().getTeamId())
		member.setTeamMembers((TeamsRepo.findById(member.getTeamMembers().getTeamId()).orElseThrow(() -> new IllegalStateException("Team with id" + member.getTeamMembers().getTeamId() + " does not exist"))));
		
		if (joiningDate != null)
			member.setJoiningDate(joiningDate);
		if (isTeamLead != null)
			member.setIsTeamLead(isTeamLead);

		
	}




	public void deletemember(Long memberid) {
			
		Members member = memberRepo.findById(memberid).orElseThrow(() -> new IllegalStateException("Member with id " + memberid + " does not exist"));
		
		Teamserv.decremental(member.getTeamMembers().getTeamId());
		memberRepo.delete(member);
		
	}




	public List<Object[]> getTasksmember(Long memberid) {
		
		Members member = memberRepo.findById(memberid).orElseThrow(() -> new IllegalStateException("Member with id " + memberid + " does not exist"));
		
		
		
		return MemberTasksrepo.getTasksByMemberId(member.getMemberId());
	}




	public void Assign_task(Long memberid, Long taskid) {
		Members member = memberRepo.findById(memberid).orElseThrow(() -> new IllegalStateException("Member with id " + memberid + " does not exist"));
		Tasks task = taskrepo.findById(taskid).orElseThrow(() -> new IllegalStateException("task with id " + taskid + " does not exist"));
		
		CompositeKey CompoKey = new CompositeKey();
		
		CompoKey.setMemberId(member.getMemberId());
		
		CompoKey.setTaskId(task.getTaskId());
		


        MemberTask membertasks = new MemberTask();
        
		membertasks.setMembers(member);
		membertasks.setTasks(task);
		
		membertasks.setCompositeKey(CompoKey);
		
		MemberTasksrepo.save(membertasks);
		
	}
	
	

}
