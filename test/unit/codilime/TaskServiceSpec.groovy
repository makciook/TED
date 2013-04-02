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
}
