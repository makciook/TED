package codilime

import spock.lang.Specification
import grails.test.mixin.Mock
import grails.test.mixin.TestFor

/**
 * Created with IntelliJ IDEA.
 * User: maciek
 * Date: 31.03.13
 * Time: 12:48
 * To change this template use File | Settings | File Templates.
 */


@TestFor(TaskService)
@Mock(Task)
class TaskServiceSpec extends Specification {
    def "check task submit"() {
        setup:
            def taskService = new TaskService()
            def name = "codilime.ted.example.Itemize"
            def task = new Task(state: 0, classname: name, m_date: new Date().toString())

        when:
            taskService.threadStart(name, task)

        then:
            Task.countByState(1) == 1
    }
}
