package com.elwgomes.cadastro.api.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

	@RequestMapping("/")
	String home () {
		return "<h1>Register - API <br><br> <a>github.com/elwgomes/register-api</a></h1>";
	}

}
