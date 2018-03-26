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
    private boolean isActive = true;

    private final static int HORIZONTAL_INSET = 5;
    private final static int VERTICAL_INSET = 5;
    private final static int SIZE_OF_CELL = 45;
    private final static int MAX_SIZE_OF_BOARD = 20;


    private void initFrame() {
        int sizeOfCell = SIZE_OF_CELL + 2;
        int sizeOfMenuBar = 30;
        int sizeOfCounters = 30;
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
//        frame.setMinimumSize(new Dimension(sizeOfCell * cols, sizeOfCell * rows + sizeOfMenuBar + sizeOfCounters));
        frame.setSize(new Dimension(sizeOfCell * cols, sizeOfCell * rows + sizeOfMenuBar + sizeOfCounters));
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
        timer.setText("TIME: 00:00");
        contentPanel.add(timer, c2);

        GridBagConstraints c1 = new GridBagConstraints();
        c1.gridx = 0;
        c1.gridy = 1;
        c1.gridwidth = 2;
        c1.gridheight = 2;
        c1.anchor = GridBagConstraints.CENTER;
        c1.weighty = 1.0;
        c1.weightx = 1.0;
        c1.insets = insets;
        c1.fill = GridBagConstraints.BOTH;
        contentPanel.add(mineField, c1);

        frame.setContentPane(contentPanel);
    }

    private void initField(int rows, int cols, int mines) {
        isActive = true;
        for (ViewListener listener : listeners) {
            listener.setBoard(rows, cols, mines);
            listener.startTimer(false);
        }
        mineField.setLayout(new GridLayout(rows, cols));
//        mineField.setSize(new Dimension(SIZE_OF_CELL * cols, SIZE_OF_CELL * rows));
//        mineField.setMinimumSize(new Dimension(SIZE_OF_CELL * cols, SIZE_OF_CELL * rows));
        buttons = new JButton[rows][cols];
        Font BigFontTR = new Font("TimesRoman", Font.BOLD, 17);//Тут все про шрифт)
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                buttons[i][j] = new JButton();
                buttons[i][j].setFont(BigFontTR);
                buttons[i][j].setSize(new Dimension(SIZE_OF_CELL, SIZE_OF_CELL));
//                buttons[i][j].setMinimumSize(new Dimension(SIZE_OF_CELL, SIZE_OF_CELL));
//                buttons[0][0].setIcon(new ImageIcon());
                mineField.add(buttons[i][j]);
            }
        }
    }

    private void initMenu() {
        JMenuBar menuBar = new JMenuBar();

        JMenu menu = new JMenu("Menu");
        menuBar.add(menu);

        JMenuItem newGame = new JMenuItem("New Game");
        newGame.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
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
                    listener.resetTimer();
                    changeFlagCounter(mines);
                    isActive = true;
                }
            }
        });

        // Настройка поля
        JMenuItem edit = new JMenuItem("Edit");
        edit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                JDialog editDialog = new JDialog(frame, true);
                editDialog.setLocationRelativeTo(null);
                editDialog.setSize(new Dimension(200, 200));

                JPanel editPanel = new JPanel(new GridLayout(6, 1));
                JLabel rowLabel = new JLabel("Number of rows");
                JTextField numberOfRows = new JTextField();
                numberOfRows.setText("9");
                JLabel colLabel = new JLabel("Number of columns");
                JTextField numberOfCols = new JTextField();
                numberOfCols.setText("9");
                JLabel mineLabel = new JLabel("Number of mines");
                JTextField numberOfMines = new JTextField();
                numberOfMines.setText("10");
                editPanel.add(rowLabel);
                editPanel.add(numberOfRows);
                editPanel.add(colLabel);
                editPanel.add(numberOfCols);
                editPanel.add(mineLabel);
                editPanel.add(numberOfMines);

                numberOfRows.addKeyListener(new KeyAdapter() {
                    @Override
                    public void keyPressed(KeyEvent e) {

                        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                            editBoard(editDialog, numberOfRows, numberOfCols, numberOfMines);
                        }
                    }
                });

                numberOfCols.addKeyListener(new KeyAdapter() {
                    @Override
                    public void keyPressed(KeyEvent e) {

                        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                            editBoard(editDialog, numberOfRows, numberOfCols, numberOfMines);
                        }
                    }
                });

                numberOfMines.addKeyListener(new KeyAdapter() {
                    @Override
                    public void keyPressed(KeyEvent e) {

                        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                            editBoard(editDialog, numberOfRows, numberOfCols, numberOfMines);
                        }
                    }
                });

                editDialog.add(editPanel);
                editDialog.setVisible(true);
            }
        });

        JMenuItem highScores = new JMenuItem("High Scores");
        highScores.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                for (ViewListener listener : listeners) {
                    listener.callChampions();
                }
            }

        });

        JMenuItem exit = new JMenuItem("Exit");
        exit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                System.exit(0);
            }

        });


        JMenuItem about = new JMenuItem("About");

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

        menu.add(newGame);
        menu.add(edit);
        menu.add(highScores);
        menu.add(exit);
        menu.add(about);

        menuBar.add(cheat);

        frame.setJMenuBar(menuBar);
    }

    private void editBoard(JDialog editDialog, JTextField numberOfRows, JTextField numberOfCols, JTextField numberOfMines) {
        int oldRows = rows;
        int oldCols = cols;
        int rows;
        int cols;
        int mines;
        try {
            rows = Integer.parseInt(numberOfRows.getText());
            if (rows < 0 || rows > MAX_SIZE_OF_BOARD) {
                throw new IllegalArgumentException();
            }
            cols = Integer.parseInt(numberOfCols.getText());
            if (cols < 0 || cols > MAX_SIZE_OF_BOARD) {
                throw new IllegalArgumentException();
            }
            mines = Integer.parseInt(numberOfMines.getText());
            if (mines <= 0 || mines > rows * cols - 1) {
                throw new IllegalArgumentException();
            }
        } catch (IllegalArgumentException e1) {
            JOptionPane.showMessageDialog(frame, "One or more of parameter was select wrong. Board will create with default setting");
            rows = 9;
            cols = 9;
            mines = 10;
        }
        editDialog.setVisible(false);
        frame.setVisible(false);
        for (int i = 0; i < oldRows; i++) {
            for (int j = 0; j < oldCols; j++) {
                mineField.remove(buttons[i][j]);
            }
        }
        initField(rows, cols, mines);
        initFrame();
        initEvents();
        for (ViewListener listener : listeners) {
            listener.resetTimer();
            listener.startTimer(false);
            changeFlagCounter(mines);
        }
    }

    private void initEvents() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                int finalJ = j;
                int finalI = i;
                buttons[finalI][finalJ].addMouseListener(new MouseAdapter() {
//                    @Override
//                    public void mousePressed(MouseEvent e) {
//                        super.mousePressed(e);
//                        int centerX = mineField.getSize().width / cols / 2;
//                        int centerY = mineField.getSize().height / rows / 2;
//                        int x = buttons[finalI][finalJ].getX() / centerX / 2;
//                        int y = buttons[finalI][finalJ].getY() / centerY / 2;
//                        if (e.getButton() == MouseEvent.BUTTON3) {
//                            ActionListener start = e1 -> {
//
//                            };
//                            Timer timer = new Timer(100, start);
//                            timer.start();
//                            if (e.getButton() == MouseEvent.BUTTON1) {
//                                buttons[y][x].setIcon(new ImageIcon("Minesweeper\\src\\ru\\academits\\tolmachev\\resources\\question.png"));
////                                buttons[y][x].set
//                            }
//                        }
//                    }

                    public void mouseReleased(MouseEvent e) {
                        super.mouseReleased(e);
                        if (isActive) {
                            for (ViewListener listener : listeners) {
                                listener.startTimer(true);
                            }
                        }
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

//        frame.addMouseListener(new MouseAdapter() {
//            @Override
//            public void mousePressed(MouseEvent e) {
//                int centerX = frame.getSize().width / cols / 2;
//                int centerY = frame.getSize().height / rows / 2;
//                int x = frame.getX() / centerX / 2;
//                int y = frame.getY() / centerY / 2;
//                if (e.getButton() == MouseEvent.BUTTON3) {
//                    ActionListener start = e1 -> {
//
//                    };
//                    Timer timer = new Timer(100, start);
//                    timer.start();
//                    if (e.getButton() == MouseEvent.BUTTON3) {
//                        JOptionPane.showMessageDialog(frame, "row: " + x + " col: " + y);
//                    }
//                }
//
//            }
//        });
    }

    public void startApplication() {
        SwingUtilities.invokeLater(() -> {
            initFrame();
            initContent();
            initEvents();
            initTimer();
        });
    }

    private void initTimer() {
        ActionListener start = e -> {
            for (ViewListener listener : listeners) {
                listener.changeTimeCounter();
            }
        };
        Timer timer = new Timer(0, start);
        timer.start();
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
        timer.setText("TIME:" + String.format("%02d:%02d", seconds / 60, seconds % 60));
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
            deactivateBoard();
            JOptionPane.showMessageDialog(frame, "WIN");
            String name = JOptionPane.showInputDialog(frame, "Carve your name in Champions Hall!");
            if (name != null) {
                for (ViewListener listener : listeners) {
                    listener.carveName(name);
                }
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
        for (ViewListener listener : listeners) {
            listener.startTimer(false);
        }
        isActive = false;
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
        if (table == null) {
            JOptionPane.showMessageDialog(frame, "Nobody is champion, yet!");
        } else {
            String[] newTable = new String[table.length + 1];
            newTable[0] = "Place  Points   Time   Name";
            System.arraycopy(table, 0, newTable, 1, table.length);
            JOptionPane.showMessageDialog(frame, newTable);
        }
    }

}
