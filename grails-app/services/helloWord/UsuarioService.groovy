package helloWord

import grails.gorm.transactions.Transactional



@Transactional
class UsuarioService {

    def serviceMethod() {

    }

    def criar(def recebido){
        def role1 = Permissao.get(1);
        def user1 = new Usuario(username: recebido.nome,password: recebido.senha).save(flush:true); 
        def usuarioPermissao = new  UsuarioPermissao();
        usuarioPermissao.usuario = user1;
        usuarioPermissao.permissao = role1;
        usuarioPermissao.save(flush:true);
        return [codereturn: 0, message: "Cadastro realizado com sucesso!"]
    }
}
