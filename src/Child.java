public class Child extends Thread {
    @Override
    public void run() {
        for (int i = 0; i <= 100; i++) {
            System.out.print(i + " ");
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
