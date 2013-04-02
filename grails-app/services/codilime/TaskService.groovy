package codilime

import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import org.springframework.transaction.annotation.Transactional


/**
 * Task's logic - job scheduling & thread management
 */
class TaskService {
    final static int MAX_THREADS = 4

    boolean transactional = false

    private ExecutorService serv = Executors.newFixedThreadPool(MAX_THREADS)

    /**
     * Function submiting a runnable to the pool
     */
    def scheduleJob(Runnable x) {
        serv.submit(x)
    }

    /**
     * Function responsible for creating new thread instance and managing its states.
     */
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

    /**
     * Function changing state of task t to value i
     */
    def changeState(Task t, int i) {
        t.withTransaction {
            t.state = i
            t.save()
        }
    }

    /**
     * Remove task t
     * @param t - task to remove
     */
    def removeTask(Task t) {
        t.withTransaction {
            t.delete()
        }
    }
}
