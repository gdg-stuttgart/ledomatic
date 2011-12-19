package com.ledomatic.server;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Device {

	@Id
	private String id;
	private boolean status;
	private List<Pin> pins = new ArrayList<Pin>();

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public List<Pin> getPins() {
		return pins;
	}

	public void setPins(List<Pin> pins) {
		this.pins = pins;
	}

}
