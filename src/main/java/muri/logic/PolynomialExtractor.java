
package muri.logic;

import muri.model.Polynomial;

import java.util.AbstractMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class takes care of the parsing of the polynomials, from a string format to the {@link Polynomial} form.
 * The interface consists of a method {@link #extractPolynomials(String)} takes as input a String formatted polynomial
 * and returns the correspondent {@link Polynomial} variable. {@link #parseToMonomial(Matcher)} is a helper method that
 * takes a match and transforms it to a {@code AbstractMap.SimpleEntry<Integer, Number>}, i.e. a monomial.
 *
 * @author Muresan Andrei, Technical University of Cluj-Napoca, Computer Science, Second Year, English, Group 30425_2
 * @version 1.0
 * @Year: 2024
 */
public class PolynomialExtractor {
    /**
     * Takes a string formatted polynomial and transforms it to a {@link Polynomial} variable and returns it. It also
     * checks if the input string is in the correct format. Example of correct formats: x^2-5x, 1.2x^1, -x+6x^2-7x^2,
     * 0x+0+1, etc. Incorrect examples: x^(-1), x^(1.2), x^2++x, --x^3 etc. It uses pattern matching.
     * @param input polynomial in string format
     * @return corresponding {@link Polynomial} variable
     * @throws IllegalArgumentException if the input string is not in the right format
     */
    public Polynomial extractPolynomials(String input) {
        IllegalArgumentException wrongFormat = new IllegalArgumentException("Illegal polynomial format");
        Polynomial p = new Polynomial();
        Pattern pattern = Pattern.compile("((^|[-+])\\b(\\d{1,9}(\\.\\d{1,4})?)?(x(\\^(\\d{1,2}))?)?)");
        Matcher matcher = pattern.matcher(input);

        int endOfLastMatch = 0;
        boolean foundMatch = false;

        while(matcher.find()) {
            foundMatch = true;
            p.addElement(parseToMonomial(matcher));
            if(matcher.start() != endOfLastMatch) {
                throw wrongFormat;
            }
            endOfLastMatch = matcher.end();
        }

        if (endOfLastMatch != input.length() || !foundMatch) {
            throw wrongFormat;
        }
        return p;
    }

    /**
     * Helper method that transforms a {@link Matcher} variable into a monomial.
     * @param match match found in the {@link #extractPolynomials(String)}
     * @return a {@link Map.Entry}, i.e. a monomial
     */
    private Map.Entry<Integer, Number> parseToMonomial(Matcher match) {
        Integer power;
        double coeff;
        if(match.group(3) == null) {
            coeff = 1.0;
        } else {
            coeff = Double.parseDouble(match.group(3));
        }
        if(match.group(2).equals("-")) {
            coeff = -(double)coeff;
        }
        if(match.group(7) != null) {
            power = Integer.parseInt(match.group(7));
        } else if(match.group(5) != null) {
            power = 1;
        } else {
            power = 0;
        }
        return new AbstractMap.SimpleEntry<>(power, coeff);
    }
}
