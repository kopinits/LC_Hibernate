package com.br.kopinits.loancontrol.db;

/**
 * 
 */
public class DBObjectException extends LCException {
	//~ Atributos/inicializadores estaticos ----------------------------------------------------------------------------------------

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	//~ Construtores ---------------------------------------------------------------------------------------------------------------

	/**
	 * Cria um novo objeto InsertObjectException.
	 *
	 * @param e  
	 */
	public DBObjectException(Exception e) {
		super(e);
	}
}
