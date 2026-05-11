import java.util.Scanner;

public class Berkeley {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // Number of nodes
        System.out.print("Enter number of nodes: ");
        int n = sc.nextInt();

        int[] clock = new int[n];

        // Input clock times
        System.out.println("\nEnter clock time of each node:");

        for (int i = 0; i < n; i++) {

            System.out.print("Node " + (i + 1) + ": ");
            clock[i] = sc.nextInt();
        }

        // Display initial clocks
        System.out.println("\nInitial Clock Values:");

        for (int i = 0; i < n; i++) {

            System.out.println(
                    "Node " + (i + 1)
                            + " = " + clock[i]);
        }

        // Master node
        int master = 1;

        System.out.println(
                "\nMaster Node: Node " + master);

        // Calculate average time
        int sum = 0;

        for (int i = 0; i < n; i++) {

            sum += clock[i];
        }

        int avg = sum / n;

        System.out.println(
                "\nAverage Time = " + avg);

        // Synchronize clocks
        System.out.println(
                "\nClock Synchronization:");

        for (int i = 0; i < n; i++) {

            int offset = avg - clock[i];

            System.out.println(
                    "Node " + (i + 1)
                            + " adjusted by "
                            + offset);

            clock[i] = clock[i] + offset;
        }

        // Display synchronized clocks
        System.out.println(
                "\nSynchronized Clock Values:");

        for (int i = 0; i < n; i++) {

            System.out.println(
                    "Node " + (i + 1)
                            + " = " + clock[i]);
        }

        sc.close();
    }
}