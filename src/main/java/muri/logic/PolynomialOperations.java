package muri.logic;

import muri.model.Polynomial;

import java.util.AbstractMap;
import java.util.Map;

/**
 * Utility class that implements operations on {@link Polynomial} variables. Its interface consists of just {@link Polynomial}
 * variables. The operations are: {@linkplain #add addition}, {@link #subtract subtraction}, {@link #multiply multiplication},
 * {@linkplain #divide division}, {@linkplain #differentiate differentiation} and {@linkplain #integrate integration}.
 *
 * @author Muresan Andrei, Technical University of Cluj-Napoca, Computer Science, Second Year, English, Group 30425_2
 * @version 1.0
 * @Year: 2024
 */
public class PolynomialOperations {
    /**
     * Takes as input two {@link Polynomial} polynomials and returns the sum in a NEW {@link Polynomial}.
     * OPERATIONS ARE NOT DONE ON
     * THE PARAMETERS.
     * @param p1 first {@link Polynomial}
     * @param p2 second {@link Polynomial}
     * @return new {@link Polynomial} result
     */
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

    /**
     * Takes as input two {@link Polynomial} polynomials and returns the difference in a NEW {@link Polynomial}.
     * OPERATIONS ARE NOT DONE ON
     * THE PARAMETERS.
     * @param p1 first {@link Polynomial}
     * @param p2 second {@link Polynomial}
     * @return new {@link Polynomial} result
     */
    public static Polynomial subtract(Polynomial p1, Polynomial p2) {
        Polynomial result = new Polynomial();
        Polynomial negativeP2 = new Polynomial(p2.getMonomials());
        for(Map.Entry<Integer, Number> monomial : p1.getMonomials().entrySet()) {
            result.addElement(monomial);
        }
        for(Map.Entry<Integer, Number> monomial : negativeP2.getMonomials().entrySet()) {
            negativeP2.getMonomials().put(monomial.getKey(), (Double) monomial.getValue() * -1);
            result.addElement(monomial);
        }
        return result;
    }

    /**
     * Takes as input on {@link Polynomial} and returns the integration in a NEW {@link Polynomial}.
     * OPERATIONS ARE NOT DONE ON
     * THE PARAMETER.
     * @param p1 first {@link Polynomial}
     * @return new {@link Polynomial} result
     */
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

    /**
     * Takes as input on {@link Polynomial} and returns the differentiation in a NEW {@link Polynomial}.
     * OPERATIONS ARE NOT DONE ON
     * THE PARAMETER.
     * @param p1 first {@link Polynomial}
     * @return new {@link Polynomial} result
     */
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

    /**
     * Takes as input two {@link Polynomial} polynomials and returns the multiplication in a NEW {@link Polynomial}.
     * OPERATIONS ARE NOT DONE ON
     * THE PARAMETERS.
     * @param p1 first {@link Polynomial}
     * @param p2 second {@link Polynomial}
     * @return new {@link Polynomial} result
     */
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

    /**
     * Takes as input two {@link Polynomial} polynomials and returns the division in a NEW {@link Polynomial}.
     * OPERATIONS ARE NOT DONE ON
     * THE PARAMETERS.
     * @param n numerator {@link Polynomial}
     * @param d divider {@link Polynomial}
     * @return new {@link Polynomial} result
     * @throws ArithmeticException if the second polynomial is zero
     */
    public static Polynomial[] divide(Polynomial n, Polynomial d) {
        if(d.isZero()) {
            throw new ArithmeticException("Divider cannot be zero!");
        }

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
