package codilime

import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import org.springframework.transaction.annotation.Transactional

class TaskService {
    final static int MAX_THREADS = 4;

    boolean transactional = true;

    private ExecutorService serv = Executors.newFixedThreadPool(MAX_THREADS)

    def scheduleJob(Runnable x) {
        serv.submit(x)
    }

    def threadStart(String name, Task t) throws ClassNotFoundException {
        try {
            def thr =  Thread.currentThread().contextClassLoader.loadClass(name)
            def run = { changeState(t,1); thr.newInstance().run(); removeTask(t); } as Runnable
            scheduleJob(run)
        }
        catch(ClassNotFoundException e) {
            removeTask(t)
            throw new ClassNotFoundException()
        }
    }

    def changeState(Task t, int i) {
        t.withTransaction {
            t.state = i
            t.save()
        }
    }

    def removeTask(Task t) {
        t.withTransaction {
            t.delete()
        }
    }
}
