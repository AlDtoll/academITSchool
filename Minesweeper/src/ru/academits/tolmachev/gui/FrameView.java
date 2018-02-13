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
    private final JLabel counter = new JLabel();
    private JPanel mineField = new JPanel();
    private JButton[][] buttons;
    private JLabel timer = new JLabel();

    private final static int HORIZONTAL_INSET = 5;
    private final static int VERTICAL_INSET = 5;


    private void initFrame() {
        int sizeOfCell = 47;
        int sizeOfMenuBar = 30;
        int sizeOfCounters = 30;
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setMinimumSize(new Dimension(sizeOfCell * cols, sizeOfCell * rows + sizeOfMenuBar + sizeOfCounters));
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
    }


    private void initContent() {
        initMenu();
        initField(rows, cols, mines);

        JPanel contentPanel = new JPanel(new GridBagLayout());
        Insets insets = new Insets(VERTICAL_INSET, HORIZONTAL_INSET, VERTICAL_INSET, HORIZONTAL_INSET);
        GridBagConstraints c3 = new GridBagConstraints();
        c3.gridx = 0;
        c3.gridy = 0;
        c3.gridwidth = 1;
        c3.gridheight = 1;
        c3.weighty = 0.1;
        c3.weightx = 0.5;
        c3.anchor = GridBagConstraints.CENTER;
        c3.insets = insets;

        counter.setText("FLAGS:" + mines);
        contentPanel.add(counter, c3);

        GridBagConstraints c2 = new GridBagConstraints();
        c2.gridx = 1;
        c2.gridy = 0;
        c2.gridwidth = 1;
        c2.gridheight = 1;
        c2.anchor = GridBagConstraints.CENTER;
        c2.weighty = 0.1;
        c2.weightx = 0.5;
        c2.insets = insets;

        timer.setText("TIME:" + 0);
        contentPanel.add(timer, c2);

        GridBagConstraints c1 = new GridBagConstraints();
        c1.gridx = 0;
        c1.gridy = 1;
        c1.gridwidth = 2;
        c1.gridheight = 2;
        c1.weighty = 1.0;
        c1.weightx = 1.0;
        c1.insets = insets;
        contentPanel.add(mineField, c1);


        frame.setContentPane(contentPanel);
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
//        frame.setContentPane(mineField);
    }

    //TODO сделать меню выпадающим
    private void initMenu() {
        JMenuBar menuBar = new JMenuBar();

        JMenu newGame = new JMenu("New Game");
        newGame.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseReleased(e);
                Icon defaultIcon = new JButton().getIcon();
                for (int i = 0; i < rows; i++) {
                    for (int j = 0; j < cols; j++) {
                        buttons[i][j].setIcon(defaultIcon);
                        buttons[i][j].setEnabled(true);
                        buttons[i][j].setText("");
                    }
                }
                for (ViewListener listener : listeners) {
                    listener.setBoard(rows, cols, mines);
                }
            }
        });

        //TODO сделать одним окном и проверку
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
                int rows = Integer.parseInt(JOptionPane.showInputDialog(frame, "Set rows"));
                int cols = Integer.parseInt(JOptionPane.showInputDialog(frame, "Set cols"));
                int mines = Integer.parseInt(JOptionPane.showInputDialog(frame, "Set mines"));
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
                System.exit(0);
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
//                        JOptionPane.showMessageDialog(frame, "row: " + x + " col: " + y);
                        if (e.getButton() == MouseEvent.BUTTON3) {
                            for (ViewListener listener : listeners) {
                                listener.needMarkCell(y, x);
                                listener.changeFlagCounter();
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

    public void changeFlagCounter(int flags) {
        counter.setText("FLAGS: " + flags);
    }

    @Override
    public void changeTimeCounter(int seconds) {
        timer.setText("TIME:" + seconds);
    }

    // TODO общаее имя пути убрать
    public void changeCell(ArrayList<ResultOfPress> arrayList) {
        boolean isVictory = false;
        boolean isLoss = false;
        Icon defaultIcon = new JButton().getIcon();
        for (ResultOfPress node : arrayList) {
            switch (node.command) {
                case MineBoard.FLAG:
                    buttons[node.y][node.x].setIcon(new ImageIcon("Minesweeper\\src\\ru\\academits\\tolmachev\\resources\\flag.png"));
                    break;
                case MineBoard.QUESTION:
                    buttons[node.y][node.x].setIcon(new ImageIcon("Minesweeper\\src\\ru\\academits\\tolmachev\\resources\\question.png"));
                    break;
                case MineBoard.DEFAULT:
                    buttons[node.y][node.x].setIcon(defaultIcon);
                    break;
                case MineBoard.EXPLOSION:
                    buttons[node.y][node.x].setIcon(new ImageIcon("Minesweeper\\src\\ru\\academits\\tolmachev\\resources\\explosion.png"));
                    isLoss = true;
                    break;
                // TODO а оно здесь надо?
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
            String name = JOptionPane.showInputDialog(frame, "Carve your name in Champions Hall!");
            for (ViewListener listener : listeners) {
                listener.carveName(name);
            }
        }
        if (isLoss) {
            JOptionPane.showMessageDialog(frame, new String[]{"Explosion!", "You was blown up, try again"});
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
    }

    public void showBomb(ArrayList<ResultOfPress> arrayList) {
        Icon defaultIcon = new JButton().getIcon();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                buttons[i][j].setIcon(defaultIcon);
            }
        }
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
