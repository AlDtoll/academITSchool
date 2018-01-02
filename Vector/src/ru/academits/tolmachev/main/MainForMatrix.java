package ru.academits.tolmachev.main;

import ru.academits.tolmachev.matrix.Matrix;
import ru.academits.tolmachev.vector.Vector;

public class MainForMatrix {
    public static void main(String[] args) {
        System.out.println("Матрица)");

        Matrix m1 = new Matrix(3, 2);
        double[][] array = new double[][]{{0, 1, 2}, {3, 4, 5}};
        Matrix m2 = new Matrix(array);
        Matrix m3 = new Matrix(m2);
        Vector v1 = new Vector(new double[]{1, 3, 5, 7});
        Vector v2 = new Vector(new double[]{2, 4, 6, 8});
        Vector v3 = new Vector(new double[]{3, 5, 7, 9});
        Vector[] vectorArray = new Vector[]{v1, v2, v3};
        Matrix m4 = new Matrix(vectorArray);
        System.out.println(m1);
        System.out.println(m2);
        System.out.println(m3);
        System.out.println(m4);
        System.out.println("Размер матрицы: " + m2.getRow() + "x" + m2.getColumn());
        System.out.println("Размер матрицы: " + m4.getRow() + "x" + m4.getColumn());
        System.out.println("Размер матрицы: " + m4.getSize()[0] + "x" + m4.getSize()[1]);
        int index = 1;
        System.out.println("Вектор-строка по индексу " + index + ": " + m4.getVectorRow(index));
        System.out.println("Вектор-столбец по индексу " + index + ": " + m4.getVectorColumn(index));
        Vector vRow = new Vector(new double[]{11, 12, 13, 14});
        System.out.println(vRow.getSize());
        m4.setVectorRow(index, vRow);
        System.out.println("Матрица после установки вектора-строки:");
        System.out.println(m4);
        Vector vCol = new Vector(new double[]{111, 121, 131});
        m4.setVectorColumn(index, vCol);
        System.out.println("Матрица после установки вектора-столбца:");
        System.out.println(m4);
        m4 = m4.transpositionMatrix();
        System.out.println("Матрица после транспонирования:");
        System.out.println(m4);
        System.out.println("Матрица после умножения на 2:");
        System.out.println(m4.multiplyMatrix(2));
        System.out.println();
        Matrix qMatrix = new Matrix(new double[][]{{11, 2, 3, 11, 5},
                {6, 0, 8, 91, 10},
                {113, 12, 13, 148, 15},
                {16, 170, 18, 19, 20},
                {-1, 22, -23, 24, 255}
        });
        System.out.println(qMatrix);
        System.out.println("Определитель матрицы: " + qMatrix.findDeterminant());
        System.out.println();
        System.out.println(m4);
        Vector vectorForMultiply = new Vector(new double[]{111, 121, 131});
        System.out.println(vectorForMultiply); // считаем, что это вектор столбец
        System.out.println(m4.multiplyByVector(vectorForMultiply));
        System.out.println();
        System.out.println(m2.addMatrix(m3));
        System.out.println(m3.subMatrix(m2));
        System.out.println();

        System.out.println(Matrix.additionTwoMatrices(m2, m3));
        System.out.println(Matrix.subtractionTwoMatrices(m2, m3));
        m3 = m3.transpositionMatrix();
        System.out.println(Matrix.multiplicationTwoMatrices(m2, m3));

        System.out.println();
        System.out.println("Хэшкод: " + m3.hashCode());
        Matrix copyOfMatrix = new Matrix(m2);
        System.out.println("Хэшкоды: " + m2.hashCode() + " и " + copyOfMatrix.hashCode());
        System.out.println("Хэшкоды: " + m2.hashCode() + " и " + copyOfMatrix.hashCode());
        System.out.println("Матрицы: " + m2 + " и " + copyOfMatrix + " равны? " + m2.equals(copyOfMatrix));
        Vector vector = new Vector(new double[]{20, 20});
        m2.setVectorColumn(0, vector);
        System.out.println("А теперь? : " + m2 + " и " + copyOfMatrix + " равны? " + m2.equals(copyOfMatrix));
    }
}
