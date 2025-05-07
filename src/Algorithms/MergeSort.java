package Algorithms;

public class MergeSort {
    public static void sort(SortPanel panel, int delay) {
        int[] arr = panel.getValues();
        panel.clearSorted();
        panel.resetStepCount(); // reset kroků

        try {
            mergeSort(arr, 0, arr.length - 1, panel, delay);

            // Označíme všechny jako hotové
            for (int i = 0; i < arr.length; i++) {
                panel.markSorted(i);
                Thread.sleep(2);
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void mergeSort(int[] arr, int left, int right, SortPanel panel, int delay) throws InterruptedException {
        if (left < right) {
            int mid = (left + right) / 2;

            mergeSort(arr, left, mid, panel, delay);
            mergeSort(arr, mid + 1, right, panel, delay);

            merge(arr, left, mid, right, panel, delay);
        }
    }

    private static void merge(int[] arr, int left, int mid, int right, SortPanel panel, int delay) throws InterruptedException {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        int[] L = new int[n1];
        int[] R = new int[n2];

        System.arraycopy(arr, left, L, 0, n1);
        System.arraycopy(arr, mid + 1, R, 0, n2);

        int i = 0, j = 0;
        int k = left;

        while (i < n1 && j < n2) {
            panel.highlight(left + i, mid + 1 + j);
            panel.incrementStepCount(); // krok: porovnání
            Thread.sleep(delay);

            if (L[i] <= R[j]) {
                arr[k] = L[i];
                i++;
            } else {
                arr[k] = R[j];
                j++;
            }
            panel.incrementStepCount(); // krok: kopírování do hlavního pole
            panel.setValues(arr);
            k++;
            Thread.sleep(delay);
        }

        while (i < n1) {
            arr[k] = L[i];
            panel.incrementStepCount(); // krok: zbytek zleva
            i++;
            k++;
            panel.setValues(arr);
            Thread.sleep(delay);
        }

        while (j < n2) {
            arr[k] = R[j];
            panel.incrementStepCount(); // krok: zbytek zprava
            j++;
            k++;
            panel.setValues(arr);
            Thread.sleep(delay);
        }
    }
}
