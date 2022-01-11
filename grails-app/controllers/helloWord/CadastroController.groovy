package helloWord

import groovy.json.*;
import grails.converters.JSON;
/*
*   @author: Felipe Gama
*/
class CadastroController  {
    
    static allowedMethods = [save: "POST" ]

    def cadastroService;

    def save(){
        def recebido = request.JSON;
        if( !recebido.nome?.trim() || !recebido.telefone.trim() || 
        !recebido.cpf?.trim() || !recebido.senha?.trim()){
            response.status = 400;
            def result = [codereturn: 100,message: "Faltou dados!!!"]
            render result as JSON;
            return 
        }
        def result = cadastroService.cadastrarCliente(recebido);
        response.status = 200;
        if(result.codereturn>0){
            response.status = 400;
        }
        render result as JSON;

    }

}
