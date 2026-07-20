import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;

class DiningPhilosophers {

    private final ReentrantLock[] forks;
    private final Semaphore seats;

    public DiningPhilosophers() {
        forks = new ReentrantLock[5];

        for (int i = 0; i < 5; i++) {
            forks[i] = new ReentrantLock();
        }

        // Allow only 4 philosophers to compete for forks at once.
        seats = new Semaphore(4);
    }

    public void wantsToEat(
            int philosopher,
            Runnable pickLeftFork,
            Runnable pickRightFork,
            Runnable eat,
            Runnable putLeftFork,
            Runnable putRightFork) throws InterruptedException {

        int leftFork = philosopher;
        int rightFork = (philosopher + 1) % 5;

        seats.acquire();

        forks[leftFork].lock();
        forks[rightFork].lock();

        try {
            pickLeftFork.run();
            pickRightFork.run();

            eat.run();

            putRightFork.run();
            putLeftFork.run();
        } finally {
            forks[rightFork].unlock();
            forks[leftFork].unlock();
            seats.release();
        }
    }
}