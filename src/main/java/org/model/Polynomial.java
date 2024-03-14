package org.model;

import java.util.AbstractMap;
import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

public class Polynomial {
    Map<Integer, Number> monomials;
    int degree = 0;

    public Polynomial() {
        this.monomials = new TreeMap<>(Comparator.reverseOrder());
        this.monomials.put(0,0.0);
    }

    public Polynomial(Map<Integer, Number> input) {
        this();
        for(Map.Entry<Integer, Number> monomial : input.entrySet()) {
            addElement(monomial);
        }
    }

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
            if(this.monomials.isEmpty()) {
                this.monomials.put(0,0.0);
            }

        } else if (inputValue != 0){
            this.monomials.put(inputKey, inputValue);
        }

        if(inputKey > this.degree) {
            this.degree = inputKey;
        }
    }

    public int degree() {
        return this.degree;
    }

    public Map.Entry<Integer, Number> lead() {
        if(this.monomials instanceof TreeMap<Integer, Number> map) {
            return map.firstEntry();
        }
        return null;
    }

    public Map<Integer, Number> getMonomials() {
        return this.monomials;
    }

    public boolean isZero() {
        if(this.monomials instanceof TreeMap<Integer, Number> map) {
            if(!this.monomials.isEmpty()) {
                return map.firstEntry().getKey() == 0 && map.firstEntry().getValue().doubleValue() == 0.0;
            }
        }
        return false;
    }

    public void replace(Map<Integer, Number> input) {
        this.monomials.clear();
        this.degree = 0;
        for(Map.Entry<Integer, Number> monomial : input.entrySet()) {
            addElement(monomial);
        }
    }

    public static Map.Entry<Integer, Number> divideMonomials(Map.Entry<Integer, Number> m1, Map.Entry<Integer, Number> m2) {
        return new AbstractMap.SimpleEntry<>(m1.getKey() - m2.getKey(), m1.getValue().doubleValue() / m2.getValue().doubleValue());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(Map.Entry<Integer, Number> entry : monomials.entrySet()) {
            int power = entry.getKey();
            Number coefficient = entry.getValue();
            if(!sb.isEmpty() && coefficient.doubleValue() > 0) {
                sb.append("+");
            }
            if(coefficient.doubleValue() != 1 && coefficient.doubleValue() != -1 || power == 0) {
                sb.append(coefficient);
            } else if(coefficient.doubleValue() == -1) {
                sb.append("-");
            }
            if(power > 0) {
                sb.append("x");
                if(power > 1) {
                    sb.append("^").append(power);
                }
            }
        }
        return sb.toString();
    }
}
