package com.msd.pool.validators;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.msd.items.Applicant;
import com.msd.items.Vacancy;

public class PoolVacancyValidator implements Validator {

	@Override
	public boolean supports(Class<?> c) {
		// Validate if the classes are the same
		return Vacancy.class.equals(c);
	}

	@Override
	public void validate(Object arg0, Errors errors) {
		Vacancy vacancy = (Vacancy) arg0;
		/*
		 * // User name if (user.getName().isEmpty()) {
		 * errors.rejectValue("name", "NotEmpty.userForm.name"); } // Surname if
		 * (user.getSurname().isEmpty()) { errors.rejectValue("surname",
		 * "NotEmpty.userForm.surname"); } // Email if
		 * (user.getEmailAddress().isEmpty()) {
		 * errors.rejectValue("emailAddress", "NotEmpty.userForm.emailAddress");
		 * } else if (!validate(user.getEmailAddress().toString())) {
		 * errors.rejectValue("emailAddress", "Valid.userForm.emailAddress"); }
		 * // Password try { if (user.getPassword().isEmpty()) {
		 * errors.rejectValue("password", "NotEmpty.userForm.password"); } }
		 * catch (NullPointerException e) { // TODO: handle exception } //
		 * Confirm Password try { if (user.getConfirmPassword().isEmpty()) {
		 * errors.rejectValue("confirmPassword",
		 * "NotEmpty.userForm.confirmPassword"); } if
		 * (!user.getPassword().equals(user.getConfirmPassword())) {
		 * errors.rejectValue("confirmPassword",
		 * "Diff.userForm.confirmPassword"); } } catch (NullPointerException e)
		 * { // TODO: handle exception } // Index Number if
		 * (user.getIndexNumber().isEmpty()) { errors.rejectValue("indexNumber",
		 * "NotEmpty.userForm.indexNumber"); } else if
		 * (user.getIndexNumber().length() != 7) {
		 * errors.rejectValue("indexNumber", "Valid.userForm.indexNumber"); } //
		 * Telephone if (user.getTelephone().isEmpty()) {
		 * errors.rejectValue("telephone", "NotEmpty.userForm.telephone"); }
		 * else if (user.getTelephone().length() != 10) {
		 * errors.rejectValue("telephone", "Diff.userForm.telephone"); } // GPA
		 * try { if (!(user.getGradedPoint() instanceof Double)) {
		 * errors.rejectValue("gradedPoint", "Diff.userForm.gradedPoint"); }
		 * else if (user.getGradedPoint() > 4.3 || user.getGradedPoint() < 1.0)
		 * { errors.rejectValue("gradedPoint", "Diff.userForm.gradedPoint"); } }
		 * catch (java.lang.NullPointerException e) {
		 * errors.rejectValue("gradedPoint", "NotEmpty.userForm.gradedPoint"); }
		 * // About me if (vacancy.getDescription_1().isEmpty()) {
		 * errors.rejectValue("aboutMe", "NotEmpty.userForm.aboutMe"); } //
		 * Preferences if (vacancy.getPreferences().size() < 3) {
		 * errors.rejectValue("preferences", "NotEmpty.userForm.preferences"); }
		 * else if (vacancy.getPreferences().size() > 10) {
		 * errors.rejectValue("preferences", "Valid.userForm.preferences"); }
		 */
	}

}
