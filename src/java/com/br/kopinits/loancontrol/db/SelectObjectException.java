package com.br.kopinits.loancontrol.db;


public class SelectObjectException extends LCException {
	//~ Atributos/inicializadores estaticos ----------------------------------------------------------------------------------------

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	//~ Construtores ---------------------------------------------------------------------------------------------------------------

	/**
	 * Cria um novo objeto SelectObjectException.
	 *
	 * @param msg  
	 */
	public SelectObjectException(String msg) {
		super(msg);
	}
}
