package ru.academits.tolmachev.common;

public interface View extends AutoCloseable {

    void startApplication();

    void addViewListener(ViewListener listener);

    void changeCell(int y, int x, int command);

    void setBoard(int rows, int cols);
//
//    void removeViewListener(ViewListener listener);
//
}
