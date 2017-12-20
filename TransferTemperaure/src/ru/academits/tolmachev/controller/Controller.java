package ru.academits.tolmachev.controller;

import ru.academits.tolmachev.common.View;
import ru.academits.tolmachev.common.ViewListener;
import ru.academits.tolmachev.model.Converter;

public class Controller implements ViewListener {


    private final View view;


    public Controller(View view) {
        this.view = view;
    }

    @Override
    public void needConvertTemperature(double startTemperature, String from, String to) {
        view.onTemperatureConverted(Converter.convertTemperature(startTemperature, from, to));
    }
}