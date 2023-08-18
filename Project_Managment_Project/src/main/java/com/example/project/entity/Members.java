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
		
	private Boolean IsTeamLead;

	



	public Members() {
		super();
	}



	public Members(Long memberId, Date joiningDate, Boolean isTeamLead, Long user_id,
			Long team_id) {
		super();
		MemberId = memberId;
		JoiningDate = joiningDate;
		IsTeamLead = isTeamLead;
		
		User user = new User();
		
		Teams team = new Teams();
		
		user.setUserId(user_id);
		team.setTeamId(team_id);
		
		UserMember = user;
		TeamMembers = team;
	}

	
	public void setuser_id (Long user_id)
	{
		
		User user = new User();
		user.setUserId(user_id);
		UserMember = user;
	}

	
	public void setteam_id (Long team_id)
	{
		Teams team = new Teams();
		team.setTeamId(team_id);
		TeamMembers = team;
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



	public Boolean getIsTeamLead() {
		return IsTeamLead;
	}



	public void setIsTeamLead(Boolean isTeamLead) {
		IsTeamLead = isTeamLead;
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



	
	@OneToOne(targetEntity = User.class)
	@JoinColumn(name = "UserId" , referencedColumnName = "UserId")
    private User UserMember;
	
	@ManyToOne(targetEntity = Teams.class)
	@JoinColumn(name = "TeamId" , referencedColumnName = "teamId")
	
	private Teams TeamMembers;
	
}
