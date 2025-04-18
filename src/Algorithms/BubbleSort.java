package Algorithms;


public class BubbleSort {
    public static void sort(SortPanel panel, int delay) {
        int[] arr = panel.getValues();
        panel.clearSorted(); // smaže předchozí značky

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

                panel.markSorted(arr.length - i - 1); // označí jako hotový
            }

            panel.markSorted(0); // první prvek je nakonec taky hotový

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

