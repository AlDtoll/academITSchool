package ru.academits.tolmachev.model;

import ru.academits.tolmachev.common.ResultOfPress;

import java.util.ArrayList;
import java.util.Random;

public class MineBoard {

    private MineCell[][] cells;
    private int mines = 10;
    private boolean isActive = true;
    //    private int numberOfMine = 10;
    private int markedBomb = 0;
    //    private int rows = 9;
//    private int cols = 9;
    public static final int WIN = 111;
    public static final int EXPLOSION = 12;
    public static final int FLAG = 11;
    public static final int BOMB = 10;
    public static final int DEFAULT = 9;
    public static final int EMPTY = 0;

    private final ArrayList<ResultOfPress> bombMap = new ArrayList<>();

    public MineBoard() {
    }

    public MineBoard(int rows, int cols, int mines) {
        this.cells = new MineCell[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                cells[i][j] = new MineCell();
            }
        }
        this.mines = mines;
        setMines();
    }

    public int getRows() {
        return cells.length;
    }

    public int getCols() {
        return cells[0].length;
    }

    public int getMines() {
        return mines;
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

    public void deactivateBoard() {
        isActive = false;
    }

    private void setMines() {
        int capacity = 0;
        int[][] array = new int[cells.length][cells[0].length];
        while (capacity < mines) {// пока не будет нужное число бомб, заново заполняем поле
            for (int i = 0; i < array.length; i++) {
                for (int j = 0; j < array[0].length; j++) {
                    final Random random = new Random();
                    array[i][j] = random.nextInt(100);
                    if (array[i][j] < mines * 100 / array.length / array[0].length && capacity < mines) {
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
                    setHint(i, j);
                }
            }
        }
        // Собираем координаты бомб, чтобы показать в случае проигрыша
        createBombMap();
    }

    private void setHint(int i, int j) {
        int hint = 0;
        int i0 = i == 0 ? 1 : 0;
        int j0 = j == 0 ? 1 : 0;
        int iN = i == cells.length - 1 ? 1 : 0;
        int jN = j == cells[0].length - 1 ? 1 : 0;
        for (int y = i - 1 + i0; y <= i + 1 - iN; y++) {
            for (int x = j - 1 + j0; x <= j + 1 - jN; x++) {
                if (cells[y][x].isBomb) {
                    hint += 1;
                }
            }
        }
        cells[i][j].hint = hint;
    }

    private void pressEmpty(int i, int j, ArrayList<ResultOfPress> resultOfPresses) {
        ArrayList<ResultOfPress> stack = new ArrayList<>();
        // Положили в очередь выбраную ячейку
        stack.add(new ResultOfPress(i, j, EMPTY));
        while (stack.size() != 0) {
            // Достали элемент
            ResultOfPress last = stack.remove(stack.size() - 1);
            // Сделали работку
            resultOfPresses.add(last);
            // Положили соседей
            int i0 = last.y == 0 ? 1 : 0;
            int j0 = last.x == 0 ? 1 : 0;
            int iN = last.y == cells.length - 1 ? 1 : 0;
            int jN = last.x == cells[0].length - 1 ? 1 : 0;
            for (int y = last.y - 1 + i0; y <= last.y + 1 - iN; y++) {
                for (int x = last.x - 1 + j0; x <= last.x + 1 - jN; x++) {
                    if (last.command == EMPTY && !cells[y][x].isPressed) {
                        cells[y][x].pressCell();
                        stack.add(new ResultOfPress(y, x, cells[y][x].hint));
                    }
                }
            }
        }
    }

    public ArrayList<ResultOfPress> markCell(int row, int col) {
        ArrayList<ResultOfPress> resultOfPresses = new ArrayList<>();
        if (!isActive) {
            return resultOfPresses;
        }
        cells[row][col].markCell();
        tryWin(resultOfPresses);
        if (cells[row][col].isMarked) {
            resultOfPresses.add(new ResultOfPress(row, col, FLAG));
        } else {
            resultOfPresses.add(new ResultOfPress(row, col, DEFAULT));
        }
        return resultOfPresses;
    }

    public ArrayList<ResultOfPress> changeCell(int row, int col) {
        ArrayList<ResultOfPress> resultOfPresses = new ArrayList<>();
        if (!isActive) {
            return resultOfPresses;
        }
        cells[row][col].pressCell();
        tryWin(resultOfPresses);
        if (cells[row][col].isMarked) {
            resultOfPresses.add(new ResultOfPress(row, col, FLAG));
        } else if (cells[row][col].isBomb) {
            resultOfPresses.addAll(bombMap);
            resultOfPresses.add(new ResultOfPress(row, col, EXPLOSION));
        } else if (cells[row][col].hint == 0) {
            pressEmpty(row, col, resultOfPresses);
        } else {
            resultOfPresses.add(new ResultOfPress(row, col, cells[row][col].hint));
        }
        return resultOfPresses;
    }

    private void tryWin(ArrayList<ResultOfPress> resultOfPresses) {
        markedBomb = 0;
        for (MineCell[] cell : cells) {
            for (int j = 0; j < cells[0].length; j++) {
                if (cell[j].isBomb && cell[j].isMarked) {
                    markedBomb++;
                }
                if (markedBomb == bombMap.size()) {
                    resultOfPresses.add(new ResultOfPress(0, 0, WIN));
                }
            }
        }
    }

    public ArrayList<ResultOfPress> getBombMap() {
        return bombMap;
    }

    public int getMarkedBomb() {
        return markedBomb;
    }
}
