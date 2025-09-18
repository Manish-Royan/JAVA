## Critical Aspect: Thread Safety and Concurrency Issues
The shared nature of static variables presents the most significant challenge, particularly in multi-threaded environments.

### **Shared Mutable State**:
When a static variable is mutable (its value can be changed) and is accessed concurrently by multiple threads, it creates a "shared mutable state". This is the primary underlying cause of race conditions, where the correctness of the program becomes dependent on the unpredictable timing and interleaving of low-level operations by different threads.   

#### Demonstrating Shared Mutable Static State and Race Conditions
Below is a simple Java program where a mutable static variable (`counter`) is accessed and modified by two threads without any synchronization. You’ll see that, despite each thread performing the same number of increments, the final value of `counter` is often less than expected due to a race condition.

```java
public class RaceConditionDemo {
    // Shared mutable static state
    private static int counter = 0;

    public static void main(String[] args) throws InterruptedException {
        // Create two threads that each increment the counter 100,000 times
        Thread t1 = new Thread(new CounterIncrementer(), "T1");
        Thread t2 = new Thread(new CounterIncrementer(), "T2");

        t1.start();
        t2.start();

        // Wait for both threads to finish
        t1.join();
        t2.join();

        // We expect 2 * 100,000 = 200,000, but often get less
        System.out.println("Final counter value: " + counter);
    }

    static class CounterIncrementer implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 100_000; i++) {
                // Incrementing shared mutable state without synchronization
                counter++;
            }
            System.out.println(Thread.currentThread().getName() + " done.");
        }
    }
}
```
#### What’s Happening Under the Hood
- **Shared State**: `counter` is a single static field shared by both `T1` and `T2`.  
- **Race Condition**: Each `counter++` involves multiple CPU steps (read, increment, write). Without coordination, these steps can interleave:
  - Thread 1 reads `counter`  
  - Thread 2 reads the same value  
  - Both write back the incremented value once—effectively losing one update.  
- **Unpredictable Result**: The final print often shows a number lower than `200000`, proving that timing and interleaving broke correctness.


#### Observations
- Even though both threads do exactly 100,000 increments, missing updates happen because they’re racing over the same mutable static field.  
- This illustrates why **shared mutable state** is the root cause of many concurrency bugs.


### **Lack of Confinement**: 
Unlike local variables, which are inherently thread-confined to a single thread's stack, static variables are not automatically confined. Their global accessibility (within their defined visibility scope) to all threads makes them inherently risky for concurrency if they are mutable.   

#### A concrete demo showing how a mutable static variable is shared (and thus risky) across threads, whereas a local variable is confined to each thread’s stack:

```java
public class ConfinementDemo {
    // Mutable static: shared by ALL threads
    private static int staticCount = 0;

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(new Worker(), "T1");
        Thread t2 = new Thread(new Worker(), "T2");

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        // Expecting 2 * 100_000 = 200_000, but races on staticCount will likely make this wrong
        System.out.println("Final staticCount = " + staticCount);
    }

    static class Worker implements Runnable {
        @Override
        public void run() {
            // Local variable: confined to this thread
            int localCount = 0;

            // Both counters incremented same number of times…
            for (int i = 0; i < 100_000; i++) {
                staticCount++;   // shared mutable state → race condition
                localCount++;    // thread-confined, always correct
            }

            // Each thread prints its own localCount (always 100_000)
            System.out.printf(
                "[%s] localCount = %d%n",
                Thread.currentThread().getName(), localCount
            );
        }
    }
}
```

What this shows:

- **staticCount** lives in a single slot shared by **T1** and **T2**. Their unsynchronized `staticCount++` operations race, so the final value is unpredictably < 200 000.  
- **localCount** is allocated on each thread’s stack. Each thread has its own copy, so both print exactly 100 000—no races, no synchronization needed.

This contrast illustrates “**lack of confinement**”: mutable static fields are inherently exposed to every thread in that class-loader, whereas local variables are automatically thread-safe by being confined to a single thread’s stack.


### Visibility Issues: 
Changes made by one thread to a static variable are not guaranteed to be immediately visible to other threads unless a "happens-before" relationship is explicitly established. Without such a relationship, threads might operate on stale data, leading to logical errors that are difficult to diagnose.   

