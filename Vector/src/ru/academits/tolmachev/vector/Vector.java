package ru.academits.tolmachev.vector;

import java.util.Arrays;

public class Vector {

    private double[] array;

    public Vector(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("Размерность должна быть положительным числом");
        } else {
            this.array = new double[n];
        }
    }

    public Vector(Vector vector) {
        this(vector.array);
    }

    public Vector(double[] array) {
        this(array.length);
        for (int i = 0; i < array.length; i++) {
            this.array[i] = array[i];
        }
    }

    public Vector(int n, double[] array) {
        this(n);
        int numberOfStep = Math.min(n, array.length);
        for (int i = 0; i < numberOfStep; i++) {
            this.array[i] = array[i];
        }
    }

    public int getSize() {
        return this.array.length;
    }

    public String toString() {
        StringBuilder str = new StringBuilder("{");
        for (int i = 0; i < this.array.length; i++) {
            if (i == this.array.length - 1) {
                str.append(this.array[i]);
            } else {
                str.append(this.array[i]).append(", ");
            }
        }
        return str + "}";
    }

    public int hashCode() {
        final int prime = 11;
        int hash = 1;
        hash = prime * hash + Arrays.hashCode(this.array);
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
        if (this.array.length != vector.array.length) {
            return false;
        }
        for (int i = 0; i < this.array.length; i++) {
            if (this.array[i] != vector.array[i]) {
                return false;
            }
        }
        return true;
    }

    public Vector addVector(Vector vector) {
        if (this.array.length < vector.array.length) {
            double[] newArray = new double[vector.array.length];
            for (int i = 0; i < this.array.length; i++) {
                newArray[i] = this.array[i];
            }
            this.array = newArray;
        }
        for (int i = 0; i < vector.array.length; i++) {
            this.array[i] += vector.array[i];
        }
        return this;
    }

    public Vector subVector(Vector vector) {
        if (this.array.length < vector.array.length) {
            double[] newArray = new double[vector.array.length];
            for (int i = 0; i < this.array.length; i++) {
                newArray[i] = this.array[i];
            }
            this.array = newArray;
        }
        for (int i = 0; i < vector.array.length; i++) {
            this.array[i] -= vector.array[i];
        }
        return this;
    }

    public Vector multVector(double scalar) {
        for (int i = 0; i < this.array.length; i++) {
            this.array[i] *= scalar;
        }
        return this;
    }

    public Vector turnVector() {
        return multVector(-1);
    }

    public double getLength() {
        double sum = 0;
        for (double item : this.array) {
            sum += item * item;
        }
        return Math.sqrt(sum);
    }

    public static Vector additionTwoVectors(Vector v1, Vector v2) {
        Vector newVector = new Vector(Math.max(v1.array.length, v2.array.length));
        for (int i = 0; i < v1.array.length; i++) {
            newVector.array[i] = v1.array[i];
        }
        for (int i = 0; i < v2.array.length; i++) {
            newVector.array[i] += v2.array[i];
        }
        return newVector;

    }

    public static Vector subtractionTwoVectors(Vector v1, Vector v2) {
        Vector newVector = new Vector(Math.max(v1.array.length, v2.array.length));
        for (int i = 0; i < v1.array.length; i++) {
            newVector.array[i] = v1.array[i];
        }
        for (int i = 0; i < v2.array.length; i++) {
            newVector.array[i] -= v2.array[i];
        }
        return newVector;
    }

    public static Vector multiplicationTwoVectors(Vector v1, Vector v2) {
        Vector newVector = new Vector(Math.max(v1.array.length, v2.array.length));
        Vector firstVector = v1;
        Vector secondVector = v2;
        if (v2.array.length < v1.array.length) {
            firstVector = v2;
            secondVector = v1;
        }
        for (int i = 0; i < firstVector.array.length; i++) {
            newVector.array[i] = firstVector.array[i];
        }
        for (int i = 0; i < secondVector.array.length; i++) {
            newVector.array[i] *= secondVector.array[i];
        }
        return newVector;
    }

}