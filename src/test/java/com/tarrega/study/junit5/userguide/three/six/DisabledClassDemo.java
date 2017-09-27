package com.tarrega.study.junit5.userguide.three.six;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

// 3.6. Disabling Tests
@Disabled
class DisabledClassDemo {
	@Test
	void testWillBeSkipped() {
	}
}