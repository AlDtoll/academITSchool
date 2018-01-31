package ru.academits.tolmachev.gui;

import ru.academits.tolmachev.common.ResultOfPress;
import ru.academits.tolmachev.common.View;
import ru.academits.tolmachev.common.ViewListener;
import ru.academits.tolmachev.model.MineBoard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class FrameView implements View {

    private final ArrayList<ViewListener> listeners = new ArrayList<>();

    private final JFrame frame = new JFrame("Minesweeper");
    private int rows = 9;
    private int cols = 9;
    private int mines = 10;
    private JPanel mineField = new JPanel();
    private JButton[][] buttons;


    private void initFrame() {
        int sizeOfCell = 45;
        int sizeOfMenuBar = 30;
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setMinimumSize(new Dimension(sizeOfCell * cols, sizeOfCell * rows + sizeOfMenuBar));
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
    }


    private void initContent() {
        initMenu();
        initField(rows, cols, mines);
    }

    private void initField(int rows, int cols, int mines) {
        for (ViewListener listener : listeners) {
            listener.setBoard(rows, cols, mines);
        }
        mineField.setLayout(new GridLayout(rows, cols));
        buttons = new JButton[rows][cols];
        Font BigFontTR = new Font("TimesRoman", Font.BOLD, 17);//Тут все про шрифт)
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                buttons[i][j] = new JButton();
                buttons[i][j].setFont(BigFontTR);
                mineField.add(buttons[i][j]);
            }
        }
        frame.setContentPane(mineField);
    }

    private void initMenu() {
        JMenuBar menuBar = new JMenuBar();

        JMenu newGame = new JMenu("New Game");
        newGame.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseReleased(e);
                for (int i = 0; i < rows; i++) {
                    for (int j = 0; j < cols; j++) {
                        buttons[i][j].setIcon(new JButton().getIcon());
                        buttons[i][j].setEnabled(true);
                        buttons[i][j].setText("");
                    }
                }
                for (ViewListener listener : listeners) {
                    listener.setBoard(rows, cols, mines);
                }
            }
        });

        JMenu edit = new JMenu("Edit");
        edit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseReleased(e);
                frame.setVisible(false);
                for (int i = 0; i < rows; i++) {
                    for (int j = 0; j < cols; j++) {
                        mineField.remove(buttons[i][j]);
                    }
                }
                int rows = Integer.parseInt(JOptionPane.showInputDialog(frame, "Set number of rows"));
                int crutch = 20;
                if (rows > crutch) {
                    rows = crutch;
                    JOptionPane.showMessageDialog(frame, "Number of rows is too big. It will be " + rows);
                }
                int cols = Integer.parseInt(JOptionPane.showInputDialog(frame, "Set number of cols"));
                if (cols > crutch) {
                    cols = crutch;
                    JOptionPane.showMessageDialog(frame, "Number of cols is too big. It will be " + cols);
                }
                int mines = Integer.parseInt(JOptionPane.showInputDialog(frame, "Set number of mines"));
                if (mines > cols * rows / 4) {
                    mines = cols * rows / 4;
                    JOptionPane.showMessageDialog(frame, "Number of mines is too big. It will be " + mines);
                }
                initField(rows, cols, mines);
                initFrame();
                initEvents();
            }

        });

        JMenu highScores = new JMenu("High Scores");
        highScores.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseReleased(e);
                for (ViewListener listener : listeners) {
                    listener.callChampions();
                }
            }

        });

        JMenu exit = new JMenu("Exit");
        exit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseReleased(e);
                frame.setVisible(false);
            }

        });


        JMenu about = new JMenu("About");

        JMenu cheat = new JMenu("           ");
        cheat.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseReleased(e);
                for (ViewListener listener : listeners) {
                    listener.showBomb();
                }
            }
        });

        menuBar.add(newGame);
        menuBar.add(edit);
        menuBar.add(highScores);
        menuBar.add(exit);
        menuBar.add(about);
        menuBar.add(cheat);

        frame.setJMenuBar(menuBar);
    }


    private void initEvents() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                int finalJ = j;
                int finalI = i;
                buttons[finalI][finalJ].addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseReleased(MouseEvent e) {
                        super.mouseReleased(e);
                        int centerX = mineField.getSize().width / cols / 2;
                        int centerY = mineField.getSize().height / rows / 2;
                        int x = buttons[finalI][finalJ].getX() / centerX / 2;
                        int y = buttons[finalI][finalJ].getY() / centerY / 2;
                        if (e.getButton() == MouseEvent.BUTTON3) {
                            for (ViewListener listener : listeners) {
                                listener.needMarkCell(y, x);
                            }
                        }
                        if (e.getButton() == MouseEvent.BUTTON1) {
                            for (ViewListener listener : listeners) {
                                listener.needOpenCell(y, x);
                            }
                        }
                    }
                });
            }
        }
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


    public void changeCell(ArrayList<ResultOfPress> arrayList) {
        boolean isVictory = false;
        boolean isLoss = false;
        for (ResultOfPress node : arrayList) {
            switch (node.command) {
                case MineBoard.FLAG:
                    buttons[node.y][node.x].setIcon(new ImageIcon("Minesweeper\\src\\ru\\academits\\tolmachev\\resources\\flag.png"));
                    break;
                case MineBoard.DEFAULT:
                    buttons[node.y][node.x].setIcon(new JButton().getIcon());
                    break;
                case MineBoard.EXPLOSION:
                    buttons[node.y][node.x].setIcon(new ImageIcon("Minesweeper\\src\\ru\\academits\\tolmachev\\resources\\explosion.png"));
                    isLoss = true;
                    break;
                case MineBoard.BOMB:
                    buttons[node.y][node.x].setIcon(new ImageIcon("Minesweeper\\src\\ru\\academits\\tolmachev\\resources\\mine.png"));
                    break;
                case MineBoard.EMPTY:
                    buttons[node.y][node.x].setEnabled(false);
                    break;
                case MineBoard.WIN:
                    isVictory = true;
                    break;
                default:
                    buttons[node.y][node.x].setEnabled(false);
                    buttons[node.y][node.x].setText("" + node.command);
                    break;
            }
        }
        if (isVictory) {
            JOptionPane.showMessageDialog(frame, "WIN");
            deactivateBoard();
        }
        if (isLoss) {
            JOptionPane.showMessageDialog(frame, "Explosion!\n You was blown up, try again");
            deactivateBoard();
        }
    }

    private void deactivateBoard() {
        for (ViewListener listener : listeners) {
            listener.deactivateBoard();
        }
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                buttons[i][j].setEnabled(false);
            }
        }
        String name = JOptionPane.showInputDialog(frame, "Carve your name in Champions Hall!");
        for (ViewListener listener : listeners) {
            listener.carveName(name);
        }
    }

    public void showBomb(ArrayList<ResultOfPress> arrayList) {
        for (ResultOfPress node : arrayList) {
            buttons[node.y][node.x].setIcon(new ImageIcon("Minesweeper\\src\\ru\\academits\\tolmachev\\resources\\mine.png"));
        }
    }


    @Override
    public void setBoard(int rows, int cols, int mines) {
        this.rows = rows;
        this.cols = cols;
        this.mines = mines;
    }

    public void showScore(String[] table) {
        JOptionPane.showMessageDialog(frame, table);
    }

}
