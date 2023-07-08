package model;

import factory.ConexaoFactory;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import lombok.Data;

public class AgendamentoDAO {

    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    String sql;

    public ArrayList<Agendamento> getLista() throws SQLException {

        ArrayList<Agendamento> lista = new ArrayList<>();

        sql = "Select a.idAgendamento, a.valorTotal, a.status, c.nome, u.nome "
                + "From agendamento a "
                + "Inner join cliente c On c.idCliente = a.idCliente "
                + "Inner join usuario u On u.idUsuario = a.idUsuario ";

        con = ConexaoFactory.conectar();
        ps = con.prepareStatement(sql);
        rs = ps.executeQuery();
        
        
        while (rs.next()) {
            Agendamento a = new Agendamento();
            a.setIdAgendamento(rs.getInt("a.idAgendamento"));
            a.setValorTotal(rs.getDouble("a.valorTotal"));
            a.setStatus(rs.getInt("a.status"));

            Cliente c = new Cliente();
            c.setNome(rs.getString("c.nome"));
            a.setCliente(c);

            Usuario u = new Usuario();
            u.setNome(rs.getString("u.nome"));
            a.setUsuario(u);

            lista.add(a);

        }
        
        return lista;
    }

    public boolean gravar(Agendamento a) throws SQLException {
        con = ConexaoFactory.conectar();

        if (a.getIdAgendamento() == 0) {
            sql = "Insert into agendamento (valorTotal, status, idCliente , idUsuario)"
                    + "values(?, ?, ?, ?)";
            ps = con.prepareStatement(sql);

            ps.setDouble(1, a.getValorTotal());
            ps.setInt(2, a.getStatus());
            ps.setInt(3, a.getCliente().getIdCliente());
            ps.setInt(4, a.getUsuario().getIdUsuario());

        } else {
            sql = "Update agendamento set valorTotal = ?, status = ?, idCliente = ?, idUsuario = ? WHERE idAgendamento = ?";
            ps = con.prepareStatement(sql);

            ps.setDouble(1, a.getValorTotal());
            ps.setInt(2, a.getStatus());
            ps.setInt(3, a.getCliente().getIdCliente());
            ps.setInt(4, a.getUsuario().getIdUsuario());
            ps.setInt(5, a.getIdAgendamento());
        }

        ps.executeUpdate();
        ConexaoFactory.close(con);

        return true;

    }

    public Agendamento getCarregarPotId(int idAgendamento) throws SQLException {
        Agendamento a = new Agendamento();

        sql = "SELECT idAgendamento, valorTotal, status, idCliente, idUsuario "
                + "FROM agendamento WHERE idAgendamento = ? ";
        con = ConexaoFactory.conectar();
        ps = con.prepareStatement(sql);
        ps.setInt(1, idAgendamento);
        rs = ps.executeQuery();
        if (rs.next()) {
            a.setIdAgendamento(rs.getInt("a.idAgendamento"));
            a.setValorTotal(rs.getDouble("a.valorTotal"));
            a.setStatus(rs.getInt("a.status"));

            Cliente c = new Cliente();
            c.setIdCliente(rs.getInt("idCliente"));

            a.setCliente(c);

            Usuario u = new Usuario();
            u.setIdUsuario(rs.getInt("idUsuario"));
            a.setUsuario(u);

        }

        return a;

    }

    public boolean desativar(Agendamento a) throws SQLException {
        sql = "UPDATE agendamento set status = 0 WHERE idAgendamento = ?";
        con = ConexaoFactory.conectar();
        ps = con.prepareStatement(sql);
        ps.setInt(1, a.getIdAgendamento());
        ps.executeUpdate();
        ConexaoFactory.close(con);

        return true;
    }

    public boolean ativar(Agendamento a) throws SQLException {
        sql = "UPDATE agendamento set status = 1 WHERE idAgendamento = ?";
        con = ConexaoFactory.conectar();
        ps = con.prepareStatement(sql);
        ps.setInt(1, a.getIdAgendamento());
        ps.executeUpdate();
        ConexaoFactory.close(con);

        return true;
    }
}
