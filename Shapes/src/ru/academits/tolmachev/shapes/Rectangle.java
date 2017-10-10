package ru.academits.tolmachev.shapes;

public class Rectangle implements Shapes {
    private double widht;
    private double height;

    public Rectangle(double widht, double height) {
        this.height = height;
        this.widht = widht;
    }

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
