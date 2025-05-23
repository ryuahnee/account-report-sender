package com.batch.report.sender;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ReportSendApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReportSendApplication.class, args);
	}

}
