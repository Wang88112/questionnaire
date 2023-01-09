package com.example.questionnaire.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "userinfo")
public class UserInfo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private int userId;

	@Column(name = "serial_number")
	private int serialNumber;

	@Column(name = "user_name")
	private String userName;

	@Column(name = "phone")
	private String phone;

	@Column(name = "email")
	private String email;

	@Column(name = "age")
	private String age;

	@Column(name = "create_time")
	private LocalDateTime createTime;

	public UserInfo() {

	}

//	public UserInfo(int userId, String userName, String phone, String email, String age, LocalDateTime createTime) {
//		this.userId = userId;
//		this.userName = userName;
//		this.phone = phone;
//		this.email = email;
//		this.age = age;
//		this.createTime = createTime;
//	}

	public UserInfo(int userId, int serialNumber, String userName, String phone, String email, String age,
			LocalDateTime createTime) {
		this.userId = userId;
		this.serialNumber = serialNumber;
		this.userName = userName;
		this.phone = phone;
		this.email = email;
		this.age = age;
		this.createTime = createTime;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(int serialNumber) {
		this.serialNumber = serialNumber;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public LocalDateTime getCreateTime() {
		return createTime;
	}

	public void setCreateTime(LocalDateTime createTime) {
		this.createTime = createTime;
	}

}
