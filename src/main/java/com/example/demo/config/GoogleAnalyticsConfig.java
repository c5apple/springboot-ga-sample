package com.example.demo.config;

import java.io.IOException;
import java.security.GeneralSecurityException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.analytics.Analytics;
import com.google.api.services.analytics.AnalyticsScopes;

@Configuration
public class GoogleAnalyticsConfig {

	@Autowired
	private AppConfig appConfig;

	@Bean
	public Analytics analytics() throws GeneralSecurityException, IOException {
		String applicationName = "googleAnalytics";
		JacksonFactory jsonFactory = JacksonFactory.getDefaultInstance();
		String keyFileLocation = "/credential.p12";

		NetHttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();
		GoogleCredential credential = new GoogleCredential.Builder()
				.setTransport(httpTransport)
				.setJsonFactory(jsonFactory)
				.setServiceAccountId(appConfig.getGaMail())
				.setServiceAccountPrivateKeyFromP12File(
						new ClassPathResource(keyFileLocation).getFile())
				.setServiceAccountScopes(AnalyticsScopes.all())
				.build();
		return new Analytics.Builder(httpTransport, jsonFactory, credential)
				.setApplicationName(applicationName).build();
	}
}
