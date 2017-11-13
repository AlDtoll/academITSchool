package ru.academits.tolmachev.main;

//import ru.academits.tolmachev.myarraylist.MyArrayList;

import java.util.ArrayList;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) {

        System.out.println("Программа демонстрирует использование собственной коллекции ArrayList");

        ArrayList<Integer> list = new ArrayList<>(Arrays.asList(2, 1, 5, 2, 4, 1, 3, 5, 4, 5, 6));
        System.out.println(list);
        for (int i = list.size() - 1; i >= 0; i--)
        {
            if (list.get(i) % 2 == 0) {
                list.remove(i);
            }
        }
        System.out.println(list);

//        MyArrayList<Integer> myList = new MyArrayList<Integer>();
//        System.out.println(myList);
//        for (int i = myList.size() - 1; i >= 0; i--)
//        {
//            if (myList.get(i) % 2 == 0) {
//                myList.remove(i);
//            }
//        }
//        System.out.println(myList);


    }


}
