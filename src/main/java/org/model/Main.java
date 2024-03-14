package org.model;

import org.logic.PolynomialController;

public class Main {
    public static void main(String[] args) {
        PolynomialController controller = new PolynomialController();
        System.out.println(controller.add("0", "0"));
    }
}
