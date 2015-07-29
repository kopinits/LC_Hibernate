<%@page language="java" contentType="text/html" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<html:javascript formName="formCategoria"/>
<script type="text/javascript">
    function validar() {
        var nm = strTrim(formCategoria.descricao.value);
        if (nm == "") {
            alert("A descrição é obrigatória");
            return false;
        } else {
            formCategoria.submit();
            return true;
        }
    }
    function voltar(){
        formCategoria.action = "<html:rewrite action="/listarCategoria.do"/>";
        formCategoria.submit();        
    }
</script>
<div class="conteudoInterno">
    <h4><bean:message key="label.titulo.manter.categoria" /></h4>
    <br>
    <html:form action="/categoria.do" method="post" styleId="formCategoria">
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
        </table>

        <br><br>
        <div class="botoes">
            <html:button onclick="return window.validar();" property="btSalvar" styleClass="botao"><bean:message key="label.botao.salvar" /></html:button>
            <html:button onclick="return window.voltar();" property="btCancelar" styleClass="botao"><bean:message key="label.botao.cancelar" /></html:button>
        </div>
        <div class="obrigatorio"><bean:message key="campos.obrigatorios" /></div>
    </html:form>
</div>