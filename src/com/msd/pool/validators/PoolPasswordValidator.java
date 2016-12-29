package com.msd.pool.validators;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.msd.items.Applicant;

public class PoolPasswordValidator implements Validator {

	@Override
	public boolean supports(Class<?> c) {
		// Validate if the classes are the same
		return Applicant.class.equals(c);
	}

	@Override
	public void validate(Object arg0, Errors arg1) {
		// TODO Auto-generated method stub

	}

}
