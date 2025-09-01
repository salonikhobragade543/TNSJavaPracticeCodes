<<<<<<< HEAD
package com.Assignments.polymorphism;

public class TicketBooking {
    // Attributes
    private String stageEvent;
    private String customer;
    private Integer noOfSeats;

    // Default constructor
    public TicketBooking() {
    }

    // Parameterized constructor
    public TicketBooking(String stageEvent, String customer, Integer noOfSeats) {
        this.stageEvent = stageEvent;
        this.customer = customer;
        this.noOfSeats = noOfSeats;
    }

    // Getters and setters
    public String getStageEvent() {
        return stageEvent;
    }

    public void setStageEvent(String stageEvent) {
        this.stageEvent = stageEvent;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public Integer getNoOfSeats() {
        return noOfSeats;
    }

    public void setNoOfSeats(Integer noOfSeats) {
        this.noOfSeats = noOfSeats;
    }

    // Overloaded methods for payment

    // 1. Cash payment
    public void makePayment(double amount) {
        System.out.printf("Amount %.1f paid in cash%n", amount);
    }

    // 2. Wallet payment
    public void makePayment(double amount, String walletNumber) {
        System.out.printf("Amount %.1f paid using wallet number %s%n", amount, walletNumber);
    }

    // 3. Credit card payment
    public void makePayment(String holderName, double amount, String creditCardType, String ccv) {
        System.out.printf("Holder name:%s%n", holderName);
        System.out.printf("Amount %.1f paid using %s card%n", amount, creditCardType);
        System.out.printf("CCV:%s%n", ccv);
    }

    // Method to print booking details
    public void displayDetails() {
        System.out.println("Stage event:" + stageEvent);
        System.out.println("Customer:" + customer);
        System.out.println("Number of seats:" + noOfSeats);
    }
}
=======
package com.Assignments.polymorphism;

public class TicketBooking {
    // Attributes
    private String stageEvent;
    private String customer;
    private Integer noOfSeats;

    // Default constructor
    public TicketBooking() {
    }

    // Parameterized constructor
    public TicketBooking(String stageEvent, String customer, Integer noOfSeats) {
        this.stageEvent = stageEvent;
        this.customer = customer;
        this.noOfSeats = noOfSeats;
    }

    // Getters and setters
    public String getStageEvent() {
        return stageEvent;
    }

    public void setStageEvent(String stageEvent) {
        this.stageEvent = stageEvent;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public Integer getNoOfSeats() {
        return noOfSeats;
    }

    public void setNoOfSeats(Integer noOfSeats) {
        this.noOfSeats = noOfSeats;
    }

    // Overloaded methods for payment

    // 1. Cash payment
    public void makePayment(double amount) {
        System.out.printf("Amount %.1f paid in cash%n", amount);
    }

    // 2. Wallet payment
    public void makePayment(double amount, String walletNumber) {
        System.out.printf("Amount %.1f paid using wallet number %s%n", amount, walletNumber);
    }

    // 3. Credit card payment
    public void makePayment(String holderName, double amount, String creditCardType, String ccv) {
        System.out.printf("Holder name:%s%n", holderName);
        System.out.printf("Amount %.1f paid using %s card%n", amount, creditCardType);
        System.out.printf("CCV:%s%n", ccv);
    }

    // Method to print booking details
    public void displayDetails() {
        System.out.println("Stage event:" + stageEvent);
        System.out.println("Customer:" + customer);
        System.out.println("Number of seats:" + noOfSeats);
    }
}
>>>>>>> a990786 (Added MyPersonalMusicPlayer project as normal folder, not submodule)
