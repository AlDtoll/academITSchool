package ru.academits.tolmachev.common;

public interface ViewListener {
    // пользователь щелкнул по кнопке левой кнопкой
    void needOpenCell(int row, int col);

    // пользователь щелкнул по кнопке правой кнопкой
    void needMarkCell(int row, int col);

    void setBoard(int rows, int cols, int mines);

    void showBomb();

    void deactivateBoard();

    void callChampions();

    void carveName(String name);


}

