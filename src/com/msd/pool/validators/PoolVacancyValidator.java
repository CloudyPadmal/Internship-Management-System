package com.msd.pool.validators;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.msd.items.Vacancy;

public class PoolVacancyValidator implements Validator {

	@Override
	public boolean supports(Class<?> c) {
		// Validate if the classes are the same
		return Vacancy.class.equals(c);
	}

	@Override
	public void validate(Object v, Errors errors) {
		// Cast the object to a vacancy
		Vacancy vacancy = (Vacancy) v;
		// Title
		if (vacancy.getTitle().isEmpty()) {
			errors.rejectValue("title", "NotEmpty.vacancyForm.title");
		}
		// Salary
		if (vacancy.getSalary() < 10) {
			errors.rejectValue("salary", "NotEmpty.vacancyForm.salary");
		}
		// Description - 1
		if (vacancy.getDescription_1().isEmpty()) {
			errors.rejectValue("description_1", "NotEmpty.vacancyForm.description_1");
		}
		// Description - 2
		if (vacancy.getDescription_2().isEmpty()) {
			errors.rejectValue("description_2", "NotEmpty.vacancyForm.description_2");
		}
		// Preferences
		if (vacancy.getPreferences().size() < 3) {
			errors.rejectValue("preferences", "NotEmpty.vacancyForm.preferences");
		}
	}

}
