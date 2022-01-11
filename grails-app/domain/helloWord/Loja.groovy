package helloWord
import java.util.UUID
/*
*   @author: Felipe Gama
*/
class Loja implements Serializable {

    private static final long serialVersionUID = 1

    UUID id
    Endereco endereco;
	String razao_social;
	String nome_fantasia;

    static hasMany = [lojaUsuarios: LojaUsuario];





    static constraints = {

    }

    static mapping = {
		version false
		columns {
    	    id column: 'id'
  	    }
		id generator : 'uuid2', type: 'pg-uuid'
    }

}
