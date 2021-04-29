package ru.mikushov.ood.lab2.task6.observer;

import ru.mikushov.ood.lab2.task6.observable.WeatherInfoWind;
import ru.mikushov.ood.lab2.task6.observable.WindDirection;

public class StatisticViewWindImpl implements StatisticView<WeatherInfoWind> {
    private final StatisticCalculator<Double> temperatureCalculator = new StatisticCalculatorImpl("Temp");
    private final StatisticCalculator<Double> humidityCalculator = new StatisticCalculatorImpl("Humidity");
    private final StatisticCalculator<Double> pressureCalculator = new StatisticCalculatorImpl("Pressure");
    private final StatisticCalculator<Double> windSpeed = new StatisticCalculatorImpl("WindSpeed");
    private final StatisticCalculator<WindDirection> windDirection = new AngleStatisticCalculator("Wind Avg Angle");

    @Override
    public void render(WeatherInfoWind data) {
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
