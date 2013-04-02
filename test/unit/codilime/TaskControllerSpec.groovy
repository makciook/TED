package codilime

import grails.test.mixin.TestFor
import grails.test.mixin.Mock
<<<<<<< HEAD
import spock.lang.Specification
=======
//import spock.lang.Specification
import spock.lang.Specification;
>>>>>>> 67efca988f7ea719026e18f433548cb8a6baa655
import spock.lang.Unroll

import javax.servlet.http.HttpServletResponse

@TestFor(TaskController)
@Mock(Task)
class TaskControllerSpec extends Specification {
    def setup() {
        mockController(TaskController)
        mockFor(Task)
    }

    def "correct response of count-scheduled"() {
        when:
        controller.countScheduled()

        then:
        controller.response.getStatus() == HttpServletResponse.SC_OK
        controller.response.getStatus() != HttpServletResponse.SC_BAD_REQUEST
    }

    def "correct response of count-running"() {
        when:
        controller.countRunning()

        then:
        controller.response.getStatus() == HttpServletResponse.SC_OK
        controller.response.getStatus() != HttpServletResponse.SC_BAD_REQUEST
    }

    def "correct response of schedule for existing class"() {
        setup:
        def taskService = new TaskService()
        controller.taskService = taskService
        controller.metaClass.message = {args -> "mockMessage"}
        controller.params.class = "codilime.ted.example.Itemize"

        when:
        controller.schedule()

        then:
<<<<<<< HEAD
        controller.response.getStatus() == HttpServletResponse.SC_OK || controller.response.getStatus() == HttpServletResponse.SC_ACCEPTED
=======
        controller.response.getStatus() == HttpServletResponse.SC_OK// || controller.response.getStatus() == HttpServletResponse.SC_ACCEPTED
    }

    def "correct response of schedule for non-existing class"() {
        setup:
        def taskService = new TaskService()
        controller.taskService = taskService
        controller.metaClass.message = {args -> "mockMessage"}
        controller.params.class = "codilime.ted.example.NotExistingClass"

        when:
        controller.schedule()

        then:
        controller.response.getStatus() == HttpServletResponse.SC_ACCEPTED
>>>>>>> 67efca988f7ea719026e18f433548cb8a6baa655
    }

    def "correct response of schedule for wrong params"() {
        setup:
        //mockController(TaskController)
        def taskService = new TaskService()
        controller.taskService = taskService
        controller.metaClass.message = {args -> "mockMessage"}
        controller.params.cl = "codilime.ted.example.Itemize"

        when:
        controller.schedule()

        then:
        controller.response.getStatus() == HttpServletResponse.SC_BAD_REQUEST
    }
}
