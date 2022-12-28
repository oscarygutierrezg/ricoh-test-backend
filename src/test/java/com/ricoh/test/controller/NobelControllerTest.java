package com.ricoh.test.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ricoh.test.TestBackendRicohApplication;
import com.ricoh.test.config.NobelApiMock;
import com.ricoh.test.dto.external.nobel.NobelPrizeDto;
import com.ricoh.test.dto.jwt.JwtResponse;
import com.ricoh.test.util.UsuarioUtil;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;


@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = TestBackendRicohApplication.class)
public class NobelControllerTest {

	private static final String AUTHORIZATION = "Authorization";

	private NobelApiMock nobelApiMock = new NobelApiMock(9090);

	@BeforeAll
	void init() {
		nobelApiMock.startMockServer();
	}

	@AfterAll
	void shutDown() {
		nobelApiMock.stop();
	}

	private UsuarioUtil usuarioUtil = new UsuarioUtil();

	private  String token = "";

	@BeforeEach
	public void initToken() throws Exception {
		var usuarioRequest = usuarioUtil.createUsuarioRequestLogin();
		ResultActions res =    mockMvc.perform(
				MockMvcRequestBuilders.post("/v1/security/authenticate")
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(usuarioRequest))
						.accept(MediaType.APPLICATION_JSON)
		);

		JwtResponse jwtResponse = objectMapper.readValue(res.andReturn().getResponse().getContentAsString(), JwtResponse.class);
		token = jwtResponse.getJwttoken();
	}

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@Test
	public void test_GetNobelInfo_Should_ShowListNobelInfo_When_Invoked() throws Exception {

		ResultActions res = mockMvc.perform(
						MockMvcRequestBuilders.get("/external/v1/nobel/che/2022/2022")
								.contentType(MediaType.APPLICATION_JSON)
								.accept(MediaType.APPLICATION_JSON)
								.header(AUTHORIZATION, "Bearer "+token)

				)
				.andDo(MockMvcResultHandlers.print())
				.andExpectAll(
						MockMvcResultMatchers.status().isOk()



				);

		List list = objectMapper.readValue(res.andReturn().getResponse().getContentAsString(), List.class);
		Assertions.assertEquals(25,list.size());

	}

}
