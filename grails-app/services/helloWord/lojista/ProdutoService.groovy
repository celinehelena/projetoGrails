package helloWord.lojista

import grails.gorm.transactions.Transactional;
import grails.gorm.transactions.Transactional
import java.sql.Time;
import java.text.SimpleDateFormat; 
import groovy.time.TimeCategory; 
import java.util.Date;  
import groovy.sql.Sql;
import javax.annotation.PostConstruct;
import helloWord.Usuario;
import helloWord.Loja;
import helloWord.Produto;
import helloWord.LojaUsuario;

@Transactional
class ProdutoService {

    def dataSource;
    def query=null;

    @PostConstruct
    def init(){
        query = new Sql(dataSource);
    }


    def listar(def user_id){
        def sql = "SELECT prod.id::text, prod.nome, prod.codigo "+
	        "FROM produto as prod " +
	            "INNER JOIN rel_loja_usuario as rel "+
	                "ON rel.loja_id = prod.loja_id "+
	        "WHERE rel.usuario_id = ${user_id} AND rel.ativo = true AND prod.ativo = true ";

        def result = query.rows(sql);
        return [produtos: result]
    
    }

    def show(def user_id, def produto_id){
        def sql = "SELECT prod.id::text, prod.nome, prod.valor, prod.codigo,prod.codigo_barras "+
	            "FROM produto as prod  "+
	                "INNER JOIN rel_loja_usuario as rel "+
	                    "ON rel.loja_id = prod.loja_id "+
	            "WHERE rel.usuario_id = ${user_id}  AND prod.id = '${produto_id}' AND rel.ativo = true AND prod.ativo = true ";
        def result = query.firstRow(sql);
        if(!result){
            return [codereturn: 100, message: "Você não têm permissão de ver esse produto!"]
        }
        return [produto: result]
    }

    def delete(def user_id, def produto_id){
        def usuario = Usuario.get(user_id);
        if(!usuario){
            return [codereturn: 100, message: "Esse usuário não existe" ];
        }
        def produto = Produto.get(produto_id);
        produto.ativo = false;
        produto.save();
        //gravar quem atualizou;
        return [codereturn: 0, message: "Produto removido com sucesso!"]
    }

     def editar(def user_id, def recebido, def produto_id){
            def usuario = Usuario.get(user_id);
            if(!usuario){
            return [codereturn: 100,message: "Esse usuário não existe"]
        }
        def produto = Produto.get(produto_id);
        produto.valor = recebido.valor;
        produto.codigo = recebido.codigo;
        produto.nome = recebido.nome;
        produto.codigoBarras = recebido.codigoBarras;
        if(recebido.codigoBarras?.trim()){
            produto.codigoBarras = recebido.codigoBarras;
        }
        produto.save(flush:true);

        return [codereturn: 0, 
            message: "Produto editado com sucesso!",
            produto: show(user_id, produto_id).produto];
    }

    def save(def user_id,def recebido){
        def usuario = Usuario.get(user_id);
        def loja = usuario.lojas[0].loja;
        def produto = new Produto();
        produto.loja = loja;
        produto.valor = recebido.valor;
        produto.codigo  = recebido.codigo;
        produto.codigoBarras = recebido.codigoBarras;
        produto.nome = recebido.nome;
        produto.save(flush:true);
        return [codereturn: 0,message: "Produto cadastrado com sucesso!"];

    }




}
