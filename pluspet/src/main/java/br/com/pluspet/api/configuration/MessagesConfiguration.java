package br.com.pluspet.api.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.pluspet.v1.controller.PlusPetMessageSource;

@Configuration
public class MessagesConfiguration {

	@Bean
	public PlusPetMessageSource getPlusPetMessageSource() {
		return new PlusPetMessageSource();
	}

}
