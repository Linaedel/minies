package linaedel.minies;

import java.util.concurrent.locks.ReentrantLock;

public class Multithreading {
    public static void main(String[] args) throws InterruptedException {
        SharedPrinter sharedPrinter = new SharedPrinter();
        MyThread t1 = new MyThread("t1", sharedPrinter);
        MyThread t2 = new MyThread("t2", sharedPrinter);
        t1.start();
        t2.start();
        Thread.sleep(5L);
        t1.interrupt();
        t2.interrupt();
    }

    static class SharedPrinter {
        private final ReentrantLock lock = new ReentrantLock();
        private String takenBy = "";

        public ReentrantLock getLock() {
            return lock;
        }

        public String getTakenBy() {
            return takenBy;
        }

        public void setTakenBy(String takenBy) {
            this.takenBy = takenBy;
        }
    }

    static class MyThread extends Thread {
        private String name;
        private SharedPrinter sharedPrinter;
        private boolean increment = true;
        int currentNum = 0;

        public MyThread(String name, SharedPrinter sharedPrinter) {
            this.name = name;
            this.sharedPrinter = sharedPrinter;
        }

        @Override
        public void run() {
            boolean goOn = true;
            while (goOn) {
                sharedPrinter.getLock().lock();
                if (!sharedPrinter.getTakenBy().equals(this.name)) {
                    sharedPrinter.setTakenBy(this.name);
                    this.currentNum = nextValue(this.currentNum);
                    System.out.println(name + ": " + this.currentNum);
                }
                sharedPrinter.getLock().unlock();
                if (this.isInterrupted()) {
                    goOn = false;
                }
            }

        }

        private int nextValue( int iterator) {
            if (iterator == 1) {
                this.increment = true;
            } else if (iterator == 10) {
                this.increment = false;
            }
            return this.increment ? iterator + 1 : iterator - 1;
        }
    }
}
