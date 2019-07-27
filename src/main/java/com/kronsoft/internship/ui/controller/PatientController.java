package com.kronsoft.internship.ui.controller;

import java.util.Hashtable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;

import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;

import com.kronsoft.internship.ui.model.PatientModel;
import com.kronsoft.internship.ui.rest.PatientRestService;
import com.kronsoft.internship.ui.rest.dto.PatientDto;

@ManagedBean
@RequestScoped
public class PatientController {
	@ManagedProperty(value = "#{patientModel}")
	private PatientModel model;

	private static PatientRestService patientRestService = PatientRestService.getInstance();

	@PostConstruct
	public void init() {
		if (!model.isInitFromDB()) {
			model.setPatients(patientRestService.gellAllPatients());
			model.setInitFromDB(true);
		}
	}

	public void deletePatient() {
		PatientDto deletedPatient = model.getSelectedPatient();
		patientRestService.deletePatient(deletedPatient.getId());
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage("Deleted Patient " + deletedPatient.getId()));
	}

	public void createPatient() {

		PatientDto createdPatient = patientRestService.createPatient(model.getNewPatient());
		model.getPatients().add(createdPatient);
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Created client succesfully!"));

	}


	public PatientModel getModel() {
		return model;
	}

	public void setModel(PatientModel model) {
		this.model = model;
	}

	public void onRowEdit(RowEditEvent event) {
//		int patientId=((PatientDto) event.getObject()).getId().intValue();
//		patientRestService.updatePatient(model.getPatients().get(patientId));
		FacesMessage msg = new FacesMessage("Edited Patient: " +((PatientDto) event.getObject()).getId());
		FacesContext.getCurrentInstance().addMessage(null, msg);
		
		patientRestService.updatePatient(model.getSelectedPatient());
	}

	public void onRowCancel(RowEditEvent event) {
		FacesMessage msg = new FacesMessage("Edit Cancelled"+ ((PatientDto) event.getObject()).getId());
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

}
