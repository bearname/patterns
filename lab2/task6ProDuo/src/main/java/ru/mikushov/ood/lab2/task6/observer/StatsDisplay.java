package ru.mikushov.ood.lab2.task6.observer;

import java.util.HashSet;
import java.util.Set;

public class StatsDisplay extends BaseObserver<WeatherInfo> {

    private final Set<StatisticView<WeatherInfo>> statisticViews = new HashSet<>();

    public StatsDisplay(StatisticView<WeatherInfo> statisticView) {
        super();
        this.statisticViews.add(statisticView);
    }

    public StatsDisplay(StatisticView<WeatherInfo> statisticView, int priority) {
        super(priority);
        this.statisticViews.add(statisticView);
    }

    public StatsDisplay(Set<StatisticView<WeatherInfo>> list) {
        this.statisticViews.addAll(list);
    }

    public void addStatisticView(StatisticView<WeatherInfo> statisticView) {
        this.statisticViews.add(statisticView);
    }

    public void removeStatisticView(StatisticView<WeatherInfo> statisticView) {
        this.statisticViews.remove(statisticView);
    }

    @Override
    public void update(WeatherInfo data) {
        statisticViews.forEach(statisticView -> statisticView.render(data));
    }
}
