package org.logic;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PolynomialExtractor {
    public List<Map.Entry<Integer, Number>> extractPolynomials(String input) {
        List<Map.Entry<Integer, Number>> monomials = new ArrayList<>();
        MonomialParser parser = new MonomialParser();
        Pattern pattern = Pattern.compile("((^|[-+])\\b(\\d{1,9}(\\.\\d{1,4})?)?(x(\\^(\\d{1,2}))?)?)");
        Matcher matcher = pattern.matcher(input);

        int endOfLastMatch = 0;

        while(matcher.find()) {
            monomials.add(parser.parseToMonomial(matcher));
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
}
