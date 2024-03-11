package org.model;

import org.logic.PolynomialController;

public class Main {
    public static void main(String[] args) {
        PolynomialController controller = new PolynomialController();
        controller.addPolynomial("3x^2+4");
        controller.addPolynomial("3x^2+7-7x^6");
        controller.addPolynomial("9x^7+2-2-9x^7");
        controller.printPolynomials();
    }
}
