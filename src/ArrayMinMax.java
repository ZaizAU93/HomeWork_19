import java.util.Scanner;

public class ArrayMinMax {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("������� ������ �������: ");
        int size = scanner.nextInt();

        int[] array = new int[size];
        System.out.println("������� �������� �������:");
        for (int i = 0; i < size; i++) {
            array[i] = scanner.nextInt();
        }

        MinThread minThread = new MinThread(array);
        MaxThread maxThread = new MaxThread(array);

        // ������ �������
        minThread.start();
        maxThread.start();

        try {
            minThread.join();
            maxThread.join();

            int min = minThread.getResult();
            int max = maxThread.getResult();

            System.out.println("�������: " + min);
            System.out.println("��������: " + max);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class MinThread extends Thread {
    private int[] array;
    private int min;

    public MinThread(int[] array) {
        this.array = array;
    }

    @Override
    public void run() {
        min = Integer.MAX_VALUE;
        for (int i = 0; i < array.length; i++) {
            if (array[i] < min) {
                min = array[i];
            }
        }
    }

    public int getResult() {
        return min;
    }
}

class MaxThread extends Thread {
    private int[] array;
    private int max;

    public MaxThread(int[] array) {
        this.array = array;
    }

    @Override
    public void run() {
        max = Integer.MIN_VALUE;
        for (int i = 0; i < array.length; i++) {
            if (array[i] > max) {
                max = array[i];
            }
        }
    }

    public int getResult() {
        return max;
    }
}