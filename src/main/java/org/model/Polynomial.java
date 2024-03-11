package org.model;

import java.util.AbstractMap;
import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Polynomial {
    Map<Integer, Number> monomials;

    public Polynomial() {
        this.monomials = new TreeMap<>(Comparator.reverseOrder());
    }

    public Polynomial(String polynom) {
        this.monomials = new TreeMap<>(Comparator.reverseOrder());
        if(!extractPolynomials(polynom)) {
            this.monomials =  null;
            throw new IllegalArgumentException("Incorrect polynomial input");
        }
    }

    private boolean extractPolynomials(String input) {
        Pattern pattern = Pattern.compile("((^|[-+])\\b(\\d{1,9}(\\.\\d{1,4})?)?(x(\\^(\\d{1,2}))?)?)");
        Matcher matcher = pattern.matcher(input);

        int endOfLastMatch = 0;

        while(matcher.find()) {
            Map.Entry<Integer, Number> monomial = parseToMonomial(matcher);
            addElement(monomial);
            if(matcher.start() != endOfLastMatch) {
                return false;
            }
            endOfLastMatch = matcher.end();
        }

        if (endOfLastMatch != input.length()) {
            return false;
        }
        return true;
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
        int integerCoeff = (int)coeff;
        if(coeff == (int)coeff) {
            return new AbstractMap.SimpleEntry<>(power, integerCoeff);
        } else {
            return new AbstractMap.SimpleEntry<>(power, coeff);
        }
    }

    private void addElement(Map.Entry<Integer, Number> monomial) {
        Integer inputKey = monomial.getKey();
        double inputValue = monomial.getValue().doubleValue();
        if(this.monomials.containsKey(inputKey)) {
            double newCoeff = this.monomials.get(inputKey).doubleValue() + inputValue;
            if(newCoeff != 0.0) {
                if(newCoeff != (int)newCoeff) {
                    this.monomials.put(inputKey, newCoeff);
                } else {
                    this.monomials.put(inputKey, (int)newCoeff);
                }
            } else {
                this.monomials.remove(inputKey);
            }
            if(this.monomials.isEmpty()) {
                this.monomials.put(0, 0.0);
            }

        } else {
            this.monomials.put(inputKey, inputValue);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<Integer, Number> entry : monomials.entrySet()) {
            int power = entry.getKey();
            Number coefficient = entry.getValue();
//            if (coefficient.doubleValue() == 0) {
//                continue;
//            }
            if (!sb.isEmpty() && coefficient.doubleValue() > 0) {
                sb.append("+");
            }
            if (coefficient.doubleValue() != 1 && coefficient.doubleValue() != -1 || power == 0) {
                sb.append(coefficient);
            } else if (coefficient.doubleValue() == -1) {
                sb.append("-");
            }
            if (power > 0) {
                sb.append("x");
                if (power > 1) {
                    sb.append("^").append(power);
                }
            }
        }
        return sb.toString();
    }
}
