package helloWord
/*
*   @author: Felipe Gama
*/
class EnderecoUsuario implements Serializable {

    private static final long serialVersionUID = 1

    Endereco endereco;
    Usuario usuario;
    Date dt_cadastro;
    Boolean ativo;

    static constraints = {
    
    }

    static mapping = {
		version false
        table 'rel_endereco_usuario'
		columns {
    	    id column: 'id'
  	    }
		id generator:'sequence', params:[sequence:'endereco_id_seq']
    }

    def EnderecoUsuario(){
        this.ativo = true;
        this.dt_cadastro = new Date();
    }
}
