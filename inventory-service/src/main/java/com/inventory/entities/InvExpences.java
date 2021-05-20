package com.inventory.entities;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
@Entity
@Table(name = "inv_expences", schema = "inventory")
public class InvExpences 
{
	@Id
	@SequenceGenerator(name = "SEQ_INV_EXPENCE_ID", sequenceName = "inventory.seq_inv_expence_id", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_INV_EXPENCE_ID")
	@Column(name = "ie_id")
	private Integer ieId;
	
	@Column(name = "ie_date")
	private String ieDate;
	
	@Column(name = "ie_amt")
	private Integer ieAmt;
	
	@Column(name = "ie_desc")
	private String ieDesc;
	
	@Column(name = "ie_crt_tm")
	private Timestamp ieCrtTm;
	
	@Column(name = "ie_crt_by")
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
