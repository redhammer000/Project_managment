package com.example.project.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.project.entity.*;
import com.example.project.services.*;

@RestController

@RequestMapping("user")
public class UserController {
	
	
	private final UserService UserServ;
	
	
	public UserController(UserService userServ) {
		super();
		UserServ = userServ;
	}

	@GetMapping("User_Data")
	public List<User> getUser()
	{
		return UserServ.get_user_details();
		
	}
	
	@GetMapping("My_User_Data")
	public User get_My_User(@RequestParam (required = true) Long UserId)
	{
		return UserServ.get_my_user_details(UserId);
		
	}
	
	@PostMapping("Register_User")
	public void register_Branch(@RequestBody User user)
	{
		
		UserServ.Register_user(user);
		
	}
	
	@DeleteMapping(path = "deleteuser/{userid}")
	
	public void deleteUser (@PathVariable ("userid") Long userid)
	{
		UserServ.deleteUser(userid);
	
	}
	
	
	@PatchMapping(path = "update/{userid}")
	
	public void updateUser(
			@PathVariable ("userid") Long userId,
			@RequestParam (required = false) String firstName,
			@RequestParam (required = false) String lastName,
			@RequestParam (required = false) String email,
			@RequestParam (required = false) String phoneNumber,
			@RequestParam (required = false) String position,
			@RequestParam (required = false) String address,
			@RequestParam (required = false) String department,
			@RequestParam (required = false) String gender,
			@RequestParam (required = false) Company companyUser
			)	 
	{
		
		UserServ.updateUser(userId,firstName,lastName,email,phoneNumber,position,address,department,gender,companyUser);
		
	}
	
	@PatchMapping(path = "adm_update/{userid}")
	public void adm_updateUser(
			@PathVariable ("userid") Long userId,
			@RequestParam (required = false) String firstName,
			@RequestParam (required = false) String lastName,
			@RequestParam (required = false) String email,
			@RequestParam (required = false) String phoneNumber,
			@RequestParam (required = false) String position,
			@RequestParam (required = false) String address,
			@RequestParam (required = false) String department,
			@RequestParam (required = false) String gender,
			@RequestParam (required = false) Boolean is_admin,
			@RequestParam (required = false) Company companyUser
			)	 
	{
		
		UserServ.adm_updateUser(userId,firstName,lastName,email,phoneNumber,position,address,department,gender,is_admin,companyUser);
		
	}
	
}
