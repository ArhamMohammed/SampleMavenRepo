package com.arhamcodes.SpringEmailDemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class SpringEmailDemoApplication {

	@Autowired
	private EmailSenderService senderService;

	public static void main(String[] args) {

		SpringApplication.run(SpringEmailDemoApplication.class, args);
	}

	@EventListener(ApplicationReadyEvent.class)
	public void sendMail(){
		senderService.sendEmail("kcharankumar@gmail.com","Spring Email Example",
				"Hi Charan. This is Mir Mohammed Arham this side. " +
						"Sending out this email to you from Spring Back end service. " +
						"\nI followed a youtube video on how to do this." +
						"\nThanks and Regards," +
						"\nMir Mohammed Arham");

	}
}
