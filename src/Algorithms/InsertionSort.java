package Algorithms;

public class InsertionSort {
    public static void sort(SortPanel panel, int delay) {
        int[] arr = panel.getValues();
        panel.clearSorted(); // smažeme staré označení
        panel.resetStepCount(); // resetujeme počítadlo kroků

        try {
            for (int i = 1; i < arr.length; i++) {
                int key = arr[i];
                int j = i - 1;

                panel.highlight(i, i);
                panel.incrementStepCount(); // krok: výběr klíče
                Thread.sleep(delay);

                while (j >= 0 && arr[j] > key) {
                    panel.highlight(j, j + 1);
                    panel.incrementStepCount(); // krok: porovnání
                    Thread.sleep(delay);

                    arr[j + 1] = arr[j]; // posun hodnoty
                    panel.incrementStepCount(); // krok: přesun
                    panel.setValues(arr);
                    j--;

                    Thread.sleep(delay);
                }

                arr[j + 1] = key;
                panel.incrementStepCount(); // krok: vložení klíče
                panel.setValues(arr);

                panel.highlight(j + 1, i);
                Thread.sleep(delay);
            }

            for (int i = 0; i < arr.length; i++) {
                panel.markSorted(i);
                Thread.sleep(delay);
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
