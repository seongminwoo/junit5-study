package com.tarrega.study.junit5.userguide;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class UserGuide {

	@Test
	void myFirstTest() {
		assertEquals(2, 1 + 1);
	}
}