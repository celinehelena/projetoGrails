package helloWord.lojista
import groovy.json.*
import grails.converters.JSON
import java.util.concurrent.TimeUnit;
import java.io.InputStream;
import grails.transaction.Transactional;
import helloWord.lojista.ProdutoService;
/*
*   @author: Celine Andrade
*/
class ProdutoController {

    def produtoService;
    def springSecurityService;

    def index(){
        def id = springSecurityService.principal.id; //pegando o id do user
        def result = produtoService.listar(id);
        render result as JSON;
    }

    def show(String id){
        def user_id = springSecurityService.principal.id;
        def result = produtoService.show(user_id,id);

        render result as JSON;
    }

    def delete(String id){
        def user_id = springSecurityService.principal.id;
        def result = produtoService.delete(user_id,id);
        render result as JSON;
    }

    def update(String id){
         def recebido = request.JSON;
        if(!recebido.codigo?.trim()  || !recebido.codigoBarras?.trim()   ){ 
			response.status = 400;
			render([codereturn: 999,
                message: 'Requisição faltando dados'] as JSON)
			return
		}else{
            def user_id =  springSecurityService.principal.id;
            def result = produtoService.editar(user_id,recebido,id);
            response.status = 200;
            if(result.codereturn>0){
                response.status = 400;
            }

            render result as JSON;
        }
    }

    def save(){
        def recebido = request.JSON;
        if(recebido.valor==null   ){ 
			response.status = 400;
			render([codereturn: 999,
                message: 'Requisição faltando dados'] as JSON)
			return
		}else{
            def id =  springSecurityService.principal.id;
            def result = produtoService.save(id,recebido);
            response.status = 200;
            if(result.codereturn>0){
                response.status = 400;
            }
            render result as JSON;
        }

    }

}
