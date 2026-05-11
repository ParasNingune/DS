import java.io.*;
import java.net.*;
import com.sun.net.httpserver.*;

public class WebServiceServer {

    public static void main(String[] args) throws Exception {

        // Create HTTP Server
        HttpServer server =
                HttpServer.create(
                        new InetSocketAddress(8000), 0);

        // Create endpoint
        server.createContext("/add", new AddHandler());

        server.setExecutor(null);

        System.out.println("Web Service Started...");
        System.out.println("URL: http://localhost:8000/add");

        server.start();
    }

    // Handler class
    static class AddHandler implements HttpHandler {

        public void handle(HttpExchange exchange)
                throws IOException {

            // Get query string
            String query =
                    exchange.getRequestURI().getQuery();

            int a = 0;
            int b = 0;

            // Parse query
            if (query != null) {

                String[] params = query.split("&");

                for (String param : params) {

                    String[] pair = param.split("=");

                    if (pair[0].equals("a")) {
                        a = Integer.parseInt(pair[1]);
                    }

                    if (pair[0].equals("b")) {
                        b = Integer.parseInt(pair[1]);
                    }
                }
            }

            // Print client request on server
            System.out.println("\nRequest received from client");
            System.out.println("First Number : " + a);
            System.out.println("Second Number: " + b);

            // Perform addition
            int result = a + b;

            System.out.println("Result Sent   : " + result);

            // Response
            String response =
                    "Addition Result = " + result;

            // Send response
            exchange.sendResponseHeaders(
                    200, response.length());

            OutputStream os =
                    exchange.getResponseBody();

            os.write(response.getBytes());

            os.close();
        }
    }
}