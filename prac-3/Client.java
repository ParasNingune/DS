import AddModule.*;

import org.omg.CosNaming.*;
import org.omg.CORBA.*;

import java.util.Scanner;

public class Client {

    public static void main(String args[]) {

        try {

            ORB orb = ORB.init(args, null);

            org.omg.CORBA.Object objRef =
                    orb.resolve_initial_references(
                            "NameService");

            NamingContextExt ncRef =
                    NamingContextExtHelper.narrow(
                            objRef);

            Add addobj =
                    AddHelper.narrow(
                            ncRef.resolve_str("ADD"));

            Scanner sc =
                    new Scanner(System.in);

            while (true) {

                System.out.print(
                        "\nEnter first number: ");

                int a = sc.nextInt();

                System.out.print(
                        "Enter second number: ");

                int b = sc.nextInt();

                int result =
                        addobj.addNumbers(a, b);

                System.out.println(
                        "Result = " + result);

                System.out.print(
                        "\nContinue? (yes/no): ");

                String ch = sc.next();

                if (ch.equalsIgnoreCase("no")) {

                    break;
                }
            }

            sc.close();

        } catch (Exception e) {

            System.out.println(e);
        }
    }
}