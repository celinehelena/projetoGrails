package helloWord
import java.util.UUID
/*
*   @author: Felipe Gama
*/
class Produto implements Serializable {

    private static final long serialVersionUID = 1

    UUID id
    Loja loja;
    Float valor;
    String codigo;
    String codigoBarras;
	String nome;
    Boolean ativo;
    Date dt_cadastro;

    static constraints = {

    }

    static mapping = {
		version false
		columns {
    	    id column: 'id'
  	    }
		id generator : 'uuid2', type: 'pg-uuid'
    }

    def Produto(){
        this.dt_cadastro = new Date();
        this.ativo = true;
    }

}
