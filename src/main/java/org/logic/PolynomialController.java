package org.logic;

import org.model.Polynomial;

import java.util.AbstractMap;
import java.util.List;
import java.util.Map;

public class PolynomialController {

    private void addPolynomial(String input, Polynomial p) {
        PolynomialExtractor extractor = new PolynomialExtractor();
        List<Map.Entry<Integer, Number>> monomials = extractor.extractPolynomials(input);
        if(monomials != null) {
            for(Map.Entry<Integer, Number> monomial : monomials) {
                p.addElement(monomial);
            }
        } else {
            throw new IllegalArgumentException("Invalid polynom type");
        }
    }

    public Polynomial add(String input1, String input2) {
        Polynomial p1 = new Polynomial();
        addPolynomial(input1, p1);
        Polynomial p2 = new Polynomial();
        addPolynomial(input2, p2);
        Polynomial result = new Polynomial();

        for(Map.Entry<Integer, Number> monomial : p1.getMonomials().entrySet()) {
            result.addElement(monomial);
        }
        for(Map.Entry<Integer, Number> monomial : p2.getMonomials().entrySet()) {
            result.addElement(monomial);
        }
        return result;
    }

    public Polynomial subtract(String input1, String input2) {
        Polynomial p1 = new Polynomial();
        addPolynomial(input1, p1);
        Polynomial p2 = new Polynomial();
        addPolynomial(input2, p2);
        Polynomial result = new Polynomial();

        for(Map.Entry<Integer, Number> monomial : p1.getMonomials().entrySet()) {
            result.addElement(monomial);
        }
        for(Map.Entry<Integer, Number> monomial : p2.getMonomials().entrySet()) {
            p2.getMonomials().put(monomial.getKey(), (Double)monomial.getValue() * -1);
            result.addElement(monomial);
        }
        return result;
    }

    public Polynomial integrate(String input) {
        Polynomial p = new Polynomial();
        addPolynomial(input, p);
        Polynomial result = new Polynomial();
        for(Map.Entry<Integer, Number> monomial : p.getMonomials().entrySet()) {
            double coeff;
            int power;
            if(monomial.getKey() == 0) {
                power = 0;
                coeff = 0;
            } else {
                coeff = (Double)monomial.getValue() / (monomial.getKey().doubleValue()+1);
                power = monomial.getKey() + 1;
            }

            Map.Entry<Integer, Number> resultMonomial = new AbstractMap.SimpleEntry<>(power, coeff);
            result.addElement(resultMonomial);
        }
        return result;
    }
}
