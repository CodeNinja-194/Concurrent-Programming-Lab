1 public class TTASLock implements Lock {
2 AtomicBoolean state = new AtomicBoolean(false);
3 public void lock() {
4 while (true) {
5 while (state.get()) {};
6 if (!state.getAndSet(true))
7 return;
8 }
9 }
10 public void unlock() {
11 state.set(false);
12 }
13 }