//lab-9: Write a client-server TCP socket program in java that the server listens for connection requests, and whatever message the client sends, the server converts it to uppercase and sends it back. 
import java.io.*;
import java.net.*;

public class UppercaseClient {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 5000);

            BufferedReader keyboard = new BufferedReader(
                new InputStreamReader(System.in));

            PrintWriter output = new PrintWriter(
                socket.getOutputStream(), true);

            BufferedReader input = new BufferedReader(
                new InputStreamReader(socket.getInputStream()));

            System.out.println("Connected to server!");
            System.out.print("Enter a message: ");

            String message = keyboard.readLine();
            output.println(message);

            String response = input.readLine();
            System.out.println("Server Response: " + response);

            socket.close();

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
