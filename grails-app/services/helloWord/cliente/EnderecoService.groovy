package helloWord.cliente

import grails.gorm.transactions.Transactional;
import grails.gorm.transactions.Transactional
import java.sql.Time;
import java.text.SimpleDateFormat; 
import groovy.time.TimeCategory; 
import java.util.Date;  
import groovy.sql.Sql;
import javax.annotation.PostConstruct;
import helloWord.Usuario;
import helloWord.Endereco;
import helloWord.EnderecoUsuario;
/*
* @author: Felipe Gama
*/
@Transactional
class EnderecoService {

    def dataSource;
    def query=null;

    @PostConstruct
    def init(){
        query = new Sql(dataSource);
    }

    def listar(def user_id){
        def sql = " SELECT en.id::text ,en.logradouro, en.cep "+
            " FROM endereco as en  "+
                " INNER JOIN rel_endereco_usuario as rel "+
                    " ON rel.endereco_id = en.id "+
            " WHERE rel.usuario_id = ${user_id} AND rel.ativo=true ";
        def result = query.rows(sql);
        return [enderecos: result]
    }


    def show(def user_id,def endereco_id){
        def sql = " SELECT en.id::text ,en.logradouro, en.cep,  "+
            " en.cidade, en.bairro, en.estado, en.complemento, en.logradouro, en.numero"+
            " FROM endereco as en  "+
                " INNER JOIN rel_endereco_usuario as rel "+
                    " ON rel.endereco_id = en.id "+
            " WHERE rel.usuario_id = ${user_id} AND en.id = '${endereco_id}' AND rel.ativo=true ";
        def result = query.firstRow(sql);
        if(!result){
            return [codereturn: 100, message: "Você não têm permissão de ver esse endereço!"]
        }
        return [endereco: result]
    }


    def delete(def user_id,def endereco_id){
        def usuario = Usuario.get(user_id);
        if(!usuario){
            return [codereturn: 100,message: "Esse usuário não existe"]
        }
        def endereco = Endereco.get(endereco_id);
        for(def rel in endereco.enderecoUsuarios){
            rel.ativo = false;
            rel.save();
        } 
        
        return [codereturn: 0, 
            message: "Endereço removido com sucesso!"];

    }

    def editar(def user_id,def recebido,def endereco_id){
        def usuario = Usuario.get(user_id);
        if(!usuario){
            return [codereturn: 100,message: "Esse usuário não existe"]
        }
        def endereco = Endereco.get(endereco_id);
        endereco.cep = recebido.cep;
        endereco.bairro = recebido.bairro;
        endereco.cidade = recebido.cidade;
        endereco.estado = recebido.estado;
        if(recebido.complemento?.trim()){
            endereco.complemento = recebido.complemento;
        }
        endereco.logradouro = recebido.logradouro;
        endereco.numero = recebido.numero;
        endereco.save(flush:true);
         
        
        return [codereturn: 0, 
            message: "Endereço editado com sucesso!",
            enredeco: show(user_id,endereco_id).endereco];

    }


    def cadastrar(def user_id,def recebido){
        def usuario = Usuario.get(user_id);
        if(!usuario){
            return [codereturn: 100,message: "Esse usuário não existe"]
        }
        def endereco = new Endereco();
        endereco.cep = recebido.cep;
        endereco.bairro = recebido.bairro;
        endereco.cidade = recebido.cidade;
        endereco.estado = recebido.estado;
        if(recebido.complemento?.trim()){
            endereco.complemento = recebido.complemento;
        }
        endereco.logradouro = recebido.logradouro;
        endereco.numero = recebido.numero;
        if(endereco.save(flush:true)){
            def rel = new  EnderecoUsuario();
            rel.endereco =endereco;
            rel.usuario=usuario;
            rel.save(flush:true);
        }else{
            return [codereturn: 200, message: "Não foi possível salvar!"];
        }
        
        return [codereturn: 0, 
        message: "Endereço cadastrado com sucesso!",
        enredeco: show(user_id,endereco.id).endereco];

    }

}
