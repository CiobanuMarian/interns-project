package com.kronsoft.internship.ui.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.primefaces.event.RowEditEvent;

import com.kronsoft.internship.ui.model.AppoimentModel;
import com.kronsoft.internship.ui.model.PatientModel;
import com.kronsoft.internship.ui.rest.AppoimentRestService;
import com.kronsoft.internship.ui.rest.dto.AppoimentDto;
import com.kronsoft.internship.ui.rest.dto.PatientDto;

@ManagedBean
@RequestScoped
public class AppoimentController {
	@ManagedProperty(value = "#{appoimentModel}")
	private AppoimentModel model;

	
	@ManagedProperty(value = "#{patientModel}")
	private PatientModel modelPatient;

	private static AppoimentRestService appoimentRestService = AppoimentRestService.getInstance();
	private static final Logger LOGGER = LogManager.getLogger(AppoimentController.class);

	@PostConstruct
	public void init() {
		if (!model.isInitFromDB()) {
			model.setAppoiments(appoimentRestService.gellAllAppoiments());
			model.setInitFromDB(true);
		}
	}
	public void deleteAppoiment() {

		AppoimentDto deletedAppoiment = model.getSelectedAppoiment();
		LOGGER.info("Deleting Appoiment with id: " + deletedAppoiment.getId());
		appoimentRestService.deleteAppoiment(deletedAppoiment.getId());
		model.getAppoiments().remove(deletedAppoiment);
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage("Deleted Appoiment " + deletedAppoiment.getId()));
	}

	public void createAppoiment() {

		AppoimentDto createdAppoiment = appoimentRestService.createAppoiment(model.getNewAppoiment());
		model.getAppoiments().add(createdAppoiment);
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Created appoiment succesfully!"));
		LOGGER.info("Creating appoiment with id:"+createdAppoiment.getId());

	}
	public AppoimentModel getModel() {
		return model;
	}

	public void setModel(AppoimentModel model) {
		this.model = model;
	}

	public void updateAppoiment() {
		AppoimentDto appoimentToBeUpdated= model.getUpdatedAppoiment();
		appoimentRestService.updateAppoiment(appoimentToBeUpdated);
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Changes Saved!"));
	}
	
	public void cancelUpdate() {
		model.setUpdatedAppoiment(new AppoimentDto());
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Edit canceled!"));
	}
	public PatientModel getModelPatient() {
		return modelPatient;
	}
	public void setModelPatient(PatientModel modelPatient) {
		this.modelPatient = modelPatient;
	}

	public void findPatientAppoiments() {
//		PatientDto patient=modelPatient.getSelectedPatient();
//		List<AppoimentDto> appoiments=appoimentRestService.showPatientAppoiments(patient);
//		model.setAppoiments(appoiments);
		
		PatientDto patient=modelPatient.getSelectedPatient();
		List<AppoimentDto>appoiments=new ArrayList<AppoimentDto>();
		Stream<AppoimentDto>appoimentsStream= model.getAppoiments().stream().filter(appoiment->appoiment.getPatient().getId()==patient.getId());
		
		appoimentsStream.forEach(appoiment->appoiments.add(appoiment));
		model.setPatientAppoiments(appoiments);
		
	}
}
