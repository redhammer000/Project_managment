package com.example.project.services;

import java.sql.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.project.entity.Sprints;
import com.example.project.entity.Tasks;
import com.example.project.repository.*;

import jakarta.transaction.Transactional;

@Service
public class TaskService {
	
	
	private final TasksRepository TaskRepo;
	private final SprintsRepository SprintRepo;
	
	


	public TaskService(TasksRepository taskRepo, SprintsRepository sprintRepo) {
		super();
		TaskRepo = taskRepo;
		SprintRepo = sprintRepo;
	}




	public List<Object[]> getTasksBySprint(Long sprintid) {
		
	 Sprints sprint	 = SprintRepo.findById(sprintid).orElseThrow(() -> new IllegalStateException("Sprint with id " + sprintid + " does not exist"));
	 
	 List <Object[]> tasks = TaskRepo.findTaskInfoBysprinttasks(sprint.getSprintId());
	 
	return tasks;
	}




	public void TaskServe(Tasks task) {
		
		task.setSprintTasks(SprintRepo.findById(task.getSprintTasks().getSprintId()).orElseThrow(() -> new IllegalStateException("Sprint with id " + task.getSprintTasks().getSprintId() + " does not exist")));
		
		TaskRepo.save(task);
	}




	public void deleteTask(Long taskid) {
		
		Tasks task = TaskRepo.findById(taskid).orElseThrow(() -> new IllegalStateException("Task with id " + taskid + " does not exist"));
		
		TaskRepo.delete(task);

		
	}



	@Transactional
	public void updateTask(Long taskid, String taskTitle, String taskDiscription, Date startDate, Date endDate,
			String taskStatus, Sprints sprintTasks) {
		
		Tasks task = TaskRepo.findById(taskid).orElseThrow(() -> new IllegalStateException("task with id " + taskid + " does not exist"));
		
		
		
		if(sprintTasks != null)
		if (sprintTasks.getSprintId() != task.getSprintTasks().getSprintId())
		{
			task.setSprintTasks(SprintRepo.findById(task.getSprintTasks().getSprintId()).orElseThrow(() -> new IllegalStateException("sprint with id " + task.getSprintTasks().getSprintId() + " does not exist")));
		}
		
		
		;
		

		if (taskTitle != null)
			task.setTaskTitle(taskTitle);
		if (taskDiscription != null)
			task.setTaskDiscription(taskDiscription);
		if (startDate != null)
			task.setStartDate(startDate);

		if (endDate != null)
			task.setEndDate(endDate);
		if (taskStatus != null)
			task.setTaskStatus(taskStatus);
		
	}
	
	
}
