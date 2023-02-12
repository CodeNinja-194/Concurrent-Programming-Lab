// import java.util.concurrent.locks.Lock;
// import java.util.concurrent.locks.ReentrantLock;

class Bakery {
    static boolean[] flag;
    static int[] label;
    int n;

    public Bakery(int n) {
        this.n = n;
        flag = new boolean[n];
        label = new int[n];
        for (int i = 0; i < n; i++) {
            flag[i] = false;
            label[i] = 0;
        }
    }

    public void lock(int threadId) {
        flag[threadId] = true;
        int max = 0;
        for (int i = 0; i < n; i++) {
            max = Math.max(max, label[i]);
        }
        label[threadId] = max + 1;
        flag[threadId] = false;
        for (int i = 0; i < n; i++) {
            if (i == threadId)
                continue;
            while (flag[i]) {
                // busy wait
            }
            while (label[i] != 0 && (label[threadId] > label[i] || (label[threadId] == label[i] && threadId > i))) {
                // busy wait
            }
        }
    }

    public void unlock(int threadId) {
        label[threadId] = 0;
    }
}

public class BakeryTest {
    public static void main(String[] args) {
        Bakery lock = new Bakery(2);

        Thread t0 = new Thread(() -> {
            int id = 0;
            while (true) {
                lock.lock(id);
                // critical section
                System.out.println("Thread 0 in critical section");
                lock.unlock(id);
                // non-critical section
                System.out.println("Thread 0 out of critical section");
            }
        });

        Thread t1 = new Thread(() -> {
            int id = 1;
            while (true) {
                lock.lock(id);
                // critical section
                System.out.println("Thread 1 in critical section");
                lock.unlock(id);
                // non-critical section
                System.out.println("Thread 1 out of critical section");
            }
        });

        t0.start();
        t1.start();
    }
}
