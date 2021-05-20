package com.inventory.dto;

import java.sql.Timestamp;

public class ExpencesDTO 
{
	private Integer ieId;
	private String ieDate;
	private Integer ieAmt;
	private String ieDesc;
	private Timestamp ieCrtTm;
	private Integer ieCrtBy;
	
	public Integer getIeId() {
		return ieId;
	}
	public void setIeId(Integer ieId) {
		this.ieId = ieId;
	}
	public String getIeDate() {
		return ieDate;
	}
	public void setIeDate(String ieDate) {
		this.ieDate = ieDate;
	}
	public Integer getIeAmt() {
		return ieAmt;
	}
	public void setIeAmt(Integer ieAmt) {
		this.ieAmt = ieAmt;
	}
	public String getIeDesc() {
		return ieDesc;
	}
	public void setIeDesc(String ieDesc) {
		this.ieDesc = ieDesc;
	}
	public Timestamp getIeCrtTm() {
		return ieCrtTm;
	}
	public void setIeCrtTm(Timestamp ieCrtTm) {
		this.ieCrtTm = ieCrtTm;
	}
	public Integer getIeCrtBy() {
		return ieCrtBy;
	}
	public void setIeCrtBy(Integer ieCrtBy) {
		this.ieCrtBy = ieCrtBy;
	}
	
	
}
