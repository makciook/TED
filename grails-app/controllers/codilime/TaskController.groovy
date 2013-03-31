package codilime

import org.springframework.dao.DataIntegrityViolationException

import javax.servlet.http.HttpServletResponse

class TaskController {

    TaskService taskService

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)

    }

    def scaffold = Task

    /*def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [taskInstanceList: Task.list(params), taskInstanceTotal: Task.count()]
    }
    */

    /**
     * Action controlling count-scheduled
     * Respond with 200 - SC_OK
     */
    def countScheduled() {
        render(status: 200, text: Task.countScheduledTasks())
    }

    /**
     * Action controlling count-running
     * Respond with 200 - SC_OK
     */
    def countRunning() {
        render(status: 200, text: Task.countRunningTasks())
    }

    /**
     *	If no 'class' tag or empty value field - error 400 : indicating the request sent by the client was syntactically incorrect.
     *	If correct value, but ClassNotFound    - error 202 : indicating that a request was accepted for processing, but was not completed.
     */
    def schedule() {
        def className = params['class']
        if(className==null || className.toString().empty) {
            render(status: 400)		// 400
        }
        else {
            render "Try to schedule new task @ "+className+"\n"
            try {
                /* OBSLUGA NOWEGO WATKU */
                def newTask = new Task(state: 0, classname: className, m_date: new Date().toString())	// create new as scheduled
                newTask.save()
                taskService.threadStart(className,newTask);

                /* KONIEC OBSLUGI NOWEGO WATKU */
                render(status: 200)			// 200
            } catch(Exception e) {
                render(status: 202) // accepted, but no such class. May be 400
            }
        }
    }
}
