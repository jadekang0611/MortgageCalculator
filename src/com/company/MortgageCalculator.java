package com.company;

import java.text.DecimalFormat;

public class MortgageCalculator {
    public final static byte MONTHS_IN_YEAR = 12;
    public final static byte PERCENT = 100;
    public final static DecimalFormat df = new DecimalFormat("'$'0.00");
    private  int principal;
    private float interest;
    private byte period;

    // Constructor

    public MortgageCalculator(int principal, float interest, byte period) {
        this.principal = principal;
        this.interest = interest;
        this.period = period;
    }

    public double calculateMortgage() {
        float monthlyInt = getMonthlyInt();
        int monthlyPd = getMonthlyPd();
        float factor = (float) Math.pow(1 + monthlyInt, monthlyPd);
        double mortgageAmt = (principal * (monthlyInt * factor)) / (factor - 1);
        return mortgageAmt;

    }

    public double calculatePaymentSchedule(short numberOfPaymentsMade) {
        float monthlyInt = getMonthlyInt();
        int monthlyPd = getMonthlyPd();
        float factor = (float) Math.pow(1 + monthlyInt, monthlyPd);
        double balanceUpdated = principal * (factor - Math.pow(1 + monthlyInt, numberOfPaymentsMade))
                / (factor - 1);

        return balanceUpdated;
    }

    public double[] getRemainingBalances() {
        var balances = new double[getMonthlyPd()];
        for (short month = 1; month <= balances.length; month++) {
            balances[month - 1] = calculatePaymentSchedule(month);
        }
        return balances;
    }

    private int getMonthlyPd() {
        return period * MONTHS_IN_YEAR;
    }

    private float getMonthlyInt() {
        return interest / PERCENT / MONTHS_IN_YEAR;
    }

    public short getYears() {
        return period;
    }
}
