package com.tarrega.study.junit5.userguide.three.three;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

// 3.3. Display Names
// Test classes and test methods can declare custom display names — with spaces, special characters, and even emojis — that will be displayed by test runners and test reporting.
@DisplayName("A special test case")
public class DisplayNameDemo {

	@Test
	@DisplayName("Custom test name containing spaces")
	void testWithDisplayNameContainingSpaces() {
	}

	@Test
	@DisplayName("╯°□°）╯")
	void testWithDisplayNameContainingSpecialCharacters() {
	}

	@Test
	@DisplayName("😱")
	void testWithDisplayNameContainingEmoji() {
	}

}