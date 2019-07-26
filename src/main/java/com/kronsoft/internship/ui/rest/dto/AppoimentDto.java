package com.kronsoft.internship.ui.rest.dto;


import java.util.Date;

import com.kronsoft.internship.ui.entities.enums.AppoimentStatus;
import com.kronsoft.internship.ui.entities.enums.AppoimentType;




public class AppoimentDto {
	private Long id;
	
	private AppoimentType appoimentType;
	
	private AppoimentStatus appoimentStatus;
	
	private Date startTime;
	
	private Date endTime;
	
	private String appoimentDescription;
	
	private PatientDto patient;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public AppoimentType getAppoimentType() {
		return appoimentType;
	}

	public void setAppoimentType(AppoimentType appoimentType) {
		this.appoimentType = appoimentType;
	}

	public AppoimentStatus getAppoimentStatus() {
		return appoimentStatus;
	}

	public void setAppoimentStatus(AppoimentStatus appoimentStatus) {
		this.appoimentStatus = appoimentStatus;
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

	public String getAppoimentDescription() {
		return appoimentDescription;
	}

	public void setAppoimentDescription(String appoimentDescription) {
		this.appoimentDescription = appoimentDescription;
	}

	public PatientDto getPatient() {
		return patient;
	}

	public void setPatient(PatientDto patient) {
		this.patient = patient;
	}


	
}
