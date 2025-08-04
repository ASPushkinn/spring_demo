import java.util.LinkedList;
import java.util.List;

public class CustomThreadPool {

    private final LinkedList<Runnable> taskQueue;
    private final int poolSize;
    private final List<Worker> workerPool;
    private volatile boolean isShutdown = false;

    public CustomThreadPool(int poolSize) {

        this.taskQueue = new LinkedList<>();
        this.poolSize = poolSize;
        this.workerPool = new LinkedList<>();


        for (int i = 0; i < poolSize; i++) {
            Worker worker = new Worker("Worker-" + i);
            worker.start();
            workerPool.add(worker);
        }

    }

    public void execute(Runnable task) {
        if (this.isShutdown) throw
                new IllegalStateException("ThreadPool is stopped");

        synchronized (taskQueue) {
            taskQueue.addLast(task);
            taskQueue.notify();
        }
    }

    public synchronized void shutdown() {
        isShutdown = true;

        for (Worker worker : workerPool) {
            worker.interrupt();
        }
    }


    public boolean awaitTermination() {

        long startTime = System.currentTimeMillis();
        while (System.currentTimeMillis() - startTime <= 5 * 1000) {
            if (taskQueue.isEmpty()) return true;
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    private class Worker extends Thread {
        public Worker(String name) {
            super(name);
        }

        @Override
        public void run() {
            Runnable runnableTask = null;

            while (true) {
                synchronized (taskQueue) {
                    if (isShutdown && taskQueue.isEmpty()) return;

                    if (taskQueue.isEmpty()) {
                        try {
                            taskQueue.wait();
                        } catch (InterruptedException e) {
                            //log or otherwise report exception,
                            //but keep pool thread alive.
                            //Thread.currentThread().interrupt();
                            System.out.println("interrupted");
                        }
                    }

                    runnableTask = taskQueue.pollFirst(); //return null if empty
                }
                if (runnableTask != null) runnableTask.run();
            }
        }
    }


    // Тестовый пример
    public static void main(String[] args) throws InterruptedException {
        CustomThreadPool pool = new CustomThreadPool(2);

        for (int i = 0; i < 10; i++) {
            int taskId = i;
            pool.execute(() -> {
                System.out.println(Thread.currentThread().getName() + " executing task " + taskId);
                try {
                    //Thread.sleep(3000); //await termination  too long
                    Thread.sleep(1000);
                } catch (InterruptedException ignored) {
                }
            });
        }
        //Thread.sleep(7000); //task queue is empty goto wait mode
        pool.shutdown();
/*
        // no more  shutdown is on
        pool.execute(() -> {
            System.out.println(Thread.currentThread().getName() + " executing task " + 11);
        });
*/
        if (pool.awaitTermination())
            System.out.println(pool.taskQueue.size() + " All tasks completed, pool terminated");
        else {
            System.out.println(pool.taskQueue.size() + " Too long ...");
            System.exit(-1);
        }
    }
}