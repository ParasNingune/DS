import java.util.*;

public class TokenRing{
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter no of nodes: ");
        int n = sc.nextInt();

        System.out.println("\nRing formed: ");
        for(int i=0; i<n; i++)
        {
            System.out.print(i + " -> ");
        }
        System.out.println("0\n");

        int token = 0;
        int choice;

        do {

            System.out.print("Enter Sender: ");
            int sender = sc.nextInt();

            System.out.print("\nEnter Reciever: ");
            int reciever = sc.nextInt();

            System.out.print("\nEnter Data to be passed: ");
            int data = sc.nextInt();


            System.out.println("\nToken Passing: ");
            for(int i = token; i != sender; i = (i + 1) % n){
                System.out.print(" " + i + " ->");
            }
            System.out.print(sender);
            System.out.println("\n\nSender: " + sender + " Sending Data: " + data + "\n");

            for(int i=sender; i!=reciever; i = (i+1)%n){
                System.out.println("Data: " + data + " Forwaded By: " + i);
            }
            System.out.println("\nReciever: " + reciever + " Recieved Data: " + data);

            token = reciever;

            System.out.println("\n\nWant to Continue? \n1 -> Yes\n0 -> No");
            choice = sc.nextInt();

        }while(choice == 1);

        sc.close();
    }
}