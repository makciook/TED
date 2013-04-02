package codilime

import grails.plugin.spock.ControllerSpec


class TaskControllerIntegrationSpec extends ControllerSpec  {

    static transactional = true

    def "correct add 5 tasks - 1 scheduled, 4 running"() {
        setup:
        controller.metaClass.message = {args -> "mockMessage"}
        controller.params.class = "codilime.ted.example.Itemize"

        when:
        (1..5).each { controller.schedule(); println it }

        then:
        controller.countRunning() == 4
        controller.countScheduled() == 1
    }

    def "correct add 1 task - 2 scheduled, 4 running"() {
        setup:
        controller.metaClass.message = {args -> "mockMessage"}
        controller.params.class = "codilime.ted.example.Itemize"

        when:
        controller.schedule()

        then:
        controller.countRunning() == 4
        controller.countScheduled() == 2
    }

    /*@Unroll
    def "correct add 1 wrong task"() {
        setup:
        Task.executeUpdate("DELETE Task t")
        controller.metaClass.message = {args -> "mockMessage"}
        controller.params.class = "codilime.ted.example.SorryIDoNotExist"

        when:
        controller.schedule()

        then:
        controller.countRunning() == 0
        controller.countScheduled() == 0
    }*/
}
