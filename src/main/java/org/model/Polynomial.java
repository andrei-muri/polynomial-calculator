package org.model;

import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

public class Polynomial {
    Map<Integer, Number> monomials;

    public Polynomial() {
        this.monomials = new TreeMap<>(Comparator.reverseOrder());
    }

    public void addElement(Map.Entry<Integer, Number> monomial) {
        Integer inputKey = monomial.getKey();
        double inputValue = monomial.getValue().doubleValue();
        if(this.monomials.containsKey(inputKey)) {
            double newCoeff = this.monomials.get(inputKey).doubleValue() + inputValue;
            if(newCoeff != 0.0) {
                this.monomials.put(inputKey, newCoeff);
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

    public Map<Integer, Number> getMonomials() {
        return monomials;
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
