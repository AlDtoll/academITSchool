package ru.academits.tolmachev.gui;

import ru.academits.tolmachev.common.ResultOfPress;
import ru.academits.tolmachev.common.View;
import ru.academits.tolmachev.common.ViewListener;
import ru.academits.tolmachev.model.MineBoard;

import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class FrameView implements View {

    private final ArrayList<ViewListener> listeners = new ArrayList<>();

    private final JFrame frame = new JFrame("Minesweeper");
    private int rows = 3;
    private int cols = 3;
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
        GridBagLayout menu = new GridBagLayout();

        JMenuBar menuBar = new JMenuBar();


        JMenu newGame = new JMenu("New Game");

        newGame.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                for (int i = 0; i < rows; i++) {
                    for (int j = 0; j < cols; j++) {
                        buttons[i][j].setIcon(new JButton().getIcon());
                        buttons[i][j].setEnabled(true);
                        buttons[i][j].setText("");
                    }
                }
                for (ViewListener listener : listeners) {
                    listener.setBoard();
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




        JMenu edit = new JMenu("Edit");
        JMenu highScores = new JMenu("High  Scores");
        JMenu exit = new JMenu("Exit");
        JMenu about = new JMenu("About");

        menuBar.add(newGame);
        menuBar.add(edit);
        menuBar.add(highScores);
        menuBar.add(exit);
        menuBar.add(about);





        for (ViewListener listener : listeners) {
            listener.setBoard();
        }
        JPanel mineField = new JPanel(new GridLayout(rows, cols));
        buttons = new JButton[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                buttons[i][j] = new JButton();
                mineField.add(buttons[i][j]);
            }
        }
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                int finalJ = j;
                int finalI = i;
                buttons[finalI][finalJ].addMouseListener(new MouseListener() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        int centerX = mineField.getSize().width / cols / 2;
                        int centerY = mineField.getSize().height / rows / 2;
                        int x = buttons[finalI][finalJ].getX() / centerX / 2;
                        int y = buttons[finalI][finalJ].getY() / centerY / 2;
//                        JOptionPane.showMessageDialog(frame, "row: " + x + " col: " + y);
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
        frame.setContentPane(mineField);

        frame.setJMenuBar(menuBar);
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

    public void changeCell(ArrayList<ResultOfPress> arrayList) {//(int y, int x, int command) {
        for (int i = 0; i < arrayList.size(); i++) {
            switch (arrayList.get(i).command) {
                case MineBoard.FLAG:
                    buttons[arrayList.get(i).y][arrayList.get(i).x].setIcon(new ImageIcon("Minesweeper\\src\\ru\\academits\\tolmachev\\resources\\flag.png"));
                    break;
                case MineBoard.DEFAULT:
                    buttons[arrayList.get(i).y][arrayList.get(i).x].setIcon(new JButton().getIcon());
                    break;
                case MineBoard.EXPLOSION:
                    buttons[arrayList.get(i).y][arrayList.get(i).x].setIcon(new ImageIcon("Minesweeper\\src\\ru\\academits\\tolmachev\\resources\\explosion.png"));
//                JOptionPane.showMessageDialog(frame, "explosion");
                    break;
                case MineBoard.BOMB:
                    buttons[arrayList.get(i).y][arrayList.get(i).x].setIcon(new ImageIcon("Minesweeper\\src\\ru\\academits\\tolmachev\\resources\\mine.png"));
                    break;
                case MineBoard.EMPTY:
                    buttons[arrayList.get(i).y][arrayList.get(i).x].setEnabled(false);
                    break;
                default:
                    buttons[arrayList.get(i).y][arrayList.get(i).x].setEnabled(false);
                    buttons[arrayList.get(i).y][arrayList.get(i).x].setText("" + arrayList.get(i).command);
                    break;
            }
        }
    }

//    public void showEmpty(int[] y, int [] x, int command){
//        for (int i = y[0]; i < rows; i++) {
//            for (int j = 0; j < cols; j++) {
//                buttons[y][x].setEnabled(false);
//            }
//        }
//    }

    @Override
    public void setBoard(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
    }

    public void close() throws Exception {
        frame.setVisible(false);
    }
}
