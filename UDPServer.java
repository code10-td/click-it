//lab-10: Write a client-server UDP socket program in java that the server listens for connection requests, and whatever message within 1024 bytes the client sends, the server converts it to uppercase and sends it back after 6 ms. 

import java.net.*;
import java.nio.charset.StandardCharsets;

public class UDPServer {
    public static void main(String[] args) {
        try {
            DatagramSocket server = new DatagramSocket(5000);

            System.out.println("================================");
            System.out.println("        UDP UPPERCASE SERVER");
            System.out.println("================================");
            System.out.println("Server is running...");
            System.out.println("Waiting for client message...");

            byte[] buffer = new byte[1024];

            DatagramPacket request =
                new DatagramPacket(buffer, buffer.length);

            server.receive(request);

            String message = new String(
                request.getData(), request.getOffset(),
                request.getLength(), StandardCharsets.UTF_8);

            System.out.println("Received: " + message);

            Thread.sleep(6);

            String upperMessage = message.toUpperCase();
            byte[] response = upperMessage.getBytes(
                StandardCharsets.UTF_8);

            DatagramPacket reply = new DatagramPacket(
                response, response.length,
                request.getAddress(), request.getPort());

            server.send(reply);

            System.out.println("Sent: " + upperMessage);
            System.out.println("Response sent after 6 ms.");

            server.close();

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
