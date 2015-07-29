package com.br.kopinits.loancontrol.struts;

import com.br.kopinits.loancontrol.beans.Categoria;
import com.br.kopinits.loancontrol.beans.Usuario;
import com.br.kopinits.loancontrol.forms.CategoriaForm;
import com.br.kopinits.loancontrol.negocio.CategoriaNegocio;
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
public class ProcessarCategoriaAction extends LCAction {

    @Override
    public String executar(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        CategoriaForm frm = (CategoriaForm) form;
        if (frm.getAcao() != null && !frm.getAcao().equals("")) {
            Usuario usr = getUsuarioLogado(request);
            CategoriaNegocio negocio = new CategoriaNegocio(usr);
            Categoria bean = new Categoria();
            formToBean(bean, frm);
            bean.setUsuario(usr);
            if (frm.getAcao().equals(ConstantesAplicacao.SALVAR)) {
                if (bean.getId() != null) {
                    negocio.update(bean);
                }else{
                    negocio.create(bean);
                }
                setMensagemSessao("msg.operacao.sucesso", null, request);
                return ConstantesAplicacao.SALVOU;
            } else if (frm.getAcao().equals(ConstantesAplicacao.CARREGAR)) {
                bean = (Categoria) negocio.select(bean.getId());
                beanToForm(bean, frm);
            }else if (frm.getAcao().equals(ConstantesAplicacao.EXCLUIR)) {
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

    private void beanToForm(Categoria bean, CategoriaForm frm) {
        frm.setId(bean.getId());
        frm.setDescricao(bean.getDescricao());

    }

    private void formToBean(Categoria bean, CategoriaForm frm) {
        if (frm.getId() != null && frm.getId().longValue() > 0) {
            bean.setId(Long.valueOf(frm.getId()));
        }
        bean.setDescricao(frm.getDescricao());

    }
}
