package ru.academits.tolmachev.controller;

import ru.academits.tolmachev.common.View;
import ru.academits.tolmachev.common.ViewListener;
import ru.academits.tolmachev.model.MineBoard;
import ru.academits.tolmachev.model.ScoreTable;
import ru.academits.tolmachev.model.Timer;

public class Controller implements ViewListener {
    private final View view;
    private MineBoard mineBoard;
    private ScoreTable scoreTable;
    private Timer timer;

    public Controller(MineBoard mineBoard, View view, ScoreTable scoreTable, Timer timer) {
        this.mineBoard = mineBoard;
        this.view = view;
        this.scoreTable = scoreTable;
        this.timer = timer;
    }

    @Override
    public void needOpenCell(int row, int col) {
        view.changeCell(mineBoard.changeCell(row, col));

    }

    public void needMarkCell(int row, int col) {
        view.changeCell(mineBoard.markCell(row, col));

    }

    public void setBoard(int rows, int cols, int mines) {
        mineBoard = new MineBoard(rows, cols, mines);
        view.setBoard(mineBoard.getRows(), mineBoard.getCols(), mineBoard.getMines());
    }

    public void showBomb() {
        view.showBomb(mineBoard.getBombMap());
    }

    public void changeFlagCounter() {
        view.changeFlagCounter(mineBoard.getFlags());
    }

    public void deactivateBoard() {
        mineBoard.deactivateBoard();
    }

    public void callChampions() {
        view.showScore(scoreTable.callChampions());
    }

    public void carveName(String name) {
        scoreTable.carveName(name, mineBoard.getMarkedBomb());
    }

    public void changeTimeCounter() {
        view.changeTimeCounter(timer.getTime());
    }
}

