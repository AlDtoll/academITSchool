package ru.academits.tolmachev.main;


import ru.academits.tolmachev.myarraylist.MyArrayList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.ListIterator;

public class Main {

    public static void main(String[] args) {

        System.out.println("Программа демонстрирует использование собственной коллекции ArrayList");

        MyArrayList<Integer> myList = new MyArrayList<Integer>(10);
        for (int i = 0; i <= 4; i++) {
            myList.add(30 + i);
        }
        System.out.println("myList " + myList);
        System.out.println("Проверка ensureCapacity " + myList.ensureCapacity(30) + " " + myList);
        MyArrayList<Integer> myList1 = new MyArrayList<Integer>();
        for (int i = 0; i <= 4; i++) {
            myList1.add(20 + i);
        }
        System.out.println("size myList " + myList.size());
        myList.trimToSize();
        System.out.println("Проверка trimToSize");
        ArrayList<Integer> list = new ArrayList<>(Arrays.asList(2, 1, 5, 2, 4, 1, 3, 5, 4, 5, 6));
        System.out.println("Проверка add " + myList);
        myList.set(3, 1);
        System.out.println("Проверка set " + myList);
        System.out.println("Проверка get " + myList.get(4));
        myList.remove(3);
        System.out.println("Проверка remove по индексу" + myList);
        System.out.println("Проверка addAll " + myList.addAll(list) + " " + myList);
        System.out.println("Проверка containsAll " + myList.containsAll(list));
        ArrayList<Integer> list1 = new ArrayList<>(Arrays.asList(11, 10));
        System.out.println("Проверка containsAll " + myList.containsAll(list1));
        ArrayList<Integer> list2 = new ArrayList<>(Arrays.asList(5, 2));
        System.out.println("Проверка containsAll " + myList.containsAll(list2));
        System.out.println("Проверка removeAll " + myList.removeAll(list2));
        System.out.println(myList);
        myList.addAll(myList1);
        System.out.println("Проверка итератора " + myList);
        myList.clear();
        System.out.println("Проверка clear и size " + myList.size());
        myList.addAll(myList1);
        System.out.println(myList);
        System.out.println(myList.size());
        myList.add(5, 3);
        System.out.println("Проверка add по индексу " + myList);
        myList.addAll(6, myList1);
        System.out.println("Проверка addAll по индексу " + myList);
        System.out.println("Проверка contains " + myList.contains(3));
        System.out.println("Проверка indexOf " + myList.indexOf(22));
        myList.remove((Integer) 22);
        System.out.println("Проверка remove " + myList);
        System.out.println("Проверка lastIndexOf " + myList.lastIndexOf(23));
        ArrayList<Integer> listR = new ArrayList<>(Arrays.asList(2, 1, 5, 2, 4, 1, 3, 5, 4, 5, 6));
        listR.remove(5);
        ArrayList<Integer> listR1 = new ArrayList<>(Arrays.asList(2, 5, 3, 24));
        System.out.println("listR " + listR);
        System.out.println("Проверка retainAll " + listR.retainAll(listR1) + " " + listR);
        System.out.println("myList " + myList);
        System.out.println("Проверка retainAll " + myList.retainAll(listR1) + " " + myList);

        System.out.println();
        MyArrayList<Integer> ML1 = new MyArrayList<Integer>();
        for (int i = 0; i <= 4; i++) {
            ML1.add(20 + i);
        }
        System.out.println(ML1);
        MyArrayList<Integer> ML2 = new MyArrayList<Integer>();
        for (int i = 0; i <= 4; i++) {
            ML2.add(20 + i);
        }
        System.out.println(ML2);
        System.out.println("Equals? " + ML1.equals(ML2));
        ML1.set(3, null);
        System.out.println(ML1);
        System.out.println("Equals? " + ML1.equals(ML2));
        ArrayList<Integer> listWithNull = new ArrayList<>(Arrays.asList(6, 5, null, 21));
//        System.out.println("Проверка removeAll " + ML1.removeAll(listWithNull) + " " + ML1);
        System.out.println("Проверка retainAll " + ML1.retainAll(listWithNull) + " " + ML1);

        System.out.println();
        MyArrayList<Integer> myListForRemoveAll = new MyArrayList<Integer>();
        ArrayList<Integer> arrayListToAddAll = new ArrayList<Integer>(Arrays.asList(1, 1, 1, 1, 1, 1, 1, 5, 2, 2, 2, 1, 1, 1, 3, 3, 3, 3, 3));
        myListForRemoveAll.addAll(arrayListToAddAll);
        System.out.println(myListForRemoveAll);
        ArrayList<Integer> arrayListToRemoveAll = new ArrayList<Integer>(Arrays.asList(2, 3, 3, 1, 1));
        myListForRemoveAll.removeAll(arrayListToRemoveAll);
        System.out.println(myListForRemoveAll);

        MyArrayList<Integer> myListForIterator = new MyArrayList<Integer>();
        myListForIterator.add(1);
        myListForIterator.add(2);
        myListForIterator.add(3);
        myListForIterator.add(4);
        System.out.println("Список до удаления" + myListForIterator);
        Iterator<Integer> it = myListForIterator.iterator();
        it.remove();
        ListIterator<Integer> listIt = myListForIterator.listIterator();
        listIt.remove();
        listIt.remove();
        listIt.add(10);
        System.out.println("Список после удаления " + myListForIterator);
    }
}
