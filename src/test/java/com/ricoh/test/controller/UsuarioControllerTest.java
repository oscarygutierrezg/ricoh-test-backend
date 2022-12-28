package com.ricoh.test.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ricoh.test.TestBackendRicohApplication;
import com.ricoh.test.dto.jwt.JwtResponse;
import com.ricoh.test.dto.usuario.UsuarioDto;
import com.ricoh.test.dto.usuario.UsuarioRequest;
import com.ricoh.test.model.Usuario;
import com.ricoh.test.reposiroty.UsuarioRepository;
import com.ricoh.test.util.UsuarioUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;
import java.util.UUID;


@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = TestBackendRicohApplication.class)
public class UsuarioControllerTest {

	private static final String AUTHORIZATION = "Authorization";
	private  String token = "";

	@Autowired
	private MockMvc mockMvc;	
	@Autowired
	private UsuarioRepository usuarioRepository;
	@Autowired
	private ObjectMapper objectMapper;
	
	private UsuarioUtil usuarioUtil = new UsuarioUtil();

	@BeforeEach
	public void init() throws Exception {
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

	@Test
	public void test_Update_Should_NotFound_When_Invoked() throws JsonProcessingException, Exception {
		UsuarioRequest usuarioRequest = usuarioUtil.createUsuarioRequest();
		mockMvc.perform(
						MockMvcRequestBuilders.put("/v1/usuario/"+ UUID.randomUUID())
								.contentType(MediaType.APPLICATION_JSON)
								.content(objectMapper.writeValueAsString(usuarioRequest))
								.accept(MediaType.APPLICATION_JSON)
								.header(AUTHORIZATION, "Bearer "+token)
				)
				.andDo(MockMvcResultHandlers.print())
				.andExpectAll(
						MockMvcResultMatchers.status().isNotFound()

				);

	}

	@Test
	public void test_Update_Should_BadRequest_When_Invoked() throws JsonProcessingException, Exception {
		Usuario usuario = new Usuario();
		usuarioRepository.save(usuario);
		UsuarioRequest usuarioRequest = usuarioUtil.createUsuarioRequest();
		usuarioRequest.setUsername("");
		mockMvc.perform(
						MockMvcRequestBuilders.put("/v1/usuario/"+usuario.getId())
								.contentType(MediaType.APPLICATION_JSON)
								.content(objectMapper.writeValueAsString(usuarioRequest))
								.accept(MediaType.APPLICATION_JSON)
								.header(AUTHORIZATION, "Bearer "+token)
				)
				.andDo(MockMvcResultHandlers.print())
				.andExpectAll(
						MockMvcResultMatchers.status().isBadRequest()

				);

		usuarioRepository.deleteById(usuario.getId());
	}
	
	@Test
	public void test_Update_Should_UpdateUsuario_When_Invoked() throws JsonProcessingException, Exception {
		Usuario usuario = new Usuario();
		usuarioRepository.save(usuario);
		UsuarioRequest usuarioRequest = usuarioUtil.createUsuarioRequest();
		
		ResultActions res =    mockMvc.perform(
	            MockMvcRequestBuilders.put("/v1/usuario/"+usuario.getId())
	                .contentType(MediaType.APPLICATION_JSON)
	                .content(objectMapper.writeValueAsString(usuarioRequest))
	                .accept(MediaType.APPLICATION_JSON)
	                .header(AUTHORIZATION, "Bearer "+token)
	        )
	            .andDo(MockMvcResultHandlers.print())
	            .andExpectAll(
	                    MockMvcResultMatchers.status().isOk()
	                
	            );
		
		Assertions.assertNotNull(res);
		Assertions.assertNotNull(res.andReturn());
		Assertions.assertNotNull(res.andReturn().getResponse());
		Assertions.assertNotNull(res.andReturn().getResponse().getContentAsString());
		UsuarioDto usuarioDto = objectMapper.readValue(res.andReturn().getResponse().getContentAsString(), UsuarioDto.class);
		Assertions.assertNotNull(usuarioDto.getId());
		
		usuario = usuarioRepository.findById(usuarioDto.getId()).get();
		
		Assertions.assertNotNull(usuario);
		Assertions.assertTrue(usuario.getUsername().equals(usuarioDto.getUsername()));
		usuarioRepository.deleteById(usuarioDto.getId());
		
	}
	
	@Test
	public void test_Show_Should_ShowUsuario_When_Invoked() throws JsonProcessingException, Exception {
		Usuario usuario = usuarioUtil.createUsuario();
		usuario.setId(null);
		usuarioRepository.save(usuario);
		
		
		
		ResultActions res =    mockMvc.perform(
	            MockMvcRequestBuilders.get("/v1/usuario/"+usuario.getId())
	                .contentType(MediaType.APPLICATION_JSON)
	                .accept(MediaType.APPLICATION_JSON)
	                .header(AUTHORIZATION, "Bearer "+token)
	        )
	            .andDo(MockMvcResultHandlers.print())
	            .andExpectAll(
	                    MockMvcResultMatchers.status().isOk()
	                
	            );
		
		Assertions.assertNotNull(res);
		Assertions.assertNotNull(res.andReturn());
		Assertions.assertNotNull(res.andReturn().getResponse());
		Assertions.assertNotNull(res.andReturn().getResponse().getContentAsString());
		UsuarioDto UsuarioDto = objectMapper.readValue(res.andReturn().getResponse().getContentAsString(), UsuarioDto.class);
		Assertions.assertTrue(usuario.getUsername().equals(UsuarioDto.getUsername()));
		usuarioRepository.deleteById(UsuarioDto.getId());
	}
	
	@Test
	public void test_Index_Should_ShowPageUsuario_When_Invoked() throws JsonProcessingException, Exception {
		var u1 = usuarioRepository.save(usuarioUtil.createUsuario());
		var u2 =  usuarioRepository.save(usuarioUtil.createUsuario());
		var u3 =  usuarioRepository.save(usuarioUtil.createUsuario());
		var u4 = usuarioRepository.save(usuarioUtil.createUsuario());
		
		mockMvc.perform(
	            MockMvcRequestBuilders.get("/v1/usuario")
	                .contentType(MediaType.APPLICATION_JSON)
	                .accept(MediaType.APPLICATION_JSON)
	                .header(AUTHORIZATION, "Bearer "+token)
	        )
	            .andDo(MockMvcResultHandlers.print())
	            .andExpectAll(
	                    MockMvcResultMatchers.status().isOk(),
	                    MockMvcResultMatchers.jsonPath("$.totalElements").value(5),
	                    MockMvcResultMatchers.jsonPath("$.totalPages").value(1)
	                    
	                    
	                
	            );
		usuarioRepository.deleteAll(List.of(u1,u2,u3,u4));
		
	}

	@Test
	public void test_Index_Should_ShowListUsuario_When_Invoked() throws JsonProcessingException, Exception {
		var u1 = usuarioRepository.save(usuarioUtil.createUsuario());
		var u2 =  usuarioRepository.save(usuarioUtil.createUsuario());
		var u3 =  usuarioRepository.save(usuarioUtil.createUsuario());
		var u4 = usuarioRepository.save(usuarioUtil.createUsuario());


		ResultActions res = mockMvc.perform(
						MockMvcRequestBuilders.get("/v1/usuario/all")
								.contentType(MediaType.APPLICATION_JSON)
								.accept(MediaType.APPLICATION_JSON)
								.header(AUTHORIZATION, "Bearer "+token)
				)
				.andDo(MockMvcResultHandlers.print())
				.andExpectAll(
						MockMvcResultMatchers.status().isOk()



				);
		List list = objectMapper.readValue(res.andReturn().getResponse().getContentAsString(), List.class);
		Assertions.assertEquals(5,list.size());

		usuarioRepository.deleteAll(List.of(u1,u2,u3,u4));


	}
	
	@Test
	public void test_Delete_Should_DeleteUsuario_When_Invoked() throws JsonProcessingException, Exception {
		Usuario usuario = usuarioUtil.createUsuario();
		usuario.setId(null);
		usuarioRepository.save(usuario);
		
		mockMvc.perform(
	            MockMvcRequestBuilders.delete("/v1/usuario/"+usuario.getId())
	                .contentType(MediaType.APPLICATION_JSON)
	                .accept(MediaType.APPLICATION_JSON)
	                .header(AUTHORIZATION, "Bearer "+token)
	        )
	            .andDo(MockMvcResultHandlers.print())
	            .andExpectAll(
	                    MockMvcResultMatchers.status().isNoContent()
	                
	            );
		Assertions.assertTrue(usuarioRepository.findById(usuario.getId()).isEmpty());

	}
	
}


