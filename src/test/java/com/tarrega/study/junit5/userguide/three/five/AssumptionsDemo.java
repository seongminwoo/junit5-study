package com.tarrega.study.junit5.userguide.three.five;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assumptions.assumeTrue;
import static org.junit.jupiter.api.Assumptions.assumingThat;

import org.junit.jupiter.api.Test;

// 3.5. Assumptions
// JUnit Jupiter comes with a subset of the assumption methods that JUnit 4 provides and adds a few that lend themselves well to being used with Java 8 lambdas.
class AssumptionsDemo {

	@Test
	void testOnlyOnCiServer() {
		assumeTrue("CI".equals(System.getenv("ENV")));
		// remainder of test
	}

	@Test
	void testOnlyOnDeveloperWorkstation() {
		assumeTrue("DEV".equals(System.getenv("ENV")),
			() -> "Aborting test: not on developer workstation");
		// remainder of test
	}

	@Test
	void testInAllEnvironments() {
		assumingThat("CI".equals(System.getenv("ENV")),
			() -> {
				// perform these assertions only on the CI server
				assertEquals(2, 2);
			});

		// perform these assertions in all environments
		assertEquals("a string", "a string");
	}

}