package com.Assignments;

import java.util.Scanner;

public class string {
    public static void main(java.lang.String[] args) {
        Scanner sc = new Scanner(System.in);

        // Take inputWW
        java.lang.String input = sc.nextLine();

        StringBuilder lower = new StringBuilder();
        StringBuilder upper = new StringBuilder();

        // Separate characters
        for (char ch : input.toCharArray()) {
            if (Character.isUpperCase(ch)) {
                upper.append(ch); // collect uppercase
            } else {
                lower.append(ch); // keep others in order
            }
        }

        // Print result
        System.out.println(lower.toString() + upper.toString());

        sc.close();
    }
}
