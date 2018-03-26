package ru.academits.tolmachev.model;

import java.io.*;
import java.util.Arrays;
import java.util.Comparator;

public class ScoreTable {

    private int limit = 6;
    private String separator = "        ";

    public ScoreTable() {
    }

    public String[] callChampions() {
        String[] table = readTable();
        if (table == null) {
            return null;
        } else {
            for (int i = 0; i < table.length - 1 && table[i] != null; i++) {
                table[i] = "    " + (i + 1) + "." + separator + table[i];
            }
            return table;
        }
    }

    public void carveName(String name, int points, int seconds) {
        String[] table = readTable();
        try (PrintWriter writer = new PrintWriter("Minesweeper\\src\\ru\\academits\\tolmachev\\resources\\table.txt")) {
            if (table == null) {
                writer.println(String.valueOf(points) + separator + String.format("%02d:%02d", seconds / 60, seconds % 60) + "  " + name.substring(0, 5));
            } else {
                int j = 0;
                while (table[j] != null) {
                    j++;
                }
                String[] newTable = new String[j + 1];
                System.arraycopy(table, 0, newTable, 0, j);
                newTable[j] = String.valueOf(points) + separator + String.format("%02d:%02d", seconds / 60, seconds % 60) + "  " + name.substring(0, 5);
                insertionSort(newTable);
                int i = 0;
                while (newTable[i] != null) {
                    writer.println(newTable[i]);
                    i++;
                    if (i == newTable.length || i == limit) {
                        break;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String[] readTable() {
        String[] table = new String[limit];
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("Minesweeper\\src\\ru\\academits\\tolmachev\\resources\\table.txt"))) {
            String line = bufferedReader.readLine();
            if (line == null) {
                return null;
            }
            int i = 0;
            while (line != null) {
                table[i] = line;
                line = bufferedReader.readLine();
                i++;
                if (i == table.length - 1) {
                    break;
                }
            }
        } catch (IOException e) {
            System.out.println("Score file isn't created");
            return null;
        }
        return table;
    }

    private void insertionSort(String[] table) {
        ChampionsComparator championsComparator = new ChampionsComparator();
        Arrays.sort(table, championsComparator);
    }

    class ChampionsComparator implements Comparator<String> {
        public int compare(String challenger_1, String challenger_2) {
            int p1 = Integer.parseInt((challenger_1.split(separator))[0]);
            int p2 = Integer.parseInt((challenger_2.split(separator))[0]);
            String t1 = ((challenger_1.split(separator))[1]);
            String t2 = ((challenger_2.split(separator))[1]);
            if (p1 == p2) {
                return t1.compareTo(t2);
            } else {
                return Integer.compare(p2, p1);
            }
        }
    }
}
