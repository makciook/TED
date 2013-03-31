class UrlMappings {

	static mappings = {
		"/$controller/$action?/$id?"{
			constraints {
				// apply constraints here
			}
		}

		"/"(view:"/index")
		"500"(view:'/error')
        "/count-running"(controller: "task", action: "countRunning")
        "/count-scheduled"(controller: "task", action: "countScheduled")
        "/schedule"(controller: "task", action: "schedule")
	}
}
