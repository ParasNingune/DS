import java.io.*;
import java.net.*;

public class Server {
    public static void main(String[] args) {
        try {
            // Start server on port 6000
            ServerSocket serverSocket = new ServerSocket(6000);

            System.out.println("Server started...");
            System.out.println("Waiting for client...");

            Socket socket = serverSocket.accept();

            System.out.println("Client connected!");

            // Streams
            DataInputStream dis = new DataInputStream(socket.getInputStream());
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());

            while (true) {

                // Receive message from client
                String clientMsg = dis.readUTF();

                // Exit condition
                if (clientMsg.equalsIgnoreCase("exit")) {
                    System.out.println("Client disconnected.");
                    break;
                }

                System.out.println("Client: " + clientMsg);

                // Send reply to client
                dos.writeUTF("Message received: " + clientMsg);
            }

            // Close resources
            dis.close();
            dos.close();
            socket.close();
            serverSocket.close();

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}