import javax.swing.*;
import java.awt.*;

import Algorithms.BubbleSort;
import Algorithms.SortPanel;
import Algorithms.InsertionSort;
import Algorithms.SelectionSort;

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
        algorithmBox = new JComboBox<>(new String[]{"Bubble Sort","Insertion Sort", "Selection Sort"});
        controls.add(algorithmBox);

        startButton = new JButton("Spustit");
        controls.add(startButton);

        orderedCheckBox = new JCheckBox("Posloupnost 1..n", true);
        controls.add(orderedCheckBox);

        add(controls, BorderLayout.NORTH);

        // Vizuální panel
        sortPanel = new SortPanel();
        add(sortPanel, BorderLayout.CENTER);




        // Tlačítko spuštění
        startButton.addActionListener(e -> {
            int size = (int) sizeSpinner.getValue();
            int delay = (int) delaySpinner.getValue();
            boolean ordered = orderedCheckBox.isSelected();
            sortPanel.generateArray(size, ordered);

            new Thread(() -> {
                startButton.setEnabled(false);
                String algo = (String) algorithmBox.getSelectedItem();

                if ("Bubble Sort".equals(algo)) {
                    BubbleSort.sort(sortPanel, delay);
                } else if ("Insertion Sort".equals(algo)) {
                    InsertionSort.sort(sortPanel, delay);
                } else if ("Selection Sort".equals(algo)) {
                    SelectionSort.sort(sortPanel, delay);
                }

                sortPanel.clearHighlight();
                sortPanel.repaint();
                startButton.setEnabled(true);
            }).start();
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(SortVisualizer::new);
    }
}