package helloWord

class LojaUsuario implements Serializable {

    private static final long serialVersionUID = 1

    Loja loja;
    Usuario usuario;
    Date dt_cadastro;
    Boolean ativo;

    static constraints = {
    
    }

    static mapping = {
		version false
        table 'rel_loja_usuario'
		columns {
    	    id column: 'id'
  	    }
		id generator:'sequence', params:[sequence:'rel_loja_usuario_id_seq']
    }

    def LojaUsuario(){
        this.ativo = true;
        this.dt_cadastro = new Date();
    }
}
