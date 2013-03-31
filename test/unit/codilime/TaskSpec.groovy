package codilime

import grails.test.mixin.Mock
import grails.test.mixin.TestFor
import spock.lang.Specification
import spock.lang.Unroll

/**
 * Created with IntelliJ IDEA.
 * User: maciek
 * Date: 30.03.13
 * Time: 16:06
 * To change this template use File | Settings | File Templates.
 */

@TestFor(Task)
@Mock(Task)
class TaskSpec extends Specification {
    def setup() {
        mockForConstraintsTests(Task, [new Task()])
    }

    @Unroll("test task all constraints #field initialized with error value: #error")
    def "test tasks all constraints"() {
        when:
        def obj = new Task("$field": val)
        obj.validate()

        then:
        if(error && error!='valid')
            error == obj.errors[field]
        //validateConstraints(obj,field,error)

        where:
        error       | field   | val
        'min'       | 'state' | -10
        'max'       | 'state' | 2
    }



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
}
