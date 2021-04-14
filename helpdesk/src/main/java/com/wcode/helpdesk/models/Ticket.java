package com.wcode.helpdesk.models;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "tickets")
public class Ticket {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column
	@NotEmpty(message = "Can not Empty")
	private String name;

	@Column
	@NotEmpty(message = "Can not Empty")
	private String description;

	@Column
	private Date created;

	@Column
	private Date closed;

	@Column
	private Boolean finished = false;

	@ManyToOne
	@JoinColumn(name = "technician_id")
	@JsonBackReference
	private User userOpen;

	@ManyToOne
	@JoinColumn(name = "user_id")
	@JsonBackReference
	private User technician;

	@Column

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "ticket")

	private List<Interaction> interactions;

	public Ticket() {
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getdescription() {
		return description;
	}

	public void setdescription(String description) {
		this.description = description;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getClosed() {
		return closed;
	}

	public void setClosed(Date closed) {
		this.closed = closed;
	}

	public Boolean getFinished() {
		return finished;
	}

	public void setFinished(Boolean finished) {
		this.finished = finished;
	}

	public User getUserOpen() {
		return userOpen;
	}

	public void setUserOpen(User userOpen) {
		this.userOpen = userOpen;
	}

	public User getTechnician() {
		return technician;
	}

	public void setTechnician(User technician) {
		this.technician = technician;
	}

	public List<Interaction> getInteractions() {
		return interactions;
	}

	public void setInteractions(List<Interaction> interactions) {
		this.interactions = interactions;
	}

	@PrePersist
	public void prePersist() {
		this.setCreated(new Date());
	}
}
