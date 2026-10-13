//lab-9: Write a client-server TCP socket program in java that the server listens for connection requests, and whatever message the client sends, the server converts it to uppercase and sends it back.
import java.io.*;
import java.net.*;

public class UppercaseServer {
    public static void main(String[] args) {
        try {
            ServerSocket server = new ServerSocket(5000);

            System.out.println("================================");
            System.out.println("       TCP UPPERCASE SERVER");
            System.out.println("================================");
            System.out.println("Server is running...");
            System.out.println("Waiting for client...");

            Socket socket = server.accept();
            System.out.println("Client connected successfully!");

            BufferedReader input = new BufferedReader(
                new InputStreamReader(socket.getInputStream()));

            PrintWriter output = new PrintWriter(
                socket.getOutputStream(), true);

            String message = input.readLine();

            if (message != null) {
                String result = message.toUpperCase();

                System.out.println("Received : " + message);
                System.out.println("Converted: " + result);

                output.println(result);
            }

            socket.close();
            server.close();

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
