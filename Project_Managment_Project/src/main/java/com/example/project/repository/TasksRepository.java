package com.example.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.project.entity.*;

@Repository
public interface TasksRepository extends JpaRepository<Tasks, Long> {
	
	@Query("SELECT tc " +
	           "FROM Tasks tc  WHERE tc.sprinttasks.SprintId = :sprintId")
	    List<Tasks> findTaskInfoBysprinttasks(@Param("sprintId") Long sprintId);

}