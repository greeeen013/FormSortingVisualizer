import javax.swing.*;
import java.awt.*;

import Algorithms.BubbleSort;
import Algorithms.SortPanel;

// jenom načte GUI a připojí vše dohromady
public class SortVisualizer extends JFrame {
    private SortPanel sortPanel;
    private JSpinner sizeSpinner;
    private JComboBox<String> algorithmBox;
    private JButton startButton;
    private JSpinner delaySpinner;

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

        controls.add(new JLabel("Algoritmus:"));
        algorithmBox = new JComboBox<>(new String[]{"Bubble Sort"});
        controls.add(algorithmBox);

        startButton = new JButton("Spustit");
        controls.add(startButton);

        controls.add(new JLabel("Delay (ms):"));
        delaySpinner = new JSpinner(new SpinnerNumberModel(30, 0, 1000, 10));
        controls.add(delaySpinner);

        add(controls, BorderLayout.NORTH);

        // Vizuální panel
        sortPanel = new SortPanel();
        add(sortPanel, BorderLayout.CENTER);




        // Tlačítko spuštění
        startButton.addActionListener(e -> {
            int size = (int) sizeSpinner.getValue();
            int delay = (int) delaySpinner.getValue();
            sortPanel.generateArray(size);

            new Thread(() -> {
                startButton.setEnabled(false);
                String algo = (String) algorithmBox.getSelectedItem();

                if ("Bubble Sort".equals(algo)) {
                    BubbleSort.sort(sortPanel, delay);
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