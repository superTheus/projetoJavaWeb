
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Usuario;
import model.UsuarioDAO;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import model.Perfil;


@WebServlet(name = "GerenciarUsuario", 
        urlPatterns = {"/gerenciarUsuario"})
public class GerenciarUsuario extends HttpServlet {

    
    @Override
    protected void doGet(HttpServletRequest request, 
        HttpServletResponse response)
        throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        response.setContentType("text/html;charset=utf-8");
        String acao = request.getParameter("acao");
        String idUsuario = request.getParameter("idUsuario");
        String mensagem = "";
        
        Usuario usuario = new Usuario();
        UsuarioDAO udao = new UsuarioDAO();
        
        try {
            if(acao.equals("listar")){
                ArrayList<Usuario> usuarios = new ArrayList<>();
                usuarios = udao.getLista();
                RequestDispatcher dispatcher =
                    getServletContext().
                        getRequestDispatcher("/listarUsuarios.jsp");
                request.setAttribute("usuarios", usuarios);
                dispatcher.forward(request, response);
            }else if(acao.equals("alterar")){
                usuario = udao.getCarregarPorId(
                        Integer.parseInt(idUsuario));
                RequestDispatcher dispatcher =
                    getServletContext().
                        getRequestDispatcher("/cadastrarUsuario.jsp");
                request.setAttribute("usuario", usuario);
                dispatcher.forward(request, response);
            }else if(acao.equals("ativar")){
                usuario.setIdUsuario(Integer.parseInt(idUsuario));
                System.out.println("Código: "  +idUsuario);
                if(udao.ativar(usuario)){
                    mensagem = "Usuário ativado com sucesso!";
                }else{
                    mensagem = "Falha ao ativar o usuário!";
                }
            
            }else if(acao.equals("desativar")){
                usuario.setIdUsuario(Integer.parseInt(idUsuario));
                System.out.println("Código: "  +idUsuario);
                if(udao.desativar(usuario)){
                    mensagem = "Usuário desativado com sucesso!";
                }else{
                    mensagem = "Falha ao desativar o usuário!";
                    
                }
            
            }else{
                response.sendRedirect("index.jsp");
            }
            
        } catch (SQLException e) {
            mensagem = "Erro: " + e.getMessage();
            e.printStackTrace();
        }
        
         out.println(
           "<script type='text/javascript'>" +
           "alert('" + mensagem + "');" +
           "location.href='gerenciarUsuario?acao=listar';" +
           "</script>"
       );
        
    }

   
    @Override
    protected void doPost(HttpServletRequest request, 
        HttpServletResponse response)
        throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        response.setContentType("text/html");
        String idUsuario = request.getParameter("idUsuario");
        String nome = request.getParameter("nome");
        String login = request.getParameter("login");
        String senha = request.getParameter("senha");
        String status = request.getParameter("status");
        String idPerfil = request.getParameter("idPerfil");
        String mensagem = "";
        
        Usuario u = new Usuario();
        
        if(!idUsuario.isEmpty()){
            u.setIdUsuario(Integer.parseInt(idUsuario));
        }
        
        if(nome.equals("") || nome.isEmpty()){
            request.setAttribute("msg", "Informe o nome do usuário!");
            despacharRequisicao(request, response);
        }else{
            u.setNome(nome);
            
        }
        
        if(login.equals("") || login.isEmpty()){
            request.setAttribute("msg", 
                    "Informe o nome do login!");
            despacharRequisicao(request, response);
        }else{
            u.setLogin(login);
        }
        
        if(senha.equals("") || senha.isEmpty()){
            request.setAttribute("msg", 
                    "Informe a senha do usuário!");
            despacharRequisicao(request, response);
        }else{
            u.setSenha(senha);
        }
        
        if(status.equals("") || status.isEmpty()){
            request.setAttribute("msg", 
                    "Informe o status do usuário!");
            despacharRequisicao(request, response);
        }else{
            try {
                 u.setStatus(Integer.parseInt(status));
            } catch (NumberFormatException e) {
                mensagem = "Erro: " + e.getMessage();
                e.printStackTrace();
            }
            
           
        }
        
        Perfil p = new Perfil();
        
        if(idPerfil.equals("") || idPerfil.isEmpty()){
            request.setAttribute("msg", "Informe o Perfil!");
            despacharRequisicao(request, response);
        }else{
            try {
                p.setIdPerfil(Integer.parseInt(idPerfil));
                //associacao entre objeto usuario e o objeto perfil
                u.setPerfil(p);
            } catch (Exception e) {
                mensagem = "Erro: " + e.getMessage();
                e.printStackTrace();
            }
            
        }
        
        UsuarioDAO udao = new UsuarioDAO();
        try {
            if(udao.gravar(u)){
                mensagem = 
                    "Usuário gravado com sucesso na base de dados!";
            }else{
                mensagem =
                    "Falha ao gravar o usuário na base de dados!";
            }
            
        } catch (SQLException e) {
            mensagem = "Erro: " + e.getMessage();
            e.printStackTrace();
        }
        
        out.println(
           "<script type='text/javascript'>" +
           "alert('" + mensagem + "');" +
           "location.href='gerenciarUsuario?acao=listar';" +
           "</script>"
       );
        
        
        
           
    }
    
    private void despacharRequisicao(HttpServletRequest request, 
        HttpServletResponse response)
        throws ServletException, IOException{
            getServletContext().
                getRequestDispatcher("/cadastrarUsuario.jsp").
                    forward(request, response);
        
    }

  
}
