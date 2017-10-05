package client;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import compute.Compute;

public class ComputePi {
    private static final int COMPUTE_PI = 1;
    private static final int COMPUTE_PRIMES = 2;
    private static final int EXIT = 3;


    public static void main(String args[]) {
        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }

        try {
            String name = "Compute";
            Registry registry = LocateRegistry.getRegistry(args[0]);
            Compute comp = (Compute) registry.lookup(name);
            while (true) {
                showMenu();
                int option = getUserSelection("Select an option: ", "Invalid Selection");
                switch (option) {
                    case COMPUTE_PI:
                        int digits = getUserSelection("Enter number of digits: ", "Invalid number of digits");
                        Pi piTask = new Pi(digits);
                        BigDecimal pi = comp.executeTask(piTask);
                        System.out.println(pi);
                        break;
                    case COMPUTE_PRIMES:
                        int min = getUserSelection("Enter lower bound: ", "Invalid lower bound");
                        int max = getUserSelection("Enter upper bound: ", "Invalid upper bound");
                        Primes primesTask = new Primes(min, max);
                        List<Integer> primes = comp.executeTask(primesTask);
                        for (int prime:primes) {
                            System.out.print(prime + " ");
                        }
                        System.out.print("\n");
                        break;
                    case EXIT:
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Try again");
                        break;
                }
                Scanner scanner = new Scanner(System.in);
                scanner.nextLine();
            }
        } catch (Exception e) {
            System.err.println("Computing exception:");
            e.printStackTrace();
        }
    }

    public static void showMenu() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
        System.out.println("1. Compute Pi");
        System.out.println("2. Compute Primes");
        System.out.println("3. Exit");
    }

    public static int getUserSelection(String prompt, String error) {
        System.out.println(prompt);
        Scanner scanner = new Scanner(System.in);
        String selection = scanner.nextLine();
        int selectionValue = -1;
        try {
            selectionValue = Integer.parseInt(selection);
        } catch (Exception e) {
            System.out.println(error);
            System.exit(1);
        }

        return selectionValue;
    }


}
