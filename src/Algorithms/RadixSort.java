package Algorithms;

import java.util.*;

public class RadixSort {
    public static void sort(SortPanel panel, int delay) {
        int[] arr = panel.getValues();
        panel.clearSorted();

        try {
            int max = getMax(arr);

            // Pro každé místo (1s, 10s, 100s, ...)
            for (int exp = 1; max / exp > 0; exp *= 10) {
                countingSortByDigit(arr, exp, panel, delay);
            }

            // Označíme vše jako hotové
            for (int i = 0; i < arr.length; i++) {
                panel.markSorted(i);
                Thread.sleep(2);
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void countingSortByDigit(int[] arr, int exp, SortPanel panel, int delay) throws InterruptedException {
        int n = arr.length;
        int[] output = new int[n];
        int[] count = new int[10];

        // Počítání výskytů
        for (int i = 0; i < n; i++) {
            int digit = (arr[i] / exp) % 10;
            count[digit]++;
        }

        // Kumulativní pozice
        for (int i = 1; i < 10; i++) {
            count[i] += count[i - 1];
        }

        // Vkládání do výstupu
        for (int i = n - 1; i >= 0; i--) {
            int digit = (arr[i] / exp) % 10;
            output[count[digit] - 1] = arr[i];
            count[digit]--;
        }

        // Zpět do původního pole
        for (int i = 0; i < n; i++) {
            arr[i] = output[i];
            panel.setValues(arr);
            panel.highlight(i, i);
            Thread.sleep(delay);
        }
    }

    private static int getMax(int[] arr) {
        int max = arr[0];
        for (int j : arr) {
            if (j > max)
                max = j;
        }
        return max;
    }
}
