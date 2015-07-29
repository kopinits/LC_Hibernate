package com.br.kopinits.loancontrol.struts;

import com.br.kopinits.loancontrol.beans.BemMaterial;
import com.br.kopinits.loancontrol.beans.Pessoa;
import com.br.kopinits.loancontrol.beans.Usuario;
import com.br.kopinits.loancontrol.beans.Venda;
import com.br.kopinits.loancontrol.forms.VendaForm;
import com.br.kopinits.loancontrol.negocio.BemMaterialNegocio;
import com.br.kopinits.loancontrol.negocio.PessoaNegocio;
import com.br.kopinits.loancontrol.negocio.VendaNegocio;
import java.util.Date;
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
public class ProcessarVendaAction extends LCAction {

    @Override
    public String executar(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        Usuario usr = getUsuarioLogado(request);
        VendaForm frm = (VendaForm) form;

        PessoaNegocio pNegocio = new PessoaNegocio(usr);
        request.setAttribute("listComboPessoas", pNegocio.selectAll());

        BemMaterialNegocio bNegocio = new BemMaterialNegocio(usr);
        request.setAttribute("listComboBens", bNegocio.selectAllCombo());

        if (frm.getAcao() != null && !frm.getAcao().equals("")) {
            VendaNegocio negocio = new VendaNegocio(usr);
            Venda bean = new Venda();
            bean.setBemMaterial(new BemMaterial());
            bean.setPessoa(new Pessoa());
            formToBean(bean, frm);
            if (frm.getAcao().equals(ConstantesAplicacao.SALVAR)) {
                bean.setDataVenda(new Date());
                negocio.create(bean);
                setMensagemSessao("msg.operacao.sucesso", null, request);
                return ConstantesAplicacao.SALVOU;
            } else if (frm.getAcao().equals(ConstantesAplicacao.CARREGAR)) {
                bean = (Venda) negocio.select(bean.getId());
                beanToForm(bean, frm);
                return ConstantesAplicacao.CARREGAR;
            }
        }
        return ConstantesAplicacao.SUCESSO;

    }

    @Override
    protected boolean isAutenticar() {
        return true;
    }

    private void beanToForm(Venda bean, VendaForm frm) {
        frm.setId(bean.getId());
        frm.setIdBem(bean.getBemMaterial().getId());
        if (bean.getPessoa() != null) {
            frm.setIdPessoa(bean.getPessoa().getId());
            frm.setNomeComprador(bean.getPessoa().getNome());
        } else {
            frm.setNomeComprador(bean.getNomeComprador());
        }

        frm.setDescBem(bean.getBemMaterial().getDescricao());
        frm.setDescCategoria(bean.getBemMaterial().getCategoria().getDescricao());
        frm.setMotivoVenda(bean.getMotivoVenda());
        frm.setDataVenda(bean.getDataVenda());
        frm.setValor(bean.getValor());
    }

    private void formToBean(Venda bean, VendaForm frm) {
        bean.setId(frm.getId());
        bean.getBemMaterial().setId(frm.getIdBem());
        if (frm.getIdPessoa() != null && frm.getIdPessoa().longValue() > 0) {
            bean.getPessoa().setId(frm.getIdPessoa());
        }
        bean.setValor(frm.getValor());
        bean.setNomeComprador(frm.getNomeComprador());
        bean.setMotivoVenda(frm.getMotivoVenda());
    }
}
