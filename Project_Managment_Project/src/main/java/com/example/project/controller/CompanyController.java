package com.example.project.controller;


import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
	
	@DeleteMapping(path = "deletebranch/{id}")
	
	public void deleteproject (@PathVariable ("id") Long id)
	{
	
	C_service.deleteBranch(id);    
	
	}

	@PatchMapping(path = "update/{id}")
	public void updateProject(
		@PathVariable ("id") Long branchNo,
		@RequestParam (required = false) String branchName,
		@RequestParam (required = false) String branchLocation,
		@RequestParam (required = false) String branchIndustry
		)	 
{
	
	C_service.UpdateCompany(branchNo,branchName,branchLocation,branchIndustry);

	
}

}
