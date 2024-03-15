package org.logic;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.Arguments;
import org.model.Polynomial;

import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Stream;

class PolynomialExtractorTest {
    static Stream<Arguments> correctStringToPolynomialArgumentProvider() {
        return Stream.of(
                Arguments.of("x^2+3x+2", new Polynomial(new TreeMap<>(Map.of(2, 1, 1, 3, 0, 2)))),
                Arguments.of("-4x^4+9x^5-7x-2", new Polynomial(new TreeMap<>(Map.of(4, -4, 5, 9, 1, -7, 0, -2)))),
                Arguments.of("-3x^4-7x-0", new Polynomial(new TreeMap<>(Map.of(4, -3, 1, -7)))),
                Arguments.of("0", new Polynomial())
        );
    }
    @ParameterizedTest
    @MethodSource("correctStringToPolynomialArgumentProvider")
    void testParsing(String input, Polynomial expected) {
        PolynomialExtractor extractor = new PolynomialExtractor();
        Polynomial actual = extractor.extractPolynomials(input);
        Assertions.assertEquals(expected.getMonomials(), actual.getMonomials());
    }

    static Stream<String> wrongStringToPolynomialArgumentProvider() {
        return Stream.of(
                "--x^2", "x++3", "asdasx", "xasdasd", "a^3+a"
        );
    }

    @ParameterizedTest
    @MethodSource("wrongStringToPolynomialArgumentProvider")
    void testParsingThrows(String input) {
        PolynomialExtractor extractor = new PolynomialExtractor();
        Assertions.assertThrows(IllegalArgumentException.class, () -> extractor.extractPolynomials(input));
    }
}