import javax.swing.*;  // Import knihovny pro GUI komponenty
import java.awt.*;  // Import pro rozložení a grafiku (Abstract Window Toolkit)

import Algorithms.*;  // Import tříd pro algoritmy třídění (předpokládá se, že jsou ve složce Algorithms)

// Třída pro hlavní okno vizualizace třídění
public class SortVisualizer extends JFrame {
    private SortPanel sortPanel;  // Panel pro vizualizaci třídění
    private JSpinner sizeSpinner;  // Ovládací prvek pro volbu velikosti pole
    private JComboBox<String> algorithmBox;  // Výběr algoritmu
    private JButton startButton;  // Tlačítko pro spuštění třídění
    private JSpinner delaySpinner;  // Ovládací prvek pro nastavení zpoždění mezi kroky třídění

    // Konstruktor pro nastavení GUI a připojení komponent
    public SortVisualizer() {
        setTitle("Sort Visualizer");  // Nastavení názvu okna
        setDefaultCloseOperation(EXIT_ON_CLOSE);  // Ukončení aplikace při zavření okna
        setSize(800, 600);  // Nastavení velikosti okna
        setLayout(new BorderLayout());  // Nastavení layoutu okna (rozdělení na části)

        // Ovládací panel pro zadávání parametrů třídění
        JPanel controls = new JPanel();

        // Ovládací prvek pro výběr velikosti pole
        controls.add(new JLabel("Velikost pole:"));
        sizeSpinner = new JSpinner(new SpinnerNumberModel(50, 5, 2000, 1));  // Min: 5, Max: 2000, krok: 1
        controls.add(sizeSpinner);

        // Ovládací prvek pro nastavení zpoždění mezi kroky třídění
        controls.add(new JLabel("Delay (ms):"));
        delaySpinner = new JSpinner(new SpinnerNumberModel(10, 0, 1000, 10));  // Min: 0, Max: 1000, krok: 10 ms
        controls.add(delaySpinner);

        // Výběr algoritmu třídění
        controls.add(new JLabel("Algoritmus:"));
        algorithmBox = new JComboBox<>(new String[]{"Bubble Sort", "Insertion Sort", "Selection Sort", "Quick Sort", "Merge Sort", "Shell Sort", "Heap Sort", "Radix Sort"});
        controls.add(algorithmBox);

        // Tlačítko pro spuštění třídění
        startButton = new JButton("Spustit");
        controls.add(startButton);

        // Zobrazení doby běhu algoritmu
        JLabel timeLabel = new JLabel("Doba běhu: 0.000 s");
        controls.add(timeLabel);

        // Přidání ovládacího panelu do severní části okna
        add(controls, BorderLayout.NORTH);

        // Panel pro vizualizaci třídění
        sortPanel = new SortPanel();
        add(sortPanel, BorderLayout.CENTER);

        // Nastavení akce pro tlačítko "Spustit"
        startButton.addActionListener(e -> {
            int size = (int) sizeSpinner.getValue();  // Získání hodnoty pro velikost pole
            int delay = (int) delaySpinner.getValue();  // Získání hodnoty pro zpoždění
            long startTime = System.nanoTime();  // Uložení začátku času pro měření doby běhu
            sortPanel.generateArray(size, true);  // Generování náhodného pole pro třídění... ordered: true znamena ze vygenerovane čísla budou vždy +1 jinak muzou byt nahodne

            // Vytvoření nového vlákna pro spuštění algoritmu bez blokování hlavního GUI vlákna
            new Thread(() -> {
                startButton.setEnabled(false);  // Deaktivace tlačítka "Spustit" během běhu algoritmu
                String algo = (String) algorithmBox.getSelectedItem();  // Výběr algoritmu

                // Podmínky pro spuštění vybraného algoritmu
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
                } else if ("Heap Sort".equals(algo)) {
                    HeapSort.sort(sortPanel, delay);
                } else if ("Radix Sort".equals(algo)) {
                    RadixSort.sort(sortPanel, delay);
                }

                // Po dokončení třídění
                sortPanel.clearHighlight();  // Vyčištění zvýraznění v panelu
                sortPanel.repaint();  // Obnovení zobrazení panelu
                startButton.setEnabled(true);  // Znovu povolit tlačítko "Spustit"
                long endTime = System.nanoTime();  // Uložení koncového času
                double duration = (endTime - startTime) / 1_000_000_000.0;  // Výpočet doby běhu v sekundách a 1_000_000_000.0 aby to převedlo nanosekundy na seukundy
                timeLabel.setText(String.format("Doba běhu: %.3f s", duration));  // Zobrazení doby běhu
            }).start();  // Spuštění vlákna

        });

        setVisible(true);  // Nastavení viditelnosti okna
    }

    // Hlavní metoda pro spuštění aplikace
    public static void main(String[] args) {
        SwingUtilities.invokeLater(SortVisualizer::new);  // Spuštění GUI ve vlákně Swing
    }
}
