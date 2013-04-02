import java.util.concurrent.Executors

// Place your Spring DSL code here
beans = {
    executorService( grails.plugin.executor.PersistenceContextExecutorWrapper ) { bean->
        bean.destroyMethod = 'destroy'
        persistenceInterceptor = ref("persistenceInterceptor")
        executor = Executors.newFixedThreadPool(4)
    }
}
