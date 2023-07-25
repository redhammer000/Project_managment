package com.example.project.entity;


import org.springframework.beans.factory.annotation.Autowired;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table
public class Company {

	@Id
	@SequenceGenerator(
			name = "branch_sequence" , 
			sequenceName = "branch_sequence" , 
			allocationSize = 1 
			)
	@GeneratedValue(
			
			strategy = GenerationType.SEQUENCE,
			generator = "branch_sequence"
			
			)
	private Long BranchNo;
	
	private String BranchName;
	
	private String BranchLocation;
	
	private String BranchIndustry;

	
	
	public Company() {
		super();
	}
	
	@Autowired
	public Company(String branch_name, String branch_location, String branch_industry) {
		this.BranchName = branch_name;
		this.BranchLocation = branch_location;
		this.BranchIndustry = branch_industry;
	}

	public Long getBranch_no() {
		return BranchNo;
	}

	public void setBranch_no(Long branch_no) {
		BranchNo = branch_no;
	}

	public String getBranch_name() {
		return BranchName;
	}

	public void setBranch_name(String branch_name) {
		BranchName = branch_name;
	}

	public String getBranch_location() {
		return BranchLocation;
	}

	public void setBranch_location(String branch_location) {
		BranchLocation = branch_location;
	}

	public String getBranch_industry() {
		return BranchIndustry;
	}

	public void setBranch_industry(String branch_industry) {
		BranchIndustry = branch_industry;
	}

	@Override
	public String toString() {
		return "company [Branch_no=" + BranchNo + ", Branch_name=" + BranchName + ", Branch_location="
				+ BranchLocation + ", Branch_industry=" + BranchIndustry + "]";
	}
	
	
	
	
}
