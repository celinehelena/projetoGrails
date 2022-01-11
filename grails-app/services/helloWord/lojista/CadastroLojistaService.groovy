package helloWord.lojista

import grails.gorm.transactions.Transactional
import helloWord.Usuario;
import helloWord.UsuarioPermissao;
import helloWord.Permissao;
import helloWord.Loja;
import helloWord.Endereco;
import helloWord.LojaUsuario;
/*
*   @author: Felipe Gama
*/
@Transactional
class CadastroLojistaService {

    def cadastrarLojista(def recebido){

        def usuario = new Usuario();
        usuario.nome = recebido.nome;
        usuario.telefone = recebido.telefone;
        usuario.data_nascimento  = Date.parse('yyyy-MM-dd', recebido.data_nascimento);
        usuario.cpf = recebido.cpf;
        usuario.email = recebido.email;
        usuario.username = recebido.email;
        usuario.password = recebido.senha;
        usuario.save(flush:true);
        
        def endereco = new Endereco();
        endereco.cep = recebido.endereco.cep;
        endereco.bairro = recebido.endereco.bairro;
        endereco.cidade = recebido.endereco.cidade;
        endereco.estado = recebido.endereco.estado;
        if(recebido.endereco.complemento?.trim()){
            endereco.complemento = recebido.endereco.complemento;
        }
        endereco.logradouro = recebido.endereco.logradouro;
        endereco.numero = recebido.endereco.numero;
        endereco.save(flush:true)
	    
        def loja = new Loja();
        loja.endereco = endereco;
        loja.razao_social = recebido.loja.razao_social;
	    loja.nome_fantasia = recebido.loja.nome_fantasia;
        loja.save(flush:true);
        

        def rel = new LojaUsuario();
        rel.loja=loja;
        rel.usuario = usuario;
        rel.save(flush:true);

        def role1 = Permissao.get(3);
        def usuarioPermissao = new UsuarioPermissao();
        usuarioPermissao.usuario = usuario;
        usuarioPermissao.permissao = role1;
        usuarioPermissao.save(flush:true);

        def role2 = Permissao.get(1);
        usuarioPermissao = new UsuarioPermissao();
        usuarioPermissao.usuario = usuario;
        usuarioPermissao.permissao = role2;
        usuarioPermissao.save(flush:true);

        return [codereturn: 0, message: "Cadastro realizado com sucesso!"]

    }
}
