package com.inventory.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "inv_roles", schema = "inventory")

@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class InvRoles 
{
	@Id
	@Column(name = "ir_id")
	private Integer irId;
	
	@Column(name = "ir_name")
	private String irName;

	//@ManyToMany(targetEntity = InvUsers.class, mappedBy = "roles", cascade = CascadeType.ALL)
	

	@ManyToMany(targetEntity = InvUsers.class, mappedBy = "invRoles", cascade = CascadeType.ALL)
	private List<InvUsers> users;
	
	public Integer getIrId() {
		return irId;
	}

	public void setIrId(Integer irId) {
		this.irId = irId;
	}

	public String getIrName() {
		return irName;
	}

	public void setIrName(String irName) {
		this.irName = irName;
	}

	public List<InvUsers> getUsers() {
		return users;
	}

	public void setUsers(List<InvUsers> users) {
		this.users = users;
	}
	
	
	
}
