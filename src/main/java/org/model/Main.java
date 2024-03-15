package org.model;

import org.logic.PolynomialController;

public class Main {
    public static void main(String[] args) {
        PolynomialController controller = new PolynomialController();
        try {
            System.out.println(controller.subtract("x^3+x", "x-1"));
        } catch(IllegalArgumentException | ArithmeticException e) {
            System.out.println(e.getMessage());
        }
    }
}
