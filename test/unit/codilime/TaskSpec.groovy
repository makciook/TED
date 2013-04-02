package codilime

import grails.test.mixin.Mock
import grails.test.mixin.TestFor
import spock.lang.Specification
import spock.lang.Unroll

@TestFor(Task)
@Mock(Task)
class TaskSpec extends Specification {

<<<<<<< HEAD


=======
>>>>>>> 67efca988f7ea719026e18f433548cb8a6baa655
    @Unroll("test task all constraints #field initialized with error value: #error")
    def "test tasks all constraints"() {
        setup:
        mockForConstraintsTests(Task, [new Task()])

        when:
        def obj = new Task("$field": val)
        obj.validate()

        then:
        if(error && error!='valid')
            error == obj.errors[field]
<<<<<<< HEAD
        //validateConstraints(obj,field,error)
=======
>>>>>>> 67efca988f7ea719026e18f433548cb8a6baa655

        where:
        error       | field   | val
        'min'       | 'state' | -10
        'max'       | 'state' | 2
    }

    def "correct count running tasks"() {
        setup:
        //mockForConstraintsTests(Task)
        mockDomain(Task, [(1..100).each { new Task(state: 1, m_date: new Date().toString(), classname: "ll$it").save() }])

        expect:
        Task.countByState(1) == 100
    }

    def "correct count scheduled tasks"() {
        setup:
        //mockForConstraintsTests(Task)
        mockDomain(Task, [(1..100).each { new Task(state: 0, m_date: new Date().toString(), classname: "ll$it").save() }])

        expect:
        Task.countByState(0) == 100
    }
<<<<<<< HEAD

    void validateConstraints(obj, field, error) {
        def validated = obj.validate()
        if (error && error != 'valid') {
            assert !validated
            assert obj.errors[field]
            assert error == obj.errors[field]
        } else {
            assert !obj.errors[field]
        }
    }
=======
>>>>>>> 67efca988f7ea719026e18f433548cb8a6baa655
}
