<%@page language="java" contentType="text/html" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<html:javascript formName="formLogin"/>
<script type="text/javascript">
    function validar() {
        var nm = strTrim(document.getElementById('nome').value);
        var lg = strTrim(document.getElementById('login').value);
        var mail = strTrim(document.getElementById('email').value);
        var pw = strTrim(document.getElementById('senha').value);
        var pw2 = strTrim(document.getElementById('repeteSenha').value);
        var msg = "";
        if (nm == ""){
            msg = "O nome é obrigatório\n";
        }
        
        if (lg == ""){
            msg += "O login é obrigatório\n";
        }
        
        if (mail == ""){
            msg += "O e-mail é obrigatório\n";
        }else{
            if (!isEmail(mail)){
                msg += "E-mail inválido\n";
            }
        }
        
        if (pw == ""){
            msg += "A senha é obrigatória\n";
        } else{
            if (pw != pw2){
                msg += "As senhas não conferem";
            }
        }
        if (pw.length < 4){
            msg += "A senha deve possuir no mínimo 4 caracteres";
        }

        if (msg != "") {
            alert(msg);
            return false;
        } else {
            document.getElementById('formCadastro').submit();
            return true;
        }
    }
</script>
<table border="0" width="100%" cellpadding="40">
    <tr>
    <td width="50%" valign="middle" align="center">
        <h4>
            No <bean:message key="app.titulo" /> você pode controlar seus pertences de forma simples e objetiva!<br><br>
            Saiba com quem está seu pertence, a quanto tempo, dispare e-mail de solicitação de devolução e muito mais!
            <br><br>
        </h4>
        <img src="imgs/emprestar.png" border="0">
    </td>
    <td width="50%" valign="center" align="center">
        <div class="tabelaAzul">
            <h4><bean:message key="label.cadastre.se" /></h4><br><br>
            <html:form action="/cadastro.do" method="post" styleId="formCadastro">
                <table border="0" width="80%" align="center">
                    <tr>
                    <td style="text-align: right;">
                        <bean:message key="label.nome" /> <span class="obrigatorio-branco">*</span>:
                    </td>
                    <td>
                        <html:text property="nome" size="40" maxlength="80" styleId="nome"/>
                    </td>
                    </tr>
                    <tr>
                    <td style="text-align: right;">
                        <bean:message key="label.login" /> <span class="obrigatorio-branco">*</span>:
                    </td>
                    <td>
                        <html:text property="login" size="40" maxlength="50" styleId="login"/>
                    </td>
                    </tr>
                    <tr>
                    <td style="text-align: right;">
                        <bean:message key="label.email" /> <span class="obrigatorio-branco">*</span>:
                    </td>
                    <td>
                        <html:text property="email" size="40" maxlength="50" styleId="email"/>
                    </td>
                    </tr>
                    <tr>
                    <td style="text-align: right;">
                        <bean:message key="label.senha" /> <span class="obrigatorio-branco">*</span>:
                    </td>
                    <td>
                        <html:password property="senha" size="40" maxlength="10" styleId="senha"/>
                    </td>
                    </tr>
                    <tr>
                    <td style="text-align: right;">
                        <bean:message key="label.repete.senha" /> <span class="obrigatorio-branco">*</span>:
                    </td>
                    <td>
                        <html:password property="repeteSenha" size="40" maxlength="10" styleId="repeteSenha"/>
                    </td>
                    </tr>
                    <tr>
                        <td colspan="2"><br><html:button property="cadastrar" styleClass="botao" onclick="return validar();"><bean:message key="label.botao.cadastrar"/></html:button></td>
                    </tr>
                    <tr>
                        <td colspan="2"><div class="obrigatorio-branco"><bean:message key="campos.obrigatorios" /></div></td>
                    </tr>
                    </table>
            </html:form>
        </div>
    </td>
</tr>
</table>