package com.tarrega.study.junit5.userguide.three.six;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

// 3.6. Disabling Tests
class DisabledTestsDemo {

	@Disabled
	@Test
	void testWillBeSkipped() {
	}

	@Test
	void testWillBeExecuted() {
	}
}