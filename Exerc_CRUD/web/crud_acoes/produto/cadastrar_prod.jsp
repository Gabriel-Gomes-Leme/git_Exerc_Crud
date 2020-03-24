<%-- 
    Document   : cadastrar_prod
    Created on : 20/03/2020, 18:43:52
    Author     : Gabriel
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="modelo.Situacao"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="modelo.Produto"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Página de cadastro</title>
    </head>
<body>
    <h1>Cadastrar produto</h1>
    <form action="<%=application.getContextPath() %>/Controle_prod" method="POST">
        <input type="hidden" name="acao" id="acao" value="Cadastrar"/>
        <div>
             <label> Descrição :</label> <input type="text" id="txtdescricao" name="txtdescricao">
        </div>
        <div>
             <label> Quantidade: </label> <input type="text" id="txtquantidade" name="txtquantidade">
        </div>
        <div>
             <label> Quantidade: </label> <input type="text" id="txtquantidade" name="txtquantidade">
        </div>
        <div>
        <label> Situação: </select>
      <select name="cmbsituacao" id="cmbsituacao">
          <%
              ArrayList<Situacao> arrsituacao = new ArrayList<Situacao>();
              arrsituacao = (ArrayList<Situacao>) request.getSession().getAttribute("arrsituacao");
              for (Situacao obj : arrsituacao) {
          %>
          <option value="<%= obj.getId()%>"><%= obj.getDescricao()%></option>
          <%
              }
          %>
       </select>
        </div>
       <div>
            <input type="submit" name="enviar" id="enviar" value="Enviar">      
       </div>
            
    </form>

</body>

</html>
