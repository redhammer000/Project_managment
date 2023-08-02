package com.example.project.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.project.services.MemberService;

@RestController

@RequestMapping("membertasks")
public class MemberTasksController {

	
	private final MemberService memberserv ;

	public MemberTasksController(MemberService memberserv) {
		super();
		this.memberserv = memberserv;
	}
	
	
	@GetMapping("get_tasks")
	public List<Object[]> getTasksBySprints(@RequestParam (required = true) Long memberid)
	{
		
		return memberserv.getTasksmember(memberid);
		
	}
	
	@PostMapping("give_task")
	public void Assign_task(@RequestParam (required = true)  Long memberid , @RequestParam (required = true) Long taskid) {
		
		memberserv.Assign_task(memberid , taskid);
		
	}
	
	
	
	
	
}
