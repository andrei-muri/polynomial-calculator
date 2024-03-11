package org.logic;

import java.util.AbstractMap;
import java.util.Map;
import java.util.regex.Matcher;

public class MonomialParser {
    public Map.Entry<Integer, Number> parseToMonomial(Matcher match) {
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
