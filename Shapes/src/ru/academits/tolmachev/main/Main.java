package ru.academits.tolmachev.main;

import ru.academits.tolmachev.shapes.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("Программа находит среди фигур - имеющую наибольшую площаль; второй по величине периметр (иллюстирует наследование)");

        Shapes s0 = new Square(2);
        Shapes s1 = new Triangle(0, 0, 0, 3, 3, 0);
        Shapes s2 = new Rectangle(4, 5);
        Shapes s3 = new Circle(6);
        Shapes s4 = new Square(7);
        Shapes s5 = new Triangle(1, 1, 3, 4, 5, 1);
        Shapes s6 = new Rectangle(8, 9);
        Shapes s7 = new Circle(10);
        Shapes s8 = new Square(11);
        Shapes s9 = new Triangle(2, 2, 7, 3, 5, 7);

        System.out.println(s0.getPerimeter());
        System.out.println(s1.getPerimeter());
        System.out.println(s2.getPerimeter());
        System.out.println(s3.getPerimeter());
        System.out.println(s4.getPerimeter());
        System.out.println(s5.getPerimeter());
        System.out.println(s6.getPerimeter());
        System.out.println(s7.getPerimeter());
        System.out.println(s8.getPerimeter());
        System.out.println(s9.getPerimeter());


        Shapes[] listOfShapes = new Shapes[]{s0, s1, s2, s3, s4, s5, s6, s7, s8, s9};
        System.out.println("Фигура с наибольшей площадью имеет индекс " + findInexOfMaxArea(listOfShapes));
        System.out.println("Фигура со вторым по величине периметром имеет индекс " + findSecondPlacePerimetr(listOfShapes));

    }

    private static int findInexOfMaxArea(Shapes[] listOfShapes) {

        double[] area = new double[listOfShapes.length];
        for (int i = 0; i <= listOfShapes.length - 1; i++) {
            area[i] = listOfShapes[i].getArea();
        }

        int indexOfMax = 0;
        double currentMaxArea = 0;
        for (int i = 0; i <= listOfShapes.length - 1; i++) {
            if (area[i] > currentMaxArea) {
                currentMaxArea = area[i];
                indexOfMax = i;
            }
        }
        return indexOfMax;
    }

    private static int findSecondPlacePerimetr(Shapes[] listOfShapes) {

        double[] perimeter = new double[listOfShapes.length];
        for (int i = 0; i < listOfShapes.length; i++) {
            perimeter[i] = listOfShapes[i].getPerimeter();
        }

        int index = 0;
        double previousPerimeter = 0;
        double currentPerimeter = 0;
        for (int i = 0; i < listOfShapes.length; i++) {
            if (perimeter[i] > currentPerimeter) {
                currentPerimeter = perimeter[i];
            }
            if (perimeter[i] < currentPerimeter && perimeter[i] > previousPerimeter) {
                previousPerimeter = perimeter[i];
                index = i;
            }
        }
        return index;
    }
}

