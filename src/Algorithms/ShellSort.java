package Algorithms;

public class ShellSort {
    public static void sort(SortPanel panel, int delay) {
        int[] arr = panel.getValues();
        panel.clearSorted();

        try {
            int n = arr.length;

            for (int gap = n / 2; gap > 0; gap /= 2) {
                for (int i = gap; i < n; i++) {
                    int temp = arr[i];
                    int j = i;

                    while (j >= gap && arr[j - gap] > temp) {
                        panel.highlight(j, j - gap);
                        Thread.sleep(delay);

                        arr[j] = arr[j - gap];
                        j -= gap;

                        panel.setValues(arr);
                        Thread.sleep(delay);
                    }

                    arr[j] = temp;
                    panel.setValues(arr);
                    panel.highlight(j, i);
                    Thread.sleep(delay);
                }
            }

            // Označíme všechny jako hotové
            for (int i = 0; i < arr.length; i++) {
                panel.markSorted(i);
                Thread.sleep(2);
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}