//11.b.Create a java program to print all student records from the Student table of the ICE_PUST database.
//javac -cp ".;C:\JAVA\UCanAccess-5.0.1.bin\ucanaccess-5.0.1.jar;C:\JAVA\UCanAccess-5.0.1.bin\lib\*" AccessStudentDatabase.java

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Exp11_Database {

    public static void main(String[] args) {

        String url = "jdbc:ucanaccess://C:\\Users\\nitud\\Downloads\\hi\\ICE_PUST.accdb";

        String[][] students = {
            {"Tandra Datta", "tandra10@gmail.com", "01712345678"},
            {"Fatema Jahan", "fatema@gmail.com", "01812345678"},
            {"Rahim Ahmed", "rahim@gmail.com", "01912345678"},
            {"Nusrat Jahan", "nusrat@gmail.com", "01612345678"}
        };

        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");

            try (Connection con = DriverManager.getConnection(url)) {

                String checkSql =
                    "SELECT COUNT(*) FROM Student WHERE Email = ?";

                String insertSql =
                    "INSERT INTO Student ([Name], Email, Phone) VALUES (?, ?, ?)";

                try (PreparedStatement check = con.prepareStatement(checkSql);
                     PreparedStatement insert = con.prepareStatement(insertSql)) {

                    for (String[] s : students) {

                        check.setString(1, s[1]);

                        try (ResultSet rs = check.executeQuery()) {
                            rs.next();

                            if (rs.getInt(1) == 0) {
                                insert.setString(1, s[0]);
                                insert.setString(2, s[1]);
                                insert.setString(3, s[2]);

                                insert.executeUpdate();
                                System.out.println("Inserted: " + s[0]);
                            } else {
                                System.out.println("Already exists: " + s[0]);
                            }
                        }
                    }
                }

                String displaySql =
                    "SELECT [Name], Email, Phone FROM Student";

                try (Statement st = con.createStatement();
                     ResultSet rs = st.executeQuery(displaySql)) {

                    System.out.println("\n--- All Student Records ---");

                    while (rs.next()) {
                        System.out.println(
                            rs.getString("Name") + " | " +
                            rs.getString("Email") + " | " +
                            rs.getString("Phone")
                        );
                    }
                }
            }

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
