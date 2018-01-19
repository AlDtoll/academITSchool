package ru.academits.tolmachev.gui;

import ru.academits.tolmachev.common.View;
import ru.academits.tolmachev.common.ViewListener;
import ru.academits.tolmachev.model.MineBoard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class FrameView implements View {

    private final ArrayList<ViewListener> listeners = new ArrayList<>();

    private final JFrame frame = new JFrame("Minesweeper");
    private int rows = 9;
    private int cols = 9;
    private JButton[][] buttons;


//    @Override
//    public void setBoard(int rows, int cols) {
//        this.rows = rows;
//        this.cols = cols;
//    }

    private void initFrame() {
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setMinimumSize(new Dimension(450, 450));
        frame.setLocationRelativeTo(null);
//        frame.setResizable(false);
        frame.setVisible(true);
    }


    private void initContent() {
        for (ViewListener listener : listeners) {
            listener.setBoard();
        }
        GridLayout mineField = new GridLayout(rows, cols);
        JPanel contentPanel = new JPanel(mineField);
        buttons = new JButton[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                buttons[i][j] = new JButton();
                contentPanel.add(buttons[i][j]);
            }
        }
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                int finalJ = j;
                int finalI = i;
                buttons[finalI][finalJ].addMouseListener(new MouseListener() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        int centerX = contentPanel.getSize().width / cols / 2;
                        int centerY = contentPanel.getSize().height / rows / 2;
                        int x = buttons[finalI][finalJ].getX() / centerX / 2;
                        int y = buttons[finalI][finalJ].getY() / centerY / 2;
                        for (ViewListener listener : listeners) {
                            listener.needChangeCell(y, x, e);
                        }
                    }

                    @Override
                    public void mousePressed(MouseEvent e) {

                    }

                    @Override
                    public void mouseReleased(MouseEvent e) {

                    }

                    @Override
                    public void mouseEntered(MouseEvent e) {

                    }

                    @Override
                    public void mouseExited(MouseEvent e) {

                    }
                });
            }
        }
        frame.setContentPane(contentPanel);
    }

    private void initEvents() {

    }

    public void startApplication() {
        SwingUtilities.invokeLater(() -> {
            initFrame();
            initContent();
            initEvents();
        });
    }

    @Override
    public void addViewListener(ViewListener listener) {
        if (!listeners.contains(listener)) {
            listeners.add(listener);
        }
    }

    public void changeCell(int y, int x, int command) {
        JButton jButton = new JButton();
        Icon defaultIcon = jButton.getIcon();
        Icon defaultPressedIcon = jButton.getPressedIcon();

        switch (command) {
            case MineBoard.FLAG:
                buttons[y][x].setIcon(new ImageIcon("Minesweeper\\src\\ru\\academits\\tolmachev\\resources\\flag.png"));
                break;
            case MineBoard.DEFAULT:
                buttons[y][x].setIcon(defaultIcon);
                break;
            case MineBoard.EXPLOSION:
                buttons[y][x].setIcon(new ImageIcon("Minesweeper\\src\\ru\\academits\\tolmachev\\resources\\explosion.png"));
//                JOptionPane.showMessageDialog(frame, "explosion");
                break;
            default:
                buttons[y][x].setIcon(new ImageIcon("Minesweeper\\src\\ru\\academits\\tolmachev\\resources\\pressed.png"));
                buttons[y][x].setText("" + command);
                break;
        }
    }

    @Override
    public void setBoard(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
    }

    public void close() throws Exception {
        frame.setVisible(false);
    }
}
