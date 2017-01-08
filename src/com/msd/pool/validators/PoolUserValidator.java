package com.msd.pool.validators;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.msd.items.Applicant;

@Component
public class PoolUserValidator implements Validator {

	private Pattern pattern;
	private Matcher matcher;

	@Override
	public boolean supports(Class<?> c) {
		// Validate if the classes are the same
		return Applicant.class.equals(c);
	}

	@Override
	public void validate(Object subject, Errors errors) {
		// Cast subject into an Applicant
		Applicant user = (Applicant) subject;
		// User name
		if (user.getName().isEmpty()) {
			errors.rejectValue("name", "NotEmpty.userForm.name");
		}
		// Surname
		if (user.getSurname().isEmpty()) {
			errors.rejectValue("surname", "NotEmpty.userForm.surname");
		}
		// Email
		if (user.getEmailAddress().isEmpty()) {
			errors.rejectValue("emailAddress", "NotEmpty.userForm.emailAddress");
		} else if (!validate(user.getEmailAddress().toString())) {
			errors.rejectValue("emailAddress", "Valid.userForm.emailAddress");
		}
		// Password
		if (user.getPassword().isEmpty()) {
			errors.rejectValue("password", "NotEmpty.userForm.password");
		}
		// Confirm Password
		if (user.getConfirmPassword().isEmpty()) {
			errors.rejectValue("confirmPassword", "NotEmpty.userForm.confirmPassword");
		}
		if (!user.getPassword().equals(user.getConfirmPassword())) {
			errors.rejectValue("confirmPassword", "Diff.userForm.confirmPassword");
		}
		// Index Number
		if (user.getIndexNumber().isEmpty()) {
			errors.rejectValue("indexNumber", "NotEmpty.userForm.indexNumber");
		} else if (user.getIndexNumber().length() != 7) {
			errors.rejectValue("indexNumber", "Valid.userForm.indexNumber");
		}
		// Telephone
		if (user.getTelephone().isEmpty()) {
			errors.rejectValue("telephone", "NotEmpty.userForm.telephone");
		} else if (user.getTelephone().length() != 10) {
			errors.rejectValue("telephone", "Diff.userForm.telephone");
		}
		// GPA
		try {
			if (user.getGradedPoint() > 4.3 || user.getGradedPoint() < 1.0) {
				errors.rejectValue("gradedPoint", "Diff.userForm.gradedPoint");
			}
		} catch (java.lang.NullPointerException e) {
			errors.rejectValue("gradedPoint", "NotEmpty.userForm.gradedPoint");
		}
		// About me
		if (user.getAboutMe().isEmpty()) {
			errors.rejectValue("aboutMe", "NotEmpty.userForm.aboutMe");
		}
		// Gender
		if (user.getGender() == null) {
			errors.rejectValue("gender", "NotEmpty.userForm.gender");
		}
		// Preferences
		if (user.getPreferences().size() < 3) {
			errors.rejectValue("preferences", "NotEmpty.userForm.preferences");
		} else if (user.getPreferences().size() > 10) {
			errors.rejectValue("preferences", "Valid.userForm.preferences");
		}
	}

	private boolean validate(final String hex) {
		String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
				+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
		pattern = Pattern.compile(EMAIL_PATTERN);
		matcher = pattern.matcher(hex);
		return matcher.matches();
	}
}
