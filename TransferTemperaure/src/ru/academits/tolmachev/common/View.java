package ru.academits.tolmachev.common;

public interface View extends AutoCloseable {

    void startApplication();

    void onTemperatureConverted(double convertedTemperature);

    void addViewListener(ViewListener listener);

    void removeViewListener(ViewListener listener);
}
