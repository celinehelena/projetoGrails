package helloWord

import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString
import grails.compiler.GrailsCompileStatic

@GrailsCompileStatic
@EqualsAndHashCode(includes='username')
@ToString(includes='username', includeNames=true, includePackage=false)
class Usuario implements Serializable {

    private static final long serialVersionUID = 1

    // Colunas padrao
    String username;
    String password;
    boolean enabled = true;
    boolean accountExpired;
    boolean accountLocked;
    boolean passwordExpired;

    String nome;
    String email;
    String cpf;
    Date data_nascimento;
    String telefone;

    static hasMany = [enderecoUsuarios: EnderecoUsuario,lojas: LojaUsuario]
    


    Set<Permissao> getAuthorities() {
        (UsuarioPermissao.findAllByUsuario(this) as List<UsuarioPermissao>)*.permissao as Set<Permissao>
    }

    static constraints = {
        password nullable: false, blank: false, password: true
        username nullable: false, blank: false, unique: true
    }

    static mapping = {
        version false
	    password column: '`password`'
    }
}
