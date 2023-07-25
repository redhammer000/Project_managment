package com.example.project.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.project.entity.Sprints;
import com.example.project.entity.TaskCards;
import com.example.project.repository.SprintsRepository;
import com.example.project.repository.TaskCardRepository;
import com.example.project.repository.TasksRepository;

@Service
public class TaskCardService {

	private final TaskCardRepository TaskCardRepo;
	
	private final SprintsRepository SprintRepo;
	
	private final TasksRepository tasksRepo;
	
	private final KanbanService KanServe;
	
	public TaskCardService(TaskCardRepository taskCardRepo , SprintsRepository SprintRepo , TasksRepository tasksRepo , KanbanService KanServe) {
		super();
		TaskCardRepo = taskCardRepo;
		this.SprintRepo = SprintRepo;
		this.tasksRepo = tasksRepo;
		this.KanServe = KanServe;
	}



	public List<Object[]> getCardsdetails(Long sprintid) {
		
		Sprints sprint =  SprintRepo.findById(sprintid).orElseThrow(() -> new IllegalStateException("Sprint with id " + sprintid + " does not exist"));
		
		return TaskCardRepo.getTaskCardsBySprintId(sprint.getSprintId());
	}



	public void Register_TaskCard(TaskCards cards) {
		
		cards.setTaskForCards(tasksRepo.findById(cards.getTaskForCards().getTaskId()).orElseThrow(() -> new IllegalStateException("Task with id " + cards.getTaskForCards().getTaskId() + " does not exist")));
		
		TaskCardRepo.save(cards);
		
		KanServe.RegisterTaskCard(cards);
		
	}

}
