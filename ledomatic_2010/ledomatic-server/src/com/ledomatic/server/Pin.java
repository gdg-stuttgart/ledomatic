package com.ledomatic.server;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Pin {
	@Id
	@GeneratedValue
	private Integer id;
	@Enumerated(EnumType.ORDINAL)
	private InputType inputType;
	private String pinId;
	private boolean status;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public InputType getInputType() {
		return inputType;
	}

	public void setInputType(InputType inputType) {
		this.inputType = inputType;
	}

	public String getPinId() {
		return pinId;
	}

	public void setPinId(String pinId) {
		this.pinId = pinId;
	}

	public void toogleStatus() {
		if (status) {
			status = false;
		} else {
			status = true;
		}
	}

	public boolean isStatus() {
		return status;
	}

}
