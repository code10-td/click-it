
import javax.swing.*;
import java.awt.*;

public class StudentForms {
    public static void main(String[] args) {

        JFrame frame = new JFrame("Student Registration Form");
        frame.setSize(450, 400);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.getContentPane().setBackground(new Color(240, 245, 250));

        JLabel title = new JLabel("STUDENT REGISTRATION", JLabel.CENTER);
        title.setBounds(50, 10, 350, 35);
        title.setFont(new Font("Arial", Font.BOLD, 20));
        title.setForeground(new Color(25, 65, 120));
        frame.add(title);

        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setBounds(50, 60, 100, 30);
        nameLabel.setFont(new Font("Arial", Font.BOLD, 14));
        frame.add(nameLabel);

        JTextField nameField = new JTextField();
        nameField.setBounds(150, 60, 220, 30);
        frame.add(nameField);

        JLabel idLabel = new JLabel("Student ID:");
        idLabel.setBounds(50, 105, 100, 30);
        idLabel.setFont(new Font("Arial", Font.BOLD, 14));
        frame.add(idLabel);

        JTextField idField = new JTextField();
        idField.setBounds(150, 105, 220, 30);
        frame.add(idField);

        JLabel deptLabel = new JLabel("Department:");
        deptLabel.setBounds(50, 150, 100, 30);
        deptLabel.setFont(new Font("Arial", Font.BOLD, 14));
        frame.add(deptLabel);

        String[] departments = {"ICE", "CSE", "EEE", "BBA"};
        JComboBox<String> deptBox = new JComboBox<>(departments);
        deptBox.setBounds(150, 150, 220, 30);
        frame.add(deptBox);

        JLabel genderLabel = new JLabel("Gender:");
        genderLabel.setBounds(50, 195, 100, 30);
        genderLabel.setFont(new Font("Arial", Font.BOLD, 14));
        frame.add(genderLabel);

        JRadioButton male = new JRadioButton("Male");
        male.setBounds(150, 195, 80, 30);
        male.setBackground(new Color(240, 245, 250));
        frame.add(male);

        JRadioButton female = new JRadioButton("Female");
        female.setBounds(240, 195, 100, 30);
        female.setBackground(new Color(240, 245, 250));
        frame.add(female);

        ButtonGroup group = new ButtonGroup();
        group.add(male);
        group.add(female);

        JButton submit = new JButton("Submit");
        submit.setBounds(150, 260, 150, 35);
        submit.setBackground(new Color(25, 100, 180));
        submit.setForeground(Color.WHITE);
        submit.setFont(new Font("Arial", Font.BOLD, 14));
        submit.setFocusPainted(false);
        frame.add(submit);

        submit.addActionListener(e -> {

            String name = nameField.getText().trim();
            String id = idField.getText().trim();
            String dept = (String) deptBox.getSelectedItem();
            String gender = "";

            if (male.isSelected()) {
                gender = "Male";
            } else if (female.isSelected()) {
                gender = "Female";
            }

            if (name.isEmpty() || id.isEmpty() || gender.isEmpty()) {
                JOptionPane.showMessageDialog(frame,
                    "Please fill in all fields!",
                    "Warning",
                    JOptionPane.WARNING_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(frame,
                    "Student Registration Successful!\n\n"
                    + "Name: " + name
                    + "\nStudent ID: " + id
                    + "\nDepartment: " + dept
                    + "\nGender: " + gender,
                    "Student Details",
                    JOptionPane.INFORMATION_MESSAGE);
            }
        });

        frame.setVisible(true);
    }
}
