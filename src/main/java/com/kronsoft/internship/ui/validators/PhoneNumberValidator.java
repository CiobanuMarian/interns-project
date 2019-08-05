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
public class PhoneNumberValidator implements Validator{

	private static final String PIN_PATTERN = "^07[0-9]{8}";

	private Pattern pattern = Pattern.compile(PIN_PATTERN);
	private Matcher matcher;

	@Override
	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		matcher = pattern.matcher(value.toString());

		if (!matcher.matches()) {
			FacesMessage message = new FacesMessage("Invalid phone number format!");
			message.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(message);

		}
	}
}
