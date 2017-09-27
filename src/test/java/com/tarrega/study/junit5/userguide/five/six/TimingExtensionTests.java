package com.tarrega.study.junit5.userguide.five.six;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import com.tarrega.study.junit5.userguide.three.eleven.TimingExtension;

// 5.6.1. Before and After Test Execution Callbacks
@ExtendWith(TimingExtension.class)
class TimingExtensionTests {

	@Test
	void sleep20ms() throws Exception {
		Thread.sleep(20);
	}

	@Test
	void sleep50ms() throws Exception {
		Thread.sleep(50);
	}

}