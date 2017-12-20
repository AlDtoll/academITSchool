package ru.academits.tolmachev.gui;


import ru.academits.tolmachev.common.View;
import ru.academits.tolmachev.common.ViewListener;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class FrameView implements View {

    private final ArrayList<ViewListener> listeners = new ArrayList<>();

    private final JFrame frame = new JFrame("Temperature converter");
    private final JTextField tfTemperature = new JTextField();
    private final JButton okButton = new JButton("Перевести");
    private final JLabel resultLabel = new JLabel();
    private final JPanel fromPanel = new JPanel();
    private final JPanel toPanel = new JPanel();

    private final JButton fromCelsius = new JButton("Celsius");
    private final JButton fromFahrenheit = new JButton("Fahrenheit");
    private final JButton fromKelvin = new JButton("Kelvin");

    private final JButton toCelsius = new JButton("Celsius");
    private final JButton toFahrenheit = new JButton("Fahrenheit");
    private final JButton toKelvin = new JButton("Kelvin");

    private final static int HORIZONTAL_INSET = 10;
    private final static int VERTICAL_INSET = 5;

    private void initFrame() {
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setMinimumSize(new Dimension(450, 220));
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }


    private void initButtonPanel() {
        fromPanel.add(fromCelsius);
        fromPanel.add(fromFahrenheit);
        fromPanel.add(fromKelvin);

        toPanel.add(toCelsius);
        toPanel.add(toFahrenheit);
        toPanel.add(toKelvin);
    }

    private void initContent() {
        JPanel contentPanel = new JPanel(new GridBagLayout());

        Insets insets = new Insets(0, HORIZONTAL_INSET, 0, HORIZONTAL_INSET);
        GridBagConstraints c1 = new GridBagConstraints();
        c1.gridx = c1.gridy = 0; // положение
        c1.gridwidth = 2;
        c1.gridheight = 1;
        c1.anchor = GridBagConstraints.ABOVE_BASELINE_LEADING;
        c1.insets = new Insets(VERTICAL_INSET, HORIZONTAL_INSET, 0, HORIZONTAL_INSET);
        contentPanel.add(new JLabel("Enter temperature"), c1);

        GridBagConstraints c2 = new GridBagConstraints();
        c2.gridx = 0;
        c2.gridy = 1;
        c2.gridwidth = 2;
        c2.gridheight = 1;
        c2.anchor = GridBagConstraints.ABOVE_BASELINE_LEADING;
        c2.fill = GridBagConstraints.HORIZONTAL;
        c2.weightx = 1.0;
        c2.insets = new Insets(VERTICAL_INSET, HORIZONTAL_INSET, VERTICAL_INSET, HORIZONTAL_INSET);
        contentPanel.add(tfTemperature, c2);

        GridBagConstraints c3 = new GridBagConstraints();
        c3.gridx = 0;
        c3.gridy = 2;
        c3.gridwidth = 1;
        c3.gridheight = 1;
        c3.weighty = 1.0;
        c3.weightx = 1.0;
        c3.anchor = GridBagConstraints.NORTHWEST;
        c3.insets = insets;
        contentPanel.add(resultLabel, c3);

        GridBagConstraints c4 = new GridBagConstraints();
        c4.gridx = 1;
        c4.gridy = 2;
        c4.gridwidth = 1;
        c4.gridheight = 1;
        c4.weighty = 1.0;
        c4.anchor = GridBagConstraints.NORTHEAST;
        c4.insets = insets;
        contentPanel.add(okButton, c4);

        initButtonPanel();
        GridBagConstraints c5 = new GridBagConstraints();
        c5.gridx = 0;
        c5.gridy = 4;
        c5.gridwidth = 1;
        c5.gridheight = 1;
        c5.weighty = 1.0;
        c5.anchor = GridBagConstraints.SOUTHWEST;
        c5.insets = insets;
        contentPanel.add(fromPanel, c5);


        GridBagConstraints c6 = new GridBagConstraints();
        c6.gridx = 0;
        c6.gridy = 6;
        c6.gridwidth = 1;
        c6.gridheight = 1;
        c6.weighty = 1.0;
        c6.anchor = GridBagConstraints.WEST;
        c6.insets = insets;
        contentPanel.add(toPanel, c6);

        GridBagConstraints c7 = new GridBagConstraints();
        c7.gridx = 0;
        c7.gridy = 3; // положение
        c7.gridwidth = 2;
        c7.gridheight = 1;
        c7.anchor = GridBagConstraints.ABOVE_BASELINE_LEADING;
        c7.insets = new Insets(VERTICAL_INSET, HORIZONTAL_INSET, 0, HORIZONTAL_INSET);
        contentPanel.add(new JLabel("From"), c7);

        GridBagConstraints c8 = new GridBagConstraints();
        c8.gridx = 0;
        c8.gridy = 5; // положение
        c8.gridwidth = 2;
        c8.gridheight = 1;
        c8.anchor = GridBagConstraints.ABOVE_BASELINE_LEADING;
        c8.insets = new Insets(VERTICAL_INSET, HORIZONTAL_INSET, 0, HORIZONTAL_INSET);
        contentPanel.add(new JLabel("To"), c8);

        frame.setContentPane(contentPanel);

    }

    private void initEvents() {
        final String[] scale = new String[]{"C"};
        final String[] from = {scale[0]};
        final String[] to = {from[0]};
        fromCelsius.addActionListener(e -> from[0] = "C");
        fromFahrenheit.addActionListener(e -> from[0] = "F");
        fromKelvin.addActionListener(e -> from[0] = "K");
        toCelsius.addActionListener(e -> to[0] = "C");
        toFahrenheit.addActionListener(e -> to[0] = "F");
        toKelvin.addActionListener(e -> to[0] = "K");

        okButton.addActionListener(e -> {
            try {
                double temperature = Double.parseDouble(tfTemperature.getText());
                for (ViewListener listener : listeners) {
                    listener.needConvertTemperature(temperature, from[0], to[0]);
                }
            } catch (NumberFormatException ex) {
                resultLabel.setForeground(Color.RED);
                resultLabel.setText("Temperature must be number");
            }
        });
    }

    public void startApplication() {
        SwingUtilities.invokeLater(() -> {
            initFrame();
            initContent();
            initEvents();
        });
    }

    public void onTemperatureConverted(double convertedTemperature) {
        resultLabel.setForeground(Color.BLACK);
        resultLabel.setText(Double.toString(convertedTemperature));
    }

    public void addViewListener(ViewListener listener) {
        if (!listeners.contains(listener)) {
            listeners.add(listener);
        }
    }

    public void removeViewListener(ViewListener listener) {
        listeners.remove(listener);
    }

    public void close() throws Exception {
        frame.setVisible(false);
    }
}

