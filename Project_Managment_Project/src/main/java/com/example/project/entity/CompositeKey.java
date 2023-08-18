package com.example.project.entity;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Embeddable;


@Embeddable
public class CompositeKey implements Serializable{

	
	 private Long MemberId;
	 private Long TaskId;
	 
	
	 
	public CompositeKey() {
		super();
	}


	public CompositeKey(Long member_id, Long task_id
) {
		super();
		MemberId = member_id;
		TaskId = task_id;
	}
	
	
	@Override
	public int hashCode() {
		return Objects.hash(MemberId, TaskId);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CompositeKey other = (CompositeKey) obj;
		return Objects.equals(MemberId, other.MemberId) && Objects.equals(TaskId, other.TaskId);
	}
	public Long getMemberId() {
		return MemberId;
	}
	public void setmember_id(Long memberId) {
		MemberId = memberId;
	}
	public Long getTaskId() {
		return TaskId;
	}
	public void settask_id(Long taskId) {
		TaskId = taskId;
	}
	 
	 
	
}
