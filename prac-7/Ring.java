import java.util.*;

public class Ring {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of processes: ");
        int n = sc.nextInt();
        sc.nextLine();

        boolean[] alive = new boolean[n + 1];
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

        System.out.print("\nEnter process initiating election: ");
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

        System.out.println("\nElection Message Passing:");

        int current = initiator;
        int maxId = alive[current] ? current : -1;

        do {
            int next = nextAlive(current, alive, n);
            if (next == -1) {
                System.out.println("No other alive processes to pass the token to.\nProcess " + current + " becomes Coordinator");
                sc.close();
                return;
            }

            System.out.println("Process " + current + " sends message to Process " + next);

            if (alive[next]) {
                maxId = Math.max(maxId, next);
                System.out.println("Process " + next + " accepts token (id collected: " + next + ")");
            } else {
                System.out.println("Process " + next + " is crashed (token can't be processed by it)");
            }

            current = next;

        } while (current != initiator);

        if (maxId == -1) {
            System.out.println("No alive processes found.");
        } else {
            System.out.println("\nProcess " + maxId + " becomes Coordinator");
            System.out.println("Broadcasting Coordinator message around ring to alive processes...");
            int p = maxId;
            do {
                p = nextAlive(p, alive, n);
                if (p == -1) break;
                System.out.println("Coordinator message sent to Process " + p);
            } while (p != maxId);
        }

        sc.close();
    }

    private static int nextAlive(int current, boolean[] alive, int n) {
        for (int i = 1; i <= n; i++) {
            int cand = ((current + i - 1) % n) + 1;
            if (alive[cand]) return cand;
        }
        return -1;
    }
}