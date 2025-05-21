package com.example.assign2;

import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.assign2.Controller.SemesterController;


@SpringBootApplication
public class Assign2Application {

	private final SemesterController semesterController;

    Assign2Application(SemesterController semesterController) {
        this.semesterController = semesterController;
    }
	public static void main(String[] args) {
		
		ApplicationContext context = SpringApplication.run(Assign2Application.class, args);
	}

}
