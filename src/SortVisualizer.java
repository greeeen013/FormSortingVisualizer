import javax.swing.*;
import java.awt.*;

import Algorithms.*;

// jenom načte GUI a připojí vše dohromady
public class SortVisualizer extends JFrame {
    private SortPanel sortPanel;
    private JSpinner sizeSpinner;
    private JComboBox<String> algorithmBox;
    private JButton startButton;
    private JSpinner delaySpinner;
    private JCheckBox orderedCheckBox;


    public SortVisualizer() {
        setTitle("Sort Visualizer");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setSize(800, 600);
        setLayout(new BorderLayout());

        // Ovládací panel
        JPanel controls = new JPanel();

        controls.add(new JLabel("Velikost pole:"));
        sizeSpinner = new JSpinner(new SpinnerNumberModel(50, 5, 200, 1));
        controls.add(sizeSpinner);

        controls.add(new JLabel("Delay (ms):"));
        delaySpinner = new JSpinner(new SpinnerNumberModel(10, 0, 1000, 10));
        controls.add(delaySpinner);

        controls.add(new JLabel("Algoritmus:"));
        algorithmBox = new JComboBox<>(new String[]{"Bubble Sort","Insertion Sort", "Selection Sort", "Quick Sort", "Merge Sort", "Shell Sort"});
        controls.add(algorithmBox);

        startButton = new JButton("Spustit");
        controls.add(startButton);

        JLabel timeLabel = new JLabel("Doba běhu: 0.000 s");
        controls.add(timeLabel);

        add(controls, BorderLayout.NORTH);

        // Vizuální panel
        sortPanel = new SortPanel();
        add(sortPanel, BorderLayout.CENTER);




        // Tlačítko spuštění
        startButton.addActionListener(e -> {
            int size = (int) sizeSpinner.getValue();
            int delay = (int) delaySpinner.getValue();
            long startTime = System.nanoTime();
            sortPanel.generateArray(size, true);

            new Thread(() -> {
                startButton.setEnabled(false);
                String algo = (String) algorithmBox.getSelectedItem();

                if ("Bubble Sort".equals(algo)) {
                    BubbleSort.sort(sortPanel, delay);
                } else if ("Insertion Sort".equals(algo)) {
                    InsertionSort.sort(sortPanel, delay);
                } else if ("Selection Sort".equals(algo)) {
                    SelectionSort.sort(sortPanel, delay);
                } else if ("Quick Sort".equals(algo)) {
                    QuickSort.sort(sortPanel, delay);
                } else if ("Merge Sort".equals(algo)) {
                    MergeSort.sort(sortPanel, delay);
                } else if ("Shell Sort".equals(algo)) {
                    ShellSort.sort(sortPanel, delay);
                }

                sortPanel.clearHighlight();
                sortPanel.repaint();
                startButton.setEnabled(true);
                long endTime = System.nanoTime();
                double duration = (endTime - startTime) / 1_000_000_000.0;
                timeLabel.setText(String.format("Doba běhu: %.3f s", duration));
            }).start();
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(SortVisualizer::new);
    }
}