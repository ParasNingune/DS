import java.rmi.*;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        try {
            String serverURL = "rmi://127.0.0.1:1099/Server";
            ServerIntf serverIntf = (ServerIntf) Naming.lookup(serverURL);

            System.out.print("Enter 1st No: ");
            double num1 = sc.nextDouble();

            System.out.print("\nEnter 2nd No: ");
            double num2 = sc.nextDouble();

            System.out.println("**************************** Results ****************************");

            System.out.print("Addition is: " + serverIntf.Addition(num1, num2));
            System.out.print("\nSubtraction is: " + serverIntf.Subraction(num1, num2));
            System.out.print("\nMultiplication is: " + serverIntf.Multiplication(num1, num2));
            System.out.print("\nDivision is: " + serverIntf.Division(num1, num2));

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}