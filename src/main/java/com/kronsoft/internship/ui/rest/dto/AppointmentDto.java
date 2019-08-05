package com.kronsoft.internship.ui.rest.dto;


import java.util.Date;

import com.kronsoft.internship.ui.entities.enums.AppointmentStatus;
import com.kronsoft.internship.ui.entities.enums.AppointmentType;

public class AppointmentDto {
	private Long id;
	
	private AppointmentType appointmentType;
	
	private AppointmentStatus appointmentStatus;
	
	private Date startTime;
	
	private Date endTime;
	
	private String appointmentDescription;
	
	private PatientDto patient;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public AppointmentType getAppointmentType() {
		return appointmentType;
	}

	public void setAppointmentType(AppointmentType appointmentType) {
		this.appointmentType = appointmentType;
	}

	public AppointmentStatus getAppointmentStatus() {
		return appointmentStatus;
	}

	public void setAppointmentStatus(AppointmentStatus appointmentStatus) {
		this.appointmentStatus = appointmentStatus;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getAppointmentDescription() {
		return appointmentDescription;
	}

	public void setAppointmentDescription(String appointmentDescription) {
		this.appointmentDescription = appointmentDescription;
	}

	public PatientDto getPatient() {
		return patient;
	}

	public void setPatient(PatientDto patient) {
		this.patient = patient;
	}

}
