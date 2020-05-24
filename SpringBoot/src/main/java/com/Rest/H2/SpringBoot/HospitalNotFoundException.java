package com.Rest.H2.SpringBoot;

public class HospitalNotFoundException extends RuntimeException {

	HospitalNotFoundException(int id) {
	    super("Could not find employee " + id);
	  }
}
