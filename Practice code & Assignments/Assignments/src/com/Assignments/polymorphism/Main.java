<<<<<<< HEAD
package com.Assignments.polymorphism;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Read booking details in CSV format
        String[] details = sc.nextLine().split(",");
        String stageEvent = details[0];
        String customer = details[1];
        int noOfSeats = Integer.parseInt(details[2]);

        TicketBooking booking = new TicketBooking(stageEvent, customer, noOfSeats);

        // Read payment choice
        int choice = sc.nextInt();
        sc.nextLine(); // consume newline

        booking.displayDetails();

        switch (choice) {
            case 1:
                // Cash payment
                double cashAmount = sc.nextDouble();
                booking.makePayment(cashAmount);
                break;

            case 2:
                // Wallet payment
                double walletAmount = sc.nextDouble();
                sc.nextLine(); // consume newline
                String walletNumber = sc.nextLine();
                booking.makePayment(walletAmount, walletNumber);
                break;

            case 3:
                // Credit card payment
                String holderName = sc.nextLine();
                double cardAmount = sc.nextDouble();
                sc.nextLine(); // consume newline
                String cardType = sc.nextLine();
                String ccv = sc.nextLine();
                booking.makePayment(holderName, cardAmount, cardType, ccv);
                break;

            default:
                System.out.println("Invalid choice");
        }

        sc.close();
    }
}
=======
package com.Assignments.polymorphism;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Read booking details in CSV format
        String[] details = sc.nextLine().split(",");
        String stageEvent = details[0];
        String customer = details[1];
        int noOfSeats = Integer.parseInt(details[2]);

        TicketBooking booking = new TicketBooking(stageEvent, customer, noOfSeats);

        // Read payment choice
        int choice = sc.nextInt();
        sc.nextLine(); // consume newline

        booking.displayDetails();

        switch (choice) {
            case 1:
                // Cash payment
                double cashAmount = sc.nextDouble();
                booking.makePayment(cashAmount);
                break;

            case 2:
                // Wallet payment
                double walletAmount = sc.nextDouble();
                sc.nextLine(); // consume newline
                String walletNumber = sc.nextLine();
                booking.makePayment(walletAmount, walletNumber);
                break;

            case 3:
                // Credit card payment
                String holderName = sc.nextLine();
                double cardAmount = sc.nextDouble();
                sc.nextLine(); // consume newline
                String cardType = sc.nextLine();
                String ccv = sc.nextLine();
                booking.makePayment(holderName, cardAmount, cardType, ccv);
                break;

            default:
                System.out.println("Invalid choice");
        }

        sc.close();
    }
}
>>>>>>> a990786 (Added MyPersonalMusicPlayer project as normal folder, not submodule)
