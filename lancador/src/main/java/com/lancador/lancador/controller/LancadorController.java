package com.lancador.lancador.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LancadorController {

	@GetMapping("/lancar")
	public String olaSpring() {
		// exemple: curl -v http://localhost:8080/lancar
		return "Olá Lancador";
	}
	
	
	@PatchMapping("ligarMotor")
	public String ligarMotor() {
		// example: curl -v --request PATCH http://localhost:8080/ligarMotor
		return "Motor ligado";
	}
}
