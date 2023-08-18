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






	public Teams(Long teamId, String team_name, String team_description,
			Long project_id) {
		super();
		TeamId = teamId;
		TeamName = team_name;
		TeamDiscription = team_description;
		
		Project proj = new Project();
		
		proj.setprojectId(project_id);
		ProjTeam = proj;
		this.MembersCount = 0;
	}



	
	public void setproject_id(Long project_id )
	{
		Project proj = new Project();
		proj.setprojectId(project_id);
		ProjTeam = proj;
	}

	public Long getproject_id()
	{
		return ProjTeam.getprojectId();
	}

	public Long getTeamId() {
		return TeamId;
	}







	public void setTeamId(Long teamId) {
		TeamId = teamId;
	}







	public String getteam_name() {
		return TeamName;
	}







	public void setteam_name(String teamName) {
		TeamName = teamName;
	}







	public String getteam_description() {
		return TeamDiscription;
	}







	public void setteam_description(String teamDiscription) {
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
