package helloWord
import java.util.UUID
/*
*   @author: Felipe Gama
*/
class Endereco  implements Serializable {

    private static final long serialVersionUID = 1

    UUID id
    String cep
	String bairro
	String cidade
	String estado
	String complemento
	String logradouro
    String numero

    static hasMany = [enderecoUsuarios: EnderecoUsuario]





    static constraints = {
        numero nullable: true
        complemento nullable: true
    }

    static mapping = {
		version false
		columns {
    	    id column: 'id'
  	    }
		id generator : 'uuid2', type: 'pg-uuid'
    }


}
