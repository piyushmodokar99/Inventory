package com.inventory.entities;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "inv_users", schema = "inventory")

@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class InvUsers 
{
	@Id
	@SequenceGenerator(name = "SEQ_INV_USERS_ID", sequenceName = "inventory.seq_inv_users_id", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_INV_USERS_ID")
	@Column(name = "iu_uid")
	private Integer iuUId;
	
	@Column(name = "iu_username")
	private String iuUsername;
	
	@Column(name = "iu_password")
	private String iuPassword;
	
	@Column(name = "iu_is_enabled")
	private Integer iuIsEnabled;
	
	
	/*
	 * @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	 * 
	 * @JoinTable(name = "inv_roles", joinColumns = @JoinColumn(name = "iu_uid"),
	 * schema = "inventory", inverseJoinColumns = @JoinColumn(name = "ir_id"))
	 * Set<InvRoles> roles = new HashSet<InvRoles>();
	 */
	
	//@ManyToMany(targetEntity = InvRoles.class, cascade = CascadeType.ALL , fetch = FetchType.EAGER)
	
	@ManyToMany(targetEntity = InvRoles.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(
	        name="inv_users_roles", schema = "inventory",
	        joinColumns=
	        @JoinColumn( name="iur_user_id"),
	        inverseJoinColumns = @JoinColumn(name="iur_role_id"))
    private List<InvRoles> invRoles;
	

	public Integer getIuUId() {
		return iuUId;
	}

	public void setIuUId(Integer iuUId) {
		this.iuUId = iuUId;
	}

	public String getIuUsername() {
		return iuUsername;
	}

	public void setIuUsername(String iuUsername) {
		this.iuUsername = iuUsername;
	}

	public String getIuPassword() {
		return iuPassword;
	}

	public void setIuPassword(String iuPassword) {
		this.iuPassword = iuPassword;
	}

	public Integer getIuIsEnabled() {
		return iuIsEnabled;
	}

	public void setIuIsEnabled(Integer iuIsEnabled) {
		this.iuIsEnabled = iuIsEnabled;
	}

	public List<InvRoles> getInvRoles() {
		return invRoles;
	}

	public void setInvRoles(List<InvRoles> invRoles) {
		this.invRoles = invRoles;
	}

	
	
	 
}