package com.inventory.service;

import java.util.List;

import com.inventory.dto.ExpencesDTO;
import com.inventory.model.GraphResp;

public interface IExpencesService 
{
	List<GraphResp> getGraphDetails();

	void addExpences(ExpencesDTO req);
}
