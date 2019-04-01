package com.server.lwm2m;

import lombok.extern.slf4j.Slf4j;
import org.eclipse.leshan.server.LwM2mServer;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@Slf4j
@SpringBootApplication
public class Lwm2mServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(Lwm2mServerApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(LwM2mServer lwM2mServer) {
		return args -> lwM2mServer.start();
	}

}
