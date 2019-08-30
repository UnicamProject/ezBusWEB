package com.unicam.ezbus;

public class Route {
	
    private long id;
    private long idCompany;
    private String city;
    private String name;
    private double price;
    private int validity;
	
    public Route(long id, long idCompany, String city, String name, double price, int validity) {
		this.id = id;
		this.idCompany = idCompany;
		this.city = city;
		this.name = name;
		this.price = price;
		this.validity = validity;
	}
    
    // Getters and Setters ----------------------
    
    public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getIdCompany() {
		return idCompany;
	}

	public void setIdCompany(long idCompany) {
		this.idCompany = idCompany;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
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
