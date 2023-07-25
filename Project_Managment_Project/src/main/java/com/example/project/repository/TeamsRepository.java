package com.example.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.project.entity.Teams;

@Repository
public interface TeamsRepository extends JpaRepository<Teams , Long >{

	
	
	
}
