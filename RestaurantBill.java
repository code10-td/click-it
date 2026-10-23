
// Lab-2: Restaurant Billing System

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingUtilities;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

public class RestaurantBill extends JFrame implements ActionListener {

    String[] items = {"Burger", "Pizza", "Coffee", "Sandwich",
        "Pasta", "Biryani", "French Fries", "Soft Drink", "Ice Cream"};

    int[] prices = {150, 300, 100, 120, 250, 220, 100, 50, 80};

    JComboBox<String> foodMenu;
    JSpinner quantity;
    JTextArea bill;
    JButton add, generate, clear;
    JLabel priceLabel;
    int[] ordered = new int[items.length];

    Color navy = new Color(32, 53, 82);
    Color green = new Color(35, 130, 90);
    Color bg = new Color(243, 246, 250);

    RestaurantBill() {
        setTitle("Restaurant Billing System");
        setSize(650, 720);
        setMinimumSize(new Dimension(550, 600));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel main = new JPanel(new BorderLayout(15, 15));
        main.setBackground(bg);
        main.setBorder(new EmptyBorder(20, 25, 20, 25));
        setContentPane(main);

        // Header
        JPanel header = new JPanel(new GridLayout(2, 1, 5, 5));
        header.setBackground(navy);
        header.setBorder(new EmptyBorder(18, 15, 18, 15));

        JLabel title = new JLabel("RESTAURANT BILLING SYSTEM",
            JLabel.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 23));
        title.setForeground(Color.WHITE);

        JLabel subtitle = new JLabel("Delicious food, easy ordering!",
            JLabel.CENTER);
        subtitle.setFont(new Font("Arial", Font.PLAIN, 14));
        subtitle.setForeground(new Color(220, 230, 240));

        header.add(title);
        header.add(subtitle);
        main.add(header, BorderLayout.NORTH);

        // Food Menu Panel
        JPanel menu = new JPanel(new GridLayout(4, 2, 12, 14));
        menu.setBackground(Color.WHITE);
        menu.setBorder(new CompoundBorder(
            new LineBorder(new Color(220, 226, 234), 1, true),
            new EmptyBorder(20, 20, 20, 20)
        ));

        JLabel menuTitle = new JLabel("  FOOD MENU");
        menuTitle.setFont(new Font("Arial", Font.BOLD, 17));
        menuTitle.setForeground(navy);

        foodMenu = new JComboBox<>(items);
        foodMenu.setFont(new Font("Arial", Font.PLAIN, 14));

        quantity = new JSpinner(new SpinnerNumberModel(1, 1, 99, 1));
        quantity.setFont(new Font("Arial", Font.PLAIN, 14));

        priceLabel = new JLabel();
        priceLabel.setFont(new Font("Arial", Font.BOLD, 15));
        priceLabel.setForeground(green);

        add = new JButton(" +  Add to Order");
        styleButton(add, green);

        menu.add(menuTitle);
        menu.add(new JLabel(""));
        menu.add(new JLabel("Select Food Item"));
        menu.add(foodMenu);
        menu.add(new JLabel("Quantity"));
        menu.add(quantity);
        menu.add(priceLabel);
        menu.add(add);

        updatePrice();
        foodMenu.addActionListener(e -> updatePrice());

        // Receipt
        bill = new JTextArea();
        bill.setEditable(false);
        bill.setFont(new Font("Monospaced", Font.PLAIN, 13));
        bill.setForeground(new Color(45, 55, 70));
        bill.setBackground(new Color(255, 255, 250));
        bill.setMargin(new Insets(12, 12, 12, 12));
        bill.setText("        YOUR ORDER RECEIPT\n"
            + "====================================\n"
            + "  Select a food item and add it here.");

        JScrollPane scroll = new JScrollPane(bill);
        scroll.setBorder(BorderFactory.createTitledBorder(
            new LineBorder(new Color(220, 226, 234)),
            "ORDER SUMMARY",
            TitledBorder.LEFT, TitledBorder.TOP,
            new Font("Arial", Font.BOLD, 14), navy
        ));

        JPanel center = new JPanel(new BorderLayout(15, 15));
        center.setBackground(bg);
        center.add(menu, BorderLayout.NORTH);
        center.add(scroll, BorderLayout.CENTER);
        main.add(center, BorderLayout.CENTER);

        // Bottom Buttons
        JPanel buttons = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 5));
        buttons.setBackground(bg);

        generate = new JButton("Generate Bill");
        clear = new JButton("Clear Order");

        styleButton(generate, navy);
        styleButton(clear, new Color(190, 65, 65));

        buttons.add(generate);
        buttons.add(clear);
        main.add(buttons, BorderLayout.SOUTH);

        add.addActionListener(this);
        generate.addActionListener(this);
        clear.addActionListener(this);

        setVisible(true);
    }

    void styleButton(JButton button, Color color) {
        button.setBackground(color);
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial", Font.BOLD, 13));
        button.setFocusPainted(false);
        button.setBorder(new EmptyBorder(10, 15, 10, 15));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    void updatePrice() {
        priceLabel.setText("Price: Tk. "
            + prices[foodMenu.getSelectedIndex()]);
    }

    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == add) {
            int index = foodMenu.getSelectedIndex();
            ordered[index] += (Integer) quantity.getValue();
            showOrder();
        }

        if (e.getSource() == generate) {
            int total = 0;
            for (int i = 0; i < items.length; i++)
                total += ordered[i] * prices[i];

            if (total == 0) {
                JOptionPane.showMessageDialog(this,
                    "Please add food items first!");
                return;
            }

            showOrder();
            bill.append("\n====================================\n");
            bill.append(String.format("GRAND TOTAL:      Tk. %d%n", total));
            bill.append("====================================\n");
            bill.append("       Thank you! Visit again.");
        }

        if (e.getSource() == clear) {
            for (int i = 0; i < ordered.length; i++)
                ordered[i] = 0;

            bill.setText("        YOUR ORDER RECEIPT\n"
                + "====================================\n"
                + "  Select a food item and add it here.");
            quantity.setValue(1);
            foodMenu.setSelectedIndex(0);
        }
    }

    void showOrder() {
        bill.setText("        YOUR ORDER RECEIPT\n");
        bill.append("====================================\n");
        bill.append(String.format("%-16s %4s %9s%n",
            "Food Item", "Qty", "Amount"));
        bill.append("------------------------------------\n");

        int total = 0;
        for (int i = 0; i < items.length; i++) {
            if (ordered[i] > 0) {
                int amount = ordered[i] * prices[i];
                total += amount;
                bill.append(String.format("%-16s %4d Tk. %5d%n",
                    items[i], ordered[i], amount));
            }
        }

        bill.append("------------------------------------\n");
        bill.append(String.format("CURRENT TOTAL:    Tk. %d%n", total));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new RestaurantBill());
    }
}