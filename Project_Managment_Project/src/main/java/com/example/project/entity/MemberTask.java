package com.example.project.entity;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;

@Entity

public class MemberTask{

	
	 @EmbeddedId
	  private CompositeKey compositeKey;
	
	
	 public MemberTask() {
		super();
	}

	@OneToOne(targetEntity = Tasks.class)
	    @MapsId("TaskId")
	    @JoinColumn(name = "TaskId")
	    private Tasks tasks;

	    @ManyToOne(targetEntity = Members.class)
	    @MapsId("MemberId")
	    @JoinColumn(name = "MemberId")
	    private Members members;

		public MemberTask(CompositeKey compositeKey, Tasks tasks, Members members) {
			super();
			this.compositeKey = compositeKey;
			this.tasks = tasks;
			this.members = members;
		}

		public CompositeKey getCompositeKey() {
			return compositeKey;
		}

		public void setCompositeKey(CompositeKey compositeKey) {
			this.compositeKey = compositeKey;
		}

		public Tasks getTasks() {
			return tasks;
		}

		public void setTasks(Tasks tasks) {
			this.tasks = tasks;
		}

		public Members getMembers() {
			return members;
		}

		public void setMembers(Members members) {
			this.members = members;
		}
	    

	    
	    
}
