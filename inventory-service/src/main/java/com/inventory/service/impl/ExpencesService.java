package com.inventory.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inventory.dto.ExpencesDTO;
import com.inventory.entities.InvExpences;
import com.inventory.model.GraphResp;
import com.inventory.repository.ExpencesRepo;
import com.inventory.service.IExpencesService;

@Service
public class ExpencesService implements IExpencesService 
{
	@Autowired
	ExpencesRepo expencesRepo;
	
	@Override
	public List<GraphResp> getGraphDetails() 
	{
		List<GraphResp> list = new ArrayList<GraphResp>();
		
		Iterable<InvExpences> invExpences = expencesRepo.findAllByOrderByIeIdDesc();
		System.out.println(invExpences);
		
		GraphResp graphResp = null;
		for(InvExpences exp : invExpences)
		{
			graphResp = new GraphResp();
			System.out.println("Date : " + exp.getIeDate());
			graphResp.setXdata(exp.getIeDate());
			graphResp.setYdata(exp.getIeAmt());
			
			list.add(graphResp);
		}
		
		return list;
	}

	@Override
	public void addExpences(ExpencesDTO req) 
	{
		InvExpences invExpences = new InvExpences();
		invExpences.setIeDate(req.getIeDate());
		invExpences.setIeAmt(req.getIeAmt());
		invExpences.setIeDesc(req.getIeDesc());
		invExpences.setIeCrtTm(new Timestamp(System.currentTimeMillis()));
		invExpences.setIeCrtBy(3);
		
		expencesRepo.save(invExpences);
		
		System.out.println("Added");
	}	
}
