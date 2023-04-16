package com.lancador.lancador.controller;

import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LancadorController {
	
	
	@PatchMapping("ligarMotor")
	public String ligarMotor() {
		// example: curl -v --request PATCH http://localhost:8080/ligarMotor
		return "Motor ligado\n";
	}
}
