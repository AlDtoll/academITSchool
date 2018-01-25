package ru.academits.tolmachev.model;

import ru.academits.tolmachev.common.ResultOfPress;

import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class MineBoard {

    private MineCell[][] cells;
    //    private int rows;
//    private int cols;
    public static final int EXPLOSION = 12;
    public static final int FLAG = 11;
    public static final int BOMB = 10;
    public static final int DEFAULT = 9;
    public static final int EMPTY = 0;

    private final ArrayList<ResultOfPress> bombMap = new ArrayList<>();

    public MineBoard() {

    }

    public MineBoard(int rows, int cols) {
        this.cells = new MineCell[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                cells[i][j] = new MineCell();
            }
        }
        setMine();
    }

    public int getRows() {
        return cells.length;
    }

    public int getCols() {
        return cells[0].length;
    }

    private void createBombMap() {
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[0].length; j++) {
                if (cells[i][j].isBomb) {
                    bombMap.add(new ResultOfPress(i, j, BOMB));
                }
            }
        }
    }

    public void setMine() {
        int numberOfMine = 10;
        int capacity = 0;
        int[][] array = new int[cells.length][cells[0].length];
        while (capacity < numberOfMine) {// пока не будет нужное число бомб, заново заполняем поле
            for (int i = 0; i < array.length; i++) {
                for (int j = 0; j < array[0].length; j++) {

                    array[i][j] = (int) Math.round(Math.random() * 100);
                    if (array[i][j] < numberOfMine * 100 / array.length / array[0].length && capacity < numberOfMine) {
                        cells[i][j].setBomb();
                        capacity += 1;
                    }
                }
            }
        }

        // Заполняем информацию
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[0].length; j++) {
                if (!cells[i][j].isBomb) {
                    setInfo(i, j);
                }
            }
        }
        // Собираем координаты бомб, чтобы показать в случае проигрыша
        createBombMap();
    }

    private void setInfo(int i, int j) {
        int info = 0;
        if (i > 0 && j > 0 && cells[i - 1][j - 1].isBomb) {
            info += 1;
        }
        if (i > 0 && cells[i - 1][j].isBomb) {
            info += 1;
        }
        if (i > 0 && j < cells[0].length - 1 && cells[i - 1][j + 1].isBomb) {
            info += 1;
        }
        if (j > 0 && cells[i][j - 1].isBomb) {
            info += 1;
        }
        if (j < cells[0].length - 1 && cells[i][j + 1].isBomb) {
            info += 1;
        }
        if (i < cells.length - 1 && j > 0 && cells[i + 1][j - 1].isBomb) {
            info += 1;
        }
        if (i < cells.length - 1 && cells[i + 1][j].isBomb) {
            info += 1;
        }
        if (i < cells.length - 1 && j < cells[0].length - 1 && cells[i + 1][j + 1].isBomb) {
            info += 1;
        }
        cells[i][j].info = info;
    }

    private void pressEmpty(int i, int j, ArrayList<ResultOfPress> resultOfPresses) {
        if (i > 0 && cells[i - 1][j].info == 0 && !cells[i - 1][j].isPressed) {
            cells[i - 1][j].pressCell();
            resultOfPresses.add(new ResultOfPress(i - 1, j, EMPTY));
            pressEmpty(i - 1, j, resultOfPresses);
        }
        if (j > 0 && cells[i][j - 1].info == 0 && !cells[i][j - 1].isPressed) {
            cells[i][j - 1].pressCell();
            resultOfPresses.add(new ResultOfPress(i, j - 1, EMPTY));
            pressEmpty(i, j - 1, resultOfPresses);
        }
        if (j < cells[0].length - 1 && cells[i][j + 1].info == 0 && !cells[i][j + 1].isPressed) {
            cells[i][j + 1].pressCell();
            resultOfPresses.add(new ResultOfPress(i, j + 1, EMPTY));
            pressEmpty(i, j + 1, resultOfPresses);
        }
        if (i < cells.length - 1 && cells[i + 1][j].info == 0 && !cells[i + 1][j].isPressed) {
            cells[i + 1][j].pressCell();
            resultOfPresses.add(new ResultOfPress(i + 1, j, EMPTY));
            pressEmpty(i + 1, j, resultOfPresses);
        }
    }

    public ArrayList<ResultOfPress> changeCell(int row, int col, MouseEvent mouseEvent) {
        ArrayList<ResultOfPress> resultOfPresses = new ArrayList<>();
        if (mouseEvent.getButton() == MouseEvent.BUTTON3) {
            cells[row][col].markCell();
            if (cells[row][col].isMarked) {
                resultOfPresses.add(new ResultOfPress(row, col, FLAG));
            } else {
                resultOfPresses.add(new ResultOfPress(row, col, DEFAULT));
            }
            return resultOfPresses;
        }
        if (mouseEvent.getButton() == MouseEvent.BUTTON1) {
            cells[row][col].pressCell();
            if (cells[row][col].isMarked) {
                resultOfPresses.add(new ResultOfPress(row, col, FLAG));
            } else if (cells[row][col].isBomb) {
                resultOfPresses.addAll(bombMap);
                resultOfPresses.add(new ResultOfPress(row, col, EXPLOSION));
            } else if (cells[row][col].info == 0) {
                resultOfPresses.add(new ResultOfPress(row, col, EMPTY));
                pressEmpty(row, col, resultOfPresses);
            } else {
                resultOfPresses.add(new ResultOfPress(row, col, cells[row][col].info));
            }
        }
        return resultOfPresses;
    }
}
