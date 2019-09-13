package com.unicam.ezbus;

import java.util.UUID;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class Company {
	
	private String id;
	@Size(min=4, max=30)
    private String name;
    @Size(min=11, max=11)
    private String iva;
    @NotEmpty
	@Email
    private String email;

	
    public Company() { }
    
    public Company(String id, String name, String iva, String email) { 
    	this.id = id;
    	this.name = name;
    	this.iva = iva;
    	this.email = email;
    }
    
    public String getId() {
		return id;
	}

	public void setId() {
    	this.id = UUID.randomUUID().toString();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
    	this.name = name;
	}

	public String getIva() {
		return iva;
	}

	public void setIva(String iva) {
		this.iva = iva;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
}