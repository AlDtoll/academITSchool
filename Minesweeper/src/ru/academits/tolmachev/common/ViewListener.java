package ru.academits.tolmachev.common;

import java.awt.event.MouseEvent;

public interface ViewListener {
    // пользователь щелкнул по кнопке
    void needChangeCell(int row, int col, MouseEvent mouseEvent);

    void setBoard();

}

