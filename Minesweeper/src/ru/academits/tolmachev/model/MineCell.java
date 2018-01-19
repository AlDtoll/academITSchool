package ru.academits.tolmachev.model;

public class MineCell {

    public boolean isMarked = false;
    public boolean isPressed = false;
    public boolean isBomb = false;
    public int info = 0;

    public MineCell() {
    }

    public void setBomb() {
        isBomb = true;
    }

    public void markCell() {
        if (!isPressed) {
            isMarked = !isMarked;
        }
    }

    public void pressCell() {
        if (!isMarked) {
            isPressed = true;
        }
    }
}
