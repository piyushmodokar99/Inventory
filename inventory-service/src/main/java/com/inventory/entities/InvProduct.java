package com.inventory.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "inv_products", schema = "inventory")
public class InvProduct 
{
	@Id
	@SequenceGenerator(name = "SEQ_INV_PRODUCT_ID", sequenceName = "inventory.seq_inv_product_id", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_INV_PRODUCT_ID")
	@Column(name = "ip_id")
	private Integer ipId;
	
	@Column(name = "ip_code")
	private String ipCode;
	
	@Column(name = "ip_name")
	private String ipName;
	
	@Column(name = "ip_brand")
	private String ipBrand;
	
	@Column(name = "ip_price")
	private Float ipPrice;
	
	@Column(name = "ip_type")
	private String ipType;

	public Integer getIpId() {
		return ipId;
	}

	public void setIpId(Integer ipId) {
		this.ipId = ipId;
	}

	public String getIpCode() {
		return ipCode;
	}

	public void setIpCode(String ipCode) {
		this.ipCode = ipCode;
	}

	public String getIpName() {
		return ipName;
	}

	public void setIpName(String ipName) {
		this.ipName = ipName;
	}

	public String getIpBrand() {
		return ipBrand;
	}

	public void setIpBrand(String ipBrand) {
		this.ipBrand = ipBrand;
	}

	public Float getIpPrice() {
		return ipPrice;
	}

	public void setIpPrice(Float ipPrice) {
		this.ipPrice = ipPrice;
	}

	public String getIpType() {
		return ipType;
	}

	public void setIpType(String ipType) {
		this.ipType = ipType;
	}
	
}
