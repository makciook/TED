package codilime

import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import org.springframework.transaction.annotation.Transactional

class TaskService {
    final static int MAX_THREADS = 4;

    boolean transactional = false;

    private ExecutorService serv = Executors.newFixedThreadPool(4)// = Executors.newFixedThreadPool(MAX_THREADS);


    /*def countScheduledTasks() {
        return Task.countByState(0);
    }

    def countRunningTasks() {
        return Task.countByState(1);
    }*/ //moved to Task.groovy

    def scheduleJob(Runnable x) {
        serv.submit(x);
    }

    def threadStart(String name, Task t) throws ClassNotFoundException {
        try {
            def run = { changeState(t,1); println new Date(); Thread.currentThread().contextClassLoader.loadClass(name).newInstance().run(); println new Date(); removeTask(t); } as Runnable
            scheduleJob(run);
        }
        catch(ClassNotFoundException e) {
            throw new ClassNotFoundException();
        }
    }

    def changeState(Task t, int i) {
        t.withTransaction {
            t.state = i;
            t.save()
        }
    }

    def removeTask(Task t) {
        t.withTransaction {
            t.delete();
            //t.save()
        }
    }
}
