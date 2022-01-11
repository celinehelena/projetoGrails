package helloword

class UrlMappings {

    static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }
        "/api/v1/$controller/$action?/$id?"{
            constraints {
                // apply constraints here
            }
        }


        post "/api/v1/cliente/$controller"(action:"save")
        get "/api/v1/cliente/$controller/$id(.$format)?"(action:"show")
        get "/api/v1/cliente/$controller/(.$format)?"(action:"index")
        get "/api/v1/cliente/$controller"(action:"index")
        put "/api/v1/cliente/$controller/$id"(action:"update")
        delete "/api/v1/cliente/$controller/$id(.$format)?"(action:"delete")

 
        post "/api/v1/lojista/$controller"(action:"save")
        get "/api/v1/lojista/$controller/$id(.$format)?"(action:"show")
        get "/api/v1/lojista/$controller/(.$format)?"(action:"index")
        get "/api/v1/lojista/$controller"(action:"index")
        put "/api/v1/lojista/$controller/$id"(action:"update")
        delete "/api/v1/lojista/$controller/$id(.$format)?"(action:"delete")



        "/"(view:"/index")
        "500"(view:'/error')
        "404"(view:'/notFound')
    }
}
