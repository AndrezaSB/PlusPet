package br.com.pluspet.api.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.pluspet.v1.controller.PlusPetMessageSource;

@Configuration
public class ModelMapperConfiguration {

	@SuppressWarnings("unchecked")
	@Bean
	public ModelMapper getModelMapper() {

		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setAmbiguityIgnored(true);

		return modelMapper;
	}

}
