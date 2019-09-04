package com.unicam.ezbus;

import java.util.UUID;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Pass {

	private String id; 
	private String idCompany;
	@NotNull
	@Size(min=2, max=20)
	private String name; 
	@NotNull
	@Size(min=2, max=30)
	private String city;
	@NotNull
	@Min(5)
	private double price;
	@NotNull
	@Min(7)
	private int validity;
	
    
    public Pass() { }
	
	public String getId() {
		return id;
	}

	public void setId() {
    	this.id = UUID.randomUUID().toString();
	}
	
	public String getIdCompany() {
		return idCompany;
	}

	public void setIdCompany(String idCompany) {
		this.idCompany = idCompany;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
    public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
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