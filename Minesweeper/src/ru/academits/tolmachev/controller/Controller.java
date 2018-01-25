package ru.academits.tolmachev.controller;

import ru.academits.tolmachev.common.View;
import ru.academits.tolmachev.common.ViewListener;
import ru.academits.tolmachev.model.MineBoard;

import java.awt.event.MouseEvent;

public class Controller implements ViewListener {
    private final View view;
    private MineBoard mineBoard;

    public Controller(MineBoard mineBoard, View view) {
        this.mineBoard = mineBoard;
        this.view = view;
    }


    @Override
    public void needChangeCell(int row, int col, MouseEvent mouseEvent) {
        view.changeCell(mineBoard.changeCell(row, col, mouseEvent));

    }

    public void setBoard() {
        mineBoard = new MineBoard(9,9);
        view.setBoard(mineBoard.getRows(), mineBoard.getCols());
    }

}
