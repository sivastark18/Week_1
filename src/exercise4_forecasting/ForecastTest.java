// ForecastTest.java
package exercise4_forecasting;

public class ForecastTest {
    public static void main(String[] args) {
        double initial = 10000;
        double rate = 0.1;
        int years = 5;

        double result = Forecast.futureValue(initial, rate, years);
        System.out.println("Predicted future value: " + result);
    }
}
