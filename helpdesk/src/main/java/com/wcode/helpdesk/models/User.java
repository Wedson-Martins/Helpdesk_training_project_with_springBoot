package com.wcode.helpdesk.models;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

@Entity
@Table(name = "users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Long id;
	@Column(unique = true)
	@NotEmpty(message = "Can not is Empty")
	@Email(message = "It is not an email")
	private String email;
	@Column
	@NotEmpty(message = "Can not is Empty")
	private String name;
	@Column
	@NotEmpty(message = "Can not is Empty")
	private String lastName;
	@Column
	@NotEmpty(message = "Can not is Empty")
	@Length(min = 2, message = "Password must be at least 2 characters")
	private String password;
	@Column
	private boolean active = true;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "users_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles;

	@Column
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "userInteraction")
	private Set<Interaction> interactions;

	//
	// @OneToMany(cascade = CascadeType.ALL, mappedBy = "userOpen")
	// private Set<Ticket> tikets;
	//
	// @OneToMany(cascade = CascadeType.ALL, mappedBy = "technician")
	// private Set<Ticket> ticketsTechnician;

	public User() {

	}

	public User(String email, String name, String lastName, String password, boolean active) {
		this.email = email;
		this.name = name;
		this.lastName = lastName;
		this.password = password;
		this.active = active;
	}

	public User(Long id, String email, String name, String lastName, String password, boolean active) {
		this.id = id;
		this.email = email;
		this.name = name;
		this.lastName = lastName;
		this.password = password;
		this.active = active;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean getActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public Set<Interaction> getInteractions() {
		return interactions;
	}

	public void setInteractions(Set<Interaction> interactions) {
		this.interactions = interactions;
	}

}
