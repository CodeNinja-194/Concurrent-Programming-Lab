// import java.util.concurrent.locks.*

class FilterLock 
{
    static int[] level;
    static int[] victim;
    int n;
    public FilterLock(int n) 
    {
        this.n = n;
        level = new int[n];
        victim = new int[n];
        for (int i = 0; i < n; i++) 
        {
            level[i] = 0;
        }
    }
    public void lock(int threadId) 
    {
        for (int i = 1; i < n; i++) 
        {
            level[threadId] = i;
            victim[i] = threadId;
            for (int k = 0; k < n; k++) 
            {
                if (k == threadId)
                    continue;
                while (level[k] >= i && victim[i] == threadId) 
                {
                    // busy wait
                }
            }
        }
    }
    public void unlock(int threadId) 
    {
        level[threadId] = 0;
    }
}
public class FilterLockTest {
    public static void main(String[] args) {
        FilterLock lock = new FilterLock(2);

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
