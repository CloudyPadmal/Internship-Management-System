package com.msd.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.msd.pool.PoolApplicants;
import com.msd.pool.PoolVacancies;
import com.msd.pool.validators.PoolVacancyValidator;

@Controller
@RequestMapping("vacancy")
public class VacancyController {
	
	@Autowired
	PoolVacancyValidator vacancyValidator;
	@Autowired
	PoolApplicants poolApplicants;
	@Autowired
	PoolVacancies poolVacancies;
	
	
}
