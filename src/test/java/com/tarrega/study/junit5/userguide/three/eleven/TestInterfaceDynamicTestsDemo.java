package com.tarrega.study.junit5.userguide.three.eleven;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;

import java.util.Arrays;
import java.util.Collection;

import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;

interface TestInterfaceDynamicTestsDemo {

	@TestFactory
	default Collection<DynamicTest> dynamicTestsFromCollection() {
		return Arrays.asList(
			dynamicTest("1st dynamic test in test interface", () -> assertTrue(true)),
			dynamicTest("2nd dynamic test in test interface", () -> assertEquals(4, 2 * 2))
		);
	}

}