package com.tarrega.study.junit5.userguide.three.thirteen;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.EnumSource.Mode.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Year;
import java.util.EnumSet;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.TestReporter;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.converter.JavaTimeConversionPattern;
import org.junit.jupiter.params.converter.SimpleArgumentConverter;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

// 3.13. Parameterized Tests
// Parameterized tests make it possible to run a test multiple times with different arguments.
// They are declared just like regular @Test methods but use the @ParameterizedTest annotation instead. In addition, you must declare at least one source that will provide the arguments for each invocation.

// In order to use parameterized tests you need to add a dependency on the junit-jupiter-params artifact.
// Parameterized tests are currently an experimental feature. Consult the table in Experimental APIs for details.
public class ParameterizedTests {

	@ParameterizedTest
	@ValueSource(strings = { "Hello", "World" })
	void testWithStringParameter(String argument) {
		assertNotNull(argument);
	}

	@ParameterizedTest
	@ValueSource(ints = { 1, 2, 3 })
	void testWithValueSource(int argument) {
		assertNotNull(argument);
	}

	@ParameterizedTest
	@EnumSource(TimeUnit.class)
	void testWithEnumSource(TimeUnit unit) {
		assertNotNull(unit);
	}

	@ParameterizedTest
	@EnumSource(value = TimeUnit.class, names = { "DAYS", "HOURS" })
	void testWithEnumSourceInclude(TimeUnit timeUnit) {
		assertTrue(EnumSet.of(TimeUnit.DAYS, TimeUnit.HOURS).contains(timeUnit));
	}

	@ParameterizedTest
	@EnumSource(value = TimeUnit.class, mode = EXCLUDE, names = { "DAYS", "HOURS" })
	void testWithEnumSourceExclude(TimeUnit timeUnit) {
		assertFalse(EnumSet.of(TimeUnit.DAYS, TimeUnit.HOURS).contains(timeUnit));
		assertTrue(timeUnit.name().length() > 5);
	}

	@ParameterizedTest
	@EnumSource(value = TimeUnit.class, mode = MATCH_ALL, names = "^(M|N).+SECONDS$")
	void testWithEnumSourceRegex(TimeUnit timeUnit) {
		String name = timeUnit.name();
		assertTrue(name.startsWith("M") || name.startsWith("N"));
		assertTrue(name.endsWith("SECONDS"));
	}

	@ParameterizedTest
	@MethodSource("stringProvider")
	// @MethodSource allows you to refer to one or more methods of the test class.
	// Each method must return a Stream, Iterable, Iterator, or array of arguments.
	// In addition, each method must not accept any arguments.
	void testWithSimpleMethodSource(String argument) {
		assertNotNull(argument);
	}

	static Stream<String> stringProvider() {
		return Stream.of("foo", "bar");
	}

	@ParameterizedTest
	@MethodSource("range")
	void testWithRangeMethodSource(int argument) {
		assertNotEquals(9, argument);
	}

	static IntStream range() {
		return IntStream.range(0, 20).skip(10);
	}

	@ParameterizedTest
	@MethodSource("stringAndIntProvider")
	void testWithMultiArgMethodSource(String first, int second) {
		assertNotNull(first);
		assertNotEquals(0, second);
	}

	static Stream<Arguments> stringAndIntProvider() {
		return Stream.of(Arguments.of("foo", 1), Arguments.of("bar", 2));
	}

	@ParameterizedTest
	@CsvSource({ "foo, 1", "bar, 2", "'baz, qux', 3" })
	void testWithCsvSource(String first, int second) {
		assertNotNull(first);
		assertNotEquals(0, second);
	}

	@ParameterizedTest
	@CsvFileSource(resources = "/two-column.csv")
	void testWithCsvFileSource(String first, int second) {
		assertNotNull(first);
		assertNotEquals(0, second);
	}

	@ParameterizedTest
	@ArgumentsSource(MyArgumentsProvider.class)
	void testWithArgumentsSource(String argument) {
		assertNotNull(argument);
	}

	static class MyArgumentsProvider implements ArgumentsProvider {

		@Override
		public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
			return Stream.of("foo", "bar").map(Arguments::of);
		}
	}

	// 3.13.3. Argument Conversion
	// Implicit Conversion
	@ParameterizedTest
	@ValueSource(strings = "SECONDS")
	void testWithImplicitArgumentConversion(TimeUnit argument) {
		assertNotNull(argument.name());
	}

	@ParameterizedTest
	@ValueSource(strings = "2017")
	void testWithImplicitArgumentConversion(Year argument) {
		assertNotNull(argument);
		assertTrue(argument.equals(Year.of(2017)));
	}

	@ParameterizedTest
	@ValueSource(strings = "2017-03-14T12:34:56.789")
	void testWithImplicitArgumentConversion(LocalDateTime argument) {
		assertNotNull(argument);
		assertTrue(argument.equals(LocalDateTime.of(2017, 3, 14, 12, 34, 56, 789_000_000)));
	}

	// Explicit Conversion
	@ParameterizedTest
	@EnumSource(TimeUnit.class)
	void testWithExplicitArgumentConversion(@ConvertWith(ToStringArgumentConverter.class) String argument) {
		assertNotNull(TimeUnit.valueOf(argument));
	}

	static class ToStringArgumentConverter extends SimpleArgumentConverter {

		@Override
		protected Object convert(Object source, Class<?> targetType) {
			assertEquals(String.class, targetType, "Can only convert to String");
			return String.valueOf(source);
		}
	}

	// NOTE: 파볼만하다.
	@ParameterizedTest
	@ValueSource(strings = { "01.01.2017", "31.12.2017" })
	void testWithExplicitJavaTimeConverter(@JavaTimeConversionPattern("dd.MM.yyyy") LocalDate argument) {
		assertEquals(2017, argument.getYear());
	}

	@DisplayName("Display name of container")
	@ParameterizedTest(name = "{index} ==> first=''{0}'', second={1}")
	@CsvSource({ "foo, 1", "bar, 2", "'baz, qux', 3" })
	void testWithCustomDisplayNames(String first, int second) {
	}

	@ParameterizedTest
	@ValueSource(strings = "foo")
	void testWithRegularParameterResolver(String argument, TestReporter testReporter) {
		testReporter.publishEntry("argument", argument);
	}

}