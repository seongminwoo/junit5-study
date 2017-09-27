package com.tarrega.study.junit5.userguide.three.ten;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;

// 3.10. Dependency Injection for Constructors and Methods
// Other parameter resolvers must be explicitly enabled by registering appropriate extensions via @ExtendWith.
@ExtendWith(MockitoExtension.class)
class MyMockitoTest {

	class Person {
		String name;

		public String getName() {
			return name;
		}
	}

	@BeforeEach
	void init(@Mock Person person) {
		when(person.getName()).thenReturn("Dilbert");
	}

	@Test
	void simpleTestWithInjectedMock(@Mock Person person) {
		assertEquals("Dilbert", person.getName());
	}

}