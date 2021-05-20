package com.inventory.service;

import com.inventory.dto.ProductDTO;
import com.inventory.exception.InventoryCommonException;
import com.inventory.model.InvCommonResponse;
import com.inventory.model.ListProductReq;

public interface IProductService 
{

	InvCommonResponse getProducts(ListProductReq req) throws InventoryCommonException;

	ProductDTO getProductsById(Integer ipId) throws InventoryCommonException;

	void updateProduct(ProductDTO req, int addUpdFlag) throws InventoryCommonException;
	
}
