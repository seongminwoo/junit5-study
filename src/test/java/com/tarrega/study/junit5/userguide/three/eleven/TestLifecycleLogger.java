package com.tarrega.study.junit5.userguide.three.eleven;

import java.util.logging.Logger;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
interface TestLifecycleLogger {
	Logger LOG = Logger.getLogger(TestLifecycleLogger.class.getName());

	@BeforeAll
	default void beforeAllTests() {
		LOG.info("Before all tests");
	}

	@AfterAll
	default void afterAllTests() {
		LOG.info("After all tests");
	}

	@BeforeEach
	default void beforeEachTest(TestInfo testInfo) {
		LOG.info(() -> String.format("About to execute [%s]",
			testInfo.getDisplayName()));
	}

	@AfterEach
	default void afterEachTest(TestInfo testInfo) {
		LOG.info(() -> String.format("Finished executing [%s]",
			testInfo.getDisplayName()));
	}

}