package com.eshop.sonny;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.FileInputStream;
import java.io.IOException;

@SpringBootApplication
public class SonnyApplication {

	public static void main(String[] args)
	{
		SpringApplication.run(SonnyApplication.class, args);

//		try {
//			FileInputStream serviceAccount = new FileInputStream("serviceAccountKey.json");
//
//			// Create the FirebaseOptions object
//			FirebaseOptions options = FirebaseOptions.builder()
//					.setCredentials(GoogleCredentials.fromStream(serviceAccount))
//					.build();
//
//			// Initialize Firebase with the options
//			FirebaseApp.initializeApp(options);
//		} catch (IOException e) {
//			System.err.println("Error initializing Firebase: " + e.getMessage());
//			e.printStackTrace();
//		}
	}
}
