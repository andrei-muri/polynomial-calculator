package org.model;

import org.logic.PolynomialController;

public class Main {
    public static void main(String[] args) {
        PolynomialController controller = new PolynomialController();
        System.out.println(controller.divide("x^3+2x^2-5x+1", "x-2"));
    }
}
