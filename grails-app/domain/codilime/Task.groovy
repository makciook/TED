package codilime

<<<<<<< HEAD
=======
/**
 * Main class representing a single Task in grails db.
 */
>>>>>>> 67efca988f7ea719026e18f433548cb8a6baa655
class Task {
    int state;	// 0 - scheduled, 1 - running
    String classname;
    String m_date;

    static constraints = {
        state min: 0, max : 1
    }

    static def countScheduledTasks() {
        return Task.countByState(0);
    }

    static def countRunningTasks() {
        return Task.countByState(1);

    }
}
