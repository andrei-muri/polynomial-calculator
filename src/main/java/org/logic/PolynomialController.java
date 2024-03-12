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
        Polynomial result = new Polynomial();

        for(Map.Entry<Integer, Number> monomial : p1.getMonomials().entrySet()) {
            result.addElement(monomial);
        }
        for(Map.Entry<Integer, Number> monomial : p2.getMonomials().entrySet()) {
            result.addElement(monomial);
        }
        return result.toString();
    }

    public String subtract(String input1, String input2) {
        Polynomial p1 = new Polynomial();
        addPolynomial(input1, p1);
        Polynomial p2 = new Polynomial();
        addPolynomial(input2, p2);
        Polynomial result = new Polynomial();

        for(Map.Entry<Integer, Number> monomial : p1.getMonomials().entrySet()) {
            result.addElement(monomial);
        }
        for(Map.Entry<Integer, Number> monomial : p2.getMonomials().entrySet()) {
            p2.getMonomials().put(monomial.getKey(), (Double) monomial.getValue() * -1);
            result.addElement(monomial);
        }
        return result.toString();
    }

    public String integrate(String input) {
        Polynomial p = new Polynomial();
        addPolynomial(input, p);
        Polynomial result = new Polynomial();
        for(Map.Entry<Integer, Number> monomial : p.getMonomials().entrySet()) {
            double coeff;
            int power;

            coeff = (Double) monomial.getValue() / (monomial.getKey().doubleValue() + 1);
            power = monomial.getKey() + 1;

            Map.Entry<Integer, Number> resultMonomial = new AbstractMap.SimpleEntry<>(power, coeff);
            result.addElement(resultMonomial);
        }
        return result.toString();
    }

    public String differentiate(String input) {
        Polynomial p = new Polynomial();
        addPolynomial(input, p);
        Polynomial result = new Polynomial();
        for(Map.Entry<Integer, Number> monomial : p.getMonomials().entrySet()) {
            double coeff;
            int power;
            if(monomial.getKey() != 0) {
                coeff = (Double) monomial.getValue() * (Double) monomial.getKey().doubleValue();
                power = monomial.getKey() - 1;
            } else {
                coeff = 0;
                power = 0;
            }
            Map.Entry<Integer, Number> resultMonomial = new AbstractMap.SimpleEntry<>(power, coeff);
            result.addElement(resultMonomial);
        }
        return result.toString();
    }

    public String multiply(String input1, String input2) {
        Polynomial p1 = new Polynomial();
        addPolynomial(input1, p1);
        Polynomial p2 = new Polynomial();
        addPolynomial(input2, p2);
        Polynomial result = new Polynomial();
        for(Map.Entry<Integer, Number> m1 : p1.getMonomials().entrySet()) {
            for(Map.Entry<Integer, Number> m2 : p2.getMonomials().entrySet()) {
                double coeff = m1.getValue().doubleValue() * m2.getValue().doubleValue();
                int power = m1.getKey() + m2.getKey();

                result.addElement(new AbstractMap.SimpleEntry<>(power, coeff));
            }
        }
        return result.toString();
    }

    public String divide(String input1, String input2) {
        Polynomial n = new Polynomial();
        addPolynomial(input1, n);
        Polynomial d = new Polynomial();
        addPolynomial(input2, d);

        Polynomial q = new Polynomial();
        Polynomial r = new Polynomial(n.getMonomials());

        while(!r.isZero() && (r.degree() >= d.degree())) {
            Map.Entry<Integer, Number> t = Polynomial.divideMonomials(r.lead(), d.lead());
            q.addElement(t);
            Polynomial interim = new Polynomial();
            interim.addElement(t);
            r.replace(subtractAux(r, multiplyAux(interim, d)).getMonomials());
        }
        return q + " " + r;
    }
    public Polynomial subtractAux(Polynomial p1, Polynomial p2) {
        Polynomial result = new Polynomial();

        for(Map.Entry<Integer, Number> monomial : p1.getMonomials().entrySet()) {
            result.addElement(monomial);
        }
        for(Map.Entry<Integer, Number> monomial : p2.getMonomials().entrySet()) {
            p2.getMonomials().put(monomial.getKey(), (Double) monomial.getValue() * -1);
            result.addElement(monomial);
        }
        return result;
    }

    public Polynomial multiplyAux(Polynomial p1, Polynomial p2) {
        Polynomial result = new Polynomial();
        for(Map.Entry<Integer, Number> m1 : p1.getMonomials().entrySet()) {
            for(Map.Entry<Integer, Number> m2 : p2.getMonomials().entrySet()) {
                double coeff = m1.getValue().doubleValue() * m2.getValue().doubleValue();
                int power = m1.getKey() + m2.getKey();

                result.addElement(new AbstractMap.SimpleEntry<>(power, coeff));
            }
        }
        return result;
    }
}
