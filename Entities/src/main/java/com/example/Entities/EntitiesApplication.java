package com.example.Entities;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.ControlerGame.test;

@SpringBootApplication
public class EntitiesApplication {

	public static void main(String[] args) {
		SpringApplication.run(EntitiesApplication.class, args);
	}
	
	public void test() {
		test.test();

	}

}
