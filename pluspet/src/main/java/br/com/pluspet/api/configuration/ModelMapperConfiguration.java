package br.com.pluspet.api.configuration;

import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.pluspet.core.enums.AnimalGender;
import br.com.pluspet.core.enums.AppointmentType;
import br.com.pluspet.core.enums.Status;

@Configuration
public class ModelMapperConfiguration {

	@Bean
	ModelMapper getModelMapper() {

		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setSkipNullEnabled(true);
		modelMapper.getConfiguration().setAmbiguityIgnored(true);
		modelMapper.addConverter(appointmentTypeStringConverter());
		modelMapper.addConverter(appointmentTypeConverter());
		modelMapper.addConverter(StatusStringConverter());
		modelMapper.addConverter(StatusConverter());
		modelMapper.addConverter(AnimalGenderStringConverter());
		modelMapper.addConverter(AnimalGenderConverter());

		return modelMapper;
	}

	private static Converter<AppointmentType, String> appointmentTypeStringConverter() {
		return new AbstractConverter<AppointmentType, String>() {
			protected String convert(AppointmentType source) {
				return source == null ? null : source.getDescription();
			}
		};
	}

	private static Converter<String, AppointmentType> appointmentTypeConverter() {
		return new AbstractConverter<String, AppointmentType>() {
			protected AppointmentType convert(String source) {
				return AppointmentType.fromDescription(source);
			}
		};
	}

	private static Converter<Status, String> StatusStringConverter() {
		return new AbstractConverter<Status, String>() {
			protected String convert(Status source) {
				return source == null ? null : source.getDescription();
			}
		};
	}

	private static Converter<String, Status> StatusConverter() {
		return new AbstractConverter<String, Status>() {
			protected Status convert(String source) {
				return Status.fromDescription(source);
			}
		};
	}

	private static Converter<AnimalGender, String> AnimalGenderStringConverter() {
		return new AbstractConverter<AnimalGender, String>() {
			protected String convert(AnimalGender source) {
				return source == null ? null : source.getDescription();
			}
		};
	}

	private static Converter<String, AnimalGender> AnimalGenderConverter() {
		return new AbstractConverter<String, AnimalGender>() {
			protected AnimalGender convert(String source) {
				return AnimalGender.fromDescription(source);
			}
		};
	}

}
