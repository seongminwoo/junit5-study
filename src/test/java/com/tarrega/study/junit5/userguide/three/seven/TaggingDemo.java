package com.tarrega.study.junit5.userguide.three.seven;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Tag("fast")
@Tag("model")

// 3.7. Tagging and Filtering
// Test classes and methods can be tagged. Those tags can later be used to filter test discovery and execution.
class TaggingDemo {

	@Test
	@Tag("taxes")
	void testingTaxCalculation() {
	}

}