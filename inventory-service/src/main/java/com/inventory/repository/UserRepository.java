package com.inventory.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.inventory.entities.InvUsers;

public interface UserRepository extends CrudRepository<InvUsers, Integer> 
{
	@Query("Select u From InvUsers u where u.iuUsername =:username")
	public InvUsers getInvUsersByIuUsername(@Param("username") String uname);
}
