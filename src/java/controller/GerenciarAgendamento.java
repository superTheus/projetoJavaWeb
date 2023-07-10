package controller;

import com.aspose.pdf.Page;
import com.aspose.pdf.TextFragment;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Agendamento;
import model.*;
import model.AgendamentoDAO;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.servlet.RequestDispatcher;

@WebServlet(name = "GerenciarAgendamento", urlPatterns = {"/gerenciarAgendamento"})
public class GerenciarAgendamento extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String acao = request.getParameter("acao");
        String idAgendamento = request.getParameter("idAgendamento");
        String mensagem = "";

        Agendamento a = new Agendamento();
        AgendamentoDAO adao = new AgendamentoDAO();

        try {
            if (acao.equals("listar")) {
                ArrayList<Agendamento> agendamentos = new ArrayList<>();
                agendamentos = adao.getLista();

                RequestDispatcher dispatcher
                        = getServletContext()
                                .getRequestDispatcher("/listarAgendamentos.jsp");
                request.setAttribute("agendamentos", agendamentos);
                dispatcher.forward(request, response);

            } else if (acao.equals("alterar")) {
                a = adao.getCarregarPotId(Integer.parseInt(idAgendamento));
                if (a.getIdAgendamento() > 0) {
                    RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/cadastrarAgendamento.jsp");

                    request.setAttribute("agendamento", a);
                    dispatcher.forward(request, response);
                } else {
                    mensagem = "Agendamento não encontrado";
                }

            } else if (acao.equals("ativar")) {

                a.setIdAgendamento(Integer.parseInt(idAgendamento));
                if (adao.ativar(a)) {
                    mensagem = "Agendamento ativado com sucesso!!!";
                } else {
                    mensagem = "ERRO NA ALTERAÇãO :";
                }

            } else if (acao.equals("desativar")) {

                a.setIdAgendamento(Integer.parseInt(idAgendamento));
                if (adao.desativar(a)) {
                    mensagem = "Agendamento desativado com sucesso!!!";
                } else {
                    mensagem = "ERRO NA ALTERAÇÃO!";
                }

            } else if (acao.equals("relatorio")) {
                Agendamento agendamentos = new Agendamento();
                agendamentos = adao.getCarregarPotId(Integer.parseInt(idAgendamento));
                
                
                
                System.out.println(agendamentos);

                Document documento = new Document();

                response.setContentType("application/pdf");
                response.reset();
                response.addHeader("Content-Disposition", "inline; filename=agendamentos.pdf");

                try {
                    Document document = new Document();
                    PdfWriter.getInstance(document, response.getOutputStream());
                    document.open();
                    document.add(new Paragraph("Relatório de Agendamento"));
                    document.add(new Paragraph(" "));


                    PdfPTable tableValue = new PdfPTable(2);
                    PdfPCell col1Value = new PdfPCell(new Paragraph("Valor Total: "));
                    PdfPCell col2Value = new PdfPCell(new Paragraph(String.valueOf(agendamentos.getValorTotal())));
                    tableValue.addCell(col1Value);
                    tableValue.addCell(col2Value);

                    PdfPTable tableClient = new PdfPTable(2);
                    PdfPCell col1Client = new PdfPCell(new Paragraph("Cliente: "));
                    PdfPCell col2Client = new PdfPCell(new Paragraph(String.valueOf(agendamentos.getCliente().getNome())));
                    tableClient.addCell(col1Client);
                    tableClient.addCell(col2Client);

                    PdfPTable tableUser = new PdfPTable(2);
                    PdfPCell col1User = new PdfPCell(new Paragraph("Usuário: "));
                    PdfPCell col2User = new PdfPCell(new Paragraph(String.valueOf(agendamentos.getUsuario().getNome())));
                    tableUser.addCell(col1User);
                    tableUser.addCell(col2User);

                    document.add(tableValue);
                    document.add(tableClient);
                    document.add(tableUser);

                    document.close();
                } catch (Exception e) {
                    System.err.println(e);
                    documento.close();
                }

            } else {
                response.sendRedirect("/index.jsp");
            }

        } catch (SQLException e) {
            mensagem = "ERRO: " + e.getMessage();
            e.printStackTrace();
        }

        out.println(
                "<script type='text/javascript'>"
                + "alert('" + mensagem + "');"
                + "location.href='gerenciarAgendamento?acao=listar'; "
                + "</script>"
        );

    }

    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        String idAgendamento = request.getParameter("idAgendamento");
        String valorTotal = request.getParameter("valortotal");
        String status = request.getParameter("status");

        String idUsuario = request.getParameter("idUsuario");
        String idCliente = request.getParameter("idCliente");
        String mensagem = "";

        Agendamento a = new Agendamento();
        AgendamentoDAO adao = new AgendamentoDAO();

        if (!idAgendamento.isEmpty()) {
            a.setIdAgendamento(Integer.parseInt(idAgendamento));

        }

        if (valorTotal.isEmpty() || valorTotal.equals("")) {
            request.setAttribute("msg", "Informe o valor total!");
            getServletContext().
                    getRequestDispatcher("/cadastrarAgendamento.jsp").
                    forward(request, response);;

        } else {
            a.setValorTotal(Double.parseDouble(valorTotal));
        }

        if (status.isEmpty() || status.equals("")) {
            mensagem = "Informe o status!";

        } else {
            a.setStatus(Integer.parseInt(status));
        }

        Usuario u = new Usuario();

        if (idUsuario.isEmpty() || idUsuario.equals("")) {
            request.setAttribute("msg", "Informe o Usuario!");
            despacharRequisicao(request, response);
        } else {
            try {
                u.setIdUsuario(Integer.parseInt(idUsuario));
                //associacao objeto usuario
                a.setUsuario(u);
            } catch (Exception e) {
                mensagem = "Erro: " + e.getMessage();
                e.printStackTrace();
            }

        }

        Cliente c = new Cliente();

        if (idCliente.isEmpty() || idCliente.equals("")) {
            request.setAttribute("msg", "Informe o Cliente!");
            despacharRequisicao(request, response);
        } else {
            try {
                c.setIdCliente(Integer.parseInt(idCliente));
                //associacao objeto servico
                a.setCliente(c);
            } catch (Exception e) {
                mensagem = "Erro: " + e.getMessage();
                e.printStackTrace();
            }

        }

        try {
            if (adao.gravar(a)) {
                mensagem = "Agendamento salvo na base de dados!";
            } else {
                mensagem = "Erro ao salvar o agendamento na base de dados!";
            }

        } catch (SQLException e) {
            mensagem = "Erro: " + e.getMessage();
            e.printStackTrace();
        }

        out.println(
                "<script type='text/javascript'>"
                + "alert('" + mensagem + "');"
                + "location.href='gerenciarAgendamento?acao=listar'; "
                + "</script>"
        );
    }

    private void despacharRequisicao(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        getServletContext().
                getRequestDispatcher("/cadastrarAgendamento.jsp").
                forward(request, response);

    }

}
