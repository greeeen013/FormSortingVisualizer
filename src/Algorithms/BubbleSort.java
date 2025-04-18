package Algorithms;


public class BubbleSort {
    public static void sort(SortPanel panel, int delay) {
        int[] arr = panel.getValues();

        try {
            for (int i = 0; i < arr.length - 1; i++) {
                for (int j = 0; j < arr.length - i - 1; j++) {
                    panel.highlight(j, j + 1);
                    Thread.sleep(delay);

                    if (arr[j] > arr[j + 1]) {
                        int temp = arr[j];
                        arr[j] = arr[j + 1];
                        arr[j + 1] = temp;
                        panel.setValues(arr);
                    }

                    Thread.sleep(delay);
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
