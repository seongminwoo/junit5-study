package com.tarrega.study.junit5.simple;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;

// http://kimseunghyun76.tistory.com/432 따라서 학습.
@Tag("fast") // @Tag는 Juni4의 Categories를 대체.
public class Junit5StudySimple {

	@BeforeAll
	static void beforeAll() {
		System.out.println("@BeforeAll - executes once before all test methods in this class.");
	}

	@BeforeEach
	void init() {
		System.out.println("@Before- executes before all each method in this class.");
	}

	@AfterEach
	void tearDown() {
		System.out.println("@AfterEach- executed after each test method.");
	}

	@AfterAll
	static void done() {
		System.out.println("@AfterAll- executed after all test methods.");
	}

	@Test
	@DisplayName("My 1st Junit5 test! - add()")
	void add() {
		Calculator calculator = new Calculator();
		assertEquals(2, calculator.add(1, 1));
	}

	@Test
	@DisplayName("My 2nd Junit5 test! - lambda")
	void lambda() {
		List<Integer> integers = Arrays.asList(1, 2, 3);
		assertTrue(integers.stream().mapToInt(i -> i).sum() > 5, () -> "Sum should be greater than 5.");
	}

	@Test
	@Disabled("Not implemented yet")
	void ignored() {}

	@Test
	void shouldThrowException() {
		Throwable throwable = assertThrows(UnsupportedOperationException.class, () -> {
			throw new UnsupportedOperationException("Not supported");
		});

		assertEquals(throwable.getMessage(), "Not supported");
	}

	@Test
	void assertThrowsException() {
		String str = null;
		assertThrows(IllegalArgumentException.class, () -> {
			Integer.valueOf(str);
		});
	}

	@TestFactory
	public Stream<DynamicTest> translateDynamicTestFromStream() {
		List<String> in = Arrays.asList("how", "are", "you");
		List<String> out = Arrays.asList("how", "is", "her");

		return in.stream().map(word -> DynamicTest.dynamicTest("Test translate", () -> {
			int id = in.indexOf(word);
			assertEquals(out.get(id), translate(word));
			}
		));
	}

	private String translate(String word) {
		return word;
	}

	@Test
	void groupAssertions() {
		int [] numbers = {0,1,2,3,4};
		assertAll("numbers",
			() -> assertEquals(numbers[0], 0),
			() -> assertEquals(numbers[1],1),
			() -> assertEquals(numbers[2], 3)
		);
	}
}