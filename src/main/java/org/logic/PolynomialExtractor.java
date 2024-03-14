package org.logic;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PolynomialExtractor {
    public List<Map.Entry<Integer, Number>> extractPolynomials(String input) {
        List<Map.Entry<Integer, Number>> monomials = new ArrayList<>();
        Pattern pattern = Pattern.compile("((^|[-+])\\b(\\d{1,9}(\\.\\d{1,4})?)?(x(\\^(\\d{1,2}))?)?)");
        Matcher matcher = pattern.matcher(input);

        int endOfLastMatch = 0;

        while(matcher.find()) {
            monomials.add(parseToMonomial(matcher));
            if(matcher.start() != endOfLastMatch) {
                return null;
            }
            endOfLastMatch = matcher.end();
        }

        if (endOfLastMatch != input.length()) {
            return null;
        }
        return monomials;
    }

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
