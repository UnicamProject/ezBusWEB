package com.unicam.ezbus;

public class Route {
	
    private String id;
    private String idCompany;
    private String name;
    private String start;
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
