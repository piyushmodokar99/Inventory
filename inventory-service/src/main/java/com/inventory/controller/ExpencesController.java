package com.inventory.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.inventory.dto.ExpencesDTO;
import com.inventory.model.GraphResp;
import com.inventory.service.IExpencesService;

@RestController
@RequestMapping(value = "/expences")
public class ExpencesController 
{
	@Autowired
	IExpencesService expencesService; 
	
	@GetMapping("/graph-data")
	public List<GraphResp> getGraphDetails()
	{
		return expencesService.getGraphDetails();
	}
	
	@PostMapping("/add-exp")
	public void addExpences(@RequestBody ExpencesDTO req)
	{
		System.out.println("req amt" + req.getIeAmt());
		System.out.println("req date" + req.getIeDate());
		expencesService.addExpences(req);
	}
}
