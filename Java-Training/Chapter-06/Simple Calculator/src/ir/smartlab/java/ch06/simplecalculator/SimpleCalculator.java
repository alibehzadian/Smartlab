package ir.smartlab.java.ch06.simplecalculator;

import java.util.Scanner;

public class SimpleCalculator {

    public static void main(String[] args) {
        int first;
        int second;

        Scanner input = new Scanner(System.in);

        System.out.print("Enter first integer: ");
        first = input.nextInt();

        System.out.print("Enter second integer: ");
        second = input.nextInt();

        System.out.println("first + second = " + (first + second));
        System.out.println("first - second = " + (first - second));
        System.out.println("first * second = " + (first * second));
        System.out.println("first / second = " + (first / second));
    }

}
