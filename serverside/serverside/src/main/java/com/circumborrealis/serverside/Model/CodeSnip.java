package com.circumborrealis.serverside.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
public class CodeSnip {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String owner;
	private String details;
	@Column(length=4000)
	private String code;
	private String language;
	private Long time; //time counted in milliseconds from 1.1.1970 00:00:00 UTC. This is the time when the saving of the code-snippet occurred.

	@ManyToOne
    @JsonIgnore
    @JoinColumn(name = "user")
    private User user;
	
	public CodeSnip(){
	}
	
	public CodeSnip(String owner, String details, String code, String language, Long time, User user) {
		super();
		this.owner = owner;
		this.details = details;
		this.code = code;
		this.language = language;
		this.time = time;
		this.user = user;
	}
	
	public CodeSnip(Long id, String owner, String details, String code, String language, Long time, User user) {
		super();
		this.id = id;
		this.owner = owner;
		this.details = details;
		this.code = code;
		this.language = language;
		this.time = time;
		this.user = user;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}

	public String getOwner() {
		return owner;
	}
	
	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public Long getTime() {
		return time;
	}

	public void setTime(Long time) {
		this.time = time;
	}
	
	@Override
	public String toString() {
		return "CodeSnip [id=" + id + ", owner=" + owner + ", details=" + details + ", code=" + code + ", language=" + language + ", time="
				+ time + " user=" + user + "]";
	}
	
}
