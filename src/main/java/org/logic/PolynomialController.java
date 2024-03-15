package org.logic;

import org.model.Polynomial;

/**
 * This is the class that handles the communication between the main controller and the {@link Polynomial}. It handles
 * the operation requests such as {@link #add}, {@link #subtract}, {@link #multiply}, {@link #divide},
 * {@link #differentiate} and {@link #integrate}. Its interface consists only in strings, polynomial operations being
 * handled by methods from the utility class {@link PolynomialOperations}. It uses the {@link PolynomialExtractor} class for
 * string to polynomial parsing.
 *
 * @author Muresan Andrei, Technical University of Cluj-Napoca, Computer Science, Second Year, English, Group 30425_2
 * @version 1.0
 * @Year: 2024
 */
public class PolynomialController {
    /**
     * {@link PolynomialExtractor} that is used for {@link String} to {@link Polynomial} parsing.
     */
    PolynomialExtractor polynomialExtractor;

    /**
     * Constructor that initializes the {@link PolynomialExtractor} field
     */
    public PolynomialController() {
        this.polynomialExtractor = new PolynomialExtractor();
    }

    /**
     * Takes two string-formatted polynomials, converts them into {@link Polynomial}, performs addition using the
     * {@code add} method from the utility class {@link PolynomialOperations} and prints the result in string format.
     * @param input1 first string-formatted polynomial
     * @param input2 second string-formatted polynomial
     * @return addition of the two polynomials in string format
     * @throws IllegalArgumentException if any of the two string are not of polynomial format, rules presented in
     * {@link PolynomialExtractor}
     */
    public String add(String input1, String input2) {
        Polynomial p1 = polynomialExtractor.extractPolynomials(input1);
        Polynomial p2 = polynomialExtractor.extractPolynomials(input2);

        return PolynomialOperations.add(p1, p2).toString();
    }

    /**
     * Takes two string-formatted polynomials, converts them into {@link Polynomial}, performs subtraction using the
     * {@code subtract} method from the utility class {@link PolynomialOperations} and prints the result in string format.
     * @param input1 first string-formatted polynomial
     * @param input2 second string-formatted polynomial
     * @return subtraction of the two polynomials in string format
     * @throws IllegalArgumentException if any of the two string are not of polynomial format, rules presented in
     * {@link PolynomialExtractor}
     */
    public String subtract(String input1, String input2) {
        Polynomial p1 = polynomialExtractor.extractPolynomials(input1);
        Polynomial p2 = polynomialExtractor.extractPolynomials(input2);

        return PolynomialOperations.subtract(p1, p2).toString();
    }
    /**
     * Takes one string-formatted polynomial, converts it into {@link Polynomial}, performs integration using the
     * {@code integrate} method from the utility class {@link PolynomialOperations} and prints the result in string format.
     * @param input string-formatted polynomial
     * @return integration of the polynomial in string format
     * @throws IllegalArgumentException if the string isn't of polynomial format, rules presented in
     * {@link PolynomialExtractor}
     */
    public String integrate(String input) {
        Polynomial p = polynomialExtractor.extractPolynomials(input);

        return PolynomialOperations.integrate(p).toString();
    }

    /**
     * Takes one string-formatted polynomial, converts it into {@link Polynomial}, performs derivation using the
     * {@code differentiate} method from the utility class {@link PolynomialOperations} and prints the result in string format.
     * @param input string-formatted polynomial
     * @return differentiation of the polynomial in string format
     * @throws IllegalArgumentException if the string isn't of polynomial format, rules presented in
     * {@link PolynomialExtractor}
     */
    public String differentiate(String input) {
        Polynomial p = polynomialExtractor.extractPolynomials(input);

        return PolynomialOperations.differentiate(p).toString();
    }

    /**
     * Takes two string-formatted polynomials, converts them into {@link Polynomial}, performs multiplication using the
     * {@code multiply} method from the utility class {@link PolynomialOperations} and prints the result in string format.
     * @param input1 first string-formatted polynomial
     * @param input2 second string-formatted polynomial
     * @return multiplication of the two polynomials in string format
     * @throws IllegalArgumentException if any of the two string are not of polynomial format, rules presented in
     * {@link PolynomialExtractor}
     */
    public String multiply(String input1, String input2) throws IllegalArgumentException{
        Polynomial p1 = polynomialExtractor.extractPolynomials(input1);
        Polynomial p2 = polynomialExtractor.extractPolynomials(input2);

        return PolynomialOperations.multiply(p1, p2).toString();
    }

    /**
     * Takes two string-formatted polynomials, converts them into {@link Polynomial}, performs division using the
     * {@code divide} method from the utility class {@link PolynomialOperations} and prints the result in string format.
     * @param input1 first string-formatted polynomial
     * @param input2 second string-formatted polynomial
     * @return division of the two polynomials in string format
     * @throws IllegalArgumentException if any of the two string are not of polynomial format, rules presented in
     * {@link PolynomialExtractor}
     * @throws ArithmeticException if the second polynomial is 0
     */
    public String divide(String input1, String input2) throws IllegalArgumentException, ArithmeticException{
        Polynomial n = polynomialExtractor.extractPolynomials(input1);
        Polynomial d = polynomialExtractor.extractPolynomials(input2);

        Polynomial[] result = PolynomialOperations.divide(n, d);
        return result[0] + " " + result[1];
    }
}
