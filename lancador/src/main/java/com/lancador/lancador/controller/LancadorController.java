package com.lancador.lancador.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LancadorController {

	@GetMapping("/lancar")
	public String olaSpring() {
		return "Olá Lancador";
	}
	
}
