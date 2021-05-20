package com.inventory.repository;

import org.springframework.data.repository.CrudRepository;

import com.inventory.entities.InvProduct;

public interface ProductRepo extends CrudRepository<InvProduct, Integer> 
{
	
}
