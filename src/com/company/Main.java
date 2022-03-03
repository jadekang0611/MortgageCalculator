package com.company;

import java.text.DecimalFormat;
import java.util.Scanner;

public class Main {
    final static byte MONTHS_IN_YEAR = 12;
    final static byte PERCENT = 100;
    public static void main(String[] args) {
        int minPrincipal = 1_000;
        int maxPrincipal = 1_000_000;
        float minInterest = 0;
        float maxInterest = 30;
        double mortgage;
        double balance;
        final DecimalFormat df = new DecimalFormat("'$'0.00");

        int principal = (int) readNumber("Principal: ", minPrincipal, maxPrincipal);
        float interest = (float)readNumber("Interest: ", minInterest, maxInterest);
        byte period = (byte)readNumber("Period: ", 0, 30);
        mortgage = calculateMortgage(principal, interest, period);
            String mortgageFormatted = df.format(mortgage);
            System.out.println("MORTGAGE: " + mortgageFormatted);
            System.out.println();
            System.out.println("PAYMENT SCHEDULE");
            System.out.println("------------------");
            for (short numberOfMonth = 1; numberOfMonth <= period * MONTHS_IN_YEAR; numberOfMonth++) {
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
        float monthlyInt = interest / PERCENT / MONTHS_IN_YEAR;
        int monthlyPd = (period * MONTHS_IN_YEAR);
        float factor = (float)Math.pow(1+monthlyInt, monthlyPd);
        double mortgageAmt = (principal * (monthlyInt*factor)) / (factor - 1);
        return mortgageAmt;

    }


    public static double calculatePaymentSchedule(
            int principal,
            float interest,
            byte period,
            short numberOfPaymentsMade
    ) {
        float monthlyInt = interest / PERCENT / MONTHS_IN_YEAR;
        int monthlyPd = (period * MONTHS_IN_YEAR);
        float factor = (float) Math.pow(1 + monthlyInt, monthlyPd);
        double balanceUpdated = principal * (factor - Math.pow(1 + monthlyInt, numberOfPaymentsMade))
                / (factor - 1);

        return balanceUpdated;
    }
}


