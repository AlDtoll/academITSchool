package ru.academits.tolmachev.textui;

import ru.academits.tolmachev.common.ResultOfPress;
import ru.academits.tolmachev.common.View;
import ru.academits.tolmachev.common.ViewListener;
import ru.academits.tolmachev.model.MineBoard;

import java.util.ArrayList;
import java.util.Scanner;

public class ConsoleView implements View {
    private final ArrayList<ViewListener> listeners = new ArrayList<>();
    private final Scanner scanner = new Scanner(System.in);

    private final static String EXIT_COMMAND = "exit";
    private final static String NEW_GAME_COMMAND = "new game";
    private final static String EDIT_COMMAND = "edit";
    private final static String HIGH_SCORES_COMMAND = "high scores";
    private final static String CHEAT_COMMAND = "cheat";

    private int rows = 9;
    private int cols = 9;
    private int mines = 10;

    private boolean isActive = true;

    private char[][] cells;
    private boolean[][] activeCells;// массив, хранящий состояние о том, была ли нажата кнопка (crutch?)


    @Override
    public void startApplication() {
        System.out.println("Minesweeper");
        initField(rows, cols, mines);
        while (isActive) {
            try {
                System.out.println("Time:");
                System.out.println("Mines:");
                System.out.print("  ");
                for (int j = 0; j < cols; j++) {
                    System.out.print(" " + (j + 1) + " ");
                }
                System.out.println();
                for (int i = 0; i < rows; i++) {
                    System.out.print((i + 1) + " ");
                    for (int j = 0; j < cols; j++) {
                        System.out.print(" " + cells[i][j] + " ");
                    }
                    System.out.println();
                }
                System.out.println();


                // Играем
                System.out.print("Select command (press/mark): ");
                String action = scanner.nextLine();
                if (action.toLowerCase().equals(EXIT_COMMAND)) {
                    break;
                }
                if (!executeCommand(action)) {
                    System.out.print("Select row (1-" + rows + ") : ");
                    String row = scanner.nextLine();

                    System.out.print("Select col (1-" + cols + ") : ");
                    String col = scanner.nextLine();
                    int y = Integer.parseInt(row) - 1;
                    if (y < 0 || y > rows - 1) {
                        throw new ArrayIndexOutOfBoundsException();
                    }
                    int x = Integer.parseInt(col) - 1;
                    if (x < 0 || x > cols - 1) {
                        throw new ArrayIndexOutOfBoundsException();
                    }
                    if (activeCells[y][x]) {
                        switch (action) {
                            case "press":
                                for (ViewListener listener : listeners) {
                                    listener.needOpenCell(y, x);
                                }
                                break;
                            case "mark":
                                for (ViewListener listener : listeners) {
                                    listener.needMarkCell(y, x);
                                }
                                break;
                            default:
                                throw new IllegalArgumentException();
                        }
                    } else {
                        System.out.println("Button was already used");
                    }
                }
            } catch (IllegalArgumentException e) {
                System.out.println("For use cell try press/mark");
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Wrong select of cell. Try again");
            }
        }
    }

    private boolean executeCommand(String command) {
        if (command.toLowerCase().equals(CHEAT_COMMAND)) {
            for (ViewListener listener : listeners) {
                listener.showBomb();
            }
            return true;
        } else if (command.toLowerCase().equals(EDIT_COMMAND)) {
            // TODO проверка
            System.out.print("Set rows: ");
            int rows = Integer.parseInt(scanner.nextLine());
            System.out.print("Set cols: ");
            int cols = Integer.parseInt(scanner.nextLine());
            System.out.print("Set mines: ");
            int mines = Integer.parseInt(scanner.nextLine());
            initField(rows, cols, mines);
            return true;
        } else if (command.toLowerCase().equals(HIGH_SCORES_COMMAND)) {
            for (ViewListener listener : listeners) {
                listener.callChampions();
            }
            return true;
        } else {
            return false;
        }
    }

    private void initField(int rows, int cols, int mines) {
        for (ViewListener listener : listeners) {
            listener.setBoard(rows, cols, mines);
        }
        cells = new char[rows][cols];
        activeCells = new boolean[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                cells[i][j] = 'C';
                activeCells[i][j] = true;
            }
        }
    }

    @Override
    public void addViewListener(ViewListener listener) {
        if (!listeners.contains(listener)) {
            listeners.add(listener);
        }
    }

    @Override
    public void changeCell(ArrayList<ResultOfPress> arrayList) {
        boolean isVictory = false;
        boolean isLoss = false;
        for (ResultOfPress node : arrayList) {
            switch (node.command) {
                case MineBoard.FLAG:
                    cells[node.y][node.x] = 'F';
                    break;
                case MineBoard.QUESTION:
                    cells[node.y][node.x] = '?';
                    break;
                case MineBoard.DEFAULT:
                    cells[node.y][node.x] = 'C';
                    break;
                case MineBoard.EXPLOSION:
                    cells[node.y][node.x] = 'E';
                    isLoss = true;
                    break;
                case MineBoard.BOMB:
                    cells[node.y][node.x] = 'B';
                    break;
                case MineBoard.EMPTY:
                    cells[node.y][node.x] = '_';
                    activeCells[node.y][node.x] = false;
                    break;
                case MineBoard.WIN:
                    isVictory = true;
                    break;
                default:
                    cells[node.y][node.x] = Integer.toString(node.command).charAt(0);
                    activeCells[node.y][node.x] = false;
                    break;
            }
        }
        if (isLoss) {
            System.out.println("Explosion! You was blown up, try again");
            deactivateBoard();
        }
        if (isVictory) {
            System.out.println("WIN!");
            System.out.println("Carve your name in Champions Hall!");
            String name = scanner.nextLine();
            for (ViewListener listener : listeners) {
                listener.carveName(name);
            }
            deactivateBoard();
        }
    }

    private void deactivateBoard() {
        for (ViewListener listener : listeners) {
            listener.deactivateBoard();
        }
        isActive = false;
    }

    @Override
    public void setBoard(int rows, int cols, int mines) {
        this.rows = rows;
        this.cols = cols;
        this.mines = mines;
    }

    @Override
    public void showBomb(ArrayList<ResultOfPress> bombMap) {
        for (ResultOfPress node : bombMap) {
            cells[node.y][node.x] = 'B';
        }
    }

    @Override
    public void showScore(String[] table) {
        for (int i = 0; i < table.length && table[i] != null; i++) {
            String row = table[i];
            System.out.println(row);
        }
    }

    @Override
    public void changeFlagCounter(int flags) {

    }

    @Override
    public void changeTimeCounter(int seconds) {

    }


}
