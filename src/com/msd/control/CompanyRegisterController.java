package com.msd.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;

import com.msd.pool.PoolApplicants;
import com.msd.pool.PoolCompanies;
import com.msd.pool.PoolPasswords;
import com.msd.pool.validators.PoolCompanyValidator;
import com.msd.pool.validators.PoolUserValidator;
import com.msd.poolinterfaces.Preferences;

@Controller
@RequestMapping("reg/company")
public class CompanyRegisterController implements Preferences {
	
	@Autowired
	PoolCompanyValidator companyValidator;
	@Autowired
	PoolCompanies poolApplicants;
	@Autowired
	PoolPasswords poolPW;
	
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.setValidator(companyValidator);
	}

}
