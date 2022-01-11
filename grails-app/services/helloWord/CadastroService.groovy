package helloWord

import grails.gorm.transactions.Transactional
import helloWord.Usuario;
import helloWord.UsuarioPermissao;
import helloWord.Permissao;
/*
*   @author: Felipe Gama
*/
@Transactional
class CadastroService {

    def serviceMethod() {

    }

    def cadastrarCliente(def recebido){

        def usuario = new Usuario();
        usuario.nome = recebido.nome;
        usuario.telefone = recebido.telefone;
        usuario.data_nascimento  = Date.parse('yyyy-MM-dd', recebido.data_nascimento);
        usuario.cpf = recebido.cpf;
        usuario.email = recebido.email;
        usuario.username = recebido.email;
        usuario.password = recebido.senha;
        usuario.save(flush:true);

        def role1 = Permissao.get(1);
        def usuarioPermissao = new UsuarioPermissao();
        usuarioPermissao.usuario = usuario;
        usuarioPermissao.permissao = role1;
        usuarioPermissao.save(flush:true);
        return [codereturn: 0, message: "Cadastro realizado com sucesso!"]

    }


}
