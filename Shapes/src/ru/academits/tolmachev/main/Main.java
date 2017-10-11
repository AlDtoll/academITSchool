package ru.academits.tolmachev.main;

import ru.academits.tolmachev.shapes.*;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        System.out.println("Программа находит среди фигур - имеющую наибольшую площаль; второй по величине периметр (иллюстирует наследование)");

        Shape s0 = new Square(2);
        Shape s1 = new Triangle(0, 0, 0, 3, 3, 0);
        Shape s2 = new Rectangle(4, 5);
        Shape s3 = new Circle(6);
        Shape s4 = new Square(7);
        Shape s5 = new Triangle(1, 1, 3, 4, 5, 1);
        Shape s6 = new Rectangle(8, 9);
        Shape s7 = new Circle(10);
        Shape s8 = new Square(11);
        Shape s9 = new Triangle(2, 2, 7, 3, 5, 7);

        Shape[] shapes = new Shape[]{s0, s1, s2, s3, s4, s5, s6, s7, s8, s9};
        System.out.println("Фигура с наибольшей площадью - " + findMaxAreaShape(shapes));
        System.out.println("Фигура со вторым по величине периметром - " + findSecondPlacePerimeterShape(shapes));

    }

    private static Shape findMaxAreaShape(Shape[] shapes) {

        AreaComparator comparator = new AreaComparator();
        Arrays.sort(shapes, comparator);
        return shapes[shapes.length - 1];
    }

    private static Shape findSecondPlacePerimeterShape(Shape[] shapes) {

        PerimeterComparator comparator = new PerimeterComparator();
        Arrays.sort(shapes, comparator);
        return shapes[shapes.length - 1 - 1];
    }
}

