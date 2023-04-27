package com.lancador.lancador.controller;

import java.io.IOException;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class LancadorController {

	@GetMapping("/")
	public String ligarMotor() {
		// example: curl -v http://localhost:8080/
		return "API - OK\n";
	}
	
	@GetMapping("/teste")
	public String teste() throws InterruptedException, IOException {
		//raspi-gpio set 4 op
		//raspi-gpio set 4 dh
		//raspi-gpio set 4 dl
		Runtime.getRuntime().exec("raspi-gpio set 4 op");
		Runtime.getRuntime().exec("raspi-gpio set 4 dl");
		Runtime.getRuntime().exec("raspi-gpio set 4 dh");
		Thread.sleep(5000);
		Runtime.getRuntime().exec("raspi-gpio set 4 dl");
		Thread.sleep(5000);
		Runtime.getRuntime().exec("raspi-gpio set 4 dh");
		Thread.sleep(5000);
		Runtime.getRuntime().exec("raspi-gpio set 4 dl");
		return "teste - OK\n";
	}
	
	


}
