package com.example.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.project.entity.*;

public interface TaskCardRepository extends JpaRepository< TaskCards , Long>{

	@Query("SELECT tc.CardId , tc.CardTitle , tc.CardDescription , tc.DueDate FROM TaskCards tc " +
	           "JOIN tc.TaskForCards task " +
	           "WHERE task.sprinttasks.SprintId = :sprintId")
	    List<Object[]> getTaskCardsBySprintId(@Param("sprintId") Long sprintId);
	
	
}
