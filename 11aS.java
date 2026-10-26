import com.healthmarketscience.jackcess.*;
import java.io.File;

public class CreateAccessDB {
    public static void main(String[] args) {
        File file = new File("ICE_PUST.accdb");

        try {
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

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
//javac -cp ".;lib/*" CreateAccessDB.java
//java -cp ".;lib/*" CreateAccessDB)
