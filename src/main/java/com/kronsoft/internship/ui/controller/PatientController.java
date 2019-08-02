package com.kronsoft.internship.ui.controller;

import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;

import com.kronsoft.internship.ui.model.PatientModel;
import com.kronsoft.internship.ui.rest.PatientRestService;
import com.kronsoft.internship.ui.rest.dto.AppoimentDto;
import com.kronsoft.internship.ui.rest.dto.PatientDto;

@ManagedBean
@RequestScoped
public class PatientController {
	@ManagedProperty(value = "#{patientModel}")
	private PatientModel model;

	private static PatientRestService patientRestService = PatientRestService.getInstance();
	private static final Logger LOGGER = LogManager.getLogger(PatientController.class);

	@PostConstruct
	public void init() {
		if (!model.isInitFromDB()) {
			model.setPatients(patientRestService.gellAllPatients());
			model.setInitFromDB(true);
		}
	}

	public void deletePatient() {

		PatientDto deletedPatient = model.getSelectedPatient();
		LOGGER.info("Deleting Patient with id: " + deletedPatient.getId());
		patientRestService.deletePatient(deletedPatient.getId());
		model.getPatients().remove(deletedPatient);
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage("Deleted Patient " + deletedPatient.getId()));
	}

	public void createPatient() {

		PatientDto createdPatient = patientRestService.createPatient(model.getNewPatient());
		model.getPatients().add(createdPatient);
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Created client succesfully!"));
		LOGGER.info("Creating patient with id:"+createdPatient.getId());

	}

	public PatientModel getModel() {
		return model;
	}

	public void setModel(PatientModel model) {
		this.model = model;
	}

	public void updatePatient() {
		PatientDto patientToBeUpdated= model.getUpdatedPatient();
		patientRestService.updatePatient(patientToBeUpdated);
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Changes Saved!"));
	}
}
