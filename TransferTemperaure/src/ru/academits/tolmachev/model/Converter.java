package ru.academits.tolmachev.model;

public class Converter {

    public static double convertTemperature(double startTemperature, String from, String to) {
        switch (from) {
            case "C":
                switch (to) {
                    case "C":
                        return startTemperature;
                    case "F":
                        return startTemperature * 9.0 / 5.0 + 32.0;
                    case "K":
                        return startTemperature + 273.15;
                    default:
                        return startTemperature;
                }
            case "F":
                switch (to) {
                    case "C":
                        return 5.0 / 9.0 * (startTemperature - 32.0);
                    case "F":
                        return startTemperature;
                    case "K":
                        return 5.0 / 9.0 * (startTemperature - 32.0) + 273.15;
                    default:
                        return startTemperature;
                }
            default:
                switch (to) {
                    case "C":
                        return startTemperature - 273.15;
                    case "F":
                        return (startTemperature - 273.15) * 9.0 / 5.0 + 32.0;
                    case "K":
                        return startTemperature;
                    default:
                        return startTemperature;
                }
        }
    }
}
