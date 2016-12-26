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

		ValidationUtils.rejectIfEmptyOrWhitespace(err, "name", "Name cannot be blank!");
		ValidationUtils.rejectIfEmptyOrWhitespace(err, "email", "Email address empty!");
		ValidationUtils.rejectIfEmptyOrWhitespace(err, "indexNumber", "Index number empty empty!");
		ValidationUtils.rejectIfEmptyOrWhitespace(err, "telephone", "Telephone number empty!");
		ValidationUtils.rejectIfEmptyOrWhitespace(err, "gradedPoint", "GPA empty!");
		ValidationUtils.rejectIfEmptyOrWhitespace(err, "address", "NotEmpty.userForm.address");
		ValidationUtils.rejectIfEmptyOrWhitespace(err, "password", "Password empty!");
		ValidationUtils.rejectIfEmptyOrWhitespace(err, "confirmPassword", "Password empty!");
		ValidationUtils.rejectIfEmptyOrWhitespace(err, "gender", "Seriously?");

		if (user.getTelephone() == null || user.getTelephone().length() <= 0) {
			err.rejectValue("telephone", "Invalid telephone number!");
		}

		if (!user.getPassword().equals(user.getConfirmPassword())) {
			err.rejectValue("confirmPassword", "Different passwords!");
		}

		if (user.getPreferences() == null || user.getPreferences().size() < 3) {
			err.rejectValue("preferences", "Check this again!");
		}
		*/
	}
}
