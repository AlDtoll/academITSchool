package ru.academits.tolmachev.model;

import java.awt.event.MouseEvent;

public class MineBoard {

    private MineCell[][] cells;
    //    private int rows;
//    private int cols;
    public static final int EXPLOSION = 12;
    public static final int FLAG = 11;
    public static final int BOMB = 10;
    public static final int DEFAULT = 9;

    public MineBoard() {

    }

    public MineBoard(int rows, int cols) {
        this.cells = new MineCell[rows][cols];// Модель делаем больше, чем отражаемое пространство
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


    private void setMine() {
        int numberOfMine = 10;
        int capacity = 0;
        int[][] array = new int[cells.length][cells[0].length];
//        while (capacity != numberOfMine) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[0].length; j++) {
                array[i][j] = (int) Math.round(Math.random() * 100);
                if (array[i][j] < numberOfMine * 100 / array.length / array[0].length && capacity <= numberOfMine) {
                    cells[i][j].setBomb();
                    capacity += 1;
                }
            }
        }
//        }

        // Заполняем информацию
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = 1; j < array[0].length - 1; j++) {
                if (!cells[i][j].isBomb) {
                    setInfo(i, j);
                }
            }
        }
    }

    private void setInfo(int i, int j) {
        int info = 0;
        if (i > 0 && j > 0 && cells[i - 1][j - 1].isBomb) {
            info += 1;
        }
        if (i > 0 && cells[i - 1][j].isBomb) {
            info += 1;
        }
        if (i > 0 && j < cells[0].length && cells[i - 1][j + 1].isBomb) {
            info += 1;
        }
        if (j > 0 && cells[i][j - 1].isBomb) {
            info += 1;
        }
        if (j < cells[0].length && cells[i][j + 1].isBomb) {
            info += 1;
        }
        if (i < cells.length && j > 0 && cells[i + 1][j - 1].isBomb) {
            info += 1;
        }
        if (i < cells.length && cells[i + 1][j].isBomb) {
            info += 1;
        }
        if (i < cells.length && j < cells[0].length && cells[i + 1][j + 1].isBomb) {
            info += 1;
        }
        cells[i][j].info = info;
    }

    private void pressEmpty(int i, int j) {
        int info = 0;
        if (i > 0 && j > 0 && cells[i - 1][j - 1].info ==0) {
            info += 1;
        }
        if (i > 0 && cells[i - 1][j].isBomb) {
            info += 1;
        }
        if (i > 0 && j < cells[0].length && cells[i - 1][j + 1].isBomb) {
            info += 1;
        }
        if (j > 0 && cells[i][j - 1].isBomb) {
            info += 1;
        }
        if (j < cells[0].length && cells[i][j + 1].isBomb) {
            info += 1;
        }
        if (i < cells.length && j > 0 && cells[i + 1][j - 1].isBomb) {
            info += 1;
        }
        if (i < cells.length && cells[i + 1][j].isBomb) {
            info += 1;
        }
        if (i < cells.length && j < cells[0].length && cells[i + 1][j + 1].isBomb) {
            info += 1;
        }
        cells[i][j].info = info;
    }

    public int changeCell(int row, int col, MouseEvent mouseEvent) {
        if (mouseEvent.getButton() == MouseEvent.BUTTON3) {
            cells[row][col].markCell();
            if (cells[row][col].isMarked) {
                return FLAG;
            }
            return DEFAULT;
        }
        if (mouseEvent.getButton() == MouseEvent.BUTTON1) {
            cells[row][col].pressCell();
            if (cells[row][col].isMarked) {
                return FLAG;
            }
            if (cells[row][col].isBomb) {
                return EXPLOSION;
            }
            if (cells[row][col].info == 0) {
//                pressEmpty(row, col);
            }
            return cells[row][col].info;
        }
        return 33;
    }
}
