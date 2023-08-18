package com.example.project.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.project.entity.Teams;

@Repository
public interface TeamsRepository extends JpaRepository<Teams , Long >{

	@Query("SELECT t FROM Teams t WHERE t.ProjTeam.ProjectId = :projectId")
	List<Teams> findByProjectid(Long projectId);

}
