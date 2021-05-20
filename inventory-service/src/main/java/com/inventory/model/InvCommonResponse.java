package com.inventory.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.inventory.dto.ProductDTO;

@JsonInclude(value = Include.NON_NULL)
public class InvCommonResponse 
{
	private Integer status;
	private String msg;
	
	private List<ProductDTO> products;
	
	private ProductDTO product;
	
	private Integer totalListSize;
	
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	
	public InvCommonResponse() {
		// TODO Auto-generated constructor stub
	}
	public List<ProductDTO> getProducts() {
		return products;
	}
	public void setProducts(List<ProductDTO> products) {
		this.products = products;
	}
	public ProductDTO getProduct() {
		return product;
	}
	public void setProduct(ProductDTO product) {
		this.product = product;
	}
	public Integer getTotalListSize() {
		return totalListSize;
	}
	public void setTotalListSize(Integer totalListSize) {
		this.totalListSize = totalListSize;
	}
	public InvCommonResponse(Integer status, String msg, List<ProductDTO> products, ProductDTO product,
			Integer totalListSize) {
		super();
		this.status = status;
		this.msg = msg;
		this.products = products;
		this.product = product;
		this.totalListSize = totalListSize;
	}
	
	
	
}
