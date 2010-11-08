package com.ledomatic.server;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

@Entity
public class Device {

	@Id
	private String id;
	private boolean status;
	@Enumerated(EnumType.ORDINAL)
	private InputType inputType;
	private String pinnId;

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

	public InputType getInputType() {
		return inputType;
	}

	public void setInputType(InputType inputType) {
		this.inputType = inputType;
	}

	public String getPinnId() {
		return pinnId;
	}

	public void setPinnId(String pinnId) {
		this.pinnId = pinnId;
	}

}
