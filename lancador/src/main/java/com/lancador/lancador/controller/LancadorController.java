package com.lancador.lancador.controller;

import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lancador.lancador.service.Application;
import com.lancador.lancador.service.SimpleLed;
import com.lancador.lancador.utils.PIN;
import com.pi4j.context.Context;

@RestController
public class LancadorController implements Application{

	@PatchMapping("ligarMotor")
	public String ligarMotor() {
		// example: curl -v --request PATCH http://localhost:8080/ligarMotor
		return "Motor ligado\n";
	}

	@Override
	@PostMapping("ligarLed")
	public void execute(Context pi4j) {
		// example: curl -v --request PATCH http://localhost:8080/ligarLed
		System.out.println("Simple LED app started ...");
		
		// Create a new SimpleLED component
		SimpleLed led = new SimpleLed(pi4j, PIN.D26);

		// Turn on the LED to have a defined state
		System.out.println("Turn on LED.");
		led.on();
		delay(1000);

		// Make a flashing light by toggling the LED every second
		for (int i = 0; i < 10; i++) {
			System.out.println("Current LED state is " + led.toggleState() + ".");
			delay(1000);
		}

		// That's all so turn off the relay and quit
		led.off();
		System.out.println("Turn off LED.");
		delay(2000);

		System.out.println("Simple LED app done.");
	}

	
}
