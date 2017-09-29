package ru.academits.tolmachev.shapes;

public class Rectangle extends Shapes {
    private double widht;
    private double height;

    public double getWidth() {
        return widht;
    }

    public double getHeight() {
        return height;
    }

    public double getArea() {
        return widht * height;
    }

    public double getPerimeter() {
        return (widht + height) * 2;
    }
}
