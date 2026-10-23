//Suppose we have an MS Access database named ICE_PUST which has a table named by Student with field (Name, Email, and Phone). Using this database answer the following questions:
//a.Write down a java program to insert data into the Student table of the ICE_PUST database.

import com.healthmarketscience.jackcess.*;
import java.io.File;

public class CreateAccessDB {
    public static void main(String[] args) {

        File file = new File("C:\\JAVA\\ICE_PUST.accdb");

        try {
            if (file.exists()) {
                System.out.println("Database already exists!");
                return;
            }

            Database db = DatabaseBuilder.create(
                Database.FileFormat.V2010, file
            );

            new TableBuilder("Student")
                .addColumn(new ColumnBuilder("Name", DataType.TEXT))
                .addColumn(new ColumnBuilder("Email", DataType.TEXT))
                .addColumn(new ColumnBuilder("Phone", DataType.TEXT))
                .toTable(db);

            db.close();

            System.out.println("Database created successfully!");
            System.out.println("Student table created successfully!");
            System.out.println("Location: " + file.getAbsolutePath());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
