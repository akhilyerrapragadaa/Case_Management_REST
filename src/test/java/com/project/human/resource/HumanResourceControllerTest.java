package com.project.human.resource;



import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.nio.charset.Charset;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.project.human.resource.domain.Constants;
import com.project.human.resource.domain.Routes;
import com.project.human.resource.presentation.HumanResourceController;

@RunWith(SpringJUnit4ClassRunner.class)
public class HumanResourceControllerTest{
	
   private MockMvc mockMVC;
	
	@InjectMocks
	private HumanResourceController humanResourceControllerTest;
	

	private final String EMPLOYEE_REQUEST = "{\"name\":\"Donald\",\"password\":\"hellos\",\"role\":\"user\"}";
	private final String LOGIN_REQUEST = "{\"name\":\"Donald\",\"password\":\"hellos\"}";
	private final String CASE_REQUEST = "{\"requestedBy\":\"Donald\",\"type\":\"Employee Relations Case\",\"accessories\":\"books\",\"issue\":\"Employee Conflict\",\"comment\":\"Ding Dong\"}";
	

	public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(
			MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));
	
	@Before
	public void setUp() throws Exception {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/");
        viewResolver.setSuffix(".jsp");
        mockMVC = MockMvcBuilders.standaloneSetup(humanResourceControllerTest).setViewResolvers(viewResolver).build(); 
	}
	
	@Test
	public void testRegisterUser() throws Exception{
		mockMVC.perform(get(Routes.REGISTER)
		.content(EMPLOYEE_REQUEST)
		)
         .andExpect(view().name(Constants.REGISTER_PAGE))
		.andExpect(status().isOk());
	}
	
	@Test
	public void testRoutesHome() throws Exception{
		mockMVC.perform(get(Routes.HOME))
		.andExpect(view().name(Constants.HOME_PAGE))
		.andExpect(status().isOk());
	}
	
	@Test
	public void testLogin() throws Exception{
		mockMVC.perform(get(Routes.LOGIN)).andExpect(view().name(Constants.LOGIN_PAGE))
		.andExpect(status().isOk());
	}
	
	@Test
	public void testPostRegister() throws Exception{
	
		mockMVC.perform(post(Routes.REGISTER)
				.content(EMPLOYEE_REQUEST)
				.contentType(APPLICATION_JSON_UTF8)
                )
               .andExpect(status().isOk())
              .andExpect(view().name(Constants.REGISTER_PAGE));
          
	}
	
	@Test
	public void testPostLogin() throws Exception{
	
		mockMVC.perform(post(Routes.LOGIN)
				.content(LOGIN_REQUEST)
				.contentType(APPLICATION_JSON_UTF8)
                )
               .andExpect(status().isOk())
               .andExpect(view().name(Constants.LOGIN_PAGE));
	}
	
	@Test
	public void testAddCases() throws Exception{
		mockMVC.perform(get("/new-case/ercase")
				.contentType(APPLICATION_JSON_UTF8)
				)
		.andExpect(status().isOk());
	}
	
	@Test
	public void testAddCaseComment() throws Exception{
		mockMVC.perform(get("/add-comment/5")
				.contentType(APPLICATION_JSON_UTF8)
				)
		.andExpect(status().isOk());
		
	}
	
	@Test
	public void testAcceptCase() throws Exception{
		mockMVC.perform(get("/accept/5")
				.contentType(APPLICATION_JSON_UTF8)
				)
		.andExpect(status().isOk());
		
	}
	
	@Test
	public void testDeclineCase() throws Exception{
		mockMVC.perform(get("/decline/5")
				.contentType(APPLICATION_JSON_UTF8)
				)
		.andExpect(status().isOk());
		
	}
	
	@Test
	public void testCase() throws Exception{
		mockMVC.perform(post("/add-case/ercase")
				.content(CASE_REQUEST)
				.contentType(APPLICATION_JSON_UTF8)
				)
		.andExpect(status().isOk());
		
	}
	
	@Test
	public void testaddCase() throws Exception{
		mockMVC.perform(post("/add-case/ercase")
				.content(CASE_REQUEST)
				.contentType(APPLICATION_JSON_UTF8)
				)
		.andExpect(status().isOk());
		
	}
	
	
	
}
