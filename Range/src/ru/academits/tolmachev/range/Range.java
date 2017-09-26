package ru.academits.tolmachev.range;

public class Range {
    private double from;
    private double to;
    private double epsilon = 1.0e-10;

    public double getFrom() {
        return from;
    }

    public void setFrom(double from) {
        this.from = from;
    }

    public double getTo() {
        return to;
    }

    public void setTo(double to) {
        this.to = to;
    }

    public Range(double from, double to) {
        this.from = from;
        this.to = to;
    }

    public double getLength() {
        return to - from;
    }

    public boolean isInside(double number) {
        return from <= number && number <= to;
    }

    public Range getIntersection(Range interval) {
        if (from - interval.to > epsilon || interval.from - to > epsilon) {
            return null;
        } else {
            double newLeftSide = from - interval.from > epsilon ? from : interval.from;
            double newRightSide = interval.to - to > epsilon ? to : interval.to;
            return new Range(newLeftSide, newRightSide);
        }
    }

    public Range[] getUnion(Range interval) {
        if (from - interval.to > epsilon || interval.from - to > epsilon) {
            return new Range[]{new Range(from, to), new Range(interval.from, interval.to)};
        } else {
            double newLeftSide = interval.from - from > epsilon ? from : interval.from;
            double newRightSide = to - interval.to > epsilon ? to : interval.to;
            return new Range[]{new Range(newLeftSide, newRightSide)};
        }
    }

    public Range[] getResidual(Range interval) {
        if (Math.abs(interval.from - from) <= epsilon && Math.abs(interval.to - to) <= epsilon) {
            return null;
        } else if (interval.from - to > epsilon || from - interval.to > epsilon) {
            return new Range[]{new Range(from, to)};
        } else if (interval.from - from > epsilon && to - interval.to > epsilon) {
            return new Range[]{new Range(from, interval.from), new Range(interval.to, to)};
        } else {
            double newLeftSide;
            double newRightSide;
            if (from - interval.from >= epsilon) {
                newLeftSide = interval.to;
                newRightSide = to;
            } else {
                newLeftSide = from;
                newRightSide = interval.from;
            }
            return new Range[]{new Range(newLeftSide, newRightSide)};
        }
    }
}

