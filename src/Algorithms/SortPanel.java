package Algorithms;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

// Třída pro vykreslování a zobrazení grafu řadícího algoritmu
public class SortPanel extends JPanel {
    private int[] values; // Pole hodnot, které budeme vykreslovat (čísla, která budou vizualizována)
    private int activeIndex = -1; // Index aktuálně aktivního prvku (červený prvek, který se právě zpracovává)
    private int compareIndex = -1; // Index prvku, který je právě porovnáván s aktivním (žlutý prvek)
    private Set<Integer> sortedIndices = new HashSet<>(); // Sada, která uchovává indexy již seřazených prvků (zelené)

    private int sortedUntil = -1; // Pomocná proměnná pro označení, dokud kam byly prvky seřazeny (používáno k optimalizaci)

    // Metoda pro nastavení indexu do kterého byly prvky již seřazeny
    public void setSortedUntil(int index) {
        this.sortedUntil = index;
    }

    // Generování nového pole hodnot
    // Pokud je parametr 'ordered' true, pole bude seřazené (vždy o +1), pokud false, náhodně (o +0,2 nebo 1,8)
    public void generateArray(int size, boolean ordered) {
        values = new int[size];

        // Pokud má být pole seřazené
        if (ordered) {
            for (int i = 0; i < size; i++) {
                values[i] = (int) ((i + 1) * (getHeight() - 20) / (double) size);
            }

            // Zamíchání hodnot v poli, aby byly náhodné
            List<Integer> list = new ArrayList<>();
            for (int val : values) list.add(val); // Přidáme hodnoty do listu
            Collections.shuffle(list); // Zamícháme je
            for (int i = 0; i < size; i++) values[i] = list.get(i); // Vrátíme zpět zamíchané hodnoty do pole

        } else {
            Random rand = new Random();
            // Pokud má být pole náhodné
            for (int i = 0; i < size; i++) {
                values[i] = rand.nextInt(getHeight() - 20) + 10; // Generování náhodných hodnot mezi 10 a výškou panelu
            }
        }
        setBackground(Color.BLACK); // Nastavení pozadí na černou
        repaint(); // Překreslení panelu s novými hodnotami
    }

    // Getter pro hodnoty pole
    public int[] getValues() {
        return values;
    }

    // Setter pro hodnoty pole
    public void setValues(int[] newValues) {
        this.values = newValues;
        repaint(); // Překreslení panelu s novými hodnotami
    }

    // Zvýraznění dvou prvků - jeden je aktivní (red), druhý je porovnávaný (yellow)
    public void highlight(int i, int j) {
        this.activeIndex = i;
        this.compareIndex = j;
        repaint(); // Překreslení panelu s novými zvýrazněnými hodnotami
    }

    // Odstranění zvýraznění
    public void clearHighlight() {
        this.activeIndex = -1;
        this.compareIndex = -1;
    }

    // Označení prvku jako seřazeného (zelený)
    public void markSorted(int index) {
        sortedIndices.add(index); // Přidání indexu do seznamu seřazených
        repaint(); // Překreslení panelu
    }

    // Vyčištění seznamu seřazených prvků
    public void clearSorted() {
        sortedIndices.clear(); // Odstranění všech seřazených prvků
    }

    // Metoda pro vykreslení celého panelu
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (values == null) return; // Pokud nejsou hodnoty, neprovádí se vykreslování

        int panelWidth = getWidth(); // Šířka panelu
        int count = values.length; // Počet hodnot (prvků)
        double barWidth = (double) panelWidth / count; // Šířka každého sloupce na základě počtu prvků

        // Pro každý prvek vykreslíme odpovídající sloupec
        for (int i = 0; i < count; i++) {
            int x = (int) Math.round(i * barWidth); // Výpočet X pozice pro aktuální sloupec
            int nextX = (int) Math.round((i + 1) * barWidth); // Výpočet X pozice pro další sloupec
            int width = nextX - x - 1; // Výpočet šířky sloupce, aby měl minimálně šířku 1 pixel
            if (width < 1) width = 1; // Oprava pro případ, kdy je šířka 0

            // Určujeme barvu sloupce podle toho, zda je již seřazený, aktivní nebo porovnávaný
            if (sortedIndices.contains(i)) {
                g.setColor(Color.GREEN); // ✅ Seřazený prvek
            } else if (i == activeIndex) {
                g.setColor(Color.RED); // 🔴 Aktivní prvek
            } else if (i == compareIndex) {
                g.setColor(Color.YELLOW); // 🟡 Porovnávaný prvek
            } else
                g.setColor(Color.BLUE); // 🔵 Nezpracovaný / výchozí prvek

            int barHeight = values[i]; // Výška sloupce odpovídá hodnotě v poli
            g.fillRect(x, getHeight() - barHeight, width, barHeight); // Vykreslení sloupce na panelu
        }
    }
}
