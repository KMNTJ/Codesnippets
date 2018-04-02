package com.circumborrealis.serverside.Model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;


@Entity
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="username", nullable=false, updatable=false)
	private String username;
	
	@Column(name="password", nullable=false)
	private String passwordHash;
	
	@Column(name="role", nullable=false)
	private String role;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
	private List<CodeSnip> codeSnips;
	
	public User(){
	}

	public User(String username, String passwordHash, String role) {
		super();
		this.username = username;
		this.passwordHash = passwordHash;
		this.role = role;
	}
	
	public User(String username, String passwordHash, String role, List<CodeSnip> codeSnips) {
		super();
		this.username = username;
		this.passwordHash = passwordHash;
		this.role = role;
		this.codeSnips = codeSnips;
	}
	
	public User(Long id, String username, String passwordHash, String role, List<CodeSnip> codeSnips) {
		super();
		this.id = id;
		this.username = username;
		this.passwordHash = passwordHash;
		this.role = role;
		this.codeSnips = codeSnips;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPasswordHash() {
		return passwordHash;
	}

	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
	public List<CodeSnip> getCodeSnips() {
		return codeSnips;
	}

	public void setCodeSnips(List<CodeSnip> codeSnips) {
		this.codeSnips = codeSnips;
	}


}
