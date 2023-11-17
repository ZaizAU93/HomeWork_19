public class ProizPokup {
    public static void main(String[] args) {
        Magazin magazin = new Magazin();

        Thread produсerThread = new Thread(new Proizvoditel(magazin));
        Thread buyerThread = new Thread(new Pokupatel(magazin));

        produсerThread.start();
        buyerThread.start();
    }
}

class Magazin {
    private int count = 0;

    public synchronized void produce() {
        while (count >= 3) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        count++;
        System.out.println("Производитель произвел 1 продукт. Всего товаров в магазине: " + count);
        notify();
    }

    public synchronized void buy() {
        while (count < 1) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        count--;
        System.out.println("Покупатель купил 1 продукт. Всего товаров в магазине: " + count);
        notify();
    }
}

class Proizvoditel implements Runnable {
    private Magazin magazin;

    public Proizvoditel(Magazin magazin) {
        this.magazin = magazin;
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            magazin.produce();
        }
    }
}

class Pokupatel implements Runnable {
    private Magazin magazin;

    public Pokupatel(Magazin magazin) {
        this.magazin = magazin;
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            magazin.buy();
        }
    }
}
