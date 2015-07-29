<%@page language="java" contentType="text/html" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<script type="text/javascript">
    function novo(){
        formEmprestimo.action = "<html:rewrite action="/incluirEmprestimo.do"/>";
        formEmprestimo.acao.value="";
        formEmprestimo.submit();        
    }
    function alterar(id){
        formEmprestimo.action = "<html:rewrite action="/emprestimo.do"/>";
        formEmprestimo.acao.value="CARREGAR";
        formEmprestimo.id.value=id;
        formEmprestimo.submit();        
    }
    function limpar(){
        formEmprestimo.nomePesquisa.value="";
        formEmprestimo.isPessoa[0].checked=true;
        formEmprestimo.todos[0].checked=true;
        formEmprestimo.acao.value="";
        formEmprestimo.submit();        
    }
</script>
<div class="conteudoInterno">
    <html:javascript formName="formEmprestimo"/>
    <h4><bean:message key="label.titulo.listar.emprestimo" /></h4>
    <br>
    <div class="boxPesquisa">
        <html:form action="/listarEmprestimo.do" method="post">
            <html:hidden property="acao" value="PESQUISAR" />
            <html:hidden property="id"/>
            <bean:message key="label.nome" />: <html:text property="nomePesquisa" size="40" maxlength="50" /> <html:submit styleClass="botao"><bean:message key="label.botao.pesquisar"/></html:submit>
            <html:button property="limpar" styleClass="botao" onclick="return window.limpar();"><bean:message key="label.botao.limpar"/></html:button>
            <br><br>
            <span class="tblPesquisa">Opções de filtro</span>
            <br>
            <table border="0" class="tblPesquisa">
                <tr>
                    <td><html:radio value="true" property="isPessoa">Pessoa</html:radio></td>
                    <td><html:radio value="false" property="isPessoa">Bem Material</html:radio></td>
                    <td>&nbsp;</td>
                </tr>
                <tr>
                    <td><html:radio value="1" property="todos">Todos</html:radio></td>
                    <td><html:radio value="2" property="todos">Emprestados</html:radio></td>
                    <td><html:radio value="3" property="todos">Devolvidos</html:radio></td>
                </tr>
            </table>
        </html:form>
    </div>
    <br>
    <logic:notEmpty name="listaEmprestimos">
        <display:table name="listaEmprestimos" id="item" pagesize="10" class="lista" requestURI="/listarEmprestimo.do">
            <display:setProperty name="paging.banner.placement" value="bottom" />
            <display:setProperty name="paging.banner.item_name" value="registro" />
            <display:setProperty name="paging.banner.items_name" value="registros" />
            <display:setProperty name="paging.banner.one_item_found" value="Um {0} encontrado."/>
            <display:setProperty name="paging.banner.some_items_found" value="{0} {1} encontrados. Exibindo de {2} até {3}" />
            <display:setProperty name="paging.banner.all_items_found" value="{0} {1} encontrados" />
            <display:setProperty name="paging.banner.first">  
                <div class="linksPaginacao">
                <br>
                [ <a href="{1}" title="Primeira">1ª</a> / <a href="{2}" title="Anterior"><<</a> ]  
                {0}  
                [ <a href="{3}" title="Próxima">>></a> / <a href="{4}" title="Última">Últ.</a>]  
                </style>
            </display:setProperty>              
            <display:setProperty name="paging.banner.last">
                <div class="linksPaginacao">
                <br>
                [ <a href="{1}" title="Primeira">1ª</a> / <a href="{2}" title="Anterior"><<</a> ]  
                {0}  
                [ <a href="{3}" title="Próxima">>></a> / <a href="{4}" title="Última">Últ.</a>]  
                </div>
            </display:setProperty>              
            <display:setProperty name="paging.banner.onepage" value="" />
                
                
            <display:column sortable="true" property="pessoa.nome" title="Pessoa" style="width:30%;"/> 
            <display:column sortable="true" title="Telefone" style="width:10%;"> 
                (${item.pessoa.ddd})&nbsp;${item.pessoa.telefone}
            </display:column>
            <display:column sortable="true" title="Celular" style="width:10%;"> 
                (${item.pessoa.dddCel})&nbsp;${item.pessoa.celular}
            </display:column>
            <display:column sortable="true" property="bemMaterial.descricao" title="Bem Material" style="width:20%;"/> 
            <display:column sortable="true" property="bemMaterial.categoria.descricao" title="Categoria" style="width:15%;"/> 
            <display:column sortable="true" property="status" title="Situação" style="width:10%;"/> 
            <display:column style="width:5%x;text-align:center" title="Alt." sortable="false">
            <c:choose>
                <c:when test="${item.dataDevolucao eq null}">
                    <a href="#" onClick="return alterar(${item.id})"><html:img style="{text-align:center;}" srcKey="img.editar.src" titleKey="label.hint.alterar"/></a>
                </c:when>
                <c:otherwise>&nbsp;</c:otherwise>
            </c:choose>
            </display:column>
        </display:table>
    </logic:notEmpty>
    <br>
    <i><bean:write name="msgResults" scope="request" /></i>
    
    <br><br>
    <div class="botoes">
        <html:button onclick="return novo();" property="btNovo" styleClass="botao"><bean:message key="label.botao.novo" /></html:button>
    </div>
</div>