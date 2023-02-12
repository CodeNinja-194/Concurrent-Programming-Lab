import java.util.concurrent.ConcurrentLinkedQueue;

class ConcurrentQueue<E> {
    private ConcurrentLinkedQueue<E> queue;

    public ConcurrentQueue() {
        queue = new ConcurrentLinkedQueue<>();
    }

    public void enqueue(E item) {
        queue.add(item);
    }
    
    public E dequeue() {
        return queue.poll();
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }

    public int size() {
        return queue.size();
    }
}

public class ConcurrentQueueTest {
    public static void main(String[] args) {
        ConcurrentQueue<Integer> queue = new ConcurrentQueue<>();

        Thread producer = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                queue.enqueue(i);
                System.out.println("Produced: " + i);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread consumer = new Thread(() -> {
            while (true) {
                if (!queue.isEmpty()) {
                    int item = queue.dequeue();
                    System.out.println("Consumed: " + item);
                }
            }
        });

        producer.start();
        consumer.start();
    }
}
