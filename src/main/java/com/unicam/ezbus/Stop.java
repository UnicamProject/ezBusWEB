package com.unicam.ezbus;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Stop {
	
	private String id;
	private String idCompany;
	@NotNull
	@Size(min=2, max=30)
	private String name;
	@NotNull
	private Position position;
	

    public Stop() { }
    
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
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
	
	public Position getPosition() {
		return position;
	}
	
	public void setPosition(Position position) {
		this.position = position;
	}

}