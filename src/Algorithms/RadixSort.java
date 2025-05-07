package Algorithms;

import java.util.*;

public class RadixSort {
    public static void sort(SortPanel panel, int delay) {
        int[] arr = panel.getValues();
        panel.clearSorted();
        panel.resetStepCount(); // resetujeme počítadlo kroků

        try {
            int max = getMax(arr);

            for (int exp = 1; max / exp > 0; exp *= 10) {
                countingSortByDigit(arr, exp, panel, delay);
            }

            // Označíme vše jako hotové
            for (int i = 0; i < arr.length; i++) {
                panel.markSorted(i);
                Thread.sleep(delay);
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
            panel.incrementStepCount(); // krok: výpočet číslice
        }

        // Kumulativní pozice
        for (int i = 1; i < 10; i++) {
            count[i] += count[i - 1];
            panel.incrementStepCount(); // krok: kumulace
        }

        // Vkládání do výstupu
        for (int i = n - 1; i >= 0; i--) {
            int digit = (arr[i] / exp) % 10;
            output[count[digit] - 1] = arr[i];
            count[digit]--;
            panel.incrementStepCount(); // krok: vkládání do výstupu
        }

        // Zpět do původního pole
        for (int i = 0; i < n; i++) {
            arr[i] = output[i];
            panel.setValues(arr);
            panel.highlight(i, i);
            panel.incrementStepCount(); // krok: přepis zpět do pole
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
