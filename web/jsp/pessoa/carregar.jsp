<%@page language="java" contentType="text/html" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<html:javascript formName="formPessoa"/>
<script type="text/javascript">
    function validar() {
        var nm = strTrim(formPessoa.nome.value);
        var msg = "";
        if (nm == "") {
            msg = "O Nome é obrigatório.\n"
        }
        if (msg != "") {
            alert(msg);
            return false;
        } else {
            formPessoa.submit();
            return true;
        }
    }
    function voltar(){
        formPessoa.action = "<html:rewrite action="/listarPessoa.do"/>";
        formPessoa.submit();        
    }
    
    $(document).ready(function(){
        formataTelefone("telefone");
        mascara( document.getElementById("telefone"), mtel );
        formataTelefone("celular");
        mascara( document.getElementById("celular"), mtel );

    });

</script>
<div class="conteudoInterno">
    <h4><bean:message key="label.titulo.manter.pessoa" /></h4>
    <br>
    <html:form action="/pessoa.do" method="post" styleId="formPessoa">
        <html:hidden property="acao" value="SALVAR"/>
        <html:hidden property="id"/>
        <table border="0" width="80%" align="center">
            <tr>
            <td style="text-align: right;">
                <bean:message key="label.nome" /> <span class="obrigatorio">*</span>:
            </td>
            <td>
                <html:text property="nome" size="40" maxlength="80" />
            </td>
            </tr>
            <tr>
            <td style="text-align: right;">
                <bean:message key="label.email" />:
            </td>
            <td>
                <html:text property="email" size="80" maxlength="100"/>
            </td>
            </tr>
            <tr>
            <tr>
            <td style="text-align: right;">
                <bean:message key="label.telefone" />:
            </td>
            <td>
                (<input type="text" name="ddd" value="${formPessoa.ddd}" size="1" maxlength="2" onkeyup="return somenteNumero(event);" onblur="return somenteNumero(event);" onpast="return false;" />)
                &nbsp;&nbsp;
                <input type="text" id="telefone" name="telefone" value="${formPessoa.telefone}" size="10" maxlength="10" onkeyup="return somenteNumero(event);" onblur="return somenteNumero(event);" onpast="return false;"  />
            </td>
            </tr>
            <tr>
            <td style="text-align: right;">
                <bean:message key="label.celular" />:
            </td>
            <td>
                (<input type="text" name="dddCelular" value="${formPessoa.dddCelular}" size="1" maxlength="2" onkeyup="return somenteNumero(event);" onblur="return somenteNumero(event);" onpast="return false;" />)
                &nbsp;&nbsp;
                <input type="text" id="celular" name="celular" value="${formPessoa.celular}" size="10" maxlength="10" onkeyup="return somenteNumero(event);" onblur="return somenteNumero(event);" onpast="return false;"  />
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