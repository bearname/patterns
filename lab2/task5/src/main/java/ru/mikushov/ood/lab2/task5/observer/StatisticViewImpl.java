package ru.mikushov.ood.lab2.task5.observer;

public class StatisticViewImpl implements StatisticView<WeatherInfo> {
    private final StatisticCalculator<Double> temperatureCalculator = new StatisticCalculatorImpl("Temp");
    private final StatisticCalculator<Double> humidityCalculator = new StatisticCalculatorImpl("Humidity");
    private final StatisticCalculator<Double> pressureCalculator = new StatisticCalculatorImpl("Pressure");
    private final StatisticCalculator<Double> windSpeed = new StatisticCalculatorImpl("WindSpeed");
    private final StatisticCalculator<WindDirection> windDirection = new AngleStatisticCalculator("Wind Avg Angle");

    @Override
    public void render(WeatherInfo data) {
        temperatureCalculator.calculate(data.getTemperature());
        temperatureCalculator.render();

        humidityCalculator.calculate(data.getHumidity());
        humidityCalculator.render();

        pressureCalculator.calculate(data.getPressure());
        pressureCalculator.render();

        windSpeed.calculate(data.getWindSpeed());
        windSpeed.render();

        windDirection.calculate(data.getWindDirection());
        windDirection.render();
    }
}
