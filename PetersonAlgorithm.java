class PetersonAlgorithm 
{
    static int turn = 0;
    static boolean[] interested = new boolean[2];
    static Object lock = new Object();
    // interested[0] = false;
    // interested[1] = false;

    static void lock(int i) 
    {
        // System.out.println(1);
        interested[i] = true;
        turn = i;
        // System.out.println(2);
        // System.out.println(turn + " " + i);
        while (turn == (1-i) && interested[1 - i]) 
        { 
            // System.out.println(i);
        }
        // System.out.println(3);
    }

    static void unlock(int i) 
    {
        interested[i] = false;
    }

    static class Thread extends java.lang.Thread 
    {
        int i;
        public Thread(int i) {
            this.i = i;
        }

        public void run() {
            while (true) {
                lock(i);
                // critical section
                System.out.println("Hurray1 " + i);
                unlock(i);
                // System.out.println("Hurray2 " + i);

                // rest of thread code
            }
        }
    }

    public static void main(String[] args) {
        Thread threadA = new Thread(0);
        Thread threadB = new Thread(1);
        threadA.start();

        threadB.start();
    }
}