package codilime

import spock.lang.Specification
import grails.test.mixin.Mock
import grails.test.mixin.TestFor

@TestFor(TaskService)
@Mock(Task)
class TaskServiceSpec extends Specification {
    def taskService = new TaskService()

    def setup() {
        mockService(TaskService)
    }
<<<<<<< HEAD

    def "correct single task submit"() {
        given:
            def name = "codilime.ted.example.Itemize"
            def task = new Task(state: 0, classname: name, m_date: new Date().toString())

        when:
             task.save()
             taskService.threadStart(name, task)

        then:
            Task.countRunningTasks() == 1
            Task.countScheduledTasks() == 0
    }

    /*def "correct MAX_THREADS+1 tasks submit"() {
        given:
        //def task = new Task(state: 1, classname: name, m_date: new Date().toString())

        when:
        (1..TaskService.MAX_THREADS+1).each { taskService.threadStart name, new Task(state: 0, classname: "test", m_date: new Date().toString()) }
        //taskService.threadStart(name, )

        then:
        Task.countRunningTasks() == TaskService.MAX_THREADS
        Task.countScheduledTasks() == 1
    }       */
=======
>>>>>>> 67efca988f7ea719026e18f433548cb8a6baa655
}
