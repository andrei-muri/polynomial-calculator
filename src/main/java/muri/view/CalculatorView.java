package muri.view;// SwingCalculatorView.java
import javax.swing.*;
import java.awt.*;


public class CalculatorView extends JFrame {
    private JTextField firstPolynomialField;
    private JLabel operationLabel;
    private JTextField secondPolynomialField;
    private JLabel resultLabel;
    private JLabel errorLabel;
    private JButton addButton;
    private JButton subtractButton;
    private JButton multiplyButton;
    private JButton divideButton;
    private JButton differentiateButton;
    private JButton integrateButton;
    private JButton equalsButton;
    private JLabel firstLabel;
    private JLabel secondLabel;

    private void createUI() {
        setTitle("Polynomial Calculator");
        setSize(500, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        getContentPane().add(mainPanel);


        JPanel firstPolyPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        firstLabel = new JLabel("Enter a Polynomial");
        firstPolyPanel.add(firstLabel);
        firstPolynomialField = new JTextField(15);
        firstPolyPanel.add(firstPolynomialField);

        operationLabel = new JLabel("+");
        firstPolyPanel.add(operationLabel);

        JPanel secondPolyPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        secondLabel = new JLabel("Enter a Polynomial");
        secondPolyPanel.add(secondLabel);
        secondPolynomialField = new JTextField(15);
        secondPolyPanel.add(secondPolynomialField);

        mainPanel.add(firstPolyPanel);
        mainPanel.add(secondPolyPanel);

        resultLabel = new JLabel("Result: ");
        errorLabel = new JLabel("Error: ");
        errorLabel.setForeground(Color.RED);
        mainPanel.add(resultLabel);
        mainPanel.add(errorLabel);
        errorLabel.setVisible(false);

        mainPanel.add(Box.createVerticalStrut(10));

        JPanel buttonsPanel = new JPanel(new GridLayout(2, 3, 5, 5));
        addButton = new JButton("Addition");
        subtractButton = new JButton("Subtraction");
        multiplyButton = new JButton("Multiplication");
        divideButton = new JButton("Division");
        differentiateButton = new JButton("Differentiation");
        integrateButton = new JButton("Integration");
        equalsButton = new JButton("=");


        buttonsPanel.add(addButton);
        buttonsPanel.add(subtractButton);
        buttonsPanel.add(multiplyButton);
        buttonsPanel.add(divideButton);
        buttonsPanel.add(differentiateButton);
        buttonsPanel.add(integrateButton);
        buttonsPanel.add(equalsButton);

        mainPanel.add(buttonsPanel);


        setVisible(true);
    }

    public CalculatorView() {
        createUI();
    }

    public JTextField getFirstPolynomialField() {
        return firstPolynomialField;
    }

    public JTextField getSecondPolynomialField() {
        return secondPolynomialField;
    }

    public JLabel getOperationLabel() {
        return operationLabel;
    }

    public JLabel getResultLabel() {
        return resultLabel;
    }

    public JButton getAddButton() {
        return addButton;
    }

    public JLabel getErrorLabel() {
        return errorLabel;
    }

    public JButton getSubtractButton() {
        return subtractButton;
    }

    public JButton getMultiplyButton() {
        return multiplyButton;
    }

    public JButton getDivideButton() {
        return divideButton;
    }

    public JButton getDifferentiateButton() {
        return differentiateButton;
    }

    public JButton getIntegrateButton() {
        return integrateButton;
    }

    public JLabel getFirstLabel() {
        return firstLabel;
    }

    public JLabel getSecondLabel() {
        return secondLabel;
    }

    public JButton getEqualsButton() {
        return equalsButton;
    }
}
