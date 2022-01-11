package helloWord.cliente
import groovy.json.*
import grails.converters.JSON
import java.util.concurrent.TimeUnit;
import java.io.InputStream;
import grails.transaction.Transactional;
import helloWord.cliente.EnderecoService;
/*
*   @author: Felipe Gama
*/
class EnderecoController {

    def enderecoService;
    def springSecurityService;

    def index(){
        def id =  springSecurityService.principal.id;
        def result = enderecoService.listar(id);
        render result as JSON;
    }

    def show(String id){
        def user_id =  springSecurityService.principal.id;
        def result = enderecoService.show(user_id,id);
        render result as JSON;
    }

    def delete(String id){
        def user_id =  springSecurityService.principal.id;
        def result = enderecoService.delete(user_id,id);
        render result as JSON;
    }

    def update(String id){
        def recebido = request.JSON;
        if(!recebido.cep?.trim()  || !recebido.numero?.trim()   ){ 
			response.status = 400;
			render([codereturn: 999,
                message: 'Requisição faltando dados'] as JSON)
			return
		}else{
            def user_id =  springSecurityService.principal.id;
            def result = enderecoService.editar(user_id,recebido,id);
            response.status = 200;
            if(result.codereturn>0){
                response.status = 400;
            }
            render result as JSON;
        }

    }

    def save(){
        def recebido = request.JSON;
        if(!recebido.cep?.trim()  || !recebido.numero?.trim()   ){ 
			response.status = 400;
			render([codereturn: 999,
                message: 'Requisição faltando dados'] as JSON)
			return
		}else{
            def id =  springSecurityService.principal.id;
            def result = enderecoService.cadastrar(id,recebido);
            response.status = 200;
            if(result.codereturn>0){
                response.status = 400;
            }
            render result as JSON;
        }

    }
}
