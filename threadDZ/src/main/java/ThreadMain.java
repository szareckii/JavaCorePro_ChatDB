public class ThreadMain {

    public volatile char currentLetter = 'A';
    private final int COUNT = 5;
    Object monitor = new Object();

    public static void main(String[] args) {
        ThreadMain threadMain = new ThreadMain();

        Thread t1 = new Thread(() -> {
            threadMain.printA();
        });

        Thread t2 = new Thread(() -> {
            threadMain.printB();
        });

        Thread t3 = new Thread(() -> {
            threadMain.printC();
        });

        t1.start();
        t2.start();
        t3.start();
    }

    private void printA() {
        synchronized (monitor) {
            try {
                for (int i = 0; i < COUNT; i++) {
                    while (currentLetter != 'A') {
                        monitor.wait();
                    }
                    System.out.print("A");
                    currentLetter = 'B';
                    monitor.notifyAll();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void printB() {
        synchronized (monitor) {
            try {
                for (int i = 0; i < COUNT; i++) {
                    while (currentLetter != 'B') {
                        monitor.wait();
                    }
                    System.out.print("B");
                    currentLetter = 'C';
                    monitor.notifyAll();
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void printC() {
        synchronized (monitor) {
            try {
                for (int i = 0; i < COUNT; i++) {
                    while (currentLetter != 'C') {
                        monitor.wait();
                    }
                    System.out.print("C");
                    currentLetter = 'A';
                    monitor.notifyAll();
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
