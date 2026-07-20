import java.util.concurrent.Semaphore;
import java.util.function.IntConsumer;

class ZeroEvenOdd {

    private final int n;

    private final Semaphore zeroTurn = new Semaphore(1);
    private final Semaphore oddTurn = new Semaphore(0);
    private final Semaphore evenTurn = new Semaphore(0);

    public ZeroEvenOdd(int n) {
        this.n = n;
    }

    public void zero(IntConsumer printNumber) throws InterruptedException {
        for (int num = 1; num <= n; num++) {
            zeroTurn.acquire();

            printNumber.accept(0);

            if ((num & 1) == 1) {
                oddTurn.release();
            } else {
                evenTurn.release();
            }
        }
    }

    public void odd(IntConsumer printNumber) throws InterruptedException {
        for (int num = 1; num <= n; num += 2) {
            oddTurn.acquire();

            printNumber.accept(num);

            zeroTurn.release();
        }
    }

    public void even(IntConsumer printNumber) throws InterruptedException {
        for (int num = 2; num <= n; num += 2) {
            evenTurn.acquire();

            printNumber.accept(num);

            zeroTurn.release();
        }
    }
}