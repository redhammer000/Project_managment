package com.example.project.services;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.project.entity.*;
import com.example.project.repository.*;

import jakarta.transaction.Transactional;


@Service
public class CompanyService {

	@Autowired
	private final CompanyRepository Companyrepo;
	
	


	public CompanyService(CompanyRepository c_repo) {
		super();
		Companyrepo = c_repo;
	}


	
	
	public  List<Company> getcompdetails() {
		
		return Companyrepo.findAll();
	}


	
	public void add_new_branch(Company branch) {
		
		
		
		if (branch.getBranchNo() != null)
		if (Companyrepo.findById(branch.getBranchNo()).isPresent() == true)
		{ 
		throw new IllegalStateException("project with id " + branch.getBranchNo() + " already exists ");
		}
		
		else 
		{
			Companyrepo.save(branch);
		}
		else {
			Companyrepo.save(branch);
		}
	}




	public void deleteBranch(Long branchid) {

		Company comp = Companyrepo.findById(branchid).orElseThrow(() -> new IllegalStateException("project with id " + branchid + " does not exist"));
		
		Companyrepo.delete(comp);
		
	}



	@Transactional
	public void UpdateCompany(Long id, String branchName, String branchLocation, String branchIndustry) {
		
		Company comp = Companyrepo.findById(id).orElseThrow(() -> new IllegalStateException("project with id " + id + " does not exist"));
		
		if (branchName != null)
		{
			comp.setBranchName(branchName);
		}
		
		if(branchLocation != null)
		{
			comp.setBranchLocation(branchLocation);
		}
		
		if (branchLocation != null)
		{
			comp.setBranchIndustry(branchIndustry);
		}
		
	}
	
	
	

}
