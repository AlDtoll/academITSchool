package ru.academits.tolmachev.main;

import ru.academits.tolmachev.vector.Vector;

public class Main {
    public static void main(String[] args) {
        System.out.println("Вектор)");

//         Создаем вектор с разными конструкторами
        Vector v1 = new Vector(3);
        Vector v2 = new Vector(v1);
        double[] array1 = new double[]{1, 3, 5, 7};
        Vector v3 = new Vector(array1);
        double[] array2 = new double[]{1, 3};
        Vector v4 = new Vector(3, array2);
        Vector v5 = new Vector(3, array2);
        Vector v6 = new Vector(3, array2);

//         Проверяем методы
        System.out.println("Размерность вектора " + v3.getSize());
        System.out.println(v4);
        System.out.println("Результат прибавления вектора " + v4.addVector(v3));
        System.out.println("Результат вычитания вектора " + v2.subVector(v3));
        System.out.println("Результат умножения вектора на 3.5 " + v2.multiplyVector(3.5));
        System.out.println("Результат разворота вектора " + v2.turnVector());
        System.out.println("Длина вектора " + v2.getLength());
        System.out.println(v5.equals(v4));
        System.out.println(v5.equals(v6));
        System.out.println(v3 + " + " + v6 + " = " + Vector.additionTwoVectors(v3, v6));
        System.out.println(v6 + " + " + v3 + " = " + Vector.additionTwoVectors(v6, v3));
        System.out.println(v3);
        System.out.println(v6);
        System.out.println(v3 + " - " + v6 + " = " + Vector.subtractionTwoVectors(v3, v6));
        System.out.println(v6 + " - " + v3 + " = " + Vector.subtractionTwoVectors(v6, v3));
        System.out.println("Скалярное произведение векторов" + v3 + " и " + v6 + " = " + Vector.multiplicationTwoVectors(v3, v6));
        System.out.println("Скалярное произведение векторов" + v6 + " и " + v3 + " = " + Vector.multiplicationTwoVectors(v6, v3));


    }
}
