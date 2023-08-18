package com.example.project.entity;




import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table
public class User {
	
	@Id
	@SequenceGenerator(
			name = "User_sequence" , 
			sequenceName = "User_sequence" , 
			allocationSize = 1 
			)
	@GeneratedValue(
			
			strategy = GenerationType.SEQUENCE,
			generator = "User_sequence"
			
			)
	
	private Long UserId;
	
	
	private String FirstName;
	
	
	private String LastName;
	
	
	
	private String email;
	
	private String PhoneNumber;
	
	private String Position;
	
	private String Address;
	
	private  String Department;
	
	private String Gender;
	
	private Boolean Is_admin;
	
	
    




	public User() {
		super();
	}




	public User(Long userId, String first_name, String last_name, String email, String phoneNumber, String position,
			String address, String department, String gender, Boolean is_admin, Long branchNumber) {
		super();
		FirstName = first_name;
		LastName = last_name;
		this.email = email;
		PhoneNumber = phoneNumber;
		Position = position;
		Address = address;
		Department = department;
		Gender = gender;
		Is_admin = is_admin;
		Company companyUser = new Company();
		
		companyUser.setBranchNo(branchNumber);
		CompanyUser = companyUser;
	}
	
	public void setbranchNumber(Long branchNumber) {
		Company companyUser = new Company();
		companyUser.setBranchNo(branchNumber);
		CompanyUser = companyUser;
	}
	
	
	public Long getbranchNumber() {
	
		return CompanyUser.getBranchNo();
	}
	
	public Long getUserId() {
		return UserId;
	}

	public void setUserId(Long userId) {
		UserId = userId;
	}

	public String getfirst_name() {
		return FirstName;
	}

	public void setfirst_name(String firstName) {
		FirstName = firstName;
	}

	public String getlast_name() {
		return LastName;
	}

	public void setlast_name(String lastName) {
		LastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return PhoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		PhoneNumber = phoneNumber;
	}

	public String getPosition() {
		return Position;
	}

	public void setPosition(String position) {
		Position = position;
	}

	public String getAddress() {
		return Address;
	}

	public void setAddress(String address) {
		Address = address;
	}

	public String getDepartment() {
		return Department;
	}

	public void setDepartment(String department) {
		Department = department;
	}

	public String getgender() {
		return Gender;
	}

	public void setgender(String gender) {
		Gender = gender;
	}

	public Boolean getIs_admin() {
		return Is_admin;
	}

	public void setIs_admin(Boolean is_admin) {
		Is_admin = is_admin;
	}

	public Company getCompanyUser() {
		return CompanyUser;
	}

	public void setCompanyUser(Company companyUser) {
		CompanyUser = companyUser;
	}



	
	@ManyToOne(targetEntity = Company.class)
	@JoinColumn(name = "BranchNo" , referencedColumnName = "BranchNo")
    private Company CompanyUser;
	

	
}
