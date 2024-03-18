package muri.logic;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

class PolynomialControllerTest {
    static Stream<Arguments> addArgumentProvider() {
        return Stream.of(
                        Arguments.of("x^3+6x-7", "x^3-7x^2-7x+3", "2x^3-7x^2-x-4"),
                        Arguments.of("x^2+6x", "-x^2-6x", "0"),
                        Arguments.of("x^7+6x", "-x^7+9", "6x+9")
        );
    }

    @ParameterizedTest
    @MethodSource("addArgumentProvider")
    void addTest(String p1, String p2, String result) {
        PolynomialController controller = new PolynomialController();
        Assertions.assertEquals(controller.add(p1, p2), result);
    }

    static Stream<Arguments> subtractArgumentProvider() {
        return Stream.of(
                Arguments.of("x^3+6x-7", "x^3-7x^2-7x+3", "7x^2+13x-10"),
                Arguments.of("x^2+6x", "+x^2+6x", "0"),
                Arguments.of("x^7+6x", "-x^7+9", "2x^7+6x-9")
        );
    }

    @ParameterizedTest
    @MethodSource("subtractArgumentProvider")
    void subtractTest(String p1, String p2, String result) {
        PolynomialController controller = new PolynomialController();
        Assertions.assertEquals(controller.subtract(p1, p2), result);
    }

    static Stream<Arguments> multiplyArgumentProvider() {
        return Stream.of(
                Arguments.of("x^3+6x-7", "x", "x^4+6x^2-7x"),
                Arguments.of("x^2+6x", "0", "0"),
                Arguments.of("0", "-x^7+9", "0")
        );
    }

    @ParameterizedTest
    @MethodSource("multiplyArgumentProvider")
    void multiplyTest(String p1, String p2, String result) {
        PolynomialController controller = new PolynomialController();
        Assertions.assertEquals(controller.multiply(p1, p2), result);
    }

    static Stream<Arguments> integrateArgumentProvider() {
        return Stream.of(
                Arguments.of("x^3+6x-7", "0.25x^4+3x^2-7x"),
                Arguments.of("x^3+6x", "0.25x^4+3x^2"),
                Arguments.of("1", "x")
        );
    }

    @ParameterizedTest
    @MethodSource("integrateArgumentProvider")
    void integrateTest(String p1, String result) {
        PolynomialController controller = new PolynomialController();
        Assertions.assertEquals(controller.integrate(p1), result);
    }

    static Stream<Arguments> differentiationArgumentProvider() {
        return Stream.of(
                Arguments.of("x^3+6x-7", "3x^2+6"),
                Arguments.of("x^3+6x^2", "3x^2+12x"),
                Arguments.of("x", "1")
        );
    }

    @ParameterizedTest
    @MethodSource("differentiationArgumentProvider")
    void differentiationTest(String p1, String result) {
        PolynomialController controller = new PolynomialController();
        Assertions.assertEquals(controller.differentiate(p1), result);
    }

    static Stream<Arguments> divisionArgumentProvider() {
        return Stream.of(
                Arguments.of("4x^2-5x-21", "x-3", "4x+7 0"),
                Arguments.of("4x^3+5x^2+5x+8", "4x+1", "x^2+x+1 7"),
                Arguments.of("0", "-x^7+9", "0 0")
        );
    }

    @ParameterizedTest
    @MethodSource("divisionArgumentProvider")
    void divisionTest(String p1, String p2, String result) {
        PolynomialController controller = new PolynomialController();
        Assertions.assertEquals(controller.divide(p1, p2), result);
    }

    static Stream<Arguments> divisionThrowsArithmeticExceptionArgumentProvider() {
        return Stream.of(
                Arguments.of("4x^2-5x-21", "0", "4x+7 0"),
                Arguments.of("4x^3+5x^2+5x+8", "0", "x^2+x+1 7"),
                Arguments.of("0", "0", "0 0")
        );
    }

    @ParameterizedTest
    @MethodSource("divisionThrowsArithmeticExceptionArgumentProvider")
    void divisionThrowsArithmeticExceptionTest(String p1, String p2) {
        PolynomialController controller = new PolynomialController();
        Assertions.assertThrows(ArithmeticException.class, () -> controller.divide(p1, p2));
    }

    static Stream<String> throwsIllegalArgumentExceptionArgumentProvider() {
        return Stream.of("asdsa+x", "x^1++x", "x^2+x+X^3", "x^2+-x", "-x+x^2nd");
    }

    @ParameterizedTest
    @MethodSource("throwsIllegalArgumentExceptionArgumentProvider")
    void throwsIllegalArgumentExceptionTest(String input) {
        PolynomialController controller = new PolynomialController();
        Assertions.assertThrows(IllegalArgumentException.class, () -> controller.add(input, input));
        Assertions.assertThrows(IllegalArgumentException.class, () -> controller.subtract(input, input));
        Assertions.assertThrows(IllegalArgumentException.class, () -> controller.multiply(input, input));
        Assertions.assertThrows(IllegalArgumentException.class, () -> controller.divide(input, input));
        Assertions.assertThrows(IllegalArgumentException.class, () -> controller.integrate(input));
        Assertions.assertThrows(IllegalArgumentException.class, () -> controller.differentiate(input));
    }
}