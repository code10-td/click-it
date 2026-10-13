//lab-1:Write a JAVA Program to Display Image using JFrame 
import javax.swing.*;

public class DisplayImage {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Display Image");

        ImageIcon image = new ImageIcon("C:\\Users\\nitud\\Downloads\\asthetic.jpg");
        JLabel label = new JLabel(image);

        frame.add(label);
        frame.setSize(600, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
