import java.util.concurrent.Semaphore;
import java.util.function.IntConsumer;

class FizzBuzz {
    private int n;
    private int current = 1;

    private Semaphore semNum = new Semaphore(1);
    private Semaphore semFizz = new Semaphore(0);
    private Semaphore semBuzz = new Semaphore(0);
    private Semaphore semFizzBuzz = new Semaphore(0);

    public FizzBuzz(int n) {
        this.n = n;
    }

    private void releaseNext() {
        current++;

        if (current > n) {
            semNum.release();
            semFizz.release();
            semBuzz.release();
            semFizzBuzz.release();
            return;
        }

        if (current % 15 == 0) {
            semFizzBuzz.release();
        } else if (current % 3 == 0) {
            semFizz.release();
        } else if (current % 5 == 0) {
            semBuzz.release();
        } else {
            semNum.release();
        }
    }

    public void fizz(Runnable printFizz) throws InterruptedException {
        while (true) {
            semFizz.acquire();

            if (current > n) {
                return;
            }

            printFizz.run();
            releaseNext();
        }
    }

    public void buzz(Runnable printBuzz) throws InterruptedException {
        while (true) {
            semBuzz.acquire();

            if (current > n) {
                return;
            }

            printBuzz.run();
            releaseNext();
        }
    }

    public void fizzbuzz(Runnable printFizzBuzz) throws InterruptedException {
        while (true) {
            semFizzBuzz.acquire();

            if (current > n) {
                return;
            }

            printFizzBuzz.run();
            releaseNext();
        }
    }

    public void number(IntConsumer printNumber) throws InterruptedException {
        while (true) {
            semNum.acquire();

            if (current > n) {
                return;
            }

            printNumber.accept(current);
            releaseNext();
        }
    }
}