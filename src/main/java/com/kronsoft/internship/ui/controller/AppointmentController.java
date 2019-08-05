package com.kronsoft.internship.ui.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.kronsoft.internship.ui.model.AppointmentModel;
import com.kronsoft.internship.ui.model.PatientModel;
import com.kronsoft.internship.ui.rest.AppointmentRestService;
import com.kronsoft.internship.ui.rest.dto.AppointmentDto;
import com.kronsoft.internship.ui.rest.dto.PatientDto;

@ManagedBean
@RequestScoped
public class AppointmentController {
	@ManagedProperty(value = "#{appointmentModel}")
	private AppointmentModel model;

	@ManagedProperty(value = "#{patientModel}")
	private PatientModel modelPatient;

	private static AppointmentRestService appointmentRestService = AppointmentRestService.getInstance();
	private static final Logger LOGGER = LogManager.getLogger(AppointmentController.class);

	@PostConstruct
	public void init() {
		if (!model.isInitFromDB()) {
			model.setAppointments(appointmentRestService.getAllAppointments());
			model.setInitFromDB(true);
		}
	}

	public void deleteAppointment() {

		AppointmentDto deletedAppointment = model.getSelectedAppointment();
		LOGGER.info("Deleting Appointment with id: " + deletedAppointment.getId());
		appointmentRestService.deleteAppointment(deletedAppointment.getId());
		model.getAppointments().remove(deletedAppointment);
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage("Deleted Appointment " + deletedAppointment.getId()));
	}

	public void createAppointment() {

		AppointmentDto createdAppointment = appointmentRestService.createAppointment(model.getNewAppointment());
		model.getAppointments().add(createdAppointment);
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Created appointment succesfully!"));
		LOGGER.info("Creating appointment with id:" + createdAppointment.getId());

	}

	public AppointmentModel getModel() {
		return model;
	}

	public void setModel(AppointmentModel model) {
		this.model = model;
	}

	public void updateAppointment() {
		AppointmentDto appointmentToBeUpdated = model.getUpdatedAppointment();
		appointmentRestService.updateAppointment(appointmentToBeUpdated);
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Changes Saved!"));
	}

	public void cancelUpdate() {
		model.setUpdatedAppointment(new AppointmentDto());
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Edit canceled!"));
	}

	public PatientModel getModelPatient() {
		return modelPatient;
	}

	public void setModelPatient(PatientModel modelPatient) {
		this.modelPatient = modelPatient;
	}

	public void findPatientAppointments() {

		PatientDto patient = modelPatient.getSelectedPatient();
		List<AppointmentDto> appointments = new ArrayList<AppointmentDto>();

		Stream<AppointmentDto> appoimentsStream = model.getAppointments().stream()
				.filter(appointment -> appointment.getPatient().getId() == patient.getId());
		appoimentsStream.forEach(appointment -> appointments.add(appointment));
		model.setPatientAppointments(appointments);

	}
}
