package ru.academits.tolmachev.main;


import ru.academits.tolmachev.myarraylist.MyArrayList;

import java.util.ArrayList;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) {

        System.out.println("Программа демонстрирует использование собственной коллекции ArrayList");


        MyArrayList<Integer> myList = new MyArrayList<Integer>();
        for (int i = 0; i <= 4; i++) {
            myList.add(30 + i);
        }
        MyArrayList<Integer> myList1 = new MyArrayList<Integer>();
        for (int i = 0; i <= 4; i++) {
            myList1.add(20 + i);
        }
        ArrayList<Integer> list = new ArrayList<>(Arrays.asList(2, 1, 5, 2, 4, 1, 3, 5, 4, 5, 6));
        System.out.println("Проверка add " + myList);
        myList.set(3, 1);
        System.out.println("Проверка set " + myList);
        System.out.println("Проверка get " + myList.get(4));
        myList.remove(3);
        System.out.println("Проверка remove по индексу" + myList);
        myList.addAll(list);
        System.out.println("Проверка addAll" + myList);
        myList.removeAll(list);
        System.out.println("Проверка removeAll " + myList);
        myList.addAll(myList1);
        System.out.println("Проверка итератора " + myList);
        myList.clear();
        System.out.println("Проверка clear и size " + myList.size());
        myList.addAll(myList1);
        System.out.println(myList);
        System.out.println(myList.size());
        myList.add(4, 3);
        System.out.println("Проверка add по индексу " + myList);
        myList.addAll(2, myList1);
        System.out.println("Проверка addAll по индексу " + myList);
        System.out.println("Проверка contains " + myList.contains(3));
        System.out.println("Проверка indexOf " + myList.indexOf(22));
        myList.remove((Integer) 22);
        System.out.println("Проверка remove " + myList);
        System.out.println("Проверка lastIndexOf " + myList.lastIndexOf(23));

    }


}