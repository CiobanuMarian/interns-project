package com.kronsoft.internship.ui.model;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.component.datatable.DataTable;

import com.kronsoft.internship.ui.entities.enums.AppointmentStatus;
import com.kronsoft.internship.ui.entities.enums.AppointmentType;
import com.kronsoft.internship.ui.rest.dto.AppointmentDto;
import com.kronsoft.internship.ui.rest.dto.PatientDto;

@ManagedBean
@ViewScoped
public class AppointmentModel {
	private List<AppointmentDto> appointments;
	
	private DataTable appointmentsTable;

	private AppointmentDto newAppointment = new AppointmentDto();

	private AppointmentDto updatedAppointment= new AppointmentDto();
	
	private boolean initFromDB;

	private AppointmentDto selectedAppointment;
	
	private List<AppointmentDto> patientAppointments;
	
	private List<AppointmentDto> filteredAppointments;

	public List<AppointmentDto> getAppointments() {
		return appointments;
	}

	public void setAppointments(List<AppointmentDto> appointments) {
		this.appointments = appointments;
	}

	public DataTable getAppointmentsTable() {
		return appointmentsTable;
	}

	public void setAppointmentsTable(DataTable appointmentsTable) {
		this.appointmentsTable = appointmentsTable;
	}

	public AppointmentDto getNewAppointment() {
		return newAppointment;
	}

	public void setNewAppointment(AppointmentDto newAppointment) {
		this.newAppointment = newAppointment;
	}

	public AppointmentDto getUpdatedAppointment() {
		return updatedAppointment;
	}

	public void setUpdatedAppointment(AppointmentDto updatedAppointment) {
		this.updatedAppointment = updatedAppointment;
	}

	public boolean isInitFromDB() {
		return initFromDB;
	}

	public void setInitFromDB(boolean initFromDB) {
		this.initFromDB = initFromDB;
	}

	public AppointmentDto getSelectedAppointment() {
		return selectedAppointment;
	}

	public void setSelectedAppointment(AppointmentDto selectedAppointment) {
		this.selectedAppointment = selectedAppointment;
	}

	public List<AppointmentDto> getPatientAppointments() {
		return patientAppointments;
	}

	public void setPatientAppointments(List<AppointmentDto> patientAppointments) {
		this.patientAppointments = patientAppointments;
	}

	public List<AppointmentDto> getFilteredAppointments() {
		return filteredAppointments;
	}

	public void setFilteredAppointments(List<AppointmentDto> filteredAppointments) {
		this.filteredAppointments = filteredAppointments;
	}
	public AppointmentType[] getTypes() {
		return AppointmentType.values();
	}

	public AppointmentStatus[] getStatuses() {
		return AppointmentStatus.values();
	}
	
}
