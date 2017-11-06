package ru.academits.tolmachev.vector;

import java.util.Arrays;

public class Vector {

    private double[] elements;

    public Vector(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("Размерность должна быть положительным числом");
        } else {
            this.elements = new double[n];
        }
    }

    public Vector(Vector vector) {
        this(vector.elements);
    }

    public Vector(double[] array) {
        this(array.length, array);
    }

    public Vector(int n, double[] array) {
        this.elements = Arrays.copyOf(array, n);
    }

    public int getSize() {
        return this.elements.length;
    }

    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("{");
        for (int i = 0; i < this.elements.length - 1; i++) {
            str.append(this.elements[i]).append(", ");
        }
        str.append(this.elements[this.elements.length - 1])
                .append("}");
        return str.toString();
    }

    public int hashCode() {
        final int prime = 11;
        int hash = 1;
        hash = prime * hash + Arrays.hashCode(this.elements);
        return hash;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (o == null || o.getClass() != this.getClass()) {
            return false;
        }

        Vector vector = (Vector) o;
        return (this.elements.length == vector.elements.length) && Arrays.equals(this.elements, vector.elements);
    }

    public Vector addVector(Vector vector) {
        if (this.elements.length < vector.elements.length) {
            this.elements = Arrays.copyOf(this.elements, vector.elements.length);
        }
        for (int i = 0; i < vector.elements.length; i++) {
            this.elements[i] += vector.elements[i];
        }

        return this;
    }

    public Vector subVector(Vector vector) {
        if (this.elements.length < vector.elements.length) {
            this.elements = Arrays.copyOf(this.elements, vector.elements.length);
        }
        for (int i = 0; i < vector.elements.length; i++) {
            this.elements[i] -= vector.elements[i];
        }
        return this;
    }

    public Vector multVector(double scalar) {
        for (int i = 0; i < this.elements.length; i++) {
            this.elements[i] *= scalar;
        }
        return this;
    }

    public Vector turnVector() {
        return multVector(-1);
    }

    public double getLength() {
        double sum = 0;
        for (double item : this.elements) {
            sum += item * item;
        }
        return Math.sqrt(sum);
    }

    public static Vector additionTwoVectors(Vector v1, Vector v2) {
        Vector newVector = new Vector(v1);
        return newVector.addVector(v2);

    }

    public static Vector subtractionTwoVectors(Vector v1, Vector v2) {
        Vector newVector = new Vector(v1);
        return newVector.subVector(v2);
    }

    public static double multiplicationTwoVectors(Vector v1, Vector v2) {
        double scalar = 0;
        int currentMin = Math.min(v1.elements.length, v2.elements.length);
        for (int i = 0; i < currentMin; i++) {
            scalar += v1.elements[i] * v2.elements[i];
        }
        return scalar;
    }

}