<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
        <link rel="stylesheet" href="css/style.css" type="text/css">
        <link rel="stylesheet" href="webfonts/css/all.css" type="text/css">
        <link rel="stylesheet" href="datatables/css/dataTables.bootstrap4.min.css" type="text/css">
        <link rel="stylesheet" href="sweetalert2/sweetalert2.min.css" 
              type ="text/css">
        <link rel="stylesheet" href="css/styles.css" type="text/css">
        <title>PROJETO ETB</title>
    </head>

    <body>

        <header id="header">
            <div>
                <jsp:include page="template/header.jsp"></jsp:include>
                </div>
<!--                <div id="menu">
                <jsp:include page="template/menu.jsp"></jsp:include>
                </div>-->
            </header>
            <main id="main">
                <form action="gerenciarLogin" method="POST">

                    <h3 class="text-center mt-lg-5">Página de Login</h3>

                    <div class="form-group row offset-md-3 mt-5">
                        <label class="col-md-2 form-label">Usuário
                        </label> 
                        <div class="col-md-5 ">
                            <input type="text" name="login" id="idlogin"
                                   class="form-control" value="">
                        </div>

                    </div>

                    <div class="form-group row offset-md-3 mt-5">
                        <label class="col-md-2 form-label">Senha
                        </label>
                        <div class="col-md-5">
                            <input type="password" name="senha" id="idsenha"
                                   class="form-control" value="">
                        </div>

                    </div>
                    <div class="d-md-flex justify-content-md-end mr-3 mt-5 offset-md-3">
                        <button class="btn btn-primary btn-md mr-2">
                            login&nbsp;<i class="fa-solid fa-lock-open"></i>
                        </button>

                    </div>
                </form>
            </main>

        <jsp:include page="template/footer.jsp"></jsp:include>

            <!-- JQuery.js -->
            <script src="js/jquery-3.6.0.min.js"></script>

            <!-- Popper via cdn -->
            <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha512-Ua/7Woz9L5O0cwB/aYexmgoaD7lw3dWe9FvXejVdgqu71gRog3oJgjSWQR55fwWx+WKuk8cl7UwA1RS6QCadFA==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
            <!-- Bootstrap.js -->
            <script src="js/bootstrap.min.js"></script>
            <script src="sweetalert2/sweetalert2.min.js"></script>

        <%
            if (request.getAttribute("msg") != null) {
                String msg = (String) request.getAttribute("msg");

        %>
        <script type="text/javascript">
            $(document).ready(function () {
                Swal.fire({
                    position: 'top-center',
                    icon: 'error',
                    title: 'Atenção',
                    text: '<%= msg%>',
                    showConfirmButton: false,
                    timer: 3000
                });

            });


        </script>


        <%

            }

        %>


        <%             if (request.getAttribute("mensagem") != null) {
                String mensagem = (String) request.getAttribute("mensagem");

        %>

        <script type="text/javascript">
            $(document).ready(function () {

                Swal.fire({
                    position: 'top-center',
                    icon: 'error',
                    title: 'Atenção',
                    text: '<%= mensagem%>',
                    showConfirmButton: false,
                    timer: 3000
                });


            });


        </script>




        <%
            }
        %>







    </body>
</html>
