package Algorithms;

public class InsertionSort {
    public static void sort(SortPanel panel, int delay) {
        int[] arr = panel.getValues();
        panel.clearSorted(); // smažeme staré označení

        try {
            for (int i = 1; i < arr.length; i++) {
                int key = arr[i];
                int j = i - 1;

                panel.highlight(i, i);
                Thread.sleep(delay);

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

                panel.highlight(j + 1, i);
                Thread.sleep(delay);
            }

            // 🔧 Nově: označíme všechny jako hotové
            for (int i = 0; i < arr.length; i++) {
                panel.markSorted(i);
                Thread.sleep(delay); // můžeš zrychlit nebo vynechat, čistě na efekt
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

