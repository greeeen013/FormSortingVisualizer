package Algorithms;

public class QuickSort {
    public static void sort(SortPanel panel, int delay) {
        int[] arr = panel.getValues();
        panel.clearSorted();
        panel.resetStepCount(); // resetujeme počet kroků

        try {
            quickSort(arr, 0, arr.length - 1, panel, delay);

            // Označíme všechny jako hotové
            for (int i = 0; i < arr.length; i++) {
                panel.markSorted(i);
                Thread.sleep(2);
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void quickSort(int[] arr, int low, int high, SortPanel panel, int delay) throws InterruptedException {
        if (low < high) {
            int pivotIndex = partition(arr, low, high, panel, delay);

            quickSort(arr, low, pivotIndex - 1, panel, delay);
            quickSort(arr, pivotIndex + 1, high, panel, delay);
        } else if (low == high) {
            panel.markSorted(low);
        }
    }

    private static int partition(int[] arr, int low, int high, SortPanel panel, int delay) throws InterruptedException {
        int pivot = arr[high];
        int i = low - 1;

        for (int j = low; j < high; j++) {
            panel.highlight(j, high);
            panel.incrementStepCount(); // krok: porovnání s pivotem
            Thread.sleep(delay);

            if (arr[j] < pivot) {
                i++;
                swap(arr, i, j);
                panel.incrementStepCount(); // krok: swap
                panel.setValues(arr);
                Thread.sleep(delay);
            }
        }

        swap(arr, i + 1, high);
        panel.incrementStepCount(); // krok: finální swap s pivotem
        panel.setValues(arr);
        Thread.sleep(delay);

        panel.markSorted(i + 1);
        return i + 1;
    }

    private static void swap(int[] arr, int i, int j) {
        if (i != j) {
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
    }
}
