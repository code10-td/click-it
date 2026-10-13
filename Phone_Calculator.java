//lab-4:Write a JAVA Program to develop a simple calculator in GUI 
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Phone_Calculator extends JFrame implements ActionListener {

    JTextField display;
    JLabel history;

    double num1 = 0;
    String op = "";
    boolean start = true;

    String[] keys = {
        "C", "⌫", "%", "÷",
        "7", "8", "9", "×",
        "4", "5", "6", "−",
        "1", "2", "3", "+",
        "+/−", "0", ".", "="
    };

    Phone_Calculator() {
        setTitle("Phone Calculator");
        setSize(350, 520);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));
        getContentPane().setBackground(Color.BLACK);

        // Display Panel
        JPanel top = new JPanel(new BorderLayout());
        top.setBackground(Color.BLACK);
        top.setBorder(BorderFactory.createEmptyBorder(20, 15, 10, 15));

        history = new JLabel(" ");
        history.setFont(new Font("Arial", Font.PLAIN, 18));
        history.setForeground(Color.LIGHT_GRAY);
        history.setHorizontalAlignment(JLabel.RIGHT);

        display = new JTextField("0");
        display.setFont(new Font("Arial", Font.BOLD, 40));
        display.setHorizontalAlignment(JTextField.RIGHT);
        display.setEditable(false);
        display.setForeground(Color.WHITE);
        display.setBackground(Color.BLACK);
        display.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

        top.add(history, BorderLayout.NORTH);
        top.add(display, BorderLayout.CENTER);
        add(top, BorderLayout.NORTH);

        // Button Panel
        JPanel panel = new JPanel(new GridLayout(5, 4, 8, 8));
        panel.setBackground(Color.BLACK);
        panel.setBorder(BorderFactory.createEmptyBorder(5, 12, 12, 12));

        for (String key : keys) {
            JButton b = new JButton(key);
            b.setFont(new Font("Arial", Font.BOLD, 20));
            b.setFocusPainted(false);
            b.setBorderPainted(false);

            if ("÷×−+=".contains(key) && key.length() == 1) {
                b.setBackground(new Color(255, 149, 0));
                b.setForeground(Color.WHITE);
            } else if (key.equals("C") || key.equals("⌫")
                    || key.equals("%") || key.equals("+/−")) {
                b.setBackground(Color.LIGHT_GRAY);
                b.setForeground(Color.BLACK);
            } else {
                b.setBackground(new Color(50, 50, 50));
                b.setForeground(Color.WHITE);
            }

            b.addActionListener(this);
            panel.add(b);
        }

        add(panel, BorderLayout.CENTER);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        String s = ((JButton) e.getSource()).getText();
        String t = display.getText();

        // Number buttons
        if (s.matches("[0-9]")) {
            if (start || t.equals("0") || t.equals("Error")) {
                display.setText(s);
                start = false;
            } else {
                display.setText(t + s);
            }
        }

        // Decimal point
        else if (s.equals(".")) {
            if (start || t.equals("Error")) {
                display.setText("0.");
                start = false;
            } else if (!t.contains(".")) {
                display.setText(t + ".");
            }
        }

        // Clear
        else if (s.equals("C")) {
            display.setText("0");
            history.setText(" ");
            num1 = 0;
            op = "";
            start = true;
        }

        // Backspace
        else if (s.equals("⌫")) {
            if (t.equals("Error")) {
                display.setText("0");
                start = true;
            } else if (!start && t.length() > 1) {
                display.setText(t.substring(0, t.length() - 1));
            } else {
                display.setText("0");
                start = true;
            }
        }

        // Positive / Negative
        else if (s.equals("+/−")) {
            if (!t.equals("Error")) {
                double n = Double.parseDouble(t);
                display.setText(format(-n));
            }
        }

        // Percentage
        else if (s.equals("%")) {
            if (!t.equals("Error")) {
                double n = Double.parseDouble(t);
                display.setText(format(n / 100));
            }
        }

        // Arithmetic operators
        else if ("+−×÷".contains(s) && s.length() == 1) {
            if (t.equals("Error")) return;

            num1 = Double.parseDouble(t);
            op = s;
            history.setText(format(num1) + " " + op);
            start = true;
        }

        // Calculate result
        else if (s.equals("=")) {
            if (op.isEmpty() || t.equals("Error")) return;

            double num2 = Double.parseDouble(t);
            double r;

            switch (op) {
                case "+":
                    r = num1 + num2;
                    break;
                case "−":
                    r = num1 - num2;
                    break;
                case "×":
                    r = num1 * num2;
                    break;
                case "÷":
                    if (num2 == 0) {
                        history.setText(format(num1) + " ÷ "
                                + format(num2) + " =");
                        display.setText("Error");
                        op = "";
                        start = true;
                        return;
                    }
                    r = num1 / num2;
                    break;
                default:
                    return;
            }

            history.setText(format(num1) + " " + op + " "
                    + format(num2) + " =");
            display.setText(format(r));

            num1 = r;
            op = "";
            start = true;
        }
    }

    // Remove unnecessary decimal zeros
    String format(double n) {
        if (Double.isFinite(n) && n == (long) n) {
            return String.valueOf((long) n);
        }
        return String.valueOf(n);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Phone_Calculator());
    }
}
