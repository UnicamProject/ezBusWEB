package com.unicam.ezbus;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Pass {
	
	@NotNull
	@Size(min=2, max=20)
	private String city;
	@NotNull
	@Size(min=2, max=40)
	private String companyId;
	@NotNull
	@Size(min=2, max=40)
	private String id; 
	@NotNull
	@Size(min=2, max=20)
	private String name; 
	@NotNull
	@Min(5)
	private double price;
	@NotNull
	@Min(7)
	private int validity;
    
    public Pass() {}
	
    public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
	
	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	
	public int getValidity() {
		return validity;
	}
	
	public void setValidity(int validity) {
		this.validity = validity;
	}  

}
