package com.tarrega.study.junit5.userguide.three.eleven;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class TestInterfaceDemo implements TestLifecycleLogger,
	TimeExecutionLogger, TestInterfaceDynamicTestsDemo {

	@Test
	void isEqualValue() {
		assertEquals(1, 1, "is always equal");
	}

}