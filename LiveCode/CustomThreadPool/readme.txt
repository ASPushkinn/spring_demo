shortcut  250617

Original  task :


import java.util.LinkedList;
import java.util.List;

//Реализуй собственный пул потоков.

//В качестве аргументов конструктора пулу передается его емкость (количество рабочих потоков).

//Как только пул создан, он сразу инициализирует и запускает потоки.

//Внутри пула очередь задач на исполнение организуется через LinkedList<Runnable>.

//При выполнении у пула потоков метода execute(Runnabler), указанная задача должна попасть
//в очередь исполнения, и как только появится свободный поток – должна быть выполнена.

//Также необходимо реализовать метод shutdown(), после выполнения которого новые задачи больше не принимаются
//пулом (при попытке добавить задачу можно бросать IllegalStateException),
//и все потоки для которых больше нет задач завершают свою работу.

//Дополнительно можно добавить метод awaitTermination() без таймаута,
//работающий аналогично стандартным пулам потоков на Java


public class CustomThreadPool {

    //TODO

    public void execute(Runnable task) {
        //TODO
    }

    public void shutdown() {
        //TODO
    }

    public void awaitTermination() {
       //TODO
    }

    private class Worker extends Thread {

        @Override
        public void run() {

        }
    }

// Тестовый пример
    public static void main(String[] args) {
        CustomThreadPool pool = new CustomThreadPool(3);

        for (int i = 0; i < 10; i++) {
            int taskId = i;
            pool.execute(() -> {
                System.out.println(Thread.currentThread().getName() + " executing task " + taskId);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException ignored) {
                }
            });
        }

        pool.shutdown();
        pool.awaitTermination();
        System.out.println("All tasks completed, pool terminated");
    }

    //Output:

/* Worker-1 executing task 1
Worker-0 executing task 0
Worker-2 executing task 2
Worker-1 executing task 3
Worker-0 executing task 4
Worker-2 executing task 5
Worker-0 executing task 6
Worker-2 executing task 7
Worker-1 executing task 8
Worker-0 executing task 9
All tasks completed, pool terminated */



}