package com.unicam.ezbus;

import javax.validation.constraints.NotNull;

public class Position {

	@NotNull
	private double coordX;
	@NotNull
	private double coordY;
	
	
    public Position() { }
	
	public double getCoordX() {
		return coordX;
	}
	
	public void setCoordX(double coordX) {
		this.coordX = coordX;
	}
	
	public double getCoordY() {
		return coordY;
	}
	
	public void setCoordY(double coordY) {
		this.coordY = coordY;
	}

}