package com.kronsoft.internship.ui.model;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.component.datatable.DataTable;

import com.kronsoft.internship.ui.entities.enums.PatientSex;
import com.kronsoft.internship.ui.rest.dto.PatientDto;

@ManagedBean
@ViewScoped
public class PatientModel {
	private List<PatientDto> patients;

	private DataTable patientTable;

	private PatientDto newPatient = new PatientDto();
	private boolean initFromDB;

	private PatientDto selectedPatient;

	public List<PatientDto> getPatients() {
		return patients;
	}

	public void setPatients(List<PatientDto> patients) {
		this.patients = patients;
	}

	public DataTable getPatientTable() {
		return patientTable;
	}

	public void setPatientTable(DataTable patientTable) {
		this.patientTable = patientTable;
	}

	public PatientDto getNewPatient() {
		return newPatient;
	}

	public void setNewPatient(PatientDto newPatient) {
		this.newPatient = newPatient;
	}

	public boolean isInitFromDB() {
		return initFromDB;
	}

	public void setInitFromDB(boolean initFromDB) {
		this.initFromDB = initFromDB;
	}

	public PatientDto getSelectedPatient() {
		return selectedPatient;
	}

	public void setSelectedPatient(PatientDto selectedPatient) {
		this.selectedPatient = selectedPatient;
	}

	public PatientSex[] getSexes() {
		return PatientSex.values();
	}


//	public List<Long> getIds(){
//		List<Long> ids= new ArrayList<>();
//		patients.forEach(patient->ids.add(patient.getId()));
//		return ids;
//	}
	
}
