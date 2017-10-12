package ru.academits.tolmachev.shapes;

public class Rectangle implements Shape {
    private double widht;
    private double height;

    public Rectangle(double width, double height) {
        this.height = height;
        this.widht = width;
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

    public String toString() {
        return String.format("прямоугольник шириной %f и высотой %f", widht, height);
    }

    public int hashCode() {
        final int prime = 13;
        int hash = 1;
        hash = prime * hash + (int) widht;
        hash = prime * hash + (int) height;
        return hash;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (o == null || o.getClass() != this.getClass()) {
            return false;
        }

        Rectangle rectangle = (Rectangle) o;
        return widht == rectangle.widht && height == rectangle.height;
    }
}
