package br.com.pluspet;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.pluspet.v1.controller.AppointmentController;

@SpringBootTest
class PluspetApplicationTests {

	@Autowired
	private AppointmentController controller;

	@Test
	void contextLoads() {
		assertNotNull(controller);
	}

}
