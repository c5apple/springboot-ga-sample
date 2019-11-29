package com.example.demo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;

@Configuration
@Getter
public class AppConfig {

	/*
	 * GoogleAnalytics
	 */
	@Value("${google.analytics.mail}")
	private String gaMail;

	@Value("${google.analytics.view.id}")
	private String gaViewId;
}
