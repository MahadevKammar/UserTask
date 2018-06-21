package com.UserTask.mvc.Controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.UserTask.mvc.Entities.Role;
import com.UserTask.mvc.Entities.User;
import com.UserTask.mvc.services.UserServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringJUnit4ClassRunner.class)
public class UserControllerImplTest {

	private MockMvc mockMvc;

	@Mock
	private UserServiceImpl userServiceImpl;

	@InjectMocks
	private UserControllerImpl controllerImpl;

	@Before
	public void setUp() throws Exception {
		mockMvc = MockMvcBuilders.standaloneSetup(controllerImpl).build();
	}

	@Test
	public void testSaveUser() throws Exception {
		User user = new User(1, "manu", "manu@gmail.com", "manu", "8188349834", "Bagalkot", 591102, "20/06/2018", 0,
				null, null);
		String jsonObj = this.mapToJson(user);
		Mockito.when(userServiceImpl.postData(Mockito.any(User.class))).thenReturn(user);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/saveuser").accept(MediaType.APPLICATION_JSON)
				.content(jsonObj).contentType(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		String outputJson = response.getContentAsString();
		assertThat(outputJson).isEqualTo(jsonObj);
		assertEquals(HttpStatus.CREATED.value(), response.getStatus());
	}

	@Test
	public void testLogin() throws Exception {
		Role role = new Role();
		role.setRoleId(1);
		role.setRole("USER");
		Set<Role> roleSet = new HashSet<>();
		roleSet.add(role);
		User user = new User(1, "manu", "manu@gmail.com", "manu", "8188349834", "Bagalkot", 591102, "20/06/2018", 0,
				null, roleSet);
		String jsonObj = this.mapToJson(user);
		Mockito.when(userServiceImpl.GetData(Mockito.anyString(), Mockito.anyString())).thenReturn(user);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/login").accept(MediaType.APPLICATION_JSON)
				.param("email", "manu@gmail.com").param("password", "manu");
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		String outputJson = response.getContentAsString();
		assertThat(outputJson).isEqualTo(jsonObj);
		assertEquals(HttpStatus.OK.value(), response.getStatus());
	}

	@Test
	public void testGetUserById() throws Exception {
		User user = new User(1, "manu", "manu@gmail.com", "manu", "8188349834", "Bagalkot", 591102, "20/06/2018", 0,
				null, null);
		String jsonObj = this.mapToJson(user);
		Mockito.when(userServiceImpl.getUserById(Mockito.anyInt())).thenReturn(user);
		MockHttpServletResponse response = mockMvc
				.perform(get("/getuser/{userId}", 2).accept(MediaType.APPLICATION_JSON)).andReturn().getResponse();
		String outputJson = response.getContentAsString();
		assertThat(outputJson).isEqualTo(jsonObj);
		assertEquals(HttpStatus.OK.value(), response.getStatus());
	}

	private String mapToJson(Object object) throws JsonProcessingException {
		ObjectMapper objMapper = new ObjectMapper();
		return objMapper.writeValueAsString(object);
	}
}

/*
 * @Test public void TestUserControllerImpl() throws Exception {
 * Mockito.when(userServiceImpl.hellowrold()).thenReturn("Hello world");
 * mockMvc.perform(MockMvcRequestBuilders.get("/helloworld")).andExpect(
 * MockMvcResultMatchers.status().isOk())
 * .andExpect(MockMvcResultMatchers.content().string("Hello world"));
 * Mockito.verify(userServiceImpl).hellowrold(); }
 * 
 * @Test public void testHelloJson() throws Exception {
 * mockMvc.perform(MockMvcRequestBuilders.get("/helloworld/json").accept(
 * MediaType.APPLICATION_JSON))
 * .andExpect(status().isOk()).andExpect(jsonPath("$.title",
 * Matchers.is("Mahadev"))) .andExpect(jsonPath("$.value",
 * Matchers.is("Kammar"))).andExpect(jsonPath("$.*", Matchers.hasSize(2))); }
 */