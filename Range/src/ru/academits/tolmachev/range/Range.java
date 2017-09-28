package ru.academits.tolmachev.range;

public class Range {
    private double from;
    private double to;

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
        if (interval.to < from || interval.from > to) {
            return null;
        } else {
            double newLeftSide = interval.from < from ? from : interval.from;
            double newRightSide = interval.to > to ? to : interval.to;
            return new Range(newLeftSide, newRightSide);
        }
    }

    public Range[] getUnion(Range interval) {
        if (interval.to < from || interval.from > to) {
            return new Range[]{new Range(from, to), new Range(interval.from, interval.to)};
        } else {
            double newLeftSide = interval.from > from ? from : interval.from;
            double newRightSide = interval.to < to ? to : interval.to;
            return new Range[]{new Range(newLeftSide, newRightSide)};
        }
    }

    public Range[] getResidual(Range interval) {
        if (interval.from == from && interval.to == to) {
            return new Range[0];
        } else if (interval.from > to || interval.to < from) {
            return new Range[]{new Range(from, to)};
        } else if (interval.from > from && interval.to < to) {
            return new Range[]{new Range(from, interval.from), new Range(interval.to, to)};
        } else {
            double newLeftSide;
            double newRightSide;
            if (interval.from <= from) {
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