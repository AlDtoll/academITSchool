package ru.academits.tolmachev.matrix;

import ru.academits.tolmachev.vector.*;

import java.util.Arrays;
import java.util.Objects;

public class Matrix {

    private Vector[] x;

    public Matrix(int n, int m) {
        this(new double[n][m]);
        if (n <= 0 || m <= 0) {
            throw new IllegalArgumentException("Размерность должна быть положительным числом");
        }
    }

    public Matrix(Matrix matrix) {
        this(matrix.x);
    }

    public Matrix(double[][] array) {
        x = new Vector[array.length];
        for (int i = 0; i < array.length; i++) {
            x[i] = new Vector(array[i]);
        }
    }

    public Matrix(Vector[] vectors) {
        x = new Vector[vectors.length];
        for (int i = 0; i < vectors.length; i++) {
            x[i] = new Vector(vectors[i]);
        }
    }

    public int getRow() {
        return x.length;
    }

    public int getColumn() {
        return x[0].getSize();
    }

    public int[] getSize() {
        return new int[]{getRow(), getColumn()};
    }

    public Vector getVectorRow(int index) {
        if (index >= x.length || index < 0) {
            throw new IndexOutOfBoundsException("Выход за матрицы");
        }
        return x[index];
    }

    public Vector setVectorRow(int index, Vector vector) {
        Vector OldVector = this.getVectorRow(index);
        if (index >= x.length || index < 0) {
            throw new IndexOutOfBoundsException("Выход за матрицы");
        }
        if (x[0].getSize() != vector.getSize()) {
            throw new IllegalStateException("Вектор для вставки имеет отличающуюся длину ");
        }
        x[index] = vector;
        return OldVector;
    }

    public Vector getVectorColumn(int index) {
        if (index >= x[0].getSize() || index < 0) {
            throw new IndexOutOfBoundsException("Выход за матрицы");
        }
        double[] column = new double[x.length];
        for (int i = 0; i < x.length; i++) {
            column[i] = x[i].getElement(index);
        }
        return new Vector(column);
    }

    public Vector setVectorColumn(int index, Vector vector) {
        Vector OldVector = this.getVectorColumn(index);
        if (index >= x[0].getSize() || index < 0) {
            throw new IndexOutOfBoundsException("Выход за матрицы");
        }
        if (x.length != vector.getSize()) {
            throw new IllegalStateException("Вектор для вставки имеет отличающуюся длину ");
        }
        for (int i = 0; i < x.length; i++) {
            x[i].setElement(index, vector.getElement(i));
        }
        return OldVector;
    }

    public Matrix transpositionMatrix() {
        if (x.length <= 0 || x[0].getSize() <= 0) {
            throw new IllegalStateException("Матрицы нет - крутить нечего");
        }
        Matrix transposedMatrix = new Matrix(x[0].getSize(), x.length);
        for (int i = 0; i < x[0].getSize(); i++) {
            transposedMatrix.setVectorRow(i, this.getVectorColumn(i));
        }
        return transposedMatrix;
    }

    public Matrix multiplyMatrix(double scalar) {
        for (Vector X : this.x) {
            X.multiplyVector(scalar);
        }
        return this;
    }

    public double findDeterminant() {
        if (x.length != x[0].getSize() && x.length != 0) {
            throw new IllegalStateException("Матрицы либо пустая, либо не квадратная");
        }
        Matrix newMatrix = new Matrix(this);
        // TODO рассмотреть матрицу с первым нулевым элементом
        for (int i = 0; i < x.length; i++) {
            for (int j = i + 1; j < x.length; j++) {
                double temp = newMatrix.getElement(j, i) / newMatrix.getElement(i, i);
                newMatrix.setElement(j, i, 0);
                for (int k = i + 1; k < x.length; k++) {
                    newMatrix.setElement(j, k, newMatrix.getElement(j, k) - newMatrix.getElement(i, k) * temp);
                }
            }
        }
        double determinant = 1;
        for (int i = 0; i < x.length; i++) {
            determinant *= newMatrix.getElement(i, i);
        }
        return determinant;
    }


    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("{");
        for (int i = 0; i < this.x.length - 1; i++) {
            str.append(this.x[i]).append(", ");
//            str.append("\n");
        }
        str.append(this.x[this.x.length - 1])
                .append("}");
        return str.toString();
    }

    public Matrix multiplyByVector(Vector vector) {
        if (vector.getSize() != x[0].getSize()) {
            throw new IllegalStateException("Размерность вектора-столбца должна быть равна количеству строк в матрице");
        }
        Matrix newMatrix = new Matrix(x.length, 1);
        for (int i = 0; i < x.length; i++) {
            double value = 0;
            for (int j = 0; j < x[0].getSize(); j++) {
                value = getElement(i, j) * vector.getElement(j) + value;
                newMatrix.setElement(i, 0, value);
            }
        }
        return newMatrix;
    }

    public Matrix addMatrix(Matrix matrix) {
        if (x.length != matrix.x.length || x[0].getSize() != matrix.x[0].getSize()) {
            throw new IllegalStateException("Размерности матриц должны совпадать");
        }
        for (int i = 0; i < x.length; i++) {
            for (int j = 0; j < x[0].getSize(); j++) {
                setElement(i, j, getElement(i, j) + matrix.getElement(i, j));
            }
        }
        return this;
    }

    public Matrix subMatrix(Matrix matrix) {
        if (x.length != matrix.x.length || x[0].getSize() != matrix.x[0].getSize()) {
            throw new IllegalStateException("Размерности матриц должны совпадать");
        }
        for (int i = 0; i < x.length; i++) {
            for (int j = 0; j < x[0].getSize(); j++) {
                setElement(i, j, getElement(i, j) - matrix.getElement(i, j));
            }
        }
        return this;
    }

    public static Matrix additionTwoMatrices(Matrix m1, Matrix m2) {
        Matrix newMatrix = new Matrix(m1);
        return newMatrix.addMatrix(m2);

    }

    public static Matrix subtractionTwoMatrices(Matrix m1, Matrix m2) {
        Matrix newMatrix = new Matrix(m1);
        return newMatrix.subMatrix(m2);

    }

    public static Matrix multiplicationTwoMatrices(Matrix m1, Matrix m2) {
        if (m1.getRow() != m2.getColumn()) {
            throw new IllegalStateException("Количество строк второй матрицы должно совпадать с количеством столбцов первой");
        }
        Matrix newMatrix = new Matrix(m1.x.length, m2.x[0].getSize());
        for (int i = 0; i < newMatrix.x[0].getSize(); i++) {
            Matrix tempMatrix = m1.multiplyByVector(m2.getVectorColumn(i));
            newMatrix.setVectorColumn(i, tempMatrix.getVectorColumn(0));
        }
        return newMatrix;
    }

    public int hashCode() {
        final int prime = 13;
        int hash = 1;
        hash = prime * hash + Arrays.hashCode(this.x);
        return hash;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (o == null || o.getClass() != this.getClass()) {
            return false;
        }

        Matrix matrix = (Matrix) o;
        if (this.x.length != matrix.x.length || this.x[0].getSize() != matrix.x[0].getSize()) {
            return false;
        }
        for (int i = 0; i < x.length; i++) {
            if (!x[i].equals(matrix.x[i])) {
                return false;
            }
        }
        return true;
    }

    private double getElement(int row, int col) {
        return this.getVectorRow(row).getElement(col);
    }

    private double setElement(int row, int col, double value) {
        double OldValue = this.getElement(row, col);
        this.getVectorRow(row).setElement(col, value);
        return OldValue;
    }
}
