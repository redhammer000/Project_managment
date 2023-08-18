package com.example.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.project.entity.*;

public interface SprintsRepository extends JpaRepository<Sprints, Long >{
	
	
	@Query("SELECT s FROM Sprints s WHERE s.ProjectSprints.ProjectId = :projectId")
    List<Sprints> findByProjectSprintsProjectId(Long projectId);
	
	

}
