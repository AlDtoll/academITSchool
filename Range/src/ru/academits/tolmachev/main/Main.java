package ru.academits.tolmachev.main;

import ru.academits.tolmachev.range.Range;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Программа для поиска пересечения, объединения и разности двух интервалов ");

        System.out.print("Введите первый и второй интервал ");
        Scanner in = new Scanner(System.in);
        Range firstRange = new Range(in.nextDouble(), in.nextDouble());
        Range secondRange = new Range(in.nextDouble(), in.nextDouble());

        Range intersection = firstRange.getIntersection(secondRange);
        if (intersection == null) {
            System.out.println("Пересечения нет");
        } else {
            System.out.printf("Пересечение: [%f,%f]%n", intersection.getFrom(), intersection.getTo());
        }

        Range[] union = firstRange.getUnion(secondRange);
        if (union.length == 1) {
            System.out.printf("Объединение: [%f,%f]%n", union[0].getFrom(), union[0].getTo());
        } else {
            System.out.printf("Объединение: [%f,%f] [%f,%f]%n", union[0].getFrom(), union[0].getTo(), union[1].getFrom(), union[1].getTo());
        }

        Range[] residual = firstRange.getResidual(secondRange);
        if (residual == null) {
            System.out.println("Разности нет");
        } else if (residual.length == 1) {
            System.out.printf("Разность: [%f,%f]%n", residual[0].getFrom(), residual[0].getTo());
        } else {
            System.out.printf("Разность: [%f,%f] [%f,%f]%n", residual[0].getFrom(), residual[0].getTo(), residual[1].getFrom(), residual[1].getTo());
        }
    }
}
