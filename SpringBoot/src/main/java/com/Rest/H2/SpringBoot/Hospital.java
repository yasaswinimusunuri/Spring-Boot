package com.Rest.H2.SpringBoot;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;

 //Lombok annotation to create all the getters, setters, equals, hash, and toString methods, based on the fields.
@Entity //JPA annotation to make this object ready for storage in a JPA-based data store.
public class Hospital {
	
	
	private @Id @GeneratedValue int hospitalId;
	private String hospitalName;
	private String location;
	
	public Hospital() {
	}

	public Hospital(String hospitalName, String location) {
		this.hospitalName = hospitalName;
		this.location = location;
	}

	public int getHospitalId() {
		return hospitalId;
	}

	public void setHospitalId(int hospitalId) {
		this.hospitalId = hospitalId;
	}

	public String getHospitalName() {
		return hospitalName;
	}

	public void setHospitalName(String hospitalName) {
		this.hospitalName = hospitalName;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
	
	
	

}
