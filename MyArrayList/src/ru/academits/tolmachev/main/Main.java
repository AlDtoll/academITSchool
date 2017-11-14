package ru.academits.tolmachev.main;


import ru.academits.tolmachev.myarraylist.MyArrayList;

import java.util.ArrayList;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) {

        System.out.println("Программа демонстрирует использование собственной коллекции ArrayList");


        MyArrayList<Integer> myList = new MyArrayList<Integer>();
//        MyArrayList<Integer> myList = new MyArrayList<Integer>(Arrays.asList(2, 1, 5, 2, 4, 1, 3, 5, 4, 5, 6));
        for (int i=0; i<=4; i++){
            myList.add(30+i);
        }
        MyArrayList<Integer> myList1 = new MyArrayList<Integer>();
        for (int i=0; i<=4; i++){
            myList1.add(20+i);
        }
        ArrayList<Integer> list = new ArrayList<>(Arrays.asList(2, 1, 5, 2, 4, 1, 3, 5, 4, 5, 6));
        System.out.println(myList);
        myList.set(3,1);
        System.out.println(myList);
        System.out.println(myList.get(4));
        myList.remove(3);
        System.out.println(myList);
        myList.addAll(list);
        System.out.println(myList);



    }


}
