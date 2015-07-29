<%@page language="java" contentType="text/html" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<script type="text/javascript">
    function novo(){
        formCategoria.action = "<html:rewrite action="/incluirCategoria.do"/>";
        formCategoria.acao.value="";
        formCategoria.submit();        
    }
    function alterar(id){
        formCategoria.action = "<html:rewrite action="/categoria.do"/>";
        formCategoria.acao.value="CARREGAR";
        formCategoria.id.value=id;
        formCategoria.submit();        
    }
    function excluir(id){
        if (confirm('Deseja excluir o registro selecionado?')){
            formCategoria.action = "<html:rewrite action="/categoria.do"/>";
            formCategoria.acao.value="EXCLUIR";
            formCategoria.id.value=id;
            formCategoria.submit();        
        }
    }
    function limpar(){
        formCategoria.nomePesquisa.value="";
        formCategoria.acao.value="";
        formCategoria.submit();        
    }
</script>
<div class="conteudoInterno">
    <html:javascript formName="formCategoria"/>
    <h4><bean:message key="label.titulo.listar.categoria" /></h4>
    <br>
    <div class="boxPesquisa">
        <html:form action="/listarCategoria.do" method="post">
            <html:hidden property="acao" value="PESQUISAR" />
            <html:hidden property="id"/>
            <bean:message key="label.nome" />: <html:text property="nomePesquisa" size="40" maxlength="50" /> <html:submit styleClass="botao"><bean:message key="label.botao.pesquisar"/></html:submit>
            <html:button property="limpar" styleClass="botao" onclick="return window.limpar();"><bean:message key="label.botao.limpar"/></html:button>
        </html:form>
    </div>
    <br>
    <logic:notEmpty name="listaCategorias">
        <display:table name="listaCategorias" id="categoria" pagesize="10" defaultsort="1" class="lista" requestURI="/listarCategoria.do">
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
                
                
            <display:column sortable="true" property="descricao" title="Descrição" style="width:90%;"/> 
            <display:column style="width:5%x;text-align:center" title="Alt." sortable="false">
                <a href="#" onClick="return alterar(${categoria.id})"><html:img style="{text-align:center;}" srcKey="img.editar.src" titleKey="label.hint.alterar"/></a>
            </display:column>
            <display:column style="width:5%;text-align:center" title="Exc." sortable="false">
                <a href="#" onClick="return excluir(${categoria.id})"><html:img style="{text-align:center;}" srcKey="img.excluir.src" titleKey="label.hint.excluir"/></a>
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