package org.model;

import org.logic.PolynomialController;

public class Main {
    public static void main(String[] args) {
        PolynomialController controller = new PolynomialController();
        System.out.println(controller.subtract("3x^2+4", "4-3x^2"));
    }
}
