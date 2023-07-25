package com.example.project.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.project.entity.*;
import com.example.project.repository.*;


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
		
		Companyrepo.save(branch);
		
	}
	
	
	

}
