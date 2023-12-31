package com.example.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.project.entity.CompositeKey;
import com.example.project.entity.MemberTask;

@Repository
public interface MemberTasksRepository extends JpaRepository<MemberTask, CompositeKey> {

	@Query("SELECT tc FROM MemberTask tc " +
	           "WHERE tc.members.MemberId = :memberId")
	    List<MemberTask> getTasksByMemberId(@Param("memberId") Long memberId);
	
	
	
}
