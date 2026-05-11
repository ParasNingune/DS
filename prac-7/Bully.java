import java.util.*;

public class Bully {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of processes: ");
        int n = sc.nextInt();
        sc.nextLine();

        boolean[] alive = new boolean[n + 1]; // 1..n
        Arrays.fill(alive, true);

        System.out.print("Enter crashed processes (comma-separated, or none): ");
        String crashedLine = sc.nextLine().trim();

        if (!crashedLine.equalsIgnoreCase("none") && !crashedLine.isEmpty()) {
            String[] parts = crashedLine.split("\\s*,\\s*");
            for (String p : parts) {
                try {
                    int id = Integer.parseInt(p);
                    if (id >= 1 && id <= n) alive[id] = false;
                } catch (NumberFormatException ignored) {
                }
            }
        }

        int initialCoordinator = highestAlive(alive, n);
        if (initialCoordinator == -1) {
            System.out.println("No alive processes. Exiting.");
            sc.close();
            return;
        }

        System.out.println("\nCurrent Coordinator: Process " + initialCoordinator);

        System.out.print("\nEnter process which initiates election: ");
        int initiator = sc.nextInt();

        if (initiator < 1 || initiator > n) {
            System.out.println("Invalid initiator id.");
            sc.close();
            return;
        }

        if (!alive[initiator]) {
            System.out.println("Initiator Process " + initiator + " is crashed. Cannot start election.");
            sc.close();
            return;
        }

        int current = initiator;

        while (true) {
            System.out.println("\nElection started by Process " + current);

            List<Integer> responders = new ArrayList<>();

            for (int i = current + 1; i <= n; i++) {
                if (alive[i]) {
                    System.out.println("Election message sent from Process " + current + " to Process " + i);
                    System.out.println("Process " + i + " replies to Process " + current);
                    responders.add(i);
                } else {
                    System.out.println("Election message sent from Process " + current + " to Process " + i + " (no reply - crashed)");
                }
            }

            if (responders.isEmpty()) {
                System.out.println("\nProcess " + current + " becomes Coordinator");
                System.out.println("Broadcasting Coordinator message to alive processes...");
                for (int i = 1; i <= n; i++) {
                    if (alive[i]) System.out.println("Coordinator message sent to Process " + i);
                }
                break;
            } else {
                current = Collections.max(responders);
                System.out.println("Process " + current + " takes over election (higher ID responded)");
            }
        }

        sc.close();
    }

    private static int highestAlive(boolean[] alive, int n) {
        for (int i = n; i >= 1; i--) if (alive[i]) return i;
        return -1;
    }
}