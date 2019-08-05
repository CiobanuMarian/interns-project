package com.kronsoft.internship.ui.rest.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.kronsoft.internship.ui.entities.enums.PatientSex;

public class PatientDto {

	private Long id;

	private String firstName;

	private String lastName;

	private Date birthDate;

	private String pin;

	private PatientSex sex;

	private String city;

	private String country;

	private String phoneNumber;

	List<AppointmentDto> appointments = new ArrayList<>();

	public List<AppointmentDto> getAppointments() {
		return appointments;
	}

	public void setAppointments(List<AppointmentDto> appointments) {
		this.appointments = appointments;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public String getPin() {
		return pin;
	}

	public void setPin(String pin) {
		this.pin = pin;
	}

	public PatientSex getSex() {
		return sex;
	}

	public void setSex(PatientSex sex) {
		this.sex = sex;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	@Override
	public String toString() {
	    return String.valueOf(id);
	}

	@Override
	public boolean equals(Object object) {
	    // TODO: Warning - this method won't work in the case the id fields are not set
	    if (!(object instanceof PatientDto)) {
	        return false;
	    }
	    PatientDto other = (PatientDto) object;
	    if ((this.id == null && other.id != null) || (this.id!= null && !this.id.equals(other.id))) {
	        return false;
	    }
	    return true;
	}
}
