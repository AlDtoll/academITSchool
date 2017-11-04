package ru.academits.tolmachev.main;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {

        System.out.println("Программа демонстрирует использование коллекции ArrayList");

        // Чтение в список строки из файла
        ArrayList<String> lines = new ArrayList<>();
        Scanner in = new Scanner(new FileInputStream("input_strings.txt"));
//        for (int i = 0; i<3; i++){
        while (in.hasNext()) {
            lines.add(in.nextLine());
        }
        System.out.println(lines);

        // Удаление четных чисел
        ArrayList<Integer> list = new ArrayList<>(Arrays.asList(1, 5, 2, 1, 3, 5, 4, 5));

        System.out.println(list);
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) % 2 == 0) {
                list.remove(i);
            }
        }
        System.out.println(list);

        // Создание нового списка, без повторяющихся элементов
        ArrayList<Integer> newList = new ArrayList<>();
        for (Integer item : list) {
            if (!newList.contains(item)) {
                newList.add(item);
            }
        }
        System.out.println(newList);

    }
}




