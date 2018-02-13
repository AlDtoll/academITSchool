package ru.academits.tolmachev.model;

import java.io.*;

public class ScoreTable {

    public ScoreTable() {
    }

    public String[] callChampions() {
        String[] table = readTable();
        if (table[0].contains("!")) {
            return table;
        } else {
            for (int i = 0; i < table.length - 1 && table[i] != null; i++) {
                table[i] = (i + 1) + ".   " + table[i];
            }
            return table;
        }
    }

    public void carveName(String name, int points) {
        String[] table = readTable();
        try (PrintWriter writer = new PrintWriter("Minesweeper\\src\\ru\\academits\\tolmachev\\resources\\table.txt")) {
            if (table[0].contains("!")) {
                writer.println(String.valueOf(points) + "-" + name);
            } else {
                int j = 0;
                while (table[j] != null) {
                    j++;
                }
                table[j] = String.valueOf(points) + "-" + name;
                insertionSort(table);
                int i = 0;
                while (table[i] != null) {
                    writer.println(table[i]);
                    i++;
                    if (i == table.length - 1) {
                        break;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String[] readTable() {
        String[] table = new String[4];
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("Minesweeper\\src\\ru\\academits\\tolmachev\\resources\\table.txt"))) {
            String line = bufferedReader.readLine();
            int i = 0;
            while (line != null) {
                table[i] = line;
                line = bufferedReader.readLine();
                i++;
                if (i == table.length - 1) {
                    break;
                }
            }
        } catch (FileNotFoundException e) {
            table[0] = "Nobody is champions yet!";
        } catch (IOException e) {
            e.printStackTrace();
        }
        return table;
    }

    //TODO стандартная сортировка
    private static void insertionSort(String[] table) {
        for (int i = 1; i < table.length && table[i] != null; i++) {
            String[] points = table[i].split("-");
            String tempString = table[i];
            int temp = Integer.parseInt(points[0]);
            int j = i - 1;
            for (; j >= 0; j--) {
                String[] point = table[j].split("-");
                if (temp > Integer.parseInt(point[0])) {
                    table[j + 1] = table[j];
                } else {
                    break;
                }
            }
            table[j + 1] = tempString;
        }
    }
}
