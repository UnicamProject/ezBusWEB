package com.unicam.ezbus;

import javax.persistence.*;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name="positions")
@EntityListeners(AuditingEntityListener.class)

public class Position {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
	
	@Column(name = "coord_x", nullable = false)
    private long coordX;
	
	@Column(name = "coord_y", nullable = false)
    private long coordY;
	
	
	public long getId() {
		return id;	
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public long getCoordX() {
		return coordX;
	}
	
	public void setCoordX(long coordX)	{
		this.coordX = coordX;
	}
	
	public long getCoordY() {
		return coordY;
	}
	
	public void setCoordY(long coordY) {
		this.coordY = coordY;
	}

}
