/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.kopinits.loancontrol.negocio;

import com.br.kopinits.loancontrol.beans.LCEntity;
import com.br.kopinits.loancontrol.beans.Usuario;
import com.br.kopinits.loancontrol.db.DBObjectException;
import com.br.kopinits.loancontrol.db.SelectObjectException;
import com.br.kopinits.loancontrol.db.dao.LCNegocio;
import com.br.kopinits.loancontrol.db.dao.impl.UsuarioDAOImpl;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author Markus
 */
public class UsuarioNegocio extends LCNegocio {

    public UsuarioNegocio() {
        super(new UsuarioDAOImpl(null));
    }

    public Usuario login(Usuario usr) throws SelectObjectException {
        usr.setSenha(encriptaSenha(usr.getSenha()));
        return (Usuario) selectMetodoAsObjeto("selectUsuarioLogin", usr, Usuario.class);
    }

    public long resetarSenha(String login) throws SelectObjectException, DBObjectException {
        long senhaSemCripto = 0;
        Usuario usr = (Usuario) selectMetodoAsObjeto("selectLogin", login, String.class);
        if (usr != null) {
            senhaSemCripto = (long) (Math.random() * 10000);
            usr.setSenha(encriptaSenha(String.valueOf(senhaSemCripto)));
            update(usr);
            
            //EmailUtils.enviarEmailLogin(usr);
        }
        return senhaSemCripto;
    }

    public void alterarSenha(Usuario usr) throws DBObjectException {
        String novaSenha = usr.getSenha();
        usr = (Usuario)select(usr.getId());
        usr.setSenha(encriptaSenha(novaSenha));
        update(usr);
    }

    @Override
    public boolean create(LCEntity lcEntity) throws DBObjectException {
        Usuario usr = (Usuario) lcEntity;
        usr.setSenha(encriptaSenha(usr.getSenha()));
        return super.create(usr);
    }

    private String encriptaSenha(String senha) {
        String sen = "";
        try {
            BigInteger hash = new BigInteger(1, MessageDigest.getInstance("MD5").digest(senha.getBytes()));
            sen = hash.toString(16);
        } catch (NoSuchAlgorithmException e) {
            log(e);
        }
        return sen;
    }
}
