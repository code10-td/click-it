ai tao(import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class UDPClient {
    public static void main(String[] args) throws Exception {
        DatagramSocket client = new DatagramSocket();
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter message: ");
        String msg = sc.nextLine();
        byte[] data = msg.getBytes();

        if(msg.trim().equalsIgnoreCase("localhost")){
        System.out.println("This msg is invalid");
            client.close();
            sc.close();
            return;
        }

        if (data.length > 1024) {
            System.out.println("Message exceeds 1024 bytes!");
            client.close();
            sc.close();
            return;
        }

        InetAddress ip = InetAddress.getByName("localhost");

        DatagramPacket request = new DatagramPacket(
            data, data.length, ip, 5000);
        client.send(request);

        byte[] buffer = new byte[1024];
        DatagramPacket reply =
            new DatagramPacket(buffer, buffer.length);
        client.receive(reply);

        String result = new String(
            reply.getData(), 0, reply.getLength());

        System.out.println("Uppercase: " + result);

        client.close();
        sc.close();
    }
})
