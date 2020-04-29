package com.project.human.resource;


import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.project.human.resource.domain.AuthenticationException;
import com.project.human.resource.models.Cases;
import com.project.human.resource.models.User;
import com.project.human.resource.presentation.CaseRequestForm;
import com.project.human.resource.presentation.LoginForm;
import com.project.human.resource.presentation.RegistrationForm;
import com.project.human.resource.repositories.CaseRepository;
import com.project.human.resource.repositories.UserRepository;
import com.project.human.resource.services.AuthenticationService;
import com.project.human.resource.services.HumanResourcesService;

@RunWith(SpringJUnit4ClassRunner.class)
public class ServiceTest{
	
  
	
	@Mock
	private AuthenticationService authenticationService;
	
	@Mock
	private HumanResourcesService humanResourcesService;
	
	@Mock
	private UserRepository userRepository;
	
	@Mock
	private CaseRepository caseRepository;
	
	@BeforeEach
	void setUp() throws Exception{
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testRegister() {
		RegistrationForm registrationInfo = new RegistrationForm("Sarah","user","testPass");
		User userInfo = new User("Sarah","user","testPass");
		try {
			authenticationService.registerUser(registrationInfo);
		} catch (AuthenticationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		when(userRepository.findByName("Sarah")).thenReturn(userInfo);
	}
	
	@Test
	public void testLogin() {
		LoginForm loginForm = new LoginForm();
		loginForm.setName("Sarah");
		loginForm.setPassword("testPass");
		try {
			when(authenticationService.loginUser(loginForm)).thenReturn(null);
		} catch (AuthenticationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void fetchMyCases() {
		User userInfo = new User("Sarah","user","testPass");
		try {
			when(humanResourcesService.getMyCases(userInfo)).thenReturn(null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void fetchaddNewCase() {
		CaseRequestForm caseRequestForm = new CaseRequestForm("Sarah","offbcase","books","Employee Conflict","Ding Dong");
		 String caseType = caseRequestForm.getType();
	        Cases newCase;
	        if(caseType.equals("offbcase") || caseType.equals("onbcase")) {
	            newCase = caseRequestForm.getOfficeNewCase();
	        } else {
	            newCase = caseRequestForm.getUserNewCase();
	        }
	        try {
	            caseRepository.save(newCase);
		
			when(caseRepository.getCasesByRequestedBy("Sarah")).thenReturn(null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void getCase() {
		 Cases Case = new Cases("Sarah","offbcase","books", "Officer", "Employee Conflict", "books","Ding Dong");
		
	        try {
	            caseRepository.save(Case);
		
			when(caseRepository.getCasesByRequestedBy("Akhil")).thenReturn(null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void addComments() {
		 Cases  Case = new Cases("Sarah","offbcase","books", "Officer", "Employee Conflict", "books","Ding Dong");
		String ID = "1";
	        try {
	            caseRepository.save(Case);
		
			when(caseRepository.getCaseById(Long.parseLong(ID))).thenReturn(null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
