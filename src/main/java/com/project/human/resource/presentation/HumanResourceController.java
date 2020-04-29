package com.project.human.resource.presentation;
import javax.validation.Valid;

import com.project.human.resource.domain.AuthenticationException;
import com.project.human.resource.domain.CasesException;
import com.project.human.resource.domain.Constants;
import com.project.human.resource.domain.Routes;
import com.project.human.resource.models.Cases;
import com.project.human.resource.models.User;
import com.project.human.resource.services.AuthenticationService;
import com.project.human.resource.services.HumanResourcesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class HumanResourceController {

	public User loggedIn;

	@Autowired
	AuthenticationService auth;

	@Autowired
	HumanResourcesService hr;

	@GetMapping(Routes.DEFAULT)
	public String showDefaultView() {
		return loggedIn != null ? "redirect:home" : "redirect:login";
	}

	@GetMapping(Routes.HOME)
	public String homePage(Model model) throws Throwable {
     try {
		model.addAttribute(Constants.USER_INFO, loggedIn);
		Iterable<Cases> myCases = hr.getMyCases(loggedIn);
		model.addAttribute(Constants.MY_CASES, myCases);
		return Constants.HOME_PAGE;
		}
     catch(NullPointerException ex) {
    	   ex.printStackTrace();
    	  return Constants.HOME_PAGE; 
       }
       catch(Exception e) {
    	   e.printStackTrace();
    	  return Constants.HOME_PAGE; 
       }
   
	}
     
    

	@GetMapping(Routes.REGISTER)
	public String registerPage(Model model){
		model.addAttribute(Constants.REGISTER_FORM, new RegistrationForm());
		return Constants.REGISTER_PAGE;
	}

	@PostMapping(Routes.REGISTER)
	public String registerUser(@Valid @ModelAttribute(Constants.REGISTER_FORM) RegistrationForm registrationInfo, BindingResult bindingResult, Model model) {
		if(bindingResult.hasErrors()) {
			return Constants.REGISTER_PAGE;
		}

		try{
			auth.registerUser(registrationInfo);
			return Constants.REGISTER_SUCCESS;
		}catch (AuthenticationException ex) {
			ex.printStackTrace();
			model.addAttribute(Constants.AUTHENTICATION_ERROR, ex.getMessage());
			return Constants.REGISTER_PAGE;
		}
	}

	@GetMapping(Routes.LOGIN)
	public String loginPage(Model model){
		model.addAttribute(Constants.LOGIN_FORM, new LoginForm());
		return Constants.LOGIN_PAGE;
	}

	@PostMapping(Routes.LOGIN)
	public String loginUser(@Valid @ModelAttribute(Constants.LOGIN_FORM) LoginForm loginInfo, BindingResult bindingResult, Model model) throws Throwable {
		if(bindingResult.hasErrors()) {
			return Constants.LOGIN_PAGE;
		}

		try {
			loggedIn = auth.loginUser(loginInfo);
			return homePage(model);
		}
		catch(AuthenticationException ex) {
			ex.printStackTrace();
			model.addAttribute(Constants.AUTHENTICATION_ERROR, ex.getMessage());
			return Constants.LOGIN_PAGE;
		}
	}

	@GetMapping(Routes.NEW_CASE)
	public String newCasePage(@PathVariable(Constants.CASE_PATH) String newCase, Model model){
		try {
		model.addAttribute(Constants.CASE_FORM, new CaseRequestForm(loggedIn.getName(), newCase));
		return newCase;
		} catch(Exception e) {
			e.printStackTrace();
			return newCase;
		}
	}

	@PostMapping(Routes.ADD_CASE)
	public String addCase(@Valid @ModelAttribute(Constants.CASE_FORM) CaseRequestForm caseForm,
						  BindingResult bindingResult,
						  @PathVariable(Constants.CASE_PATH) String newCase,
						  Model model) throws Throwable {
		try {
		caseForm.setRequestedBy(loggedIn.getName());
		caseForm.setType(newCase);

		String role = loggedIn.getRole();
		if(role == "user" && bindingResult.hasFieldErrors("issue") && bindingResult.hasFieldErrors("comment") ||
			role == "officer" && bindingResult.hasFieldErrors("accessories") && !bindingResult.hasFieldErrors("comment")) {
			return newCase;
		}

		
			hr.addNewCase(caseForm);
			return homePage(model);
		} catch (Exception ex) {
			model.addAttribute(Constants.CASE_ERROR, ex.getMessage());
			return newCase;
		}
	}

	@GetMapping(Routes.ADD_CASE_COMMENT)
	public String getAddCaseComment(@PathVariable(Constants.CASE_PATH) String caseID, Model model) throws Throwable {
		try {
			Cases caseInfo = hr.getCase(caseID);
			model.addAttribute(Constants.CASE_INFO, caseInfo);
			model.addAttribute(Constants.COMMENT_FORM, new OfficerCommentForm());
			return Constants.COMMENT_PAGE;
		} catch (Exception ex) {
			model.addAttribute(Constants.CASE_ERROR, ex.getMessage());
			return homePage(model);
		}
	}

	@PostMapping(Routes.ADD_CASE_COMMENT)
	public String addCaseComment(@Valid @ModelAttribute(Constants.COMMENT_FORM) OfficerCommentForm officerCommentForm,
								 BindingResult bindingResult,
								 @PathVariable(Constants.CASE_PATH) String caseID, Model model) throws Throwable {

		try {
			if(bindingResult.hasErrors()) {
				Cases caseInfo = hr.getCase(caseID);
				model.addAttribute(Constants.CASE_INFO, caseInfo);
				return Constants.COMMENT_PAGE;
			}

			hr.addCaseComment(caseID, officerCommentForm.getComment());
			return homePage(model);
		} catch (Exception ex) {
			model.addAttribute(Constants.CASE_ERROR, ex.getMessage());
			return homePage(model);
		}
	}

	@GetMapping(Routes.ACCEPT_CASE)
	public String acceptCase(@PathVariable(Constants.CASE_PATH) String caseID, Model model) throws Throwable {
		try {
			hr.acceptCase(caseID);
			return homePage(model);
		} catch (Exception ex) {
			model.addAttribute(Constants.CASE_ERROR, ex.getMessage());
			return homePage(model);
		}
	}

	@GetMapping(Routes.DECLINE_CASE)
	public String declineCase(@PathVariable(Constants.CASE_PATH) String caseID, Model model) throws Throwable {
		try {
			hr.declineCase(caseID);
			return homePage(model);
		} catch (Exception ex) {
			model.addAttribute(Constants.CASE_ERROR, ex.getMessage());
			return homePage(model);
		}
	}
}



