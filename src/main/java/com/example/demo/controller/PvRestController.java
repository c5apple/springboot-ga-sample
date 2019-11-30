package com.example.demo.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.config.AppConfig;
import com.google.api.services.analytics.Analytics;
import com.google.api.services.analytics.model.GaData;

/**
 * ページビュー
 */
@RestController
@RequestMapping("/pv")
public class PvRestController {

	@Autowired
	private AppConfig appConfig;

	@Autowired
	private Analytics ga;

	/**
	 * 過去7日間
	 */
	@GetMapping("/7daysAgo")
	public List<List<String>> to7daysAgo() throws IOException {

		String ids = "ga:" + appConfig.getGaViewId();
		String startDate = "7daysAgo";
		String endDate = "yesterday";
		String metrics = "ga:pageViews";
		String dimensions = "ga:pagePath";
		String sort = "-ga:pageViews";

		GaData gaData = ga.data().ga()
				.get(ids, startDate, endDate, metrics)
				.setDimensions(dimensions)
				.setSort(sort)
				.execute();

		return gaData.getRows();
	}

	/**
	 * 過去28日間
	 */
	@GetMapping("/28daysAgo")
	public List<List<String>> to28daysAgo() throws IOException {

		String ids = "ga:" + appConfig.getGaViewId();
		String startDate = "28daysAgo";
		String endDate = "yesterday";
		String metrics = "ga:pageViews";
		String dimensions = "ga:pagePath";
		String sort = "-ga:pageViews";

		GaData gaData = ga.data().ga()
				.get(ids, startDate, endDate, metrics)
				.setDimensions(dimensions)
				.setSort(sort)
				.execute();

		return gaData.getRows();
	}
}
