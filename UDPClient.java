//lab-10: Write a client-server UDP socket program in java that the server listens for connection requests, and whatever message within 1024 bytes the client sends, the server converts it to uppercase and sends it back after 6 ms. 
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class UDPClient {
    public static void main(String[] args) {
        try {
            DatagramSocket client = new DatagramSocket();
            InetAddress address = InetAddress.getByName("localhost");

            Scanner sc = new Scanner(System.in);

            System.out.println("================================");
            System.out.println("        UDP CLIENT");
            System.out.println("================================");

            System.out.print("Enter message (max 1024 bytes): ");
            String message = sc.nextLine();

            byte[] data = message.getBytes(StandardCharsets.UTF_8);

            if (data.length > 1024) {
                System.out.println("Error: Message exceeds 1024 bytes.");
                client.close();
                sc.close();
                return;
            }

            DatagramPacket request = new DatagramPacket(
                data, data.length, address, 5000);

            client.send(request);

            byte[] buffer = new byte[4096];
            DatagramPacket reply =
                new DatagramPacket(buffer, buffer.length);

            client.receive(reply);

            String response = new String(
                reply.getData(), reply.getOffset(),
                reply.getLength(), StandardCharsets.UTF_8);

            System.out.println("Server Response: " + response);

            client.close();
            sc.close();

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
