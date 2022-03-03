package com.company;

public class MortgageReport {

    private  MortgageCalculator calculator;

    public MortgageReport(MortgageCalculator calculator) {
        this.calculator = calculator;
    }

    public void printPaymentSchedule() {
        System.out.println();
        System.out.println("PAYMENT SCHEDULE");
        System.out.println("----------------");
        for (double balance: calculator.getRemainingBalances()) {
            String balanceFormatted = calculator.df.format(balance);
            System.out.println(balanceFormatted);
        }
    }

    public void printMortgage() {
        double mortgage = calculator.calculateMortgage();
        String mortgageFormatted = MortgageCalculator.df.format(mortgage);
        System.out.println();
        System.out.println("MORTGAGE");
        System.out.println("----------");
        System.out.println("MORTGAGE: " + mortgageFormatted);
    }
}
