package br.com.pluspet.v1.controller;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceResolvable;
import org.springframework.context.NoSuchMessageException;

public class PlusPetMessageSource {

	@Autowired
	private MessageSource messageSource;

	private static final Locale LOCALE = new Locale("pt", "BR");

	public String getMessage(String code) throws NoSuchMessageException {
		return messageSource.getMessage(code, new Object[0], LOCALE);
	}

	public String getMessage(String code, Object[] args) throws NoSuchMessageException {
		return messageSource.getMessage(code, args, LOCALE);
	}

	public String getMessage(String code, Object[] args, String defaultMessage) {
		return messageSource.getMessage(code, args, defaultMessage, LOCALE);
	}

	public String getMessage(MessageSourceResolvable resolvable) throws NoSuchMessageException {
		return messageSource.getMessage(resolvable, LOCALE);
	}
}
