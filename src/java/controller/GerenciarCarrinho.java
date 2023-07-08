
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import model.Agendamento;
import model.AgendamentoServico;
import model.Servico;
import model.ServicoDAO;
import org.apache.tomcat.jni.Time;

@WebServlet(name = "GerenciarCarrinho", urlPatterns = {"/gerenciarCarrinho"})
public class GerenciarCarrinho extends HttpServlet {

    
   

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();
        try {
            Agendamento a = new Agendamento();
            ArrayList<AgendamentoServico> carrinho = a.getCarrinho();
            String acao = request.getParameter("acao");
            String idServico = request.getParameter("idServico");
            String data = request.getParameter("data");
            String horario = request.getParameter("horario");
            ServicoDAO sdao = new ServicoDAO();
            if(acao.equals("add"));
            Servico s = new Servico();
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            
            s = sdao.getCarregarPorId(Integer.parseInt(idServico));
            AgendamentoServico as = new AgendamentoServico();
            as.setServico(s);
           
        } catch (SQLException e) {
            out.print("Erro" + e.getMessage());
            e.printStackTrace();
        }
        
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }

    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
