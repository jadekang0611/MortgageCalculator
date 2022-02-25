package com.company;

import java.text.DecimalFormat;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int minPrincipal = 1_000;
        int maxPrincipal = 1_000_000;
        int principal;
        float interest;
        byte period;
        float minInterest = 0;
        float maxInterest = 30;
        final DecimalFormat df = new DecimalFormat("'$'0.00");
        double mortgage;
        double balance;

        principal = (int) readNumber("Principal: ", minPrincipal, maxPrincipal);
        interest = (float)readNumber("Interest: ", minInterest, maxInterest);
        period = (byte)readNumber("Period: ", 0, 30);
        mortgage = calculateMortgage(principal, interest, period);
            String mortgageFormatted = df.format(mortgage);
            System.out.println("Mortgage: " + mortgageFormatted);
            System.out.println();
            System.out.println("PAYMENT SCHEDULE");
            System.out.println("------------------");
            for (byte numberOfMonth = 1; numberOfMonth < period*12; numberOfMonth++) {
                balance = calculatePaymentSchedule(principal, interest, period, numberOfMonth);
                String balanceFormatted = df.format(balance);
                System.out.println(balanceFormatted);
            }
    }

    public static double readNumber(String prompt, double min, double max) {
        Scanner scanner = new Scanner(System.in);
        double value;
        while (true){
        System.out.print(prompt);
        value = scanner.nextFloat();
        if (value >= min && value <= max)
            break;
        System.out.println("Enter a value between " + min + "and " + max);
        }
        return value;
    }

    public static double calculateMortgage(int principal, float interest, byte period) {
        float monthlyInt = interest/12/100;
        int monthlyPd = (period * 12);
        float factor = (float)Math.pow(1+monthlyInt, monthlyPd);
        double mortgageAmt = (principal * (monthlyInt*factor)) / (factor - 1);
        return mortgageAmt;

    }


    public static double calculatePaymentSchedule(int principal, float interest, byte period, byte numberOfPaymentsMade) {
        float monthlyInt = interest / 12 / 100;
        int monthlyPd = (period * 12);
        float factor = (float) Math.pow(1 + monthlyInt, monthlyPd);
        double balanceUpdated = principal * (factor - Math.pow(1 + monthlyInt, numberOfPaymentsMade)) / (factor - 1);

        return balanceUpdated;
    }
}


