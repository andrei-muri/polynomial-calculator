package org.logic;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.model.Polynomial;

import java.util.Map;
import java.util.stream.Stream;;

class PolynomialOperationsTest {
    static Stream<Arguments> addArgumentsProvider() {
        return Stream.of(
                Arguments.of(new Polynomial(Map.of(3, 2, 1, 2, 0, 4)),
                             new Polynomial(Map.of(3, 1, 2, 1, 0, 5)),
                             new Polynomial(Map.of(3, 3, 2, 1, 1, 2, 0, 9))),
                Arguments.of(new Polynomial(Map.of(2, 3, 1, 1)),
                             new Polynomial(Map.of(2, -3, 1, -1)),
                             new Polynomial())
        );
    }

    @ParameterizedTest
    @MethodSource("addArgumentsProvider")
    void addTest(Polynomial p1, Polynomial p2, Polynomial result) {
        Assertions.assertEquals(PolynomialOperations.add(p1, p2).getMonomials(), result.getMonomials());
    }

    static Stream<Arguments> subtractArgumentsProvider() {
        return Stream.of(
                Arguments.of(new Polynomial(Map.of(3, 2, 1, 2, 0, 4)),
                             new Polynomial(Map.of(3, 2, 1, 2, 0, 4)),
                             new Polynomial()),
                Arguments.of(new Polynomial(Map.of(2, 3, 1, 1)),
                             new Polynomial(Map.of(2, -3, 1, -1)),
                             new Polynomial(Map.of(2, 6, 1, 2))),
                Arguments.of(new Polynomial(Map.of(2, 3, 1, 1)),
                             new Polynomial(Map.of(2, -3, 1, 1)),
                             new Polynomial(Map.of(2, 6)))
        );
    }

    @ParameterizedTest
    @MethodSource("subtractArgumentsProvider")
    void subtractTest(Polynomial p1, Polynomial p2, Polynomial result) {
        Assertions.assertEquals(PolynomialOperations.subtract(p1, p2).getMonomials(), result.getMonomials());
    }

    static Stream<Arguments> multiplyArgumentsProvider() {
        return Stream.of(
                Arguments.of(new Polynomial(Map.of(3, 2, 1, 2, 0, 4)),
                        new Polynomial(),
                        new Polynomial()),
                Arguments.of(new Polynomial(Map.of(2, 3, 1, 1)),
                        new Polynomial(Map.of(1,2)),
                        new Polynomial(Map.of(3, 6, 2, 2))),
                Arguments.of(new Polynomial(Map.of()),
                        new Polynomial(Map.of(2, -3, 1, 1)),
                        new Polynomial())
        );
    }

    @ParameterizedTest
    @MethodSource("multiplyArgumentsProvider")
    void multiplyTest(Polynomial p1, Polynomial p2, Polynomial result) {
        Assertions.assertEquals(PolynomialOperations.multiply(p1, p2).getMonomials(), result.getMonomials());
    }

    static Stream<Arguments> divisionArgumentsProvider() {
        return Stream.of(
                Arguments.of(new Polynomial(Map.of(3, 3, 2, -5, 1, 10, 0, -3)),
                             new Polynomial(Map.of(1, 3, 0, 1)),
                             new Polynomial(Map.of(2, 1, 1, -2, 0, 4)),
                             new Polynomial(Map.of(0, -7))),
                Arguments.of(new Polynomial(Map.of(3, 2, 2, -9, 1, 0, 0, 15)),
                             new Polynomial(Map.of(1, 2, 0, -5)),
                             new Polynomial(Map.of(2, 1, 1, -2, 0, -5)),
                             new Polynomial(Map.of(0, -10))),
                Arguments.of(new Polynomial(Map.of(4, 4, 3, 3, 2, 0, 1, 2, 0, 1)),
                             new Polynomial(Map.of(2, 1, 1, 1, 0, 2)),
                             new Polynomial(Map.of(2, 4, 1, -1, 0, -7)),
                             new Polynomial(Map.of(1, 11, 0, 15)))
        );
    }

    @ParameterizedTest
    @MethodSource("divisionArgumentsProvider")
    void divisionTest(Polynomial p1, Polynomial p2, Polynomial q, Polynomial r) {
        Assertions.assertEquals(PolynomialOperations.divide(p1, p2)[0].getMonomials(), q.getMonomials());
        Assertions.assertEquals(PolynomialOperations.divide(p1, p2)[1].getMonomials(), r.getMonomials());
    }

    static Stream<Arguments> divisionThrowsArithmeticExceptionProvider() {
        return Stream.of(
                        Arguments.of(new Polynomial(Map.of(1, 2, 3,4)),
                                     new Polynomial()),
                        Arguments.of(new Polynomial(Map.of(2, 4)),
                                     new Polynomial())
        );
    }

    @ParameterizedTest
    @MethodSource("divisionThrowsArithmeticExceptionProvider")
    void divisionThrowsArithmeticExceptionTest(Polynomial p1, Polynomial p2) {
        Assertions.assertThrows(ArithmeticException.class, () -> PolynomialOperations.divide(p1, p2));
    }

    static Stream<Arguments> integrationArgumentsProvider() {
        return Stream.of(
                Arguments.of(new Polynomial(Map.of(3, 2, 1, 2, 0, 4)),
                        new Polynomial(Map.of(4, 0.5, 2, 1, 1, 4))),
                Arguments.of(new Polynomial(Map.of(2, 3, 1, 1)),
                        new Polynomial(Map.of(3, 1, 2, 0.5))),
                Arguments.of(new Polynomial(Map.of(0,1)),
                        new Polynomial(Map.of(1, 1)))
        );
    }

    @ParameterizedTest
    @MethodSource("integrationArgumentsProvider")
    void integrationTest(Polynomial p1, Polynomial result) {
        Assertions.assertEquals(PolynomialOperations.integrate(p1).getMonomials(), result.getMonomials());
    }

    static Stream<Arguments> differentiationArgumentsProvider() {
        return Stream.of(
                Arguments.of(new Polynomial(Map.of(3, 2, 1, 2, 0, 4)),
                             new Polynomial(Map.of(2, 6, 0, 2))),
                Arguments.of(new Polynomial(Map.of(2, 3, 1, 1)),
                             new Polynomial(Map.of(1, 6, 0, 1))),
                Arguments.of(new Polynomial(Map.of(0,1)),
                             new Polynomial(Map.of()))
        );
    }

    @ParameterizedTest
    @MethodSource("differentiationArgumentsProvider")
    void differentiationTest(Polynomial p1, Polynomial result) {
        Assertions.assertEquals(PolynomialOperations.differentiate(p1).getMonomials(), result.getMonomials());
    }
}