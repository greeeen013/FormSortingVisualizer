package Algorithms;

public class HeapSort {
    public static void sort(SortPanel panel, int delay) {
        int[] arr = panel.getValues();
        int n = arr.length;
        panel.clearSorted();

        try {
            // Vytvoření max-heapu
            for (int i = n / 2 - 1; i >= 0; i--) {
                heapify(arr, n, i, panel, delay);
            }

            // Extrakce prvků z heapu
            for (int i = n - 1; i > 0; i--) {
                swap(arr, 0, i);
                panel.setValues(arr);
                panel.markSorted(i); // prvek na konci je hotový
                Thread.sleep(delay);

                heapify(arr, i, 0, panel, delay);
            }

            panel.markSorted(0); // poslední zbývající prvek

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void heapify(int[] arr, int n, int i, SortPanel panel, int delay) throws InterruptedException {
        int largest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        if (left < n && arr[left] > arr[largest]) {
            panel.highlight(largest, left);
            Thread.sleep(delay);
            largest = left;
        }

        if (right < n && arr[right] > arr[largest]) {
            panel.highlight(largest, right);
            Thread.sleep(delay);
            largest = right;
        }

        if (largest != i) {
            swap(arr, i, largest);
            panel.setValues(arr);
            Thread.sleep(delay);

            heapify(arr, n, largest, panel, delay);
        }
    }

    private static void swap(int[] arr, int i, int j) {
        if (i != j) {
            int tmp = arr[i];
            arr[i] = arr[j];
            arr[j] = tmp;
        }
    }
}