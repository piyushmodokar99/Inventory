package com.inventory.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.inventory.constants.InvConst;
import com.inventory.dto.ProductDTO;
import com.inventory.exception.InventoryCommonException;
import com.inventory.model.InvCommonResponse;
import com.inventory.model.ListProductReq;
import com.inventory.service.IProductService;

@RestController
@CrossOrigin
@RequestMapping(value = "/product")
public class ProductController 
{
	private static final Logger log = LoggerFactory.getLogger(ProductController.class);
	
	@Autowired
	IProductService productService;
	
	@PostMapping("/list")
	public InvCommonResponse getProductList(@RequestBody ListProductReq req)
	{
		try 
		{
			System.out.println("getProductList ..... ");
			return productService.getProducts(req);
		} 
		catch (InventoryCommonException e) 
		{
			e.printStackTrace();
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return new InvCommonResponse(0, "Fail", null, null, 0);
	}
	
	@GetMapping("/get-by-id")
	public InvCommonResponse getProductsById(@RequestParam("ipId") Integer ipId)
	{
		log.info("@@inside getProductsById " + ipId);
		if(ipId == null) return new InvCommonResponse(0, "ID is required", null, null, 0);
		ProductDTO res = null;
		try 
		{
			res = productService.getProductsById(ipId);
			log.info("Resp : " + res.getIpId());
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return new InvCommonResponse(1, "Success", null, res, 0);
	}
	
	@PostMapping("/update")
	public InvCommonResponse updateProduct(@RequestBody ProductDTO req) 
	{
		log.info("@inside updateProduct " + req.getIpId());
		log.info("@inside updateProduct Name :" + req.getIpName());
		
		if(req == null || req.getIpId() == null)
		{
			return new InvCommonResponse(0, "Invalid request", null, null, 0);
		}
		else
		{
			productService.updateProduct(req, InvConst.UPDATE);
		}
		return new InvCommonResponse(1, "Success", null, null, 0);
	}
	
	@PostMapping("/add")
	public InvCommonResponse addProduct(@RequestBody ProductDTO req) 
	{
		
		log.info("@inside addProduct Name :" + req.getIpName());
		
		if(req == null || req.getIpCode() == null)
		{
			return new InvCommonResponse(0, "Invalid request", null, null, 0);
		}
		else
		{
			productService.updateProduct(req, InvConst.ADD);
		}
		return new InvCommonResponse(1, "Success", null, null, 0);
	}
}
