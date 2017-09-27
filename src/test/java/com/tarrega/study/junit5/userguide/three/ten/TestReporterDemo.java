package com.tarrega.study.junit5.userguide.three.ten;

import java.util.HashMap;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestReporter;

// 3.10. Dependency Injection for Constructors and Methods
class TestReporterDemo {

	@Test
	void reportSingleValue(TestReporter testReporter) {
		testReporter.publishEntry("a key", "a value");
	}

	@Test
	void reportSeveralValues(TestReporter testReporter) {
		HashMap<String, String> values = new HashMap<>();
		values.put("user name", "dk38");
		values.put("award year", "1974");

		testReporter.publishEntry(values);
	}

}