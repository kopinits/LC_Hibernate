package com.br.kopinits.loancontrol.struts;

import com.br.kopinits.loancontrol.beans.BemMaterial;
import com.br.kopinits.loancontrol.beans.Emprestimo;
import com.br.kopinits.loancontrol.beans.Pessoa;
import com.br.kopinits.loancontrol.beans.Usuario;
import com.br.kopinits.loancontrol.forms.EmprestimoForm;
import com.br.kopinits.loancontrol.negocio.BemMaterialNegocio;
import com.br.kopinits.loancontrol.negocio.EmprestimoNegocio;
import com.br.kopinits.loancontrol.negocio.PessoaNegocio;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;


/**
 *
 * @author Markus
 */
public class ProcessarEmprestimoAction extends LCAction {

    @Override
    public String executar(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        Usuario usr = getUsuarioLogado(request);
        EmprestimoForm frm = (EmprestimoForm) form;

        PessoaNegocio pNegocio = new PessoaNegocio(usr);
        request.setAttribute("listComboPessoas", pNegocio.selectAll());

        BemMaterialNegocio bNegocio = new BemMaterialNegocio(usr);
        request.setAttribute("listComboBens", bNegocio.selectAllCombo());

        if (frm.getAcao() != null && !frm.getAcao().equals("")) {
            EmprestimoNegocio negocio = new EmprestimoNegocio(usr);
            Emprestimo bean = new Emprestimo();
            bean.setBemMaterial(new BemMaterial());
            bean.setPessoa(new Pessoa());
            formToBean(bean, frm);
            if (frm.getAcao().equals(ConstantesAplicacao.SALVAR)) {
                if (bean.getId() != null) {
                    negocio.processaSaida(bean);
                } else {
                    bean.setDataSaida(new Date());
                    negocio.create(bean);
                }
                setMensagemSessao("msg.operacao.sucesso", null, request);
                return ConstantesAplicacao.SALVOU;
            } else if (frm.getAcao().equals(ConstantesAplicacao.CARREGAR)) {
                bean = (Emprestimo) negocio.select(bean.getId());
                beanToForm(bean, frm);
            }
        }
        return ConstantesAplicacao.SUCESSO;

    }

    @Override
    protected boolean isAutenticar() {
        return true;
    }

    private void beanToForm(Emprestimo bean, EmprestimoForm frm) {
        frm.setId(bean.getId());
        frm.setEstado(bean.getEstadoDevolucao());
        frm.setIdBem(bean.getBemMaterial().getId().toString());
        frm.setIdPessoa(bean.getPessoa().getId().toString());
        frm.setNomeBem(bean.getBemMaterial().getDescricao());
        frm.setNomePessoa(bean.getPessoa().getNome());
    }

    private void formToBean(Emprestimo bean, EmprestimoForm frm) {
        if (frm.getId() != null && frm.getId().longValue() > 0) {
            bean.setId(Long.valueOf(frm.getId()));
        }
        bean.setEstadoDevolucao(frm.getEstado());
        if (frm.getIdBem() != null && !frm.getIdBem().equals("")) {
            bean.getBemMaterial().setId(Long.valueOf(frm.getIdBem()));
        }
        if (frm.getIdPessoa() != null && !frm.getIdPessoa().equals("")) {
            bean.getPessoa().setId(Long.valueOf(frm.getIdPessoa()));
        }
    }
}
