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

	private PatientSex Sex;

	private String city;

	private String country;

	private String phoneNumber;

	List<AppoimentDto> appoiments = new ArrayList<>();

	public List<AppoimentDto> getAppoiments() {
		return appoiments;
	}

	public void setAppoiments(List<AppoimentDto> appoiments) {
		this.appoiments = appoiments;
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
		return Sex;
	}

	public void setSex(PatientSex sex) {
		Sex = sex;
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

}
