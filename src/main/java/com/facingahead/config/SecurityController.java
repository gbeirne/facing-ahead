package com.facingahead.config;

import java.security.Principal;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SecurityController {

	@RequestMapping(value = "/user", method = RequestMethod.GET)
	public Principal user(Principal user) {
		return user;
	}
}
