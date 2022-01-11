package helloword

import helloWord.*;

class BootStrap {

    def init = { servletContext ->
        def role1 = new Permissao(authority:"ROLE_USER").save(flush:true); 
        def user1 = new Usuario(username:"user1",password:"pwd1").save(flush:true); 
        def usuarioPermissao = new  UsuarioPermissao();
        usuarioPermissao.usuario = user1;
        usuarioPermissao.permissao = role1;
        usuarioPermissao.save(flush:true);


    }
    def destroy = {
    }
}
