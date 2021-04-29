package ru.mikushov.ood.lab2.task7.observer.view;

import ru.mikushov.ood.lab2.task7.observer.calculator.StatisticCalculator;
import ru.mikushov.ood.lab2.task7.observer.calculator.StatisticCalculatorImpl;
import ru.mikushov.ood.lab2.task7.observer.model.WeatherInfo;

public class StatisticViewImpl implements StatisticView<WeatherInfo> {
    private final StatisticCalculator<Double> temperatureCalculator = new StatisticCalculatorImpl("Temp");
    private final StatisticCalculator<Double> humidityCalculator = new StatisticCalculatorImpl("Humidity");
    private final StatisticCalculator<Double> pressureCalculator = new StatisticCalculatorImpl("Pressure");

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
