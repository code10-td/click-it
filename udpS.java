import java.net.*;

public class UDPServer {
    public static void main(String[] args) throws Exception {
        DatagramSocket server = new DatagramSocket(5000);
        byte[] buffer = new byte[1024];

        System.out.println("Server is waiting for message...");

        DatagramPacket request =
            new DatagramPacket(buffer, buffer.length);

        server.receive(request);

        String msg = new String(
            request.getData(), 0, request.getLength());

        System.out.println("Received: " + msg);
        System.out.println("Message Length: " + msg.length());

        Thread.sleep(6);

        String upper = msg.toUpperCase();
        byte[] data = upper.getBytes();

        DatagramPacket reply = new DatagramPacket(
            data, data.length,
            request.getAddress(), request.getPort());

        server.send(reply);

        System.out.println("Sent: " + upper);

        server.close();
    }
}
