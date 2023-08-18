package com.example.project.services;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.project.entity.*;
import com.example.project.repository.*;

import jakarta.transaction.Transactional;

@Service
public class UserService {
	
	private final UserRepository UserRepo;
	private final CompanyRepository comprepo;
	
	
	



	public UserService(UserRepository userRepo, CompanyRepository comprepo) {
		super();
		UserRepo = userRepo;
		this.comprepo = comprepo;
	}



	public List<User> get_user_details() {
		
		return UserRepo.findAll();
	}



	public User get_my_user_details(Long userId) {
		
		User user = UserRepo.findById(userId).orElseThrow(() -> new IllegalStateException("user with id " + userId + " does not exist"));
		
		return user;
	}



	public void Register_user(User user) {
		
		user.setCompanyUser(comprepo.findById(user.getCompanyUser().getBranchNo()).orElseThrow(() -> new IllegalStateException("Branch with id " + user.getCompanyUser().getBranchNo() + " does not exist")));
		UserRepo.save(user);
	}



	public  void deleteUser(Long userid) {
		
		User user = UserRepo.findById(userid).orElseThrow(() -> new IllegalStateException("user with id " + userid + " does not exist"));
		
		UserRepo.delete(user);
		
	}


	@Transactional
	public void updateUser(Long userId, String firstName, String lastName, String email, String phoneNumber,
			String position, String address, String department, String gender, Company companyUser) {
		
		User user = UserRepo.findById(userId).orElseThrow(() -> new IllegalStateException("user with id " + userId + " does not exist"));
		
		if(companyUser != null)
		if (companyUser.getBranchNo() != user.getCompanyUser().getBranchNo())
		{
			user.setCompanyUser(comprepo.findById(companyUser.getBranchNo()).orElseThrow(() -> new IllegalStateException("Branch with id " + companyUser.getBranchNo() + " does not exist")));
		}
		
		
		
		
		if (email != null)
			if (!Objects.equals(user.getEmail(), email))
			{
				Optional<User> byEmail = UserRepo.findUserByEmail(user.getEmail());
				if (byEmail.isPresent())
			throw new IllegalStateException("email taken");
			else 
		{
			user.setEmail(email);
		}
			}
		
			
		if (firstName != null)
		user.setfirst_name(firstName);
		if (lastName != null)
		user.setlast_name(lastName);
		if (gender != null)
		user.setgender(gender);
		if (position != null)
		user.setPosition(position);
		if (phoneNumber != null)
		user.setPhoneNumber(phoneNumber);
		if (address != null)
		user.setAddress(address);
		if (department != null)
		user.setDepartment(department);
	
		
	}


	
	
	@Transactional
	public void adm_updateUser(Long userId, String firstName, String lastName, String email, String phoneNumber,
			String position, String address, String department, String gender, Boolean is_admin, Company companyUser) {
		
		User user = UserRepo.findById(userId).orElseThrow(() -> new IllegalStateException("user with id " + userId + " does not exist"));
		updateUser(userId,firstName,lastName,email,phoneNumber,position,address,address,gender,companyUser);
		if (is_admin != null)
		user.setIs_admin(is_admin);
		
		
	}



	
	
	
	
}
