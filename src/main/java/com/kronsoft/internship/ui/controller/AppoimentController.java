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
import org.primefaces.event.RowEditEvent;

import com.kronsoft.internship.ui.model.AppoimentModel;
import com.kronsoft.internship.ui.rest.AppoimentRestService;
import com.kronsoft.internship.ui.rest.dto.AppoimentDto;

@ManagedBean
@RequestScoped
public class AppoimentController {
	@ManagedProperty(value = "#{appoimentModel}")
	private AppoimentModel model;

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

	public void onRowEdit(RowEditEvent event) {

		List<AppoimentDto> appoiments = model.getAppoiments();
		int appoimentId = ((AppoimentDto) event.getObject()).getId().intValue();
		Optional<AppoimentDto> updatedAppoimentDto = appoiments.stream().filter(appoiment -> appoiment.getId() == appoimentId)
				.findFirst();
		AppoimentDto updatedAppoiment = updatedAppoimentDto.get();

		FacesMessage msg = new FacesMessage("Edited Appoiment: " + (updatedAppoiment.getId()));
		LOGGER.info("Updating Appoiment with id: " + updatedAppoiment.getId());
		LOGGER.info(updatedAppoiment.getAppoimentType()+ "\n"+ updatedAppoiment.getAppoimentStatus());
		LOGGER.info("Updating Appoiment with id: " + updatedAppoiment.getId());
		
		FacesContext.getCurrentInstance().addMessage(null, msg);
		appoimentRestService.updateAppoiment(updatedAppoiment);
	}

	public void onRowCancel(RowEditEvent event) {

		FacesMessage msg = new FacesMessage("Edit Cancelled" + ((AppoimentDto) event.getObject()).getId());
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}


}