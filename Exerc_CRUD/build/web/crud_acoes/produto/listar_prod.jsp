<%-- 
    Document   : listar_produto
    Created on : 24/03/2020, 14:56:18
    Author     : Gabriel
--%>
<%@page import="java.util.ArrayList"%>
<%@page import="modelo.Produto"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Listar produtos/title>
    </head>
    <body>
       <h1>Produtos</h1>
        <a href="Controle_prod?acao=Cadastrar">Novo</a>
        <table border="1px">
            <thead>
                <tr>
                    <th>id</th>
                    <th>Descrição</th>
                    <th>Quantidade</th>                   
                    <th>Ações</th>
                </tr>
            </thead>
            <tbody>
            <%
                ArrayList<Produto> arrproduto = new ArrayList<Produto>();
                arrproduto = (ArrayList<Produto>) request.getSession().getAttribute("arrproduto");
                for (Produto objprod : arrproduto) {
            %>
             <tr>
                    <td> <%= objprod.getId()%></td>
                    <td> <%= objprod.getDescricao()%></td>
                    <td> <%= objprod.getQuantidade()%></td>
                    <td>
                        <a href="Controle_prod?acao=Buscar&id=<%= objprod.getId()%>">Alterar</a>
                        <a href="Controle_prod?acao=Consultar&id=<%= objprod.getId()%>">Consultar</a>
                        <a href="Controle_prod?acao=Excluir&id=<%= objprod.getId()%>">Excluir</a>
                    </td>
                </tr>
           
            
                <%
                }
            %>
    </body>
</html>
