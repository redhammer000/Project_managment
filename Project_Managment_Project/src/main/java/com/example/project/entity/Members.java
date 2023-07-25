package com.example.project.entity;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table



public class Members {
	
	@Id
	
	@SequenceGenerator(
			name = "member_sequence" , 
			sequenceName = "member_sequence" , 
			allocationSize = 1 
			)
	@GeneratedValue(
			
			strategy = GenerationType.SEQUENCE,
			generator = "member_sequence"
			
			)
	
	private Long MemberId;
	
	
	private Date JoiningDate;
	private String Role;
		
	private Boolean IsTeamLead;

	



	public Members() {
		super();
	}



	public Members(Long memberId, Date joiningDate, String role, Boolean isTeamLead, Tasks task, User userMember,
			Teams teamMembers) {
		super();
		MemberId = memberId;
		JoiningDate = joiningDate;
		Role = role;
		IsTeamLead = isTeamLead;
		Task = task;
		UserMember = userMember;
		TeamMembers = teamMembers;
	}



	public Long getMemberId() {
		return MemberId;
	}



	public void setMemberId(Long memberId) {
		MemberId = memberId;
	}



	public Date getJoiningDate() {
		return JoiningDate;
	}



	public void setJoiningDate(Date joiningDate) {
		JoiningDate = joiningDate;
	}



	public String getRole() {
		return Role;
	}



	public void setRole(String role) {
		Role = role;
	}



	public Boolean getIsTeamLead() {
		return IsTeamLead;
	}



	public void setIsTeamLead(Boolean isTeamLead) {
		IsTeamLead = isTeamLead;
	}



	public Tasks getTask() {
		return Task;
	}



	public void setTask(Tasks task) {
		Task = task;
	}



	public User getUserMember() {
		return UserMember;
	}



	public void setUserMember(User userMember) {
		UserMember = userMember;
	}



	public Teams getTeamMembers() {
		return TeamMembers;
	}



	public void setTeamMembers(Teams teamMembers) {
		TeamMembers = teamMembers;
	}



	@OneToOne(targetEntity = Tasks.class)
	@JoinColumn(name = "TaskId" , referencedColumnName = "TaskId")
    private Tasks Task;
	
	@OneToOne(targetEntity = User.class)
	@JoinColumn(name = "UserId" , referencedColumnName = "UserId")
    private User UserMember;
	
	@ManyToOne(targetEntity = Teams.class)
	@JoinColumn(name = "TeamId" , referencedColumnName = "teamId")
	
	private Teams TeamMembers;
	
}
