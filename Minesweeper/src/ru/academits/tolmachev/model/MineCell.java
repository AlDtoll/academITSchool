package ru.academits.tolmachev.model;

class MineCell {

    boolean isMarked = false;
    boolean isPressed = false;
    boolean isBomb = false;
    int hint = 0;

    MineCell() {
    }

    void setBomb() {
        isBomb = true;
        hint = 9;
    }

    void markCell() {
        if (!isPressed) {
            isMarked = !isMarked;
        }
    }

    void pressCell() {
        if (!isMarked || !isPressed) {
            isPressed = true;
        }
    }
}
