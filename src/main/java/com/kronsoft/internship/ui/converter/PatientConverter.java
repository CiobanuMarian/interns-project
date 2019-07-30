package com.kronsoft.internship.ui.converter;

import java.util.List;
import java.util.Optional;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.kronsoft.internship.ui.rest.PatientRestService;
import com.kronsoft.internship.ui.rest.dto.PatientDto;

@FacesConverter(value = "patientConverter", forClass = PatientDto.class)
public class PatientConverter implements Converter {
	private static PatientRestService patientRestService = PatientRestService.getInstance();
	private static final Logger LOGGER = LogManager.getLogger(PatientConverter.class);

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String submittedValue) {
		if (submittedValue == null || submittedValue.isEmpty()) {
			return null;
		}
		try {
			// Find the patient from the server based on the id recived
			List<PatientDto> patients = patientRestService.gellAllPatients();
			Optional<PatientDto> patientOpt = patients.stream()
					.filter(patient -> patient.getId() == Long.valueOf(submittedValue)).findFirst();
			LOGGER.info(submittedValue + " VS " + patientOpt.get().getId());
			return patientOpt.get();
		} catch (NumberFormatException e) {
			throw new ConverterException(new FacesMessage(submittedValue + " is not a valid Patient Id"), e);
		}
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object modelValue) {
		if (modelValue == null) {
			return "";
		}
		if (modelValue instanceof PatientDto) {
			return String.valueOf(((PatientDto) modelValue).getId());
		} else {
			throw new ConverterException(new FacesMessage(modelValue + " is not a valid Patient"));
		}
	}

}
