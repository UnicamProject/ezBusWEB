package com.unicam.ezbus;

import java.util.UUID;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Company {
	
	private String id;
	@NotNull
    private String name;
    @NotNull
    @Size(min=11)
    private int iva;
	@NotNull
	@Size(min=5)
    private String email;
	@NotNull
	@Size(min=8)
    private String password;

	
	
    public Company() { }
    
    public String getId() {
		return id;
	}

	public void setId() {
    	this.id = UUID.randomUUID().toString();
	}

	public String getCompany() {
		return name;
	}

	public void setCompany(String name) {
    	this.name = name;
	}

	public int getIva() {
		return iva;
	}

	public void setIva(int iva) {
		this.iva = iva;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPass() {
		return password;
	}

	public void setPass(String password) {
		this.password = password;
	}
}