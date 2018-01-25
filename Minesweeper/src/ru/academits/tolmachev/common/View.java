package ru.academits.tolmachev.common;

import java.util.ArrayList;

public interface View extends AutoCloseable {

    void startApplication();

    void addViewListener(ViewListener listener);

    void changeCell(ArrayList<ResultOfPress> arrayList);

    void setBoard(int rows, int cols);

//    void showEmpty(int[] y, int [] x, int command);
//
//    void removeViewListener(ViewListener listener);
//
}
