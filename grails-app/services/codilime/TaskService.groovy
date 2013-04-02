package codilime

import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import org.springframework.transaction.annotation.Transactional

<<<<<<< HEAD
=======

/**
 * Task's logic - job scheduling & thread management
 */
>>>>>>> 67efca988f7ea719026e18f433548cb8a6baa655
class TaskService {
    final static int MAX_THREADS = 4;

    boolean transactional = false;

    private ExecutorService serv = Executors.newFixedThreadPool(MAX_THREADS)

<<<<<<< HEAD
=======
    /**
     * Function submiting a runnable to the pool
     */
>>>>>>> 67efca988f7ea719026e18f433548cb8a6baa655
    def scheduleJob(Runnable x) {
        serv.submit(x)
    }

<<<<<<< HEAD
=======
    /**
     * Function responsible for creating new thread instance and managing its states.
     */
>>>>>>> 67efca988f7ea719026e18f433548cb8a6baa655
    def threadStart(String name, Task t) throws ClassNotFoundException {
        try {
            def thr =  Thread.currentThread().contextClassLoader.loadClass(name)
            def run = { changeState(t,1); thr.newInstance().run(); removeTask(t); } as Runnable
            scheduleJob(run)
        }
        catch(ClassNotFoundException e) {
<<<<<<< HEAD
=======
            removeTask(t)
>>>>>>> 67efca988f7ea719026e18f433548cb8a6baa655
            throw new ClassNotFoundException()
        }
    }

<<<<<<< HEAD
=======
    /**
     * Function changing state of task t to value i
     */
>>>>>>> 67efca988f7ea719026e18f433548cb8a6baa655
    def changeState(Task t, int i) {
        t.withTransaction {
            t.state = i
            t.save()
        }
    }

<<<<<<< HEAD
=======
    /**
     * Remove task t
     * @param t - task to remove
     */
>>>>>>> 67efca988f7ea719026e18f433548cb8a6baa655
    def removeTask(Task t) {
        t.withTransaction {
            t.delete()
        }
    }
}
