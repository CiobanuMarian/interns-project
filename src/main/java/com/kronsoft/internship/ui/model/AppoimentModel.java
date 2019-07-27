package com.kronsoft.internship.ui.model;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.component.datatable.DataTable;

import com.kronsoft.internship.ui.rest.dto.AppoimentDto;


@ManagedBean
@ViewScoped
public class AppoimentModel {
	private List<AppoimentDto> appoiments;

	private DataTable appoimentsTable;

	private AppoimentDto newAppoiment = new AppoimentDto();

	private boolean initFromDB;

	private AppoimentDto selectedAppoiment;

	public List<AppoimentDto> getAppoiments() {
		return appoiments;
	}

	public void setAppoiments(List<AppoimentDto> appoiments) {
		this.appoiments = appoiments;
	}

	public DataTable getAppoimentsTable() {
		return appoimentsTable;
	}

	public void setAppoimentsTable(DataTable appoimentsTable) {
		this.appoimentsTable = appoimentsTable;
	}

	public AppoimentDto getNewAppoiment() {
		return newAppoiment;
	}

	public void setNewAppoiment(AppoimentDto newAppoiment) {
		this.newAppoiment = newAppoiment;
	}

	public boolean isInitFromDB() {
		return initFromDB;
	}

	public void setInitFromDB(boolean initFromDB) {
		this.initFromDB = initFromDB;
	}

	public AppoimentDto getSelectedAppoiment() {
		return selectedAppoiment;
	}

	public void setSelectedAppoiment(AppoimentDto selectedAppoiment) {
		this.selectedAppoiment = selectedAppoiment;
	}
	
}
