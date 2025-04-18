package Algorithms;

import javax.swing.*;
import java.awt.*;
import java.util.Random;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

// jenom vykreslování
public class SortPanel extends JPanel {
    private int[] values;
    private int activeIndex = -1;
    private int compareIndex = -1;

    public void generateArray(int size, boolean ordered) {
        values = new int[size];

        if (ordered) {
            for (int i = 0; i < size; i++) {
                values[i] = (int) ((i + 1) * (getHeight() - 20) / (double) size);
            }

            // Zamícháme pořadí
            List<Integer> list = new ArrayList<>();
            for (int val : values) list.add(val);
            Collections.shuffle(list);
            for (int i = 0; i < size; i++) values[i] = list.get(i);

        } else {
            Random rand = new Random();
            for (int i = 0; i < size; i++) {
                values[i] = rand.nextInt(getHeight() - 20) + 10;
            }
        }

        repaint();
    }

    public int[] getValues() {
        return values;
    }

    public void setValues(int[] newValues) {
        this.values = newValues;
        repaint();
    }

    public void highlight(int i, int j) {
        this.activeIndex = i;
        this.compareIndex = j;
        repaint();
    }

    public void clearHighlight() {
        this.activeIndex = -1;
        this.compareIndex = -1;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (values == null) return;

        int width = getWidth() / values.length;

        for (int i = 0; i < values.length; i++) {
            if (i == activeIndex) {
                g.setColor(Color.RED);
            } else if (i == compareIndex) {
                g.setColor(Color.GREEN);
            } else {
                g.setColor(Color.BLUE);
            }

            int barHeight = values[i];
            g.fillRect(i * width, getHeight() - barHeight, width - 2, barHeight);
        }
    }
}
