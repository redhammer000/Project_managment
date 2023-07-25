package com.example.project.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.project.entity.KanbanBoard;
import com.example.project.entity.TaskCards;
import com.example.project.services.KanbanService;
import com.example.project.services.TaskCardService;

@RestController
@RequestMapping("TaskCards")
public class TaskCardsController {
	
	
	private final TaskCardService TaskServ;
	
	private final KanbanService KanServe;
	
	
	
	public TaskCardsController(TaskCardService taskServ, KanbanService kanServe) {
		super();
		TaskServ = taskServ;
		KanServe = kanServe;
	}



	@GetMapping("Get_task_cards")
	public List<Object[]> getTaskCards(@RequestParam (required = true) Long sprintid)
	{
		
		return TaskServ.getCardsdetails(sprintid);
		
	}
	
	@GetMapping("Get_task_cards_status")
	public KanbanBoard getTaskCardsStatus(@RequestParam (required = true) Long Card_id)
	{
		
		return KanServe.GetTaskCardsStatus(Card_id);
		
	}
	
	
	@PostMapping("Register_Task_Card")
	public void register_Sprint(@RequestBody TaskCards Cards)
	{
		
		TaskServ.Register_TaskCard(Cards);
		
	}
	
	
	
}
