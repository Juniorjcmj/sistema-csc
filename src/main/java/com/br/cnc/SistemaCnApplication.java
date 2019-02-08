package com.br.cnc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.repository.support.DomainClassConverter;
import org.springframework.format.support.FormattingConversionService;

@SpringBootApplication
public class SistemaCnApplication {

	public static void main(String[] args) {
		SpringApplication.run(SistemaCnApplication.class, args);
				
	}
	
	

}

