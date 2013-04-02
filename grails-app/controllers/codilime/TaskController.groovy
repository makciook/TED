package codilime

import org.springframework.dao.DataIntegrityViolationException

import javax.servlet.http.HttpServletResponse

<<<<<<< HEAD
=======
/**
 * MVC's controller class for Task, responding to http/json queries
 */
>>>>>>> 67efca988f7ea719026e18f433548cb8a6baa655
class TaskController {

    TaskService taskService

    static allowedMethods = [countScheduled: "GET", countRunning: "GET", schedule: "GET"]

    def index() {
        redirect(action: "list", params: params)
    }

    def scaffold = Task

    /**
     * Action controlling count-scheduled
     * Respond with 200 - SC_OK
     */
    def countScheduled() {
        render(status: 200, text: Task.countScheduledTasks())
        response.flushBuffer()
<<<<<<< HEAD
=======
        return Task.countScheduledTasks()
>>>>>>> 67efca988f7ea719026e18f433548cb8a6baa655
    }

    /**
     * Action controlling count-running
     * Respond with 200 - SC_OK
     */
    def countRunning() {
        render(status: 200, text: Task.countRunningTasks())
        response.flushBuffer()
<<<<<<< HEAD
=======
        return Task.countRunningTasks()
>>>>>>> 67efca988f7ea719026e18f433548cb8a6baa655
    }

    /**
     *	If no 'class' tag or empty value field - error 400 : indicating the request sent by the client was syntactically incorrect.
     *	If correct value, but ClassNotFound    - error 202 : indicating that a request was accepted for processing, but was not completed.
     */
    def schedule() {
        def className = params['class']
        if(className==null || className.toString().empty) {
<<<<<<< HEAD
            render(status: 400)		// 400  bad req
=======
            render(status: 400)		        // 400  bad req
>>>>>>> 67efca988f7ea719026e18f433548cb8a6baa655
        }
        else {
            try {
                def newTask = new Task(state: 0, classname: className, m_date: new Date().toString())	// create new as scheduled
                newTask.save()
                taskService.threadStart(className,newTask);
                render(status: 200)			// 200       ok
            } catch(Exception e) {
                render(status: 202)         // 202       accepted
            }
        }
    }
}
