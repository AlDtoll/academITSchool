package ru.academits.tolmachev.shapes;

public class Square implements Shape {
    private double sideLength;

    public Square(double sideLength) {
        this.sideLength = sideLength;
    }

    public double getWidth() {
        return sideLength;
    }

    public double getHeight() {
        return sideLength;
    }

    public double getArea() {
        return sideLength * sideLength;
    }

    public double getPerimeter() {
        return 4 * sideLength;
    }

    public String toString() {
        return String.format("квадрат со стороной %f", sideLength);
    }

    public int hashCode() {
        final int prime = 17;
        int hash = 1;
        hash = prime * hash + (int) sideLength;
        return hash;
    }

    public boolean equals(Object o) {
        if (o == this){
            return true;
        }
        if (o == null || o.getClass() != this.getClass()){
            return false;
        }

        Square square = (Square) o;
        return sideLength == square.sideLength;
    }
}
