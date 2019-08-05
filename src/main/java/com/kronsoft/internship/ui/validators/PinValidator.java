package com.kronsoft.internship.ui.validators;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator
public class PinValidator implements Validator {

	private static final String PIN_PATTERN = "^(?!\\s*$)[0-9\\s]{13}$";

	private Pattern pattern = Pattern.compile(PIN_PATTERN);
	private Matcher matcher;

	@Override
	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		matcher=pattern.matcher(value.toString());
		
		if(!matcher.matches()) {
			FacesMessage message= new FacesMessage("Invalid format!");
			message.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(message);
		
	}
	}
}
