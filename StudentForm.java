//lab-3:Write a JAVA Program to Create a Student form in GUI 
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class StudentForm extends JFrame implements ActionListener {

    JTextField nameField, idField, emailField;
    JComboBox<String> department;
    JRadioButton male, female;
    JButton submit, clear;

    StudentForm() {
        setTitle("Student Registration Form");
        setSize(450, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(7, 2, 10, 15));
        panel.setBorder(BorderFactory.createEmptyBorder(25, 25, 25, 25));
        panel.setBackground(new Color(240, 248, 255));

        JLabel title = new JLabel("STUDENT REGISTRATION");
        title.setFont(new Font("Arial", Font.BOLD, 20));
        title.setForeground(new Color(25, 75, 130));

        nameField = new JTextField();
        idField = new JTextField();
        emailField = new JTextField();

        department = new JComboBox<>(
            new String[]{"ICE", "CSE", "EEE", "BBA"}
        );

        male = new JRadioButton("Male");
        female = new JRadioButton("Female");

        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(male);
        genderGroup.add(female);

        JPanel genderPanel = new JPanel();
        genderPanel.add(male);
        genderPanel.add(female);
        genderPanel.setBackground(new Color(240, 248, 255));

        submit = new JButton("Submit");
        clear = new JButton("Clear");

        panel.add(new JLabel("Student Name:"));
        panel.add(nameField);

        panel.add(new JLabel("Student ID:"));
        panel.add(idField);

        panel.add(new JLabel("Email:"));
        panel.add(emailField);

        panel.add(new JLabel("Department:"));
        panel.add(department);

        panel.add(new JLabel("Gender:"));
        panel.add(genderPanel);

        panel.add(submit);
        panel.add(clear);

        submit.addActionListener(this);
        clear.addActionListener(this);

        setLayout(new BorderLayout());
        add(title, BorderLayout.NORTH);
        add(panel, BorderLayout.CENTER);

        title.setHorizontalAlignment(JLabel.CENTER);
        title.setBorder(BorderFactory.createEmptyBorder(15, 5, 10, 5));

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == submit) {
            String name = nameField.getText().trim();
            String id = idField.getText().trim();
            String email = emailField.getText().trim();
            String dept = (String) department.getSelectedItem();

            String gender = "";
            if (male.isSelected())
                gender = "Male";
            else if (female.isSelected())
                gender = "Female";

            if (name.isEmpty() || id.isEmpty()
                    || email.isEmpty() || gender.isEmpty()) {
                JOptionPane.showMessageDialog(this,
                    "Please fill in all fields!");
                return;
            }

            JOptionPane.showMessageDialog(this,
                "Student Information\n\n"
                + "Name: " + name
                + "\nID: " + id
                + "\nEmail: " + email
                + "\nDepartment: " + dept
                + "\nGender: " + gender);
        }

        if (e.getSource() == clear) {
            nameField.setText("");
            idField.setText("");
            emailField.setText("");
            department.setSelectedIndex(0);
            male.setSelected(false);
            female.setSelected(false);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new StudentForm());
    }
}
