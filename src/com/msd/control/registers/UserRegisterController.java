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
import com.msd.items.Vacancy;
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

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.setValidator(userValidator);
	}

	// Register form for a new user
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
		return "logins/register";
	}

	// This will be called upon clicking register button
	@RequestMapping(value = "users", method = RequestMethod.POST, params = "register")
	public String addNewUser(@ModelAttribute("userForm") @Validated Applicant user, BindingResult result, Model model,
			RedirectAttributes redirectAttributes) {
		if (result.hasErrors()) {
			redirectAttributes.addFlashAttribute("msg", "Invalid registration data");
			redirectAttributes.addFlashAttribute("css", "danger");
			model.addAttribute("register", true);
			model = generatePrefList(model);
			return "logins/register";
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
				// Display user details
				return "redirect:users/" + user.getIndexNumber();
			} else {
				// Pass success message to redirect view
				model.addAttribute("msg", "A user with the same index number exist!");
				model.addAttribute("css", "danger");
				model = generatePrefList(model);
				model.addAttribute("register", true);
				return "logins/register";
			}
		}
	}

	@RequestMapping(value = "users", method = RequestMethod.POST, params = "update")
	public String updateUser(@ModelAttribute("userForm") @Validated Applicant user, BindingResult result, Model model,
			RedirectAttributes redirectAttributes) {
		if (result.hasErrors()) {
			model = generatePrefList(model);
			return "logins/register";
		} else {
			// Add applicant to the user table
			poolApplicants.updateApplicant(user);
			Applicant rUser = poolApplicants.fetchApplicant(user.getIndexNumber());
			List<Vacancy> vacancies = poolVacancies.getVacancies(user.convertListToPref());
			model.addAttribute("msg", "Updated successfully!");
			model.addAttribute("css", "success");
			model.addAttribute("vacancies", vacancies);
			model.addAttribute("user", rUser);
			// Display user details
			return "displays/show_user";
		}
	}

	// All users in the table
	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public String showAllUsers(Model model) {
		// Add the user list under "users"
		model.addAttribute("users", poolApplicants.getAllApplicants());
		return "displays/list";
	}

	// Display User details
	@RequestMapping(value = "/users/{index}", method = RequestMethod.POST)
	public String showUser(@PathVariable("index") String index, Model model) {
		// Fetch applicant from database
		Applicant user = poolApplicants.fetchApplicant(index);
		if (user == null) {
			// If there is no user, return a failure message
			model.addAttribute("msg", "User not found");
		}
		// Add the user account under "user"
		model.addAttribute("user", user);
		return "displays/show_user";
	}

	// Delete User account
	@RequestMapping(value = "/users/{index}/delete", method = RequestMethod.POST)
	public String deleteUser(@PathVariable("index") String index, final RedirectAttributes redirectAttributes) {
		poolApplicants.deleteApplicant(index);
		// Pass the successful message to redirect
		redirectAttributes.addFlashAttribute("css", "success");
		redirectAttributes.addFlashAttribute("msg", "User deleted!");
		return "redirect:/admin/view_users";
	}

	// Display Update Form
	@RequestMapping(value = "/users/{index}/update", method = RequestMethod.POST)
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
		return "logins/register";
	}

	// Generate default values for preferences
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
