package muri.model;

import java.util.AbstractMap;
import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;



/**
 * This class is a representation of a polynomial. It consists of a collection of monomials, key representing
 * the power and value the coefficient.
 * <p>
 * It has two fields: {@link #monomials} is the collection of monomials and {@link #degree} is the highest power
 * among the monomials.
 * <p>
 * It has two constructors, one that just initialize the fields and the second one that takes as parameters a
 * monomials collection that is used to populate {@link #monomials}.
 *
 * @author Muresan Andrei, Technical University of Cluj-Napoca, Computer Science, Second Year, English, Group 30425_2
 * @version 1.0
 * @Year: 2024
 */

public class Polynomial {
    /**
     * This is the collection of the monomials. It is implemented using a {@link TreeMap}.
     */
    Map<Integer, Number> monomials;
    /**
     * Maximum power among the monomials.
     */
    int degree = 0;

    /**
     * Constructor with no parameters. It allocates space for the {@link TreeMap}, then it adds the 0 element.
     */
    public Polynomial() {
        this.monomials = new TreeMap<>(Comparator.reverseOrder());
        this.monomials.put(0,0.0);
    }

    /**
     * Creates the collection of monomials using another collection of monomials.
     * @param input collection of monomials
     */
    public Polynomial(Map<Integer, Number> input) {
        this();
        for(Map.Entry<Integer, Number> monomial : input.entrySet()) {
            addElement(monomial);
        }
    }

    /**
     * Method called on object that adds a polynomial to the collection. If the power (key) already exists,
     * it adds the coefficient to the existing one, if the result is zero, the key is eliminated. The only case
     * this doesn't happen is when the deletion results in an empty collection.
     * @param monomial represents an entry in a map of the same type as a monomial in the polynomial collection
     */
    public void addElement(Map.Entry<Integer, Number> monomial) {
        Integer inputKey = monomial.getKey();
        double inputValue = monomial.getValue().doubleValue();
        if(this.monomials.containsKey(0) && this.monomials.get(0).doubleValue() == 0.0 && inputValue != 0) {
            this.monomials.remove(0);
        }
        if(this.monomials.containsKey(inputKey) && inputValue != 0) {
            double newCoeff = this.monomials.get(inputKey).doubleValue() + inputValue;
            if(newCoeff != 0.0) {
                this.monomials.put(inputKey, newCoeff);
            } else {
                this.monomials.remove(inputKey);
            }

        } else if (inputValue != 0){
            this.monomials.put(inputKey, inputValue);
        }
        if(this.monomials.isEmpty()) {
            this.monomials.put(0,0.0);
        }
        if(inputKey > this.degree) {
            this.degree = inputKey;
        }
    }

    /**
     * Returns the biggest power among the monomials.
     * @return the biggest power among the monomials
     */
    public int degree() {
        return this.degree;
    }

    /**
     * Returns the first entry, i.e. the monomial with the biggest power.
     * @return returns the first entry
     */
    public Map.Entry<Integer, Number> lead() {
        if(this.monomials instanceof TreeMap<Integer, Number> map) {
            return map.firstEntry();
        }
        return null;
    }

    /**
     * Returns the monomials collection in the form of a {@link TreeMap}.
     * @return the monomial collection
     */
    public Map<Integer, Number> getMonomials() {
        return this.monomials;
    }

    /**
     * Checks if the only element in the collection is the zero element. It can be used for divide by zero checks.
     * @return {@code true} if the polynomial is zero; {@code false} otherwise
     */
    public boolean isZero() {
        if(this.monomials instanceof TreeMap<Integer, Number> map) {
            if(!this.monomials.isEmpty()) {
                return map.firstEntry().getKey() == 0 && map.firstEntry().getValue().doubleValue() == 0.0;
            }
        }
        return false;
    }

    /**
     * Replaces the current monomials with another collection of monomials.
     * @param input another collection of monomials
     */
    public void replace(Map<Integer, Number> input) {
        this.monomials.clear();
        this.degree = 0;
        for(Map.Entry<Integer, Number> monomial : input.entrySet()) {
            addElement(monomial);
        }
    }

    /**
     * It takes as input two monomials and returns the division of the first by the second.
     * @param m1 first monomial
     * @param m2 second monomial
     * @return result of the division in the form of an {@code AbstractMap.SimpleEntry<Integer, Number>}
     * @throws ArithmeticException if the second monomial is zero
     */
    public static Map.Entry<Integer, Number> divideMonomials(Map.Entry<Integer, Number> m1, Map.Entry<Integer, Number> m2) {
        if(m2.getValue().doubleValue() == 0) throw new ArithmeticException("Cannot divide by a 0 monomial");
        return new AbstractMap.SimpleEntry<>(m1.getKey() - m2.getKey(), m1.getValue().doubleValue() / m2.getValue().doubleValue());
    }

    /**
     * Displays the monomial collection in string polynomial form. E.g. (2->1), (3->2) is {@code 2x^3+x^2}
     * @return the collection in string polynomial form
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<Integer, Number> entry : monomials.entrySet()) {
            int power = entry.getKey();
            double coefficient = entry.getValue().doubleValue();

            if (!sb.isEmpty() && coefficient > 0) {
                sb.append("+");
            }

            // Formatting the coefficient to two decimal places
            String formattedCoefficient = (coefficient != (long) coefficient)
                    ? String.format("%.2f", coefficient)
                    : String.valueOf((long) coefficient);

            // Determine if we need the coefficient part
            if (coefficient != 1 && coefficient != -1 || power == 0) {
                sb.append(formattedCoefficient);
            } else if (coefficient == -1 && power != 0) {
                sb.append("-");
            }

            // Append the variable part
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
