package br.com.pluspet.api.configuration;

import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration.AccessLevel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfiguration {

	@Bean
	ModelMapper getModelMapper() {

		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setAmbiguityIgnored(true);
		modelMapper.getConfiguration().setFieldMatchingEnabled(true);
		modelMapper.getConfiguration().setFieldAccessLevel(AccessLevel.PRIVATE);

		return modelMapper;
	}

}
