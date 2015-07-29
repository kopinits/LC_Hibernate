package com.br.kopinits.loancontrol.struts;

import com.br.kopinits.loancontrol.beans.Pessoa;
import com.br.kopinits.loancontrol.beans.Usuario;
import com.br.kopinits.loancontrol.forms.PessoaForm;
import com.br.kopinits.loancontrol.negocio.PessoaNegocio;
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
public class ProcessarPessoaAction extends LCAction {

    @Override
    public String executar(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        
        Usuario usr = getUsuarioLogado(request);
        PessoaForm frm = (PessoaForm) form;
                
        if (frm.getAcao() != null && !frm.getAcao().equals("")) {
            PessoaNegocio negocio = new PessoaNegocio(usr);
            Pessoa bean = new Pessoa();
            formToBean(bean, frm);
            bean.setUsuario(usr);
            if (frm.getAcao().equals(ConstantesAplicacao.SALVAR)) {
                if (bean.getId() != null) {
                    negocio.update(bean);
                } else {
                    negocio.create(bean);
                }
                setMensagemSessao("msg.operacao.sucesso", null, request);
                return ConstantesAplicacao.SALVOU;
            } else if (frm.getAcao().equals(ConstantesAplicacao.CARREGAR)) {
                bean = (Pessoa) negocio.select(bean.getId());
                beanToForm(bean, frm);
            } else if (frm.getAcao().equals(ConstantesAplicacao.EXCLUIR)) {
                negocio.delete(bean.getId());
                setMensagemSessao("msg.operacao.sucesso", null, request);
                return ConstantesAplicacao.SALVOU;
            }
        }
        return ConstantesAplicacao.SUCESSO;

    }

    @Override
    protected boolean isAutenticar() {
        return true;
    }

    private void beanToForm(Pessoa bean, PessoaForm frm) {
        frm.setId(bean.getId());
        frm.setNome(bean.getNome());
        frm.setDdd(bean.getDdd());
        frm.setTelefone(bean.getTelefone());
        frm.setDddCelular(bean.getDddCel());
        frm.setCelular(bean.getCelular());
        frm.setEmail(bean.getEmail());
    }

    private void formToBean(Pessoa bean, PessoaForm frm) {
        if (frm.getId() != null && frm.getId().longValue() > 0) {
            bean.setId(Long.valueOf(frm.getId()));
        }
        bean.setNome(frm.getNome());
        bean.setDdd(frm.getDdd());
        bean.setTelefone(frm.getTelefone());
        bean.setDddCel(frm.getDddCelular());
        bean.setCelular(frm.getCelular());
        bean.setEmail(frm.getEmail());
    }
}
