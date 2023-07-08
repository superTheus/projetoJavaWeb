<%@ page language="java" contentType="text/html; utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import= "controller.GerenciarLogin, model.Usuario" %>

<%
    Usuario ulogado
            = GerenciarLogin.verificarAcesso(request, response);
    request.setAttribute("ulogado", ulogado);
%>

<c:choose>
    <c:when test="${ulogado != null}">
        <div class="d-md-flex justify-content-sm-between mt-2 user-area p-3">
            <h6 class="mt-1">Bem-vindo, ${ulogado.nome}</h6>
            <a href="gerenciarLogin?" 
               class="btn btn-primary btn-sm" role="button" >
                Sair&nbsp;<i class="fa-solid fa-user-lock"></i>
            </a>
        </div>

    </c:when>
    <c:otherwise>
        <% response.sendRedirect("index.jsp");%>
        <div class="d-md-flex justify-content-sm-end mt-2">
            <a href="formLogin.jsp"
               class="btn btn-info btn-sm mr-2"
               role="button">
                Login&nbsp;<i class="fa-solid fa-user-shield"></i>   
            </a>
        </div>
    </c:otherwise>
</c:choose>    

<header>
    <nav class="navbar navbar-expand-lg navbar-light">
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav ml-lg-auto">
                <li class="nav-item active">
                    <a class="nav-link" href="/projetoteste/index.jsp">Home <span class="sr-only">(current)</span></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="gerenciarPerfil?acao=listar">Perfis</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="gerenciarMenu?acao=listar">Menus</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="gerenciarUsuario?acao=listar">Usuários</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="gerenciarCliente?acao=listar">Clientes</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="gerenciarAgendamento?acao=listar">Agendamento</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="gerenciarServico?acao=listar">Servico</a>
                </li>
            </ul>

        </div>
    </nav>

</header>