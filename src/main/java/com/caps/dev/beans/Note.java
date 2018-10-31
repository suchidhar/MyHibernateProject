package com.caps.dev.beans;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="notes_info")
public class Note {
	
	@Id
	@Column(name="msg")
	private String msg;
	
	@Column(name="color")
	private String color;

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	@Override
	public String toString() {
		return "Note [msg=" + msg + ", color=" + color + "]";
	}
}