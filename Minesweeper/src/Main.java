import ru.academits.tolmachev.common.View;
import ru.academits.tolmachev.controller.Controller;
import ru.academits.tolmachev.gui.FrameView;
import ru.academits.tolmachev.model.MineBoard;
import ru.academits.tolmachev.model.ScoreTable;
import ru.academits.tolmachev.model.Timer;
import ru.academits.tolmachev.textui.ConsoleView;

public class Main {

    public static void main(String[] args) {

        System.out.println("Сапер");

        View view = new FrameView();
//        View view = new ConsoleView();
        MineBoard mineBoard = new MineBoard();
        ScoreTable scoreTable = new ScoreTable();
        Timer timer = new Timer(new java.util.Timer());
        Controller controller = new Controller(mineBoard, view, scoreTable, timer);
        view.addViewListener(controller);
        view.startApplication();
    }
}
