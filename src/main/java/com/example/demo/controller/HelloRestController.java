package com.example.demo.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.api.services.analytics.Analytics;
import com.google.api.services.analytics.model.GaData;

@RestController
public class HelloRestController {

	@Autowired
	private Analytics ga;

	@GetMapping("/")
	public String index() throws IOException {

		GaData gaData = ga.data().ga()
				.get("ga:" + "xxxx", "yesterday", "today", "ga:sessions")
				.setDimensions("ga:visitorGender,ga:visitorAgeBracket")
				.execute();

		return gaData.getRows().toString();
	}
}
