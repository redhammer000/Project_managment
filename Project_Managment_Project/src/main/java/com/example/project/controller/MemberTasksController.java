package com.example.project.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.project.entity.CompositeKey;
import com.example.project.entity.MemberTask;
import com.example.project.services.MemberService;

@RestController

@RequestMapping("membertasks")
public class MemberTasksController {

	
	private final MemberService memberserv ;

	public MemberTasksController(MemberService memberserv) {
		super();
		this.memberserv = memberserv;
	}
	
	
	@GetMapping("get_tasks_by_member")
	public List<MemberTask> getTasksBySprints(@RequestParam (required = true) Long memberid)
	{
		
		return memberserv.getTasksmember(memberid);
		
	}
	
	
	@GetMapping("get_tasks_by_user")
	public List<MemberTask> getTasksByUser(@RequestParam (required = true) Long UserId)
	{
		
		return memberserv.getTasksUser(UserId);
		
	}
	
	
	@GetMapping("get_tasks")
	public List<MemberTask> getTasksBySprints()
	{
		
		return memberserv.getTasksmemberwithmembers();
		
	}
	
	@PostMapping("give_task")
	public void Assign_task(@RequestBody CompositeKey comp) {
		
		Long memberid = comp.getMemberId();
		Long taskid = comp.getTaskId();
		memberserv.Assign_task(memberid , taskid);
		
	}
	
	
	@DeleteMapping("Delete_assign")
	
	public void delete_task(@RequestParam (required = true) Long memberid , @RequestParam (required = true) Long taskid)
	{
		
		CompositeKey comp = new CompositeKey();
		
		comp.setmember_id(memberid);
		comp.settask_id(taskid);
		
		memberserv.My_delete_task(comp);
		
	}
	
	
}
