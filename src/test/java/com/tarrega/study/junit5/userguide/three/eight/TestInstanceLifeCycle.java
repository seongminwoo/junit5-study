package com.tarrega.study.junit5.userguide.three.eight;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

// 3.8. Test Instance Lifecycle
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TestInstanceLifeCycle {
	int count = 0;

	@Test
	void incr1() {
		count++;
		assertEquals(1, count);
	}

	@Test
	void incr2() {
		count++;
		assertEquals(2, count);
	}

	@Test
	void incr3() {
		count++;
		assertEquals(3, count);
	}

}