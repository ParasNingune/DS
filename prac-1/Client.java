import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        try {

            // Connect to server
            Socket socket = new Socket("localhost", 6000);

            System.out.println("Connected to server!");

            // Streams
            DataInputStream dis = new DataInputStream(socket.getInputStream());
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());

            Scanner sc = new Scanner(System.in);

            while (true) {

                // Take input from user
                System.out.print("Enter message: ");
                String msg = sc.nextLine();

                // Send to server
                dos.writeUTF(msg);

                // Exit condition
                if (msg.equalsIgnoreCase("exit")) {
                    break;
                }

                // Receive response from server
                String serverMsg = dis.readUTF();

                System.out.println("Server: " + serverMsg);
            }

            // Close resources
            sc.close();
            dis.close();
            dos.close();
            socket.close();

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}