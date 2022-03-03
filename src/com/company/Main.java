package com.company;

public class Main {
    public static void main(String[] args) {
        int minPrincipal = 1_000;
        int maxPrincipal = 1_000_000;
        float minInterest = 0;
        float maxInterest = 30;

        int principal = (int) Console.readNumber("Principal: ", minPrincipal, maxPrincipal);
        float interest = (float) Console.readNumber("Interest: ", minInterest, maxInterest);
        byte period = (byte) Console.readNumber("Period: ", 0, 30);

        var calculator = new MortgageCalculator(principal, interest, period);
        var report = new MortgageReport(calculator);
        report.printMortgage();
        report.printPaymentSchedule();
    }


}


