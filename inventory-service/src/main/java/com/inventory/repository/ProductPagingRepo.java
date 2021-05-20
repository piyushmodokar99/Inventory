package com.inventory.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.inventory.entities.InvProduct;

public interface ProductPagingRepo extends PagingAndSortingRepository<InvProduct, Integer> 
{

	List<InvProduct> findByIpType(String type, Pageable pageable);
	
}
