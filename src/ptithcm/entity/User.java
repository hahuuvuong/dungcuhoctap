package ptithcm.entity;

import java.util.Collection;


import javax.persistence.*;

import org.hibernate.validator.constraints.NotBlank;


public class User {
	

	public Integer userId;
	
	private String name;
	
	
	private String email;
	
	private String phone;
	
	private String address;
	
	private String username;
	
	
	private String password;
	
	private String avatar;
	
	private int roleId;


	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public User(Integer userId, String name, String email, String phone, String address, String username,
			String password, String avatar, int roleId) {
		super();
		this.userId = userId;
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.address = address;
		this.username = username;
		this.password = password;
		this.avatar = avatar;
		this.roleId = roleId;
	}

	public User() {
		super();
	}

	
	
}
