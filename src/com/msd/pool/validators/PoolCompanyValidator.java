package com.msd.pool.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.msd.users.Applicant;
import com.msd.users.Company;

@Component
public class PoolCompanyValidator implements Validator {

	@Override
	public boolean supports(Class<?> c) {
		// Validate if the classes are the same
		return Company.class.equals(c);
	}

	@Override
	public void validate(Object subject, Errors err) {
		// Cast subject into an Applicant
		Company company = (Company) subject;
		/*

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "NotEmpty.userForm.name");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "surname", "NotEmpty.userForm.surname");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "emailAddress", "NotEmpty.userForm.emailAddress");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "indexNumber", "NotEmpty.userForm.indexNumber");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "telephone", "NotEmpty.userForm.telephone");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "gradedPoint", "NotEmpty.userForm.gradedPoint");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "gender", "NotEmpty.userForm.gender");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "aboutMe", "NotEmpty.userForm.aboutMe");

		if (user.getTelephone() == null || user.getTelephone().length() <= 0) {
			errors.rejectValue("telephone", "Valid.userForm.telephone");
		}

		if (user.getPreferences() == null || user.getPreferences().size() < 3) {
			errors.rejectValue("preferences", "Valid.userForm.preferences");
		}
		
		if (user.getIndexNumber().length() != 7) {
			errors.rejectValue("indexNumber", "Valid.userForm.indexNumber");
		}
		*/
	}
}
