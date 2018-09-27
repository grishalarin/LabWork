package Finder;

import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CopyOnWriteArrayList;

public class ThreadPool {

    private int threadCount;
    private List<Thread> runningThreads = new CopyOnWriteArrayList<>();
    private Queue<Runnable> queueThreads = new ConcurrentLinkedQueue<>();

    public ThreadPool(int threadCount) {
        this.threadCount = threadCount;
    }

    public Thread runThread(Runnable runnable) {
        Thread thread;
        if (runningThreads.size() < threadCount) {
            thread = new Thread(runnable);
            runningThreads.add(thread);
            thread.start();
        } else {
            queueThreads.add(runnable);
            while (true) {
                if (runningThreads.removeIf(t -> !t.isAlive())) {
                    thread = new Thread(queueThreads.poll());
                    runningThreads.add(thread);
                    thread.start();
                    break;
                }
            }
        }
        return thread;

    }
}
