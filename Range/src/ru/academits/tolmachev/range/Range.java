package ru.academits.tolmachev.range;

import static java.lang.Math.abs;

class Range {
    private double from;
    private double to;

    public Range(double from, double to) {
        this.from = from;
        this.to = to;
    }

    public double calculateInterval() {
        return abs(to - from);
    }

    public boolean isInside(double number) {
        return from <= number && number <= to || to <= number && number <= from;
    }
}
