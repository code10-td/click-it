//lab-2:Write a JAVA Program for generating Restaurant Bill 
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class RestaurantBilll extends JFrame implements ActionListener {

    JTextField burger, pizza, coffee;
    JTextArea bill;
    JButton generate, clear;

    RestaurantBilll() {
        setTitle("Restaurant Billing System");
        setSize(500, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel main = new JPanel();
        main.setLayout(new BorderLayout(10, 10));
        main.setBackground(new Color(245, 247, 250));
        main.setBorder(BorderFactory.createEmptyBorder(20, 25, 20, 25));
        setContentPane(main);

        JLabel title = new JLabel("RESTAURANT BILLING", JLabel.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 24));
        title.setForeground(new Color(35, 65, 100));
        main.add(title, BorderLayout.NORTH);

        JPanel center = new JPanel(new GridLayout(4, 2, 12, 15));
        center.setBackground(Color.WHITE);
        center.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(220, 225, 230)),
            BorderFactory.createEmptyBorder(20, 15, 20, 15)
        ));

        burger = new JTextField("");
        pizza = new JTextField("");
        coffee = new JTextField("");

        center.add(new JLabel("Burger (Tk.150)"));
        center.add(burger);

        center.add(new JLabel("Pizza (Tk.300)"));
        center.add(pizza);

        center.add(new JLabel("Coffee (Tk.100)"));
        center.add(coffee);

        generate = new JButton("Generate Bill");
        clear = new JButton("Clear");

        generate.setBackground(new Color(35, 130, 90));
        generate.setForeground(Color.WHITE);
        generate.setFocusPainted(false);

        clear.setBackground(new Color(190, 65, 65));
        clear.setForeground(Color.WHITE);
        clear.setFocusPainted(false);

        center.add(generate);
        center.add(clear);

        main.add(center, BorderLayout.CENTER);

        bill = new JTextArea(10, 30);
        bill.setEditable(false);
        bill.setFont(new Font("Monospaced", Font.PLAIN, 14));
        bill.setText("Your bill will appear here...");
        bill.setBackground(new Color(255, 255, 245));

        JScrollPane scroll = new JScrollPane(bill);
        scroll.setBorder(BorderFactory.createTitledBorder("Bill Summary"));
        main.add(scroll, BorderLayout.SOUTH);

        generate.addActionListener(this);
        clear.addActionListener(this);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == clear) {
            burger.setText(" ");
            pizza.setText(" ");
            coffee.setText(" ");
            bill.setText("Your bill will appear here...");
            return;
        }

        try {
            int b = Integer.parseInt(burger.getText().trim());
            int p = Integer.parseInt(pizza.getText().trim());
            int c = Integer.parseInt(coffee.getText().trim());

            if (b < 0 || p < 0 || c < 0) {
                JOptionPane.showMessageDialog(this,
                    "Quantity cannot be negative!");
                return;
            }

            int burgerTotal = b * 150;
            int pizzaTotal = p * 300;
            int coffeeTotal = c * 100;
            int total = burgerTotal + pizzaTotal + coffeeTotal;

            bill.setText("");
            bill.append("       WELCOME TO OUR RESTAURANT\n");
            bill.append("====================================\n");
            bill.append(String.format("%-12s %5s %12s%n",
                "Item", "Qty", "Amount"));
            bill.append("------------------------------------\n");
            bill.append(String.format("%-12s %5d      Tk %d%n",
                "Burger", b, burgerTotal));
            bill.append(String.format("%-12s %5d      Tk.%d%n",
                "Pizza", p, pizzaTotal));
            bill.append(String.format("%-12s %5d      Tk.%d%n",
                "Coffee", c, coffeeTotal));
            bill.append("------------------------------------\n");
            bill.append(String.format("TOTAL BILL:       Tk. %d%n", total));
            bill.append("====================================\n");
            bill.append("          Thank you! Visit again.");

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this,
                "Please enter valid whole-number quantities!");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new RestaurantBilll());
    }
}
