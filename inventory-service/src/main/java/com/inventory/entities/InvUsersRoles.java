package com.inventory.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "inv_users_roles", schema = "inventory")
public class InvUsersRoles 
{
	@Id
	@Column(name = "iur_user_id")
	private Integer iurUserId;
	
	@Column(name = "iur_role_id")
	private Integer iurRoleId;

	public Integer getIurUserId() {
		return iurUserId;
	}

	public void setIurUserId(Integer iurUserId) {
		this.iurUserId = iurUserId;
	}

	public Integer getIurRoleId() {
		return iurRoleId;
	}

	public void setIurRoleId(Integer iurRoleId) {
		this.iurRoleId = iurRoleId;
	}
	
}
