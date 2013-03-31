package codilime

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
