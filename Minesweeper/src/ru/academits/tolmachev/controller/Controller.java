package ru.academits.tolmachev.controller;

import ru.academits.tolmachev.common.View;
import ru.academits.tolmachev.common.ViewListener;
import ru.academits.tolmachev.model.MineBoard;

public class Controller implements ViewListener {
    private final View view;
    private MineBoard mineBoard;

    public Controller(MineBoard mineBoard, View view) {
        this.mineBoard = mineBoard;
        this.view = view;
    }


    @Override
    public void needOpenCell(int row, int col) {
        view.changeCell(mineBoard.changeCell(row, col));

    }

    public void needMarkCell(int row, int col) {
        view.changeCell(mineBoard.markCell(row, col));

    }

    public void setBoard() {
        mineBoard = new MineBoard(9, 9);
        view.setBoard(mineBoard.getRows(), mineBoard.getCols());
    }

}
