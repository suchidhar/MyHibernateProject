package com.caps.dev.beans;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="desktop_one2one")
public class Desktop {
	
	@Id @Column
	private int id;
	
	@Column
	private String model;
	
	@Column
	private String colour;
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="cid", referencedColumnName="cid")
	private CPU cpu;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getColour() {
		return colour;
	}

	public void setColour(String colour) {
		this.colour = colour;
	}

	public CPU getCpu() {
		return cpu;
	}

	public void setCpu(CPU cpu) {
		this.cpu = cpu;
	}

	@Override
	public String toString() {
		return "Desktop [id=" + id + ", model=" + model + ", colour=" + colour + ", cpu=" + cpu + "]";
	}
	
}
