package com.msd.control.registers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.msd.items.Applicant;
import com.msd.items.LoginInfo;
import com.msd.pool.interfaces.Preferences;
import com.msd.pool.items.PoolApplicants;
import com.msd.pool.items.PoolPasswords;
import com.msd.pool.items.PoolVacancies;
import com.msd.pool.validators.PoolUserValidator;

@Controller
@RequestMapping("reg/user")
public class UserRegisterController implements Preferences {

	@Autowired
	PoolUserValidator userValidator;
	@Autowired
	PoolApplicants poolApplicants;
	@Autowired
	PoolPasswords poolPW;
	@Autowired
	PoolVacancies poolVacancies;

	/*****************************************************************************************
	 * This method is the validator binder. It will bind the user details
	 * validator to the registration page when a update or a new register is
	 * happening
	 ****************************************************************************************/
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.setValidator(userValidator);
	}

	/*****************************************************************************************
	 * This method will be called when a new user wants to register. It will
	 * generate the registration form
	 ****************************************************************************************/
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String registrationForm(Model model) {
		// Create a new applicant and add it to model
		model = generatePrefList(model);
		// Pass the Applicant as "userForm"
		model.addAttribute("userForm", new Applicant());
		// Add form notations
		model.addAttribute("register", true);
		model.addAttribute("actionURL", "users");
		// Display the login page
		return "logins/register_user";
	}

	/*****************************************************************************************
	 * When a new user creates a profile, it will be directed to here. User will
	 * be validated and directed to either home page or the registration page
	 ****************************************************************************************/
	@RequestMapping(value = "users", method = RequestMethod.POST, params = "register")
	public String addsNewUser(@ModelAttribute("userForm") @Validated Applicant user, BindingResult result, Model model,
			RedirectAttributes redirectAttributes) {
		if (result.hasErrors()) {
			model.addAttribute("register", true);
			model = generatePrefList(model);
			return "logins/register_user";
		} else {
			// Try adding password to the password_table
			int response = poolPW.addPassword(new LoginInfo(user.getIndexNumber(), user.getPassword()));
			// Response should be 1 if the process is successful
			if (response == 1) {
				// Pass success message to redirect view
				redirectAttributes.addFlashAttribute("msg", user.getName() + " added successfully!");
				redirectAttributes.addFlashAttribute("css", "success");
				// Add applicant to the user table
				poolApplicants.addApplicant(user);
				return "redirect:/";
			} else {
				// Pass success message to redirect view
				model.addAttribute("msg", "A user with the same index number exist!");
				model.addAttribute("css", "danger");
				model = generatePrefList(model);
				model.addAttribute("register", true);
				return "logins/register_user";
			}
		}
	}

	/*****************************************************************************************
	 * When a user updates his profile, this will be called. Once updated, he
	 * will be redirected to his home page
	 ****************************************************************************************/
	@RequestMapping(value = "users", method = RequestMethod.POST, params = "update")
	public String updatesUser(@ModelAttribute("userForm") @Validated Applicant user, BindingResult result, Model model,
			RedirectAttributes redirectAttributes) {
		if (result.hasErrors()) {
			model = generatePrefList(model);
			return "logins/register_user";
		} else {
			// Add applicant to the user table
			poolApplicants.updateApplicant(user);
			// Pass the success message
			redirectAttributes.addFlashAttribute("msg", "Updated successfully!");
			redirectAttributes.addFlashAttribute("css", "success");
			// Redirect
			return "redirect:/user/view/" + user.getIndexNumber();
		}
	}

	/*****************************************************************************************
	 * The method will be called when a user wants to update his profile. This
	 * will generate his existing details and show the update form. Since
	 * passwords are not available with users, they will be set to null
	 ****************************************************************************************/
	@RequestMapping(value = "/update/{index}", method = RequestMethod.POST)
	public String showUpdateUserForm(@PathVariable("index") String index, Model model) {
		// Fetch the applicant from database
		Applicant applicant = poolApplicants.fetchApplicant(index);
		applicant.setPassword("null");
		applicant.setConfirmPassword("null");
		// Add details under "userForm"
		model.addAttribute("userForm", applicant);
		// Generate preference list
		model = generatePrefList(model);
		// Add update notations
		model.addAttribute("register", false);
		model.addAttribute("actionURL", "/MSDProject/reg/user/users/");
		return "logins/register_user";
	}

	/*****************************************************************************************
	 * This method will generate the preferences in to a list which will be used
	 * in generating the web form. It will be passed down to the page with the
	 * key "preferences"
	 ****************************************************************************************/
	private Model generatePrefList(Model model) {
		// Create a list of preferences
		List<String> preferences = new ArrayList<>();
		preferences.add(ANTENNAS);
		preferences.add(BIOMECHANICS);
		preferences.add(BIOMEDICAL);
		preferences.add(WIFI);
		preferences.add(ARDUINO);
		preferences.add(AUTOMATION);
		preferences.add(AI);
		preferences.add(CIRCUITS);
		preferences.add(FPGA);
		preferences.add(IMAGEPROCESSING);
		preferences.add(IOT);
		preferences.add(NETWORKING);
		preferences.add(PROCESSORDESIGN);
		preferences.add(PROGRAMMING);
		preferences.add(SEMICONDUCTORS);
		preferences.add(SIGNALPROCESSING);
		preferences.add(TELECOM);
		// Add them under "preferences"
		model.addAttribute("preferences", preferences);
		return model;
	}
}