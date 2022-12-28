package com.ricoh.test.controller.security;


import com.ricoh.test.dto.request.OnCreate;
import com.ricoh.test.dto.request.OnLogin;
import com.ricoh.test.dto.usuario.UsuarioDto;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ricoh.test.config.security.JwtTokenUtil;
import com.ricoh.test.dto.jwt.JwtResponse;
import com.ricoh.test.dto.usuario.UsuarioRequest;
import com.ricoh.test.service.impl.JwtUserDetailsService;

@RestController
@RequestMapping(value = "/v1/security")
@CrossOrigin
@AllArgsConstructor
public class SecurityController {

	private final AuthenticationManager authenticationManager;

	private final JwtTokenUtil jwtTokenUtil;

	private final JwtUserDetailsService userDetailsService;

	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public ResponseEntity<JwtResponse> createAuthenticationToken(
			@Validated(OnLogin.class) @RequestBody UsuarioRequest user) throws Exception {

		authenticate(user.getUsername(), user.getPassword());

		final UserDetails userDetails = userDetailsService
				.loadUserByUsername(user.getUsername());

		final String token = jwtTokenUtil.generateToken(userDetails);

		return ResponseEntity.ok(new JwtResponse(token));
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ResponseEntity<UsuarioDto> saveUser(
			@Validated(OnCreate.class)  @RequestBody UsuarioRequest user) throws Exception {
		return ResponseEntity.ok(userDetailsService.save(user));
	}

	private void authenticate(String username, String password) throws Exception {
		try {
			UsernamePasswordAuthenticationToken authenticationToken = new  UsernamePasswordAuthenticationToken(username, password);
			authenticationManager.authenticate(authenticationToken);
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}
}
