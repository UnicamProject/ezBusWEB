package com.unicam.ezbus;

public class Pass {

	private String city;
    private String companyId;
    private String id; 
    private String name; 
    private double price;
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
