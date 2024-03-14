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

    public String add(String input1, String input2) {
        Polynomial p1 = new Polynomial();
        addPolynomial(input1, p1);
        Polynomial p2 = new Polynomial();
        addPolynomial(input2, p2);

        return PolynomialOperations.add(p1, p2).toString();
    }

    public String subtract(String input1, String input2) {
        Polynomial p1 = new Polynomial();
        addPolynomial(input1, p1);
        Polynomial p2 = new Polynomial();
        addPolynomial(input2, p2);

        return PolynomialOperations.subtract(p1, p2).toString();
    }

    public String integrate(String input) {
        Polynomial p = new Polynomial();
        addPolynomial(input, p);

        return PolynomialOperations.integrate(p).toString();
    }

    public String differentiate(String input) {
        Polynomial p = new Polynomial();
        addPolynomial(input, p);

        return PolynomialOperations.differentiate(p).toString();
    }

    public String multiply(String input1, String input2) {
        Polynomial p1 = new Polynomial();
        addPolynomial(input1, p1);
        Polynomial p2 = new Polynomial();
        addPolynomial(input2, p2);

        return PolynomialOperations.multiply(p1, p2).toString();
    }

    public String divide(String input1, String input2) {
        Polynomial n = new Polynomial();
        addPolynomial(input1, n);
        Polynomial d = new Polynomial();
        addPolynomial(input2, d);

        Polynomial[] result = PolynomialOperations.divide(n, d);


        return result[0] + " " + result[1];
    }
}
