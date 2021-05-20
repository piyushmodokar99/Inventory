package com.inventory.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.inventory.dto.ProductDTO;
import com.inventory.entities.InvProduct;
import com.inventory.exception.InventoryCommonException;
import com.inventory.model.InvCommonResponse;
import com.inventory.model.ListProductReq;
import com.inventory.repository.ProductPagingRepo;
import com.inventory.repository.ProductRepo;
import com.inventory.service.IProductService;

@Service
public class ProductServiceImpl implements IProductService 
{

	private static final Logger log = LoggerFactory.getLogger(ProductServiceImpl.class);
	
	@Autowired
	ProductPagingRepo productPagingRepo;
	
	@Autowired
	ProductRepo productRepo;
	
	@Override
	public InvCommonResponse getProducts(ListProductReq req) throws InventoryCommonException 
	{
		List<ProductDTO> prodList = null;
		ProductDTO dto = null;
		
		InvCommonResponse respone = null;
		log.info("req : " + req.getPage() + "req : Size : " + req.getSize());
		try 
		{
			Integer tls = (int) productRepo.count();
			PageRequest pageRequest = PageRequest.of(req.getPage(), req.getSize());
			List<InvProduct> invProduct = productPagingRepo.findAll(pageRequest).toList();
			//ByIpType("Electronic", pageRequest);
			
			log.info("Total List Size " + tls);
			
			respone = new InvCommonResponse(); 
			
			if(invProduct != null && invProduct.size() > 0)
			{
				log.info("Size " + invProduct.size());
				prodList =  new ArrayList<ProductDTO>();
				for(InvProduct pDto: invProduct)
				{
					dto = new ProductDTO();
					
					dto.setIpId(pDto.getIpId());
					dto.setIpCode(pDto.getIpCode());
					dto.setIpName(pDto.getIpName());
					dto.setIpBrand(pDto.getIpBrand());
					dto.setIpPrice(pDto.getIpPrice());
					dto.setIpType(pDto.getIpType());
					prodList.add(dto);
				}
				
				respone.setProducts(prodList);
				respone.setTotalListSize(tls);
			}
			else
			{
				throw new InventoryCommonException("Products not found");
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			throw new InventoryCommonException(e.getMessage());
		}
		return respone;
	}

	@Override
	public ProductDTO getProductsById(Integer ipId) throws InventoryCommonException 
	{
		ProductDTO dto = null;
		try 
		{
			InvProduct invProduct = productRepo.findById(ipId).get();
			if(invProduct == null) throw new InventoryCommonException("No Data Found for Requested ID " + ipId);
			
			dto = new ProductDTO();
			dto.setIpId(invProduct.getIpId());
			dto.setIpCode(invProduct.getIpCode());
			dto.setIpName(invProduct.getIpName());
			dto.setIpBrand(invProduct.getIpBrand());
			dto.setIpPrice(invProduct.getIpPrice());
			dto.setIpType(invProduct.getIpType());
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			throw new InventoryCommonException(e.getMessage());
		}
		
		return dto;
	}

	@Override
	public void updateProduct(ProductDTO req, int addUpdFlag) throws InventoryCommonException 
	{
		InvProduct invProduct = null;
		try 
		{
			invProduct = new InvProduct();
			if(addUpdFlag == 2) invProduct = productRepo.findById(req.getIpId()).get();
			
			invProduct.setIpCode(req.getIpCode());
			invProduct.setIpBrand(req.getIpBrand());
			invProduct.setIpName(req.getIpName());
			invProduct.setIpPrice(req.getIpPrice());
			invProduct.setIpType(req.getIpType());
			
			productRepo.save(invProduct);
			if(addUpdFlag == 2) log.info("Product Updated");
			log.info("Product Added");
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	
}
