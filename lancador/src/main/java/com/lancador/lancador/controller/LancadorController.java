package com.lancador.lancador.controller;

import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lancador.lancador.service.Application;
import com.lancador.lancador.service.SimpleLed;
import com.lancador.lancador.utils.PIN;
import com.pi4j.Pi4J;
import com.pi4j.context.Context;
import com.pi4j.io.gpio.digital.DigitalState;


@RestController
public class LancadorController implements Application{
	
	private static Scanner cmd;

	@PatchMapping("ligarMotor")
	public String ligarMotor() {
		// example: curl -v --request PATCH http://localhost:8080/ligarMotor
		return "Motor ligado\n";
	}

	
	@PostMapping("ligarLed")
	@Override
	public void execute(Context pi4j) {
		// example: curl -v --request POST http://localhost:8080/ligarLed
		System.out.println("Simple LED app started ...");
		
		// Create a new SimpleLED component
		SimpleLed led = new SimpleLed(pi4j, PIN.D20);

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
	
	@PatchMapping("ligarLed2")
	public void ligarLed2() throws IOException, InterruptedException {
		
		int DIGITAL_OUTPUT_PIN = 4;
		// Initialize Pi4J with an auto context
		// An auto context includes AUTO-DETECT BINDINGS enabled
		// which will load all detected Pi4J extension libraries
		// (Platforms and Providers) in the class path
		var pi4j = Pi4J.newAutoContext();

		// create a digital output instance using the default digital output provider
		var output = pi4j.dout().create(DIGITAL_OUTPUT_PIN);
		output.config().shutdownState(DigitalState.HIGH);

		// setup a digital output listener to listen for any state changes on the digital output
		output.addListener(System.out::println);

		// lets invoke some changes on the digital output
		output.state(DigitalState.HIGH)
		          .state(DigitalState.LOW)
		          .state(DigitalState.HIGH)
		          .state(DigitalState.LOW);

		// lets toggle the digital output state a few times
		output.toggle()
		          .toggle()
		          .toggle();

		// another friendly method of setting output state
		output.high()
		          .low();

		// lets read the digital output state
		System.out.print("CURRENT DIGITAL OUTPUT [" + output + "] STATE IS [");
		System.out.println(output.state() + "]");

		// pulse to HIGH state for 3 seconds
		System.out.println("PULSING OUTPUT STATE TO HIGH FOR 3 SECONDS");
		output.pulse(3, TimeUnit.SECONDS, DigitalState.HIGH);
		System.out.println("PULSING OUTPUT STATE COMPLETE");

		// shutdown Pi4J
		pi4j.shutdown();
		
		
		
		
	}

	
}
