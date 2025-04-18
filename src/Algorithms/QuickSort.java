package Algorithms;

public class QuickSort {
    public static void sort(SortPanel panel, int delay) {
        int[] arr = panel.getValues();
        panel.clearSorted(); // vymaže dřívější označení

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

            // Rekurzivní volání pro levou a pravou část
            quickSort(arr, low, pivotIndex - 1, panel, delay);
            quickSort(arr, pivotIndex + 1, high, panel, delay);
        } else if (low == high) {
            // Pokud už zbývá jen jeden prvek, je hotový
            panel.markSorted(low);
        }
    }

    private static int partition(int[] arr, int low, int high, SortPanel panel, int delay) throws InterruptedException {
        int pivot = arr[high];
        int i = low - 1;

        for (int j = low; j < high; j++) {
            panel.highlight(j, high); // porovnáváme s pivotem
            Thread.sleep(delay);

            if (arr[j] < pivot) {
                i++;
                swap(arr, i, j);
                panel.setValues(arr);
                Thread.sleep(delay);
            }
        }

        swap(arr, i + 1, high);
        panel.setValues(arr);
        Thread.sleep(delay);

        // Označíme pivot jako hotový
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
