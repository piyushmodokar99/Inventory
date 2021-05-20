package com.inventory.repository;

import org.springframework.data.repository.CrudRepository;

import com.inventory.entities.InvExpences;

public interface ExpencesRepo extends CrudRepository<InvExpences, Integer> 
{



	Iterable<InvExpences> findAllByOrderByIeIdDesc();
	
}
