package Algorithms;

public class SelectionSort {
    public static void sort(SortPanel panel, int delay) {
        int[] arr = panel.getValues();

        try {
            for (int i = 0; i < arr.length - 1; i++) {
                int minIndex = i;

                for (int j = i + 1; j < arr.length; j++) {
                    panel.highlight(minIndex, j);
                    Thread.sleep(delay);

                    if (arr[j] < arr[minIndex]) {
                        minIndex = j;
                    }
                }

                if (minIndex != i) {
                    int temp = arr[i];
                    arr[i] = arr[minIndex];
                    arr[minIndex] = temp;
                    panel.setValues(arr);
                    Thread.sleep(delay);
                }
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
