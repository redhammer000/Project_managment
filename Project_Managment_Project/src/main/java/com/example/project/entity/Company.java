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
	public Company(String branchIndustry, String branchLocation, String branchName) {
		this.BranchName = branchName;
		this.BranchLocation = branchLocation;
		this.BranchIndustry = branchIndustry;
	}



	public Long getBranchNo() {
		return BranchNo;
	}

	public void setBranchNo(Long branchNo) {
		BranchNo = branchNo;
	}

	public String getBranchName() {
		return BranchName;
	}

	public void setBranchName(String branchName) {
		BranchName = branchName;
	}

	public String getBranchLocation() {
		return BranchLocation;
	}

	public void setBranchLocation(String branchLocation) {
		BranchLocation = branchLocation;
	}

	public String getBranchIndustry() {
		return BranchIndustry;
	}

	public void setBranchIndustry(String branchIndustry) {
		BranchIndustry = branchIndustry;
	}

	@Override
	public String toString() {
		return "company [Branch_no=" + BranchNo + ", Branch_name=" + BranchName + ", Branch_location="
				+ BranchLocation + ", Branch_industry=" + BranchIndustry + "]";
	}
	
	
	
	
}
