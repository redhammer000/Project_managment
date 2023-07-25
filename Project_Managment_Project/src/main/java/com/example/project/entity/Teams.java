package com.example.project.entity;





import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;


@Entity
@Table


public class Teams {

	
@Id
	
	@SequenceGenerator(
			name = "Team_sequence" , 
			sequenceName = "Team_sequence_name" , 
			allocationSize = 1 
			)
	@GeneratedValue(
			
			strategy = GenerationType.IDENTITY,
			generator = "Team_sequence"
			
			)
	
	private Long TeamId;
	private String TeamName;
	private String TeamDiscription;
	
	private	int MembersCount;
	
	
	public Teams ()
	{
		
	}






	public Teams(Long teamId, String teamName, String teamDiscription,
			Project projTeam) {
		super();
		TeamId = teamId;
		TeamName = teamName;
		TeamDiscription = teamDiscription;
		ProjTeam = projTeam;
		this.MembersCount = 0;
	}







	public Long getTeamId() {
		return TeamId;
	}







	public void setTeamId(Long teamId) {
		TeamId = teamId;
	}







	public String getTeamName() {
		return TeamName;
	}







	public void setTeamName(String teamName) {
		TeamName = teamName;
	}







	public String getTeamDiscription() {
		return TeamDiscription;
	}







	public void setTeamDiscription(String teamDiscription) {
		TeamDiscription = teamDiscription;
	}




	public int incrementMembersCount() {
        MembersCount++;
        return MembersCount;
    }



	 public int decrementMembersCount() {
	        MembersCount--;
	        return MembersCount;
	    }


	public void setMembersCount(int membersCount) {
		MembersCount = membersCount;
	}


	public int getMembersCount() {
		 return MembersCount;
	}





	public Project getProjTeam() {
		return ProjTeam;
	}







	public void setProjTeam(Project projTeam) {
		ProjTeam = projTeam;
	}




	@OneToOne(targetEntity = Project.class)
	@JoinColumn(name = "ProjectId" , referencedColumnName = "ProjectId")
    private Project ProjTeam;
	
	

}
