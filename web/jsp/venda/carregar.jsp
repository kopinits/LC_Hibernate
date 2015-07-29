<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page language="java" contentType="text/html" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<html:javascript formName="formVenda"/>
<script type="text/javascript">
    function validar() {
        var ps = formVenda.valor.value;
        var bem = formVenda.idBem.value;
        var es = "";
        
        var msg = "";
        if (ps == "") {
            msg = "O Valor é obrigatório.\n"
        }
        if (bem == "") {
            msg += "O Bem Material é obrigatório.\n"
        }
       
        if (msg != "") {
            alert(msg);
            return false;
        } else {
            formVenda.submit();
            return true;
        }
    }
    function voltar(){
        formVenda.action = "<html:rewrite action="/listarVenda.do"/>";
        formVenda.submit();        
    }
    
</script>
<div class="conteudoInterno">
    <h4><bean:message key="label.titulo.manter.venda" /></h4>
    <br>
    <html:form action="/venda.do" method="post" styleId="formVenda">
        <html:hidden property="acao" value="SALVAR"/>
        <html:hidden property="id"/>
        <table border="0" width="80%" align="center">
            <tr>
                <td style="text-align: right;">
                    <bean:message key="label.menu.bemmaterial" /> <span class="obrigatorio">*</span>:
                </td>
                <td>
                    <c:if test="${formVenda.id ne ''}">
                        ${formVenda.nomeBem}
                        <html:hidden property="idBem"/>
                    </c:if>
                    <c:if test="${formVenda.id eq ''}">
                    <html:select property="idBem" style="width:262px;" styleId="idBem">
                        <html:option value=""/>
                        <html:options collection="listComboBens" labelProperty="descricao" property="id" />
                    </html:select>
                    </c:if>
                </td>
            </tr>
            <tr>
                <td style="text-align: right;">
                    <bean:message key="label.pessoa" />:
                </td>
                <td>
                    <c:if test="${formVenda.id ne ''}">
                        ${formVenda.nomePessoa}
                        <html:hidden property="idPessoa"/>
                    </c:if>
                    <c:if test="${formVenda.id eq ''}">
                    <html:select property="idPessoa" style="width:262px;" styleId="idPessoa">
                        <html:option value=""/>
                        <html:options collection="listComboPessoas" labelProperty="nome" property="id" />
                    </html:select>
                    </c:if>
                </td>
            </tr>
             <tr>
                <td style="text-align: right;">
                    <bean:message key="label.nome.comprador" />:
                </td>
                <td>
                    <html:text property="nomeComprador" size="80" maxlength="100" />
                </td>
            </tr>
            <tr>
                <td style="text-align: right;">
                    <bean:message key="label.valor" />:
                </td>
                <td>
                    <html:text property="valor" styleId="valor" size="20" maxlength="18" onkeypress="return(mascaraMoeda(this,'.',',',event,22))" onblur="return somenteNumero(event);" onkeyup="return somenteNumero(event);"/>
                </td>
            </tr>
            <tr>
                <td style="text-align: right;">
                    <bean:message key="label.motivo" />:
                </td>
                <td>
                    <html:text property="motivoVenda" size="80" maxlength="100" />
                </td>
            </tr>
        </table>

        <br><br>
        <div class="botoes">
            <c:if test="${formVenda.id eq ''}">
                <html:button onclick="return window.validar();" property="btSalvar" styleClass="botao"><bean:message key="label.botao.salvar" /></html:button>
            </c:if>
            <c:if test="${formVenda.id ne ''}">
                <html:button onclick="return window.validar();" property="btSalvar" styleClass="botao"><bean:message key="label.botao.devolver" /></html:button>
            </c:if>
            <html:button onclick="return window.voltar();" property="btCancelar" styleClass="botao"><bean:message key="label.botao.cancelar" /></html:button>
        </div>
        <div class="obrigatorio"><bean:message key="campos.obrigatorios" /></div>
    </html:form>
</div>