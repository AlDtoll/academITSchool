package ru.academits.tolmachev.common;

import java.util.ArrayList;

public interface View{

    void startApplication();

    void addViewListener(ViewListener listener);

    void changeCell(ArrayList<ResultOfPress> arrayList);

    void setBoard(int rows, int cols, int mines);

    void showBomb(ArrayList<ResultOfPress> bombMap);

    void showScore(String[] table);
}
