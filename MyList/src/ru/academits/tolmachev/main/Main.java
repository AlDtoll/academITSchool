package ru.academits.tolmachev.main;

import ru.academits.tolmachev.mylist.MyList;

public class Main {

    public static void main(String[] args) {

        System.out.println("Программа демонстрирует использование односвязного списка");

        MyList <Integer> myList = new MyList<>();
        System.out.println(myList.getLengh());

    }
}
