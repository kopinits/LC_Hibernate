/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.kopinits.loancontrol.utils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;

/**
 *
 * @author Markus
 */
public class Email implements Serializable {

    private static final long serialVersionUID = -2028638590176737187L;

    public static final String CONTENT_TYPE_TEXT_PLAIN = "text/plain";
    public static final String CONTENT_TYPE_TEXT_HTML = "text/html";

    public static final String TEXTO_CUMPRIMENTO = "cumprimento";
    public static final String TEXTO_DESENVOLVIMENTO = "desenvolvimento";
    public static final String TEXTO_ASSINATURA = "assinatura";

    public static final String IMAGEM_CABECALHO = "cabecalho";
    public static final String IMAGEM_ASSINATURA = "assinatura";

    //String
    private String remetente;
    private String assunto;
    private String content;

    //Array
    private String[] anexos;
    private String[] nomeAnexos;
    private Object[] destinatarios;
    private Object[] copiaDestinatarios;

    //Map
    private Map imagens;
    private Map mensagem;

    //Object
    private ArrayList streams;

    /**
     * Métodos get e set
     */
    public String[] getAnexos() {
        return anexos;
    }

    public void setAnexos(String[] anexos) {
        this.anexos = anexos;
    }

    public String getAssunto() {
        return assunto;
    }

    public void setAssunto(String assunto) {
        this.assunto = assunto;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Map getImagens() {
        return imagens;
    }

    public void setImagens(Map imagens) {
        this.imagens = imagens;
    }

    public Object[] getDestinatarios() {
        return destinatarios;
    }

    public void setDestinatarios(Object[] destinatarios) {
        this.destinatarios = destinatarios;
    }

    public Object[] getCopiaDestinatarios() {
        return copiaDestinatarios;
    }

    public void setCopiaDestinatarios(Object[] destinatarios) {
        this.copiaDestinatarios = destinatarios;
    }

    public Map getMensagem() {
        return mensagem;
    }

    public void setMensagem(Map mensagem) {
        this.mensagem = mensagem;
    }

    /**
     * Necessário informar apenas para anexos de streans.
     */
    public String[] getNomeAnexos() {
        return nomeAnexos;
    }

    public void setNomeAnexos(String[] nomeAnexos) {
        this.nomeAnexos = nomeAnexos;
    }

    public String getRemetente() {
        return remetente;
    }

    public void setRemetente(String remetente) {
        this.remetente = remetente;
    }

    public ArrayList getStreams() {
        return streams;
    }

    public void setStreams(ArrayList streams) {
        this.streams = streams;
    }
}
