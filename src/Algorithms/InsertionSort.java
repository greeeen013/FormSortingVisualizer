package Algorithms;

public class InsertionSort {
    public static void sort(SortPanel panel, int delay) {
        int[] arr = panel.getValues();

        try {
            for (int i = 1; i < arr.length; i++) {
                int key = arr[i];
                int j = i - 1;

                while (j >= 0 && arr[j] > key) {
                    panel.highlight(j, j + 1);
                    Thread.sleep(delay);

                    arr[j + 1] = arr[j];
                    panel.setValues(arr);
                    j--;

                    Thread.sleep(delay);
                }

                arr[j + 1] = key;
                panel.setValues(arr);
                panel.highlight(j + 1, i); // vizuálně zvýrazníme, kam se to "vloží"
                Thread.sleep(delay);
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
