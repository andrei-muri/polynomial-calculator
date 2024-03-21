package muri.logic;

import muri.model.Operation;
import muri.view.CalculatorView;

public class CalculatorController {
    PolynomialController polynomialController;
    CalculatorView view;
    Operation op;
    public CalculatorController() {
        view = new CalculatorView();
        polynomialController = new PolynomialController();
        op = Operation.ADDITION;
        setActionListeners();
    }

    private void setVisibilityOfSecondPolynomialInput(boolean isVisible) {
        view.getSecondLabel().setVisible(isVisible);
        view.getSecondPolynomialField().setVisible(isVisible);
    }

    private void setActionListeners() {
        view.getAddButton().addActionListener((e) -> {
            this.view.getOperationLabel().setText("+");
            this.view.getErrorLabel().setVisible(false);
            setVisibilityOfSecondPolynomialInput(true);
            op = Operation.ADDITION;
        });
        view.getSubtractButton().addActionListener((e) -> {
            this.view.getOperationLabel().setText("-");
            this.view.getErrorLabel().setVisible(false);
            setVisibilityOfSecondPolynomialInput(true);
            op = Operation.SUBTRACTION;
        });
        view.getMultiplyButton().addActionListener((e) -> {
            this.view.getOperationLabel().setText("*");
            this.view.getErrorLabel().setVisible(false);
            setVisibilityOfSecondPolynomialInput(true);
            op = Operation.MULTIPLICATION;
        });
        view.getDivideButton().addActionListener((e) -> {
            this.view.getOperationLabel().setText("/");
            this.view.getErrorLabel().setVisible(false);
            setVisibilityOfSecondPolynomialInput(true);
            op = Operation.DIVISION;
        });
        view.getDifferentiateButton().addActionListener((e) -> {
            this.view.getOperationLabel().setText("d/dx");
            this.view.getErrorLabel().setVisible(false);
            setVisibilityOfSecondPolynomialInput(false);
            op = Operation.DIFFERENTIATION;
        });
        view.getIntegrateButton().addActionListener((e) -> {
            this.view.getOperationLabel().setText("∫");
            this.view.getErrorLabel().setVisible(false);
            setVisibilityOfSecondPolynomialInput(false);
            op = Operation.INTEGRATION;
        });
        view.getEqualsButton().addActionListener((e) -> {
            this.view.getErrorLabel().setVisible(false);
            executeOperation();
        });
    }

    private void executeOperation() {
        String firstPolynomial = view.getFirstPolynomialField().getText().trim();
        String secondPolynomial = view.getSecondPolynomialField().getText().trim();
        try {
            switch(op) {
                case ADDITION -> setResult(polynomialController.add(firstPolynomial, secondPolynomial));
                case SUBTRACTION -> setResult(polynomialController.subtract(firstPolynomial, secondPolynomial));
                case MULTIPLICATION -> setResult(polynomialController.multiply(firstPolynomial, secondPolynomial));
                case DIVISION -> setResult(polynomialController.divide(firstPolynomial, secondPolynomial));
                case DIFFERENTIATION -> setResult(polynomialController.differentiate(firstPolynomial));
                case INTEGRATION -> setResult(polynomialController.integrate(firstPolynomial));
            }
        } catch(IllegalArgumentException | ArithmeticException e) {
            setError(e.getMessage());
        }
    }
    private void setResult(String result) {
        if(op != Operation.DIVISION) {
            this.view.getResultLabel().setText("Result: " + result);
        } else {
            this.view.getResultLabel().setText("Result: Q = " + result.split(" ")[0] + " R = " + result.split(" ")[1]);
        }
    }
    private void setError(String msg) {
        this.view.getErrorLabel().setText("Error: " + msg);
        this.view.getErrorLabel().setVisible(true);
    }
}
