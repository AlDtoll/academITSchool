package ru.academits.tolmachev.range;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Программа для вычисления интервала с дополнительным функционалом ");

        System.out.printf("Введите от какого числа начинается интервал ");
        Scanner in = new Scanner(System.in);
        double from = in.nextDouble();
        System.out.printf("и где заканчивается ");
        double to = in.nextDouble();
        Range range = new Range(from, to);
        double residual = range.calculateInterval();
        System.out.printf("Длина интервала %f%n", residual);

        System.out.printf("Какое число проверить на принадлежность диапазону? ");
        double number = in.nextDouble();
        if (range.isInside(number)){
            System.out.printf("Число принадлежит диапазону");
        } else {
            System.out.printf("Число за пределами диапазона");
        }
    }
}




