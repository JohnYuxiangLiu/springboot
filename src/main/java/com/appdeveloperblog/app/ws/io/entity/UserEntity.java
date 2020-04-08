package com.appdeveloperblog.app.ws.io.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

//this class is to communicate/store/retrieve data from database
//users is the db table name
@Entity(name="users")
public class UserEntity implements Serializable {

	private static final long serialVersionUID = -3772691377276902875L;
	
//	id is the db id, not the id send to output
//	primary key 
	@Id
//	auto incremented and generated id
	@GeneratedValue
	private long id;
	
//	userId is the id send to output, including alpha-numerical
//	nullable = false: required field in db
	@Column(nullable=false)
	private String userId;
	
	@Column(nullable=false,length=50)
	private String firstName;
	
	@Column(nullable=false,length=50)
	private String lastName;
	
	@Column(nullable=false,length=120)
	private String email;
	
	@Column(nullable=false)
	private String encryptedPassword;
	
	private String emailVerificationToken;
	
//	=false is the default value
	@Column(nullable=false)
	private Boolean emailVerificationStatus=false;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEncryptedPassword() {
		return encryptedPassword;
	}

	public void setEncryptedPassword(String encryptedPassword) {
		this.encryptedPassword = encryptedPassword;
	}

	public String getEmailVerificationToken() {
		return emailVerificationToken;
	}

	public void setEmailVerificationToken(String emailVerificationToken) {
		this.emailVerificationToken = emailVerificationToken;
	}

	public Boolean getEmailVerificationStatus() {
		return emailVerificationStatus;
	}

	public void setEmailVerificationStatus(Boolean emailVerificationStatus) {
		this.emailVerificationStatus = emailVerificationStatus;
	}
	

}
