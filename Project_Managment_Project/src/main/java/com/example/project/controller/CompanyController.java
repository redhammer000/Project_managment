package com.example.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.project.entity.*;
import com.example.project.services.CompanyService;

@RestController

@RequestMapping("company")
public class CompanyController {
	
	@Autowired
	private final CompanyService C_service;

	public CompanyController(CompanyService c_service) {
		C_service = c_service;
	}
	
	
	@GetMapping("Display_branches")
	public List<Company> getBranches()
	{
		
		return C_service.getcompdetails();
		
	}
	
	
	@PostMapping("save_branch")
	public void register_Branch(@RequestBody Company branch)
	{
		
		C_service.add_new_branch(branch);
		
	}

}
