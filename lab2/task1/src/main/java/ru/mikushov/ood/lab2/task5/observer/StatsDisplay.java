package ru.mikushov.ood.lab2.task5.observer;

import java.util.HashSet;
import java.util.Set;

public class StatsDisplay implements IObserver<WeatherInfo> {

    private final Set<StatisticView> statisticViews = new HashSet<>();

    public StatsDisplay(StatisticView statisticView) {
        this.statisticViews.add(statisticView);
    }

    public StatsDisplay(Set<StatisticView> list) {
        this.statisticViews.addAll(list);
    }

    public void addStatisticView(StatisticView statisticView) {
        this.statisticViews.add(statisticView);
    }

    public void removeStatisticView(StatisticView statisticView) {
        this.statisticViews.remove(statisticView);
    }

    @Override
    public void update(WeatherInfo data) {
        statisticViews.forEach(statisticView -> statisticView.render(data));
    }
}
