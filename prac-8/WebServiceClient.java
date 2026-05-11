import java.io.*;
import java.net.*;
import java.util.Scanner;

public class WebServiceClient {

    public static void main(String[] args) {

        try {

            Scanner sc = new Scanner(System.in);

            // Take input
            System.out.print("Enter first number: ");
            int a = sc.nextInt();

            System.out.print("Enter second number: ");
            int b = sc.nextInt();

            // Create URL with parameters
            String urlString =
                    "http://localhost:8000/add?a="
                    + a + "&b=" + b;

            URL url = new URL(urlString);

            // Open connection
            HttpURLConnection conn =
                    (HttpURLConnection)
                            url.openConnection();

            conn.setRequestMethod("GET");

            // Read response
            BufferedReader br =
                    new BufferedReader(
                            new InputStreamReader(
                                    conn.getInputStream()));

            String output;

            System.out.println("\nResponse from Web Service:");

            while ((output = br.readLine()) != null) {

                System.out.println(output);
            }

            conn.disconnect();

            sc.close();

        } catch (Exception e) {

            System.out.println(e);
        }
    }
}