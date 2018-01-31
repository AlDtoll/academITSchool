package ru.academits.tolmachev.textui;

import ru.academits.tolmachev.common.ResultOfPress;
import ru.academits.tolmachev.common.View;
import ru.academits.tolmachev.common.ViewListener;

import java.util.ArrayList;
import java.util.Scanner;

public class ConsoleView implements View {
    private final ArrayList<ViewListener> listeners = new ArrayList<>();
    private final Scanner scanner = new Scanner(System.in);

    private final static String EXIT_COMMAND = "exit";
    private final static String NEW_GAME_COMMAND = "new game";
    private final static String EDIT_COMMAND = "edit";
    private final static String HIGH_SCORES_COMMAND = "high scores";

    private int rows = 9;
    private int cols = 9;
    private int mines = 10;
    private char[][] cells = new char[rows][cols];


    @Override
    public void startApplication() {
        while (true) {
            try {
                System.out.println("Minesweeper:");

                for (ViewListener listener : listeners) {
                    listener.setBoard(rows, cols, mines);
                }
                for (int i = 0; i < rows; i++) {
                    for (int j = 0; j < cols; j++) {
                        System.out.print(" C ");
                    }
                    System.out.println();
                }
                System.out.println();

                String row = scanner.nextLine();
                String col = scanner.nextLine();
                String action = scanner.nextLine();
                if (row.toLowerCase().equals(EXIT_COMMAND) || col.toLowerCase().equals(EXIT_COMMAND) || action.toLowerCase().equals(EXIT_COMMAND)) {
                    break;
                }

                int y = Integer.parseInt(row);
                int x = Integer.parseInt(col);
                if (action.equals("press")) {
                    for (ViewListener listener : listeners) {
                        listener.needOpenCell(y, x);
                    }
                }
                if (action.equals("mark")) {
                    for (ViewListener listener : listeners) {
                        listener.needMarkCell(y, x);
                    }
                } else {
                    throw new NumberFormatException();
                }
            } catch (NumberFormatException e) {
                System.out.println("Use scheme as: row column action(press/mark)");
            }
        }
    }

    @Override
    public void addViewListener(ViewListener listener) {

    }

    @Override
    public void changeCell(ArrayList<ResultOfPress> arrayList) {

    }

    @Override
    public void setBoard(int rows, int cols, int mines) {

    }

    @Override
    public void showBomb(ArrayList<ResultOfPress> bombMap) {

    }

    @Override
    public void showScore(String[] table) {

    }
}
