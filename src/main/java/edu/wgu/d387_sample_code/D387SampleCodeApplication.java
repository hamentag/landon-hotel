package edu.wgu.d387_sample_code;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.io.ClassPathResource;

import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


@SpringBootApplication
public class D387SampleCodeApplication {

	public static void main(String[] args) {
		SpringApplication.run(D387SampleCodeApplication.class, args);
		///
		Runnable englishWelcome = () -> {
			try {
				Properties properties = new Properties();
				InputStream stream = new ClassPathResource("translation_en.properties").getInputStream();
				properties.load(stream);
				System.out.println(properties.getProperty("welcome"));
			} catch (Exception e) {
				e.printStackTrace();
			}
		};

		Runnable frenchWelcome = () -> {
			try {
				Properties properties = new Properties();
				InputStream stream = new ClassPathResource("translation_fr.properties").getInputStream();
				properties.load(stream);
				System.out.println(properties.getProperty("welcome"));
			} catch (Exception e) {
				e.printStackTrace();
			}
		};

		// Create and start threads
		new Thread(englishWelcome).start();
		new Thread(frenchWelcome).start();
		////
	}

}
