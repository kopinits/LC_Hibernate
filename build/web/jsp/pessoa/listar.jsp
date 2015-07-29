<%@page language="java" contentType="text/html" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<script type="text/javascript">
    function novo(){
        formPessoa.action = "<html:rewrite action="/incluirPessoa.do"/>";
        formPessoa.acao.value="";
        formPessoa.submit();        
    }
    function alterar(id){
        formPessoa.action = "<html:rewrite action="/pessoa.do"/>";
        formPessoa.acao.value="CARREGAR";
        formPessoa.id.value=id;
        formPessoa.submit();        
    }
    function excluir(id){
        if (confirm('Deseja excluir o registro selecionado?')){
            formPessoa.action = "<html:rewrite action="/pessoa.do"/>";
            formPessoa.acao.value="EXCLUIR";
            formPessoa.id.value=id;
            formPessoa.submit();        
        }
    }
    function limpar(){
        formPessoa.nomePesquisa.value="";
        formPessoa.acao.value="";
        formPessoa.campoPesquisa[0].checked=true;
        formPessoa.submit();        
    }
</script>
<div class="conteudoInterno">
    <html:javascript formName="formPessoa"/>
    <h4><bean:message key="label.titulo.listar.pessoa" /></h4>
    <br>
    <div class="boxPesquisa">
        <html:form action="/listarPessoa.do" method="post">
            <html:hidden property="acao" value="PESQUISAR" />
            <html:hidden property="id"/>
            <bean:message key="label.nome" />: <html:text property="nomePesquisa" size="40" maxlength="50" /> <html:submit styleClass="botao"><bean:message key="label.botao.pesquisar"/></html:submit>
            <html:button property="limpar" styleClass="botao" onclick="return window.limpar();"><bean:message key="label.botao.limpar"/></html:button>
            <br><br>
            <span class="tblPesquisa">Opções de filtro</span>
            <br>
            <table border="0" class="tblPesquisa">
                <tr>
                    <td><html:radio value="0" property="campoPesquisa">Nome</html:radio></td>
                    <td><html:radio value="1" property="campoPesquisa">E-mail</html:radio></td>
                    <td><html:radio value="2" property="campoPesquisa">Telefone</html:radio></td>
                    <td><html:radio value="3" property="campoPesquisa">Celular</html:radio></td>
                </tr>
            </table>
        </html:form>
    </div>
    <br>
    <logic:notEmpty name="listaPessoas">
        <display:table name="listaPessoas" id="item" pagesize="10" defaultsort="1" class="lista" requestURI="/listarPessoa.do">
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
                
                
            <display:column sortable="true" property="nome" title="Nome" style="width:40%;"/> 
            <display:column sortable="true" property="email" title="E-mail" style="width:20%;"/> 
            <display:column sortable="true" title="Telefone" style="width:15%;"> 
                (${item.ddd})&nbsp;${item.telefone}
            </display:column>
            <display:column sortable="true" title="Celular" style="width:15%;"> 
                (${item.dddCel})&nbsp;${item.celular}
            </display:column>
            <display:column style="width:5%x;text-align:center" title="Alt." sortable="false">
                <a href="#" onClick="return alterar(${item.id})"><html:img style="{text-align:center;}" srcKey="img.editar.src" titleKey="label.hint.alterar"/></a>
            </display:column>
            <display:column style="width:5%;text-align:center" title="Exc." sortable="false">
                <a href="#" onClick="return excluir(${item.id})"><html:img style="{text-align:center;}" srcKey="img.excluir.src" titleKey="label.hint.excluir"/></a>
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