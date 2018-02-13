package ru.academits.tolmachev.model;

class MineCell {

    public static final int DEFAULT_CELL = 0;
    public static final int CELL_WITH_FLAG = 1;
    public static final int CELL_WITH_QUESTION = 2;

    boolean isMarked = false;
    boolean isPressed = false;
    boolean isBomb = false;
    boolean isFlag = false;
    int status = 0;
    int hint = 0;

    MineCell() {
    }

    void setBomb() {
        isBomb = true;
        hint = 9;
    }

    void markCell() {
//        if (!isPressed) {
//            isMarked = !isMarked;
//        }
        status += 1;
        if (status == 3) {
            status = 0;
        }
        if (status == DEFAULT_CELL) {
            isMarked = false;
            isFlag = false;
        }
        if (status == CELL_WITH_FLAG) {
            isMarked = true;
            isFlag = true;
        }
        if (status == CELL_WITH_QUESTION) {
            isMarked = true;
            isFlag = false;
        }
    }

    void pressCell() {
        if (!isMarked || !isPressed) {
            isPressed = true;
        }
    }
}
