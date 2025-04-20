package Algorithms;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

// jenom vykreslování
public class SortPanel extends JPanel {
    private int[] values;
    private int activeIndex = -1;
    private int compareIndex = -1;
    private Set<Integer> sortedIndices = new HashSet<>();

    private int sortedUntil = -1;

    public void setSortedUntil(int index) {
        this.sortedUntil = index;
    }

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
        setBackground(Color.BLACK);
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

    public void markSorted(int index) {
        sortedIndices.add(index);
        repaint();
    }

    public void clearSorted() {
        sortedIndices.clear();
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (values == null) return;

        int panelWidth = getWidth();
        int count = values.length;
        double barWidth = (double) panelWidth / count;

        for (int i = 0; i < count; i++) {
            int x = (int) Math.round(i * barWidth);
            int nextX = (int) Math.round((i + 1) * barWidth);
            int width = nextX - x - 1;
            if (width < 1) width = 1;

            if (sortedIndices.contains(i)) {
                g.setColor(Color.GREEN); // ✅ Final position
            } else if (i == activeIndex) {
                g.setColor(Color.RED); // 🔴 Main working element
            } else if (i == compareIndex) {
                g.setColor(Color.YELLOW); // 🟡 Element being compared to red
            } else
                g.setColor(Color.BLUE); // 🔵 Default / unsorted

            int barHeight = values[i];
            g.fillRect(x, getHeight() - barHeight, width, barHeight);
        }
    }
}
