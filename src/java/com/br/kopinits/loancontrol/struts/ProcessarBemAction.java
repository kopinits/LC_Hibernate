package com.br.kopinits.loancontrol.struts;

import com.br.kopinits.loancontrol.beans.BemMaterial;
import com.br.kopinits.loancontrol.beans.Categoria;
import com.br.kopinits.loancontrol.beans.Usuario;
import com.br.kopinits.loancontrol.forms.BemMaterialForm;
import com.br.kopinits.loancontrol.negocio.BemMaterialNegocio;
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
public class ProcessarBemAction extends LCAction {

    @Override
    public String executar(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        
        Usuario usr = getUsuarioLogado(request);
        BemMaterialForm frm = (BemMaterialForm) form;

        CategoriaNegocio cateNegocio = new CategoriaNegocio(usr);
        request.setAttribute("listComboCategorias", cateNegocio.selectAll());
                
        if (frm.getAcao() != null && !frm.getAcao().equals("")) {
            BemMaterialNegocio negocio = new BemMaterialNegocio(usr);
            BemMaterial bean = new BemMaterial();
            bean.setCategoria(new Categoria());
            formToBean(bean, frm);
            bean.getCategoria().setUsuario(usr);
            if (frm.getAcao().equals(ConstantesAplicacao.SALVAR)) {
                if (bean.getId() != null) {
                    negocio.update(bean);
                } else {
                    negocio.create(bean);
                }
                setMensagemSessao("msg.operacao.sucesso", null, request);
                return ConstantesAplicacao.SALVOU;
            } else if (frm.getAcao().equals(ConstantesAplicacao.CARREGAR)) {
                bean = (BemMaterial) negocio.select(bean.getId());
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

    private void beanToForm(BemMaterial bean, BemMaterialForm frm) {
        frm.setId(bean.getId());
        frm.setDescricao(bean.getDescricao());
        frm.setEstado(bean.getEstado());
        frm.setIdCategoria(bean.getCategoria().getId().toString());
        frm.setDescCategoria(bean.getCategoria().getDescricao());
    }

    private void formToBean(BemMaterial bean, BemMaterialForm frm) {
        if (frm.getId() != null && frm.getId().longValue() > 0) {
            bean.setId(Long.valueOf(frm.getId()));
        }
        bean.setDescricao(frm.getDescricao());
        bean.setEstado(frm.getEstado());
        if (frm.getIdCategoria() != null && !frm.getIdCategoria().equals(""))
            bean.getCategoria().setId(Long.valueOf(frm.getIdCategoria()));
    }
}
