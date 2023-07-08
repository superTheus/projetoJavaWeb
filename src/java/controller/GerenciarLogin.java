
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Usuario;
import model.UsuarioDAO;
import java.sql.SQLException;
import javax.servlet.http.HttpSession;
import model.Menu;


@WebServlet(name = "GerenciarLogin", 
        urlPatterns = {"/gerenciarLogin"})
public class GerenciarLogin extends HttpServlet {

    private static HttpServletResponse response;

  
    @Override
    protected void doGet(HttpServletRequest request, 
        HttpServletResponse response)
        throws ServletException, IOException {
        HttpSession sessao = request.getSession();
        if(sessao.getAttribute("ulogado") != null){
            sessao.removeAttribute("ulogado");
            sessao.invalidate();
            response.sendRedirect("index.jsp");
        }
      
    }

   
    @Override
    protected void doPost(HttpServletRequest request, 
        HttpServletResponse response)
        throws ServletException, IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();
        GerenciarLogin.response = response;
        String login = request.getParameter("login");
        String senha = request.getParameter("senha");
                
        Usuario usuario = new Usuario();
        UsuarioDAO udao = new UsuarioDAO();
       
        
        if(login.equals("") || login.isEmpty()){
            request.setAttribute("msg", "Informe o login do usuário!");
            despacharRequisicao(request, response);
        }else{
            usuario.setLogin(login);
        }
        
        if(senha.equals("") || senha.isEmpty()){
            request.setAttribute("msg", "Informe a senha do usuário!");
            despacharRequisicao(request, response);
        }else{
            usuario.setSenha(senha);
        }
        
        try {
             usuario = udao.getRecuperarUsuario(login);
             if((usuario.getIdUsuario() >0) && 
                     (usuario.getSenha().equals(senha))){
                 HttpSession sessao = request.getSession();
                 sessao.setAttribute("ulogado", usuario);
                 response.sendRedirect("index.jsp");
                          
             }else{
                 request.setAttribute("msg", "Login ou senha inválidos");
                 despacharRequisicao(request, response);
                
             }
            
        } catch (SQLException e) {
            System.out.println("Erro: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    public static Usuario verificarAcesso(HttpServletRequest request, 
        HttpServletResponse response)
        throws ServletException, IOException{
        GerenciarLogin.response = response;
        Usuario usuario = null;
        
        try{
            HttpSession sessao = request.getSession();
            if(sessao.getAttribute("ulogado") == null){
                sessao.setAttribute(
                    "msg", "Usuário não autenticado no sistema!");
                response.sendRedirect("formLogin.jsp");
            
            }else{
                String uri = request.getRequestURI();
                String queryString =request.getQueryString();
                if(queryString != null){
                    uri += "?" + queryString;
                }
                usuario = 
                    (Usuario) request.getSession().
                        getAttribute("ulogado");
        
                if(usuario == null){
                    sessao.setAttribute(
                        "msg", "Usuário não autenticado no sistema!");
                    response.sendRedirect("formLogin.jsp");
                }else{
                    boolean possuiAcesso = false;
                    for(Menu m : usuario.getPerfil().getMenus() ){
                        if(uri.contains(m.getLink())){
                            possuiAcesso = true;
                            break;
                        }   
                    }   
                
                    if(!possuiAcesso){
                        sessao.setAttribute("msg", "Usuário não autorizado!");
                        response.sendRedirect("formLogin.jsp");
                    }
                }
            }
        }catch(Exception e){
            System.out.println("Erro: " + e.getMessage());
            e.printStackTrace();
        }
        
         return usuario;
    }
    
    
    public static boolean verificarPermissao(HttpServletRequest request, 
        HttpServletResponse response)
        throws ServletException, IOException{
        GerenciarLogin.response = response;
        Usuario usuario = null;
        boolean possuiAcesso = false;
        
        try{
            HttpSession sessao = request.getSession();
                if(sessao.getAttribute("ulogado") == null){
                    sessao.setAttribute(
                        "msg", "Usuário não autenticado no sistema!");
                    response.sendRedirect("formLogin.jsp");
            
                }else{
                    String uri = request.getRequestURI();
                    String queryString =request.getQueryString();
                    if(queryString != null){
                        uri += "?" + queryString;
                    }
                    usuario = 
                        (Usuario) request.getSession().
                                    getAttribute("ulogado");
        
                    if(usuario == null){
                        sessao.setAttribute(
                            "msg", "Usuário não autenticado no sistema!");
                            response.sendRedirect("formLogin.jsp");
                    }else{
                
                        for(Menu m : usuario.getPerfil().getMenus() ){
                            if(uri.contains(m.getLink())){
                                possuiAcesso = true;
                                break;
                            }
                        }   
                
                    }
                }
        }catch(Exception e){
            System.out.println("Erro: " + e.getMessage());
            e.printStackTrace();
        }
        
         return true;
    }
    
    
    
    
   
     private void despacharRequisicao(HttpServletRequest request, 
        HttpServletResponse response)
        throws ServletException, IOException{
            getServletContext().
                getRequestDispatcher("/formLogin.jsp").
                    forward(request, response);
        
    }




}
