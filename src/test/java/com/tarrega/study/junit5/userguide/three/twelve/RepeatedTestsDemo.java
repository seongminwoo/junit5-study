package com.tarrega.study.junit5.userguide.three.twelve;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.logging.Logger;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;
import org.junit.jupiter.api.TestInfo;

// 3.12. Repeated Tests
// JUnit Jupiter provides the ability to repeat a test a specified number of times simply by annotating a method with @RepeatedTest and specifying the total number of repetitions desired.
// Each invocation of a repeated test behaves like the execution of a regular @Test method with full support for the same lifecycle callbacks and extensions.
class RepeatedTestsDemo {

	private Logger logger = Logger.getLogger(RepeatedTestsDemo.class.getName());

	@BeforeEach
	void beforeEach(TestInfo testInfo, RepetitionInfo repetitionInfo) {
		int currentRepetition = repetitionInfo.getCurrentRepetition();
		int totalRepetitions = repetitionInfo.getTotalRepetitions();
		String methodName = testInfo.getTestMethod().get().getName();
		logger.info(String.format("About to execute repetition %d of %d for %s", //
			currentRepetition, totalRepetitions, methodName));
	}

	@RepeatedTest(10)
	void repeatedTest() {
		// ...
	}

	@RepeatedTest(5)
	void repeatedTestWithRepetitionInfo(RepetitionInfo repetitionInfo) {
		assertEquals(5, repetitionInfo.getTotalRepetitions());
	}

	@RepeatedTest(value = 1, name = "{displayName} {currentRepetition}/{totalRepetitions}")
	@DisplayName("Repeat!")
	void customDisplayName(TestInfo testInfo) {
		assertEquals(testInfo.getDisplayName(), "Repeat! 1/1");
	}

	@RepeatedTest(value = 1, name = RepeatedTest.LONG_DISPLAY_NAME)
	@DisplayName("Details...")
	void customDisplayNameWithLongPattern(TestInfo testInfo) {
		assertEquals(testInfo.getDisplayName(), "Details... :: repetition 1 of 1");
	}

	@RepeatedTest(value = 5, name = "Wiederholung {currentRepetition} von {totalRepetitions}")
	void repeatedTestInGerman() {
		// ...
	}

}