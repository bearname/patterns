package ru.mikushov.ood.lab2.task6.observer;

import ru.mikushov.ood.lab2.task6.observable.WeatherInfoWind;

import java.util.HashSet;
import java.util.Set;

public class StatsDisplayWind extends BaseObserver<WeatherInfoWind> {

    private final Set<StatisticView<WeatherInfoWind>> statisticViews = new HashSet<>();

    public StatsDisplayWind(StatisticView<WeatherInfoWind> statisticView) {
        super();
        this.statisticViews.add(statisticView);
    }

    public StatsDisplayWind(StatisticView<WeatherInfoWind> statisticView, int priority) {
        super(priority);
        this.statisticViews.add(statisticView);
    }

    public StatsDisplayWind(Set<StatisticView<WeatherInfoWind>> list) {
        this.statisticViews.addAll(list);
    }

    public void addStatisticView(StatisticView<WeatherInfoWind> statisticView) {
        this.statisticViews.add(statisticView);
    }

    public void removeStatisticView(StatisticView<WeatherInfoWind> statisticView) {
        this.statisticViews.remove(statisticView);
    }

    @Override
    public void update(WeatherInfoWind data) {
        statisticViews.forEach(statisticView -> statisticView.render(data));
    }
}
