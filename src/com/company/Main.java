package com.company;

import java.text.DecimalFormat;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int minPrincipal = 1_000;
        int maxPrincipal = 1_000_000;
        int principal = 0;
        float interest = 0;
        byte period = 0;
        float minInterest = 0;
        float maxInterest = 30;
        final DecimalFormat df = new DecimalFormat("'$'0.00");
        double mortgage = 0;
        double balance = 0;
//            while (true) {
//                System.out.print("Principal ($1K - $1M): ");
//                principal = scanner.nextInt();
//                if (principal >= minPrincipal && principal <= maxPrincipal)
//                    break;
//                System.out.print("Enter a value between $1,000 and $1,000,000 ");
//            }
        principal = (int) readNumber("Principal: ", minPrincipal, maxPrincipal);
        interest = (float)readNumber("Interest: ", minInterest, maxInterest);
//            while (true) {
//                System.out.print("Annual Interest Rate: ");
//                interest = scanner.nextFloat();
//                if (interest >= minInterest && interest <= maxInterest)
//                    break;
//                System.out.println("Enter a value greater than 0 and less than 30");
//            }
        period = (byte)readNumber("Period: ", 0, 30);
//            while (true) {
//                System.out.print("Period (Years): ");
//                period = scanner.nextByte();
//                if (period >= 0 && period <= 30)
//                    break;
//                System.out.println("Enter a value greater than 0 and less than 30");
//            }
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
//
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


