import ru.academits.tolmachev.common.View;
import ru.academits.tolmachev.controller.Controller;
import ru.academits.tolmachev.gui.FrameView;
import ru.academits.tolmachev.model.MineBoard;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {

        System.out.println("Сапер");

        try (View view = new FrameView()) {
            int rows = 9;
            int cols = 9;
            MineBoard mineBoard = new MineBoard(rows, cols);
            Controller controller = new Controller(mineBoard, view);
            view.addViewListener(controller);
            view.startApplication();
        } catch (Exception e) {
            e.printStackTrace();
        }

        double[][] test = new double[][]{{0, 1, 2}, {3, 4, 5}};
        double[] test1 = test[0];
        System.out.println(Arrays.deepToString(test));
    }
}
