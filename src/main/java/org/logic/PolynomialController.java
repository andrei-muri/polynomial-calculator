package org.logic;

import org.model.Polynomial;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PolynomialController {
    List<Polynomial> polynomials;

    public PolynomialController() {
        this.polynomials = new ArrayList<>(2);
    }
    public void addPolynomial(String input) {
        PolynomialExtractor extractor = new PolynomialExtractor();
        Polynomial polynomial = new Polynomial();
        List<Map.Entry<Integer, Number>> monomials = extractor.extractPolynomials(input);
        if(monomials != null) {
            for(Map.Entry<Integer, Number> monomial : monomials) {
                polynomial.addElement(monomial);
            }
            polynomials.add(polynomial);
        } else {
            throw new IllegalArgumentException("Invalid polynom type");
        }
    }

    public void printPolynomials() {
        for(Polynomial p : polynomials) {
            System.out.println(p);
        }
    }
}
