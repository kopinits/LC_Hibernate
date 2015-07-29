package com.br.kopinits.loancontrol.struts;

import com.br.kopinits.loancontrol.beans.Usuario;
import com.br.kopinits.loancontrol.forms.LoginForm;
import com.br.kopinits.loancontrol.negocio.UsuarioNegocio;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Markus
 */
public class MeusDadosAction extends LCAction {

    @Override
    public String executar(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        LoginForm frm = (LoginForm) form;
        if (frm.getAcao() != null) {
            UsuarioNegocio negocio = new UsuarioNegocio();
            Usuario usr = (Usuario)negocio.select(frm.getId());
            formToBean(usr, frm);
            if (frm.getAcao().equals(ConstantesAplicacao.SALVAR)) {
                negocio.update(usr);
                request.getSession().setAttribute(ConstantesAplicacao.NM_USUARIO, usr.getLogin());
                request.getSession().setAttribute(ConstantesAplicacao.USUARIO, usr);
                setMensagemSessao("msg.operacao.sucesso", null, request);
            } else if (frm.getAcao().equals(ConstantesAplicacao.SENHA)) {
                usr.setSenha(frm.getSenha());
                negocio.alterarSenha(usr);
                setMensagemSessao("msg.operacao.sucesso", null, request);
                return ConstantesAplicacao.SALVOU;
            }
        } else {//carregar
            Usuario usr = getUsuarioLogado(request);
            if (usr == null) {
                return ConstantesAplicacao.NAO_AUTENTICADO;
            }
            beanToForm(usr, frm);
        }
        return ConstantesAplicacao.SUCESSO;

    }

    @Override
    protected boolean isAutenticar() {
        return true;
    }

    private void beanToForm(Usuario usr, LoginForm frm) {
        frm.setId(usr.getId());
        frm.setNome(usr.getNome());
        frm.setLogin(usr.getLogin());
        frm.setEmail(usr.getEmail());
    }

    private void formToBean(Usuario usr, LoginForm frm) {
        usr.setId(Long.valueOf(frm.getId()));
        usr.setNome(frm.getNome());
        usr.setLogin(frm.getLogin());
        usr.setEmail(frm.getEmail());

    }
}
