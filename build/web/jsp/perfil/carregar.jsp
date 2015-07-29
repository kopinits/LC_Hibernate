<%@page language="java" contentType="text/html" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<html:javascript formName="formBem"/>
<script type="text/javascript">
    function validar() {
        var nm = strTrim(formBem.descricao.value);
        var es = strTrim(formBem.estado.value);
        var ct = formBem.idCategoria.value;
        var msg = "";
        if (nm == "") {
            msg = "A Descrição é obrigatória.\n"
        }
        if (ct == "") {
            msg += "A Categoria é obrigatória.\n"
        }
        if (es == "") {
            msg += "O Estado Atual é obrigatório.\n"
        }
        if (msg != "") {
            alert(msg);
            return false;
        } else {
            formBem.submit();
            return true;
        }
    }
    function voltar(){
        formBem.action = "<html:rewrite action="/listarBem.do"/>";
        formBem.submit();        
    }
</script>
<div class="conteudoInterno">
    <h4><bean:message key="label.titulo.manter.perfil" /></h4>
    <br>
    <html:form action="/salvar.do" method="post" styleId="formPerfil">
        <html:hidden property="acao" value="SALVAR"/>
        <html:hidden property="id"/>
        <table border="0" width="80%" align="center">
            <tr>
                <td style="text-align: right;">
                    <bean:message key="label.descricao" /> <span class="obrigatorio">*</span>:
                </td>
                <td>
                    <html:text property="descricao" size="40" maxlength="50" styleId="descricao"/>
                </td>
            </tr>
            <tr>
                <td style="text-align: right;">
                    <bean:message key="label.categoria" /> <span class="obrigatorio">*</span>:
                </td>
                <td>
                    <html:select property="idCategoria" style="width:262px;" styleId="idCategoria">
                        <html:option value=""/>
                        <html:options collection="listComboCategorias" labelProperty="descricao" property="id" />
                    </html:select>
                </td>
            </tr>
            <tr>
                <td style="text-align: right;">
                    <bean:message key="label.estado" /> <span class="obrigatorio">*</span>:
                </td>
                <td>
                    <html:text property="estado" size="80" maxlength="200" styleId="estado"/>
                </td>
            </tr>
        </table>

        <br><br>
        <div class="botoes">
            <html:button onclick="return window.validar();" property="btSalvar" styleClass="botao"><bean:message key="label.botao.salvar" /></html:button>
            <html:button onclick="return window.voltar();" property="btCancelar" styleClass="botao"><bean:message key="label.botao.cancelar" /></html:button>
        </div>
        <div class="obrigatorio"><bean:message key="campos.obrigatorios" /></div>
    </html:form>
</div>