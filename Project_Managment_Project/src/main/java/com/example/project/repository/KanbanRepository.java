package com.example.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.project.entity.*;

public interface KanbanRepository extends JpaRepository<KanbanBoard, TaskCards>{

}
