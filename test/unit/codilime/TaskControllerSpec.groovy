package codilime


import grails.test.mixin.TestFor
import grails.test.mixin.Mock
import spock.lang.Specification
import spock.lang.Unroll

import javax.servlet.http.HttpServletResponse


/**
 * Created with IntelliJ IDEA.
 * User: maciek
 * Date: 30.03.13
 * Time: 15:38
 * To change this template use File | Settings | File Templates.
 */
@TestFor(TaskController)
@Mock(Task)
class TaskControllerSpec extends Specification {

    def "mocktest only"() {
        expect:
        name.size() == size

        where:
        name << ["Maciek", "Marek", "Bartek", "Ewa"]
        //size << [6, 5, 10, 10]
        size << [6,5,6,3]
    }

    def "correct response scheduled"() {
        when:
        controller.countScheduled()

        then:
        controller.response.getStatus() == HttpServletResponse.SC_OK
        controller.response.getStatus() != HttpServletResponse.SC_BAD_REQUEST
    }

    def "correct response running"() {
        when:
        controller.countRunning()

        then:
        controller.response.getStatus() == HttpServletResponse.SC_OK
        controller.response.getStatus() != HttpServletResponse.SC_BAD_REQUEST
    }

    /*def "correct response schedule"() {
        setup:
        mockController(TaskController)
        controller.metaClass.message = {args -> "mockMessage"}
        controller.params.class = "codilime.ted.example.Itemize"

        when:
        controller.schedule()

        then:
        controller.response.getStatus() == HttpServletResponse.SC_OK
        controller.response.getStatus() == HttpServletResponse.SC_ACCEPTED
        controller.countRunning() == 1
    }         */

    def "correct count running tasks"() {
        given:
        (0..100).each { i-> new Task(state: i%2, m_date: new Date().toString(), classname: "Test" ) }
        controller.countRunning()

        expect:
        controller.response.text.equals((new Integer(50).toString()))
    }


}
