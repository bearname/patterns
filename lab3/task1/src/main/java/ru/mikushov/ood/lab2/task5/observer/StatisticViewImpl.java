package ru.mikushov.ood.lab2.task5.observer;


public class StatisticViewImpl implements StatisticView {
    private final StatisticCalculator temperatureCalculator = new StatisticCalculatorImpl("Temp");
    private final StatisticCalculator humidityCalculator = new StatisticCalculatorImpl("Humidity");
    private final StatisticCalculator pressureCalculator = new StatisticCalculatorImpl("Pressure");

    @Override
    public void render(WeatherInfo data) {
        temperatureCalculator.calculate(data.getTemperature());
        temperatureCalculator.render();
        humidityCalculator.calculate(data.getHumidity());
        humidityCalculator.render();
        pressureCalculator.calculate(data.getPressure());
        pressureCalculator.render();
    }
}
