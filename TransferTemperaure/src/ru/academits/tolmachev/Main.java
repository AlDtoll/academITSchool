package ru.academits.tolmachev;

import ru.academits.tolmachev.common.View;
import ru.academits.tolmachev.controller.Controller;
import ru.academits.tolmachev.gui.FrameView;
import ru.academits.tolmachev.texitui.ConsoleView;

public class Main {

    public static void main(String[] args) {

        System.out.println("Swing");

        try (View view = new FrameView()) {
//        try (View view = new ConsoleView()) {


            Controller controller = new Controller(view);
            view.addViewListener(controller);

            view.startApplication();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
