package com.br.kopinits.loancontrol.db;

/**
 *
 */
public class LCException extends Exception {
    //~ Atributos/inicializadores estaticos ----------------------------------------------------------------------------------------

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public LCException(String msg) {
        super(msg);
    }

    public LCException(Exception e) {
        super(e);
    }

    public static String buscarChaveErro(String codigo, String erro) {

        String key = "erro.nao.tratado";
        String pre = "erro.constraint.";
        int inicio;
        int fim;

        if (("23505".equals(codigo))
                && (erro.lastIndexOf("un_") > -1)) {

            inicio = erro.lastIndexOf("un_");
            fim = erro.substring(inicio).indexOf("\"");
            key = pre + erro.substring(inicio, inicio+fim);

        } else if ("23503".equals(codigo) && (erro.lastIndexOf("fk_") > -1)) {
            inicio = erro.lastIndexOf("fk_");
            fim = erro.substring(inicio).indexOf("\"");
            key = pre + erro.substring(inicio, inicio+fim);
        }
        //23514 check
        //23502 not null

        return key;
    }
}
