package gfx;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Frame extends JFrame {
    
    private Renderer render;
    private JPanel control = new JPanel();

    private JLabel title;
    private JTextArea keybind, sorting;

    private JTextField currentDelay;
    private JSlider delaySlider;

    public Frame(String name, int windowSize) {
        render = new Renderer(windowSize);
        setupPanel(windowSize);
        this.setTitle(name);
        this.add(render, BorderLayout.WEST);
        this.add(control, BorderLayout.EAST);

        this.pack();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.addKeyListener(new KeyHandler(this, render));
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public void setupPanel(int height) {
        control.setPreferredSize(new Dimension(200, height));
        control.setFocusable(false);
        control.setBackground(new Color(37, 37, 38));
        control.setLayout(new FlowLayout());

        title = new JLabel("Keybinding");
        title.setFont(new Font("Nunito", Font.BOLD, 20));
        title.setForeground(Color.white);
        
        keybind = new JTextArea("R - Start sorting\n" +
                                "Q - Pause the sorting process\n" +
                                "Space - Shuffle the array\n" +
                                "W - Increase array\'s length\n" +
                                "S - Decrease array\'s length\n\n" +
                                "Number of Algorithms: " + render.getNumOfAlgorithm() + "\n" +
                                "A - Previous Algorithm\n" +
                                "D - Next Algorithm\n");
        keybind.setBackground(new Color(37, 37, 38));
        keybind.setForeground(Color.white);
        keybind.setFont(new Font("Nunito", Font.BOLD, 12));
        keybind.setEditable(false);
        keybind.setFocusable(false);

        sorting = new JTextArea();
        updateTextArea();
        sorting.setBackground(new Color(37, 37, 38));
        sorting.setForeground(Color.white);
        sorting.setFont(new Font("Nunito", Font.BOLD, 12));
        sorting.setEditable(false);
        sorting.setFocusable(false);

        currentDelay = new JTextField(12);
        currentDelay.setText("Current delay: " + render.getDelay() + " ms");
        currentDelay.setHorizontalAlignment(JTextField.CENTER);
        currentDelay.setBackground(new Color(37, 37, 38));
        currentDelay.setForeground(Color.white);
        currentDelay.setFont(new Font("Nunito", Font.BOLD, 12));
        currentDelay.setEditable(false);
        currentDelay.setFocusable(false);

        delaySlider = new JSlider(0, 10);
        delaySlider.setValue(render.getDelay());
        delaySlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                render.setDelay(delaySlider.getValue());
                currentDelay.setText("Current delay: " + render.getDelay() + " ms");
            }
        });
        delaySlider.setBackground(new Color(37, 37, 38));
        delaySlider.setForeground(Color.white);
        delaySlider.setFocusable(false);

        control.add(title, BorderLayout.CENTER);
        control.add(keybind, BorderLayout.CENTER);
        control.add(sorting, BorderLayout.CENTER);
        control.add(currentDelay, BorderLayout.CENTER);
        control.add(delaySlider, BorderLayout.CENTER);
    }

    public void updateTextArea() {
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < render.getNumOfAlgorithm(); i++) {
            sb.append((i+1) + " - " + render.getSorterAtIndex(i).getName());
            if(i != render.getNumOfAlgorithm()-1) sb.append("\n");
        }

        sorting.setText("Algorithms List (" + (render.getSorterIndex()+1) + "):\n" + sb.toString());
    }
}
