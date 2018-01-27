package ru.academits.tolmachev.model;

public class MineCell {

    public boolean isMarked = false;
    public boolean isPressed = false;
    public boolean isBomb = false;
    public int hint = 0;

    public MineCell() {
    }

    public void setBomb() {
        isBomb = true;
        hint = 9;
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
