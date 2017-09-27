package com.tarrega.study.junit5.userguide.three.two;

import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

// 3.2. Standard Test Class
// Neither test classes nor test methods need to be public.
public class StandardTests {

	@BeforeAll
	static void initAll() {
	}

	@BeforeEach
	void init() {
	}

	@Test
	void succeedingTest() {
	}

	@Test
	void failingTest() {
		fail("a failing test");
	}

	@Test
	@Disabled("for demonstration purposes")
	void skippedTest() {
		// not executed
	}

	@AfterEach
	void tearDown() {
	}

	@AfterAll
	static void tearDownAll() {
	}
}