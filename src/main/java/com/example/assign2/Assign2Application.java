/* 
 * Assignment 2 - Web Engineering
 * John Villegas (c3476534)
 * Paul Cabanas (c3481070)
 */

package com.example.assign2;

import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.assign2.Controller.Controller;

@SpringBootApplication
public class Assign2Application {

	private final Controller semesterController;

	Assign2Application(Controller semesterController) {
		this.semesterController = semesterController;
	}

	public static void main(String[] args) {

		ApplicationContext context = SpringApplication.run(Assign2Application.class, args);
	}

}
