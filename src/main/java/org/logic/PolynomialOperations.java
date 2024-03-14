package org.logic;

import org.model.Polynomial;

import java.util.AbstractMap;
import java.util.Map;

public class PolynomialOperations {
    public static Polynomial add(Polynomial p1, Polynomial p2) {
        Polynomial result = new Polynomial();
        for(Map.Entry<Integer, Number> monomial : p1.getMonomials().entrySet()) {
            result.addElement(monomial);
        }
        for(Map.Entry<Integer, Number> monomial : p2.getMonomials().entrySet()) {
            result.addElement(monomial);
        }
        return result;
    }

    public static Polynomial subtract(Polynomial p1, Polynomial p2) {
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

    public static Polynomial integrate(Polynomial p1) {
        Polynomial result = new Polynomial();
        for(Map.Entry<Integer, Number> monomial : p1.getMonomials().entrySet()) {
            double coeff;
            int power;

            coeff = (Double) monomial.getValue() / (monomial.getKey().doubleValue() + 1);
            power = monomial.getKey() + 1;

            Map.Entry<Integer, Number> resultMonomial = new AbstractMap.SimpleEntry<>(power, coeff);
            result.addElement(resultMonomial);
        }
        return result;
    }

    public static Polynomial differentiate(Polynomial p1) {
        Polynomial result = new Polynomial();
        for(Map.Entry<Integer, Number> monomial : p1.getMonomials().entrySet()) {
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
        return result;
    }

    public static Polynomial multiply(Polynomial p1, Polynomial p2) {
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

    public static Polynomial[] divide(Polynomial n, Polynomial d) {
        Polynomial q = new Polynomial();
        Polynomial r = new Polynomial(n.getMonomials());

        while(!r.isZero() && (r.degree() >= d.degree())) {
            Map.Entry<Integer, Number> t = Polynomial.divideMonomials(r.lead(), d.lead());
            q.addElement(t);
            Polynomial interim = new Polynomial();
            interim.addElement(t);
            r.replace(PolynomialOperations.subtract(r, PolynomialOperations.multiply(interim, d)).getMonomials());
        }
        return new Polynomial[]{q, r};
    }
}
