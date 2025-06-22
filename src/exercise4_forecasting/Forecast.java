// Forecast.java
package exercise4_forecasting;

public class Forecast {
    public static double futureValue(double amount, double rate, int years) {
        if (years == 0) return amount;
        return futureValue(amount * (1 + rate), rate, years - 1);
    }
}
