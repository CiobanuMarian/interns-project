package com.kronsoft.internship.ui.controller;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import com.kronsoft.internship.ui.model.PatientModel;


@ManagedBean
@RequestScoped
public class PatientController {
	@ManagedProperty(value = "#{patientModel}")
	private PatientModel model;
}
