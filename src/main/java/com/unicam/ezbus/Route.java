package com.unicam.ezbus;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Route {
	
	@NotNull
	@Size(min=2, max=40)
    private String id;
	@NotNull
	@Size(min=2, max=40)
    private String idCompany;
	@NotNull
	@Size(min=2, max=20)
    private String name;
	@NotNull
	@Size(min=2, max=40)
    private String start;
	@NotNull
	@Size(min=2, max=40)
    private String end;
	
    public Route() {}

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

	public String getStart() {
		return start;
	}

	public void setStart(String start) {
		this.start = start;
	}

	public String getEnd() {
		return end;
	}

	public void setEnd(String end) {
		this.end = end;
	}

}