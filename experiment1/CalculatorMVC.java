import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

// MODEL
class CalculatorModel {
    public double calculate(double a, double b, String op) {
        switch (op) {
            case "+": return a + b;
            case "-": return a - b;
            case "*": return a * b;
            case "/":
                if (b == 0)
                    throw new ArithmeticException("Cannot divide by zero");
                return a / b;
            default:
                throw new IllegalArgumentException("Invalid operation");
        }
    }
}

// VIEW
class CalculatorView extends JFrame {
    JTextField num1 = new JTextField(10);
    JTextField num2 = new JTextField(10);
    JComboBox<String> operator =
        new JComboBox<>(new String[]{"+", "-", "*", "/"});
    JButton calculate = new JButton("Calculate");
    JLabel result = new JLabel("Result: ");

    CalculatorView() {
        setTitle("MVC Calculator");
        setSize(350, 220);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(5, 2, 8, 8));
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        panel.add(new JLabel("First Number:"));
        panel.add(num1);
        panel.add(new JLabel("Second Number:"));
        panel.add(num2);
        panel.add(new JLabel("Operation:"));
        panel.add(operator);
        panel.add(new JLabel(""));
        panel.add(calculate);
        panel.add(new JLabel(""));
        panel.add(result);

        add(panel);
    }
}

// CONTROLLER
class CalculatorController {
    CalculatorModel model;
    CalculatorView view;

    CalculatorController(CalculatorModel model, CalculatorView view) {
        this.model = model;
        this.view = view;

        view.calculate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    double a = Double.parseDouble(view.num1.getText());
                    double b = Double.parseDouble(view.num2.getText());
                    String op = (String) view.operator.getSelectedItem();

                    double ans = model.calculate(a, b, op);
                    view.result.setText("Result: " + ans);

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(
                        view, "Please enter valid numbers!");
                } catch (ArithmeticException ex) {
                    JOptionPane.showMessageDialog(view, ex.getMessage());
                }
            }
        });
    }
}

// MAIN CLASS
public class CalculatorMVC {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CalculatorModel model = new CalculatorModel();
            CalculatorView view = new CalculatorView();
            new CalculatorController(model, view);
            view.setVisible(true);
        });
    }
}
