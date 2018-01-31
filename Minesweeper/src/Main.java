import ru.academits.tolmachev.common.View;
import ru.academits.tolmachev.controller.Controller;
import ru.academits.tolmachev.gui.FrameView;
import ru.academits.tolmachev.model.MineBoard;
import ru.academits.tolmachev.textui.ConsoleView;

public class Main {

    public static void main(String[] args) {

        System.out.println("Сапер");

//        View view = new FrameView();
        View view = new ConsoleView();
        MineBoard mineBoard = new MineBoard();
        Controller controller = new Controller(mineBoard, view);
        view.addViewListener(controller);
        view.startApplication();
    }
}
