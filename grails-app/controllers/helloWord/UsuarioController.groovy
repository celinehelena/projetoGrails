package helloWord

import groovy.json.*;
import grails.converters.JSON;

class UsuarioController {

    static allowedMethods = [criar: "POST" ]
    def usuarioService;

    def index() {

    
    }

    def criar(){

        def recebido = request.JSON;
        def result = usuarioService.criar(recebido);
        response.status = 200;
        if(result.codereturn>0){
            response.status = 400;
        }
        render result as JSON;

    }

}
