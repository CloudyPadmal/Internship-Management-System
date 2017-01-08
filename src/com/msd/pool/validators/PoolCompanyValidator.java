package com.msd.pool.validators;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.msd.items.Company;

@Component
public class PoolCompanyValidator implements Validator {

	private Pattern pattern;
	private Matcher matcher;

	@Override
	public boolean supports(Class<?> c) {
		// Validate if the classes are the same
		return Company.class.equals(c);
	}

	@Override
	public void validate(Object subject, Errors errors) {
		// Cast subject into an Applicant
		Company company = (Company) subject;

		// company
		if (company.getCompany().isEmpty()) {
			errors.rejectValue("company", "NotEmpty.companyForm.name");
		}
		// loginID
		try {
			if (company.getLoginID().isEmpty()) {
				errors.rejectValue("loginID", "NotEmpty.companyForm.loginID");
			}
		} catch (java.lang.NullPointerException e) {
			// TODO: handle exception
		}
		// address
		if (company.getAddress().isEmpty()) {
			errors.rejectValue("address", "NotEmpty.companyForm.address");
		}
		// emailAddress
		if (company.getEmailAddress().isEmpty()) {
			errors.rejectValue("emailAddress", "NotEmpty.companyForm.emailAddress");
		} else if (!validate(company.getEmailAddress().toString())) {
			errors.rejectValue("emailAddress", "Valid.companyForm.emailAddress");
		}
		// password
		try {
			if (company.getPassword().isEmpty()) {
				errors.rejectValue("password", "NotEmpty.companyForm.password");
			}
		} catch (java.lang.NullPointerException e) {
			// TODO: handle exception
		}
		// confirmPassword
		try {
			if (company.getConfirmPassword().isEmpty()) {
				errors.rejectValue("confirmPassword", "NotEmpty.companyForm.confirmPassword");
			}
			if (!company.getPassword().equals(company.getConfirmPassword())) {
				errors.rejectValue("confirmPassword", "Diff.companyForm.confirmPassword");
			}
		} catch (java.lang.NullPointerException e) {
			// TODO: handle exception
		}
		// telephone
		if (company.getTelephone().isEmpty()) {
			errors.rejectValue("telephone", "NotEmpty.companyForm.telephone");
		} else if (company.getTelephone().length() != 10) {
			errors.rejectValue("telephone", "Valid.companyForm.telephone");
		}
		// aboutUs
		if (company.getAboutUs().isEmpty()) {
			errors.rejectValue("aboutUs", "NotEmpty.companyForm.aboutUs");
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
