11-b(import java.sql.*;
import java.io.File;
import java.util.Scanner;

public class Exp11_AccessStudentDatabase {

    public static void main(String[] args) {

        String databasePath = "C:\\Users\\HP\\OneDrive\\Desktop\\lab\\ICE_PUST.accdb";

        String url = "jdbc:ucanaccess://" + databasePath
                + ";newDatabaseVersion=V2010";

        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");

            Connection con = DriverManager.getConnection(url);

            System.out.println("Database connected successfully.");

            Scanner sc = new Scanner(System.in);

            // Insert student data
            String sql
                    = "INSERT INTO Student ([Name], Email, Phone) VALUES (?, ?, ?)";

            PreparedStatement ps = con.prepareStatement(sql);

            System.out.print("Enter student name: ");
            ps.setString(1, sc.nextLine());

            System.out.print("Enter student email: ");
            ps.setString(2, sc.nextLine());

            System.out.print("Enter student phone: ");
            ps.setString(3, sc.nextLine());

            int rows = ps.executeUpdate();

            System.out.println(rows + " student record inserted.");

            // Display all student records
            Statement st = con.createStatement();

            ResultSet rs = st.executeQuery(
                    "SELECT [Name], Email, Phone FROM Student"
            );

            System.out.println("\nAll Student Records:");
            System.out.println("------------------------------");

            while (rs.next()) {
                System.out.println("Name: " + rs.getString("Name"));
                System.out.println("Email: " + rs.getString("Email"));
                System.out.println("Phone: " + rs.getString("Phone"));
                System.out.println("------------------------------");
            }

            rs.close();
            st.close();
            ps.close();
            con.close();
            sc.close();

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}

//Terminal command
//cd E:\Final_lab_Java
//javac -cp ".;lib/*" Exp11_AccessStudentDatabase.java
//java -cp ".;lib/*" Exp11_AccessStudentDatabase)
