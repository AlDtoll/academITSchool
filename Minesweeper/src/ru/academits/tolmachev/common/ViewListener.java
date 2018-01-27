package ru.academits.tolmachev.common;

import java.awt.event.MouseEvent;

public interface ViewListener {
    // пользователь щелкнул по кнопке
    void needOpenCell(int row, int col);

    void needMarkCell(int row, int col);

    void setBoard();

}

