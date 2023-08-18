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




	public List<Tasks> getTasksBySprint(Long sprintid) {
		
	 Sprints sprint	 = SprintRepo.findById(sprintid).orElseThrow(() -> new IllegalStateException("Sprint with id " + sprintid + " does not exist"));
	 
	 List <Tasks> tasks = TaskRepo.findTaskInfoBysprinttasks(sprint.getSprintId());
	 
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
			String taskStatus, Long sprint_id ) {
		
		Tasks task = TaskRepo.findById(taskid).orElseThrow(() -> new IllegalStateException("task with id " + taskid + " does not exist"));
		
		
		if(sprint_id != null)
		{
		Sprints sprintTasks = SprintRepo.findById(sprint_id).get();
		
		
		if(sprintTasks != null)
		if (sprintTasks.getSprintId() != task.getSprintTasks().getSprintId())
		{
			task.setSprintTasks(SprintRepo.findById(task.getSprintTasks().getSprintId()).orElseThrow(() -> new IllegalStateException("sprint with id " + task.getSprintTasks().getSprintId() + " does not exist")));
		}
		}

		if (taskTitle != null)
			task.settask_title(taskTitle);
		if (taskDiscription != null)
			task.settask_description(taskDiscription);
		if (startDate != null)
			task.setstartDate(startDate);

		if (endDate != null)
			task.setendDate(endDate);
		if (taskStatus != null)
			task.settaskStatus(taskStatus);
		
	}




	public List<Tasks> getMytasks() {
		
		return TaskRepo.findAll();
	}




	public Tasks gettasks(Long taskId) {
	
		
		return TaskRepo.findById(taskId).get();
	}





	
	
}
