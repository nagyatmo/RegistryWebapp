package com.release.iktatoapi;

import com.release.iktatoapi.data.repository.DataRepository;
import com.release.iktatoapi.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

@SpringBootApplication
public class IktatoapiApplication {



	public static void main(String[] args) {
		SpringApplication.run(IktatoapiApplication.class, args);

	}



}
