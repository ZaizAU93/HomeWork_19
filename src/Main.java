import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        int[] array = {9, 5, 2, 7, 1, 6, 3, 8, 4, 87, 15, 14, 7};

        Thread insertionSortThread = new Thread(new InsertionSort(array));
        Thread selectionSortThread = new Thread(new SelectionSort(array));
        Thread bubbleSortThread = new Thread(new BubbleSort(array));

        insertionSortThread.start();
        selectionSortThread.start();
        bubbleSortThread.start();

        try {
            insertionSortThread.join();
            selectionSortThread.join();
            bubbleSortThread.join();

            System.out.println("Insertion Sort: " + Arrays.toString(array));
            System.out.println("Selection Sort: " + Arrays.toString(array));
            System.out.println("Bubble Sort: " + Arrays.toString(array));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    static class InsertionSort implements Runnable {
        private int[] array;

        public InsertionSort(int[] array) {
            this.array = array;
        }

        @Override
        public void run() {
            int n = array.length;
            for (int i = 1; i < n; ++i) {
                int key = array[i];
                int j = i - 1;

                while (j >= 0 && array[j] > key) {
                    array[j + 1] = array[j];
                    j = j - 1;
                }
                array[j + 1] = key;
            }
        }
    }

    static class SelectionSort implements Runnable {
        private int[] array;

        public SelectionSort(int[] array) {
            this.array = array;
        }

        @Override
        public void run() {
            int n = array.length;

            for (int i = 0; i < n-1; i++) {
                int minIdx = i;
                for (int j = i+1; j < n; j++)
                    if (array[j] < array[minIdx])
                        minIdx = j;

                int temp = array[minIdx];
                array[minIdx] = array[i];
                array[i] = temp;
            }
        }
    }

    static class BubbleSort implements Runnable {
        private int[] array;

        public BubbleSort(int[] array) {
            this.array = array;
        }

        @Override
        public void run() {
            int n = array.length;
            for (int i = 0; i < n-1; i++) {
                for (int j = 0; j < n-i-1; j++) {
                    if (array[j] > array[j+1]) {
                        int temp = array[j];
                        array[j] = array[j+1];
                        array[j+1] = temp;
                    }
                }
            }
        }
    }
}
