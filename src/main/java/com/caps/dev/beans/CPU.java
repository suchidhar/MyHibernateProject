package com.caps.dev.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="cpu_one2one")
public class CPU {
	
	@Id @Column
	private int cid;
	@Column
	private String model;
	@Column
	private String processor;
	public int getCid() {
		return cid;
	}
	public void setCid(int cid) {
		this.cid = cid;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getProcessor() {
		return processor;
	}
	public void setProcessor(String processor) {
		this.processor = processor;
	}
	@Override
	public String toString() {
		return "CPU [cid=" + cid + ", model=" + model + ", processor=" + processor + "]";
	}
	
}
