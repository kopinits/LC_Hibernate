<%@page language="java" contentType="text/html" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<html:javascript formName="formLogin"/>
<script type="text/javascript">
    function altSenha(){
        formLogin.action = "<html:rewrite action="/trocaSenha.do"/>";
        formLogin.acao.value="TROCASENHA";
        formLogin.submit();
    }
    function validar() {
        var nm = document.getElementById('nome').value;
        var lg = document.getElementById('login').value;
        var mail = document.getElementById('email').value;
        var msg = "";
        if (nm == "") {
            msg = "O nome é obrigatório\n";
        }

        if (lg == "") {
            msg += "O login é obrigatório\n";
        }

        if (mail == "") {
            msg += "O e-mail é obrigatório\n";
        }

        if (msg != "") {
            alert(msg);
            return false;
        } else {
            document.forms["formLogin"].submit();
            return true;
        }
    }
</script>
<div class="conteudoInterno">
    <h4><bean:message key="label.titulo.manter.usuario" /></h4>
    <br>
    <html:form action="/salvarDados.do" method="post" styleId="formLogin">
        <html:hidden property="acao" value="SALVAR"/>
        <html:hidden property="id"/>
        <table border="0" width="80%" align="center">
            <tr>
            <td style="text-align: right;">
                <bean:message key="label.nome" />:
            </td>
            <td>
                <html:text property="nome" size="40" maxlength="80" styleId="nome"/>
            </td>
            </tr>
            <tr>
            <td style="text-align: right;">
                <bean:message key="label.login" />:
            </td>
            <td>
                <html:text property="login" size="40" maxlength="50" styleId="login"/>
            </td>
            </tr>
            <tr>
            <td style="text-align: right;">
                <bean:message key="label.email" />:
            </td>
            <td>
                <html:text property="email" size="40" maxlength="50" styleId="email"/>
            </td>
            </tr>
        </table>

        <br><br>
        <div class="botoes">
            <html:button onclick="return validar();" property="btSalvar" styleClass="botao"><bean:message key="label.botao.salvar" /></html:button>
            <html:button onclick="return altSenha();" property="btAltSenha" styleClass="botao"><bean:message key="label.botao.altSenha" /></html:button>
        </div>
    </html:form>
</div>