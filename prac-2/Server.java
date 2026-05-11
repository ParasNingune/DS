import java.rmi.*;

public class Server {

    public static void main(String[] args) {

        try {

            System.setProperty("java.rmi.server.hostname", "127.0.0.1");

            ServerImpl serverImpl = new ServerImpl();

            Naming.rebind("rmi://127.0.0.1:1099/Server", serverImpl);

            System.out.println("Server Started");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}