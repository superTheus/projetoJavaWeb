<%@page contentType="text/html" pageEncoding="UTF-8"
        import="model.*"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width,
              initial-scale=1.0, shrink-to-fit=no">
        <link rel="stylesheet" href="bootstrap/bootstrap.min.css" 
              type="text/css">
        <link rel="stylesheet" href="css/nav.css" type="text/css">
        <link rel="stylesheet" href="webfonts/css/all.css" type="text/css">
        <link rel="stylesheet" href="datatables/css/dataTables.bootstrap4.min.css" type="text/css">
        <link rel="stylesheet" href="sweetalert/sweetalert.css" type="text/css">    
        <title>PROJETO ETB</title>
    </head>
    <body>
        <div id="container">
            <div id="header">
                <jsp:include page="template/header.jsp"></jsp:include>
                </div>
                <div id="menu">
                <jsp:include page="template/menu.jsp"></jsp:include>
                </div>
                <div id="conteudo">
                <%-- 
                 if( request.getAttribute("msg")!= null){
                            out.println(msg);
                %>
                <script type="text/javascript">
                    alert('<%= request.getAttribute("msg") %>');
                </script>
                <%
                
}               }
                --%>

                <%
                    if (request.getAttribute("msg") != null) {

                %> 
                <script type="text/javascript">
                    $(document).ready(function(){
                    Swal(
                            "Atenção",
                             "<%= request.getAttribute("msg")%>",
                            "error"
                            );
                </script>


                <%

                    }
                %>

                <form action="gerenciarAgendamento" method="POST">

                    <h3 class="text-center mt-5" style="padding-top:20px">
                        Página de Cadastro
                    </h3>
                    <input type="hidden" id="idagendamento" name="idAgendamento"
                           value ="${agendamento.idAgendamento}">

                    <div class="form-group row mt-5 offset-md-2">
                        <label for="idvalortotal"
                               class="col-md-3 col-form-label-lg">Valor Total
                        </label>
                        <div class="col-md-5">
                            <input type="text" id="idvalortotal" name="valortotal"
                                   class="form-control" value="${agendamento.valortotal}">
                        </div>


                    </div>



                    <div class="form-group row mt-5 offset-md-2">
                        <label for="idcliente"
                               class="col-md-3 col-form-label-lg mt-2">Cliente</label>
                        <div class="col-md-5">
                            <select id="idcliente" name="idCliente"
                                    class="form-control mt-2">
                                <jsp:useBean class="model.ClienteDAO" id="cdao">

                                    <c:forEach items="${cdao.lista}" var="c">
                                        <option value="${c.idCliente}"
                                                <c:if test="${c.idCliente == agendamento.cliente.idCliente}"> 
                                                    selected=""  
                                                </c:if> >${c.nome}
                                        </option>  
                                    </c:forEach>
                                </jsp:useBean>

                            </select>

                        </div>

                    </div>

                    <div class="form-group row mt-5 offset-md-2">
                        <label for="idusuario"
                               class="col-md-3 col-form-label-lg mt-2">Usuario</label>
                        <div class="col-md-5">
                            <select id="idusuario" name="idUsuario"
                                    class="form-control mt-2">
                                <jsp:useBean class="model.UsuarioDAO" id="udao">

                                    <c:forEach items="${udao.lista}" var="u">
                                        <option value="${u.idUsuario}"
                                                <c:if test="${u.idUsuario == agendamento.usuario.idUsuario}"> 
                                                    selected=""  
                                                </c:if> >${u.nome}
                                        </option>  
                                    </c:forEach>
                                </jsp:useBean>

                            </select>

                        </div>

                    </div>



                    <div class="form-group row mt-5 offset-md-2">
                        <label for="idstatus"
                               class="col-md-3 col-form-label-lg">Status
                        </label>
                        <div class="col-md-5">
                            <select id="idstatus" name="status"
                                    class="form-control mt-2">
                                <option value="">Escolha uma opção</option>
                                <option value="1"
                                        <c:if test="${agendamento.status == 1}">
                                            selected=""</c:if>>Ativado
                                        </option>
                                        <option value="0"
                                        <c:if test="${agendamento.status == 0}">
                                            selected=""</c:if>>Desativado
                                </option>

                            </select>
                        </div>

                    </div>
                    <div class="d-md-flex justify-content-md-end mt-5">
                        <button class="btn btn-primary btn-md mr-4">
                            Gravar&nbsp;<i class="fa-solid fa-floppy-disk"></i>
                        </button>
                        <a href="gerenciarAgendamento?acao=listar"
                           class="btn btn-danger btn-md mr-3"
                           role="button">
                            Voltar&nbsp;<i class="fa-solid fa-cirle-left"></i>

                        </a>

                    </div>



                </form>

            </div>

            <!-- JQuery.js -->
            <script src="js/jquery-3.6.0.min.js"></script>

            <!-- Popper via cdn -->
            <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha512-Ua/7Woz9L5O0cwB/aYexmgoaD7lw3dWe9FvXejVdgqu71gRog3oJgjSWQR55fwWx+WKuk8cl7UwA1RS6QCadFA==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
            <!-- Bootstrap.js -->
            <script src="js/bootstrap.min.js"></script>
            <script src="js/sweetalert.js"></script>

    </body>
</html>
