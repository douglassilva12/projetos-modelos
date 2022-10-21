package asd;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class ControllerPrimeiroDB extends ControllerDBPadrao {

    public ModelPrimeiro getPrimeiro(int codigo) {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        ModelPrimeiro primeiroBancoDados = new ModelPrimeiro();
        try {
            conn = Conexao.getConexao();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(
                    "select "
                    + " pricodigo, "
                    + " prinome, "
                    + " priidade"
                    + " from tbprimeiro "
                    + " where pricodigo = " + codigo);
            if (rs.next()) {
                // Pega valor inteiro
                int codigoBancoDados = rs.getInt("pricodigo");
                String nomeBancoDados = rs.getString("prinome");
                String idadeBancoDados = rs.getString("priidade");
                // Setar no modelo
                primeiroBancoDados.setCodigo(codigoBancoDados);
                primeiroBancoDados.setNome(nomeBancoDados);
                primeiroBancoDados.setIdade(idadeBancoDados);
            }
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro de conexão! " + erro);
        } finally {
            Conexao.closeAll(conn);
        }
        return primeiroBancoDados;
    }

    public boolean gravarAlteracao(ModelPrimeiro primeiro) {
        Connection conn = null;
        Statement stmt = null;
        PreparedStatement pstmt = null;
        boolean executou = false;
        try {
            conn = Conexao.getConexao();
            stmt = conn.createStatement();
            pstmt = conn.prepareStatement(" update tbprimeiro set "
                    + " pricodigo = ?, "
                    + " prinome = ?, "
                    + " priidade = ?"
                    + " where forcodigo = ?");
            pstmt.setInt(1, primeiro.getCodigo());
            pstmt.setString(2, primeiro.getNome());
            pstmt.setString(3, primeiro.getIdade());
            pstmt.executeUpdate();
            executou = true;
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro de conexão! " + erro);
        } finally {
            Conexao.closeAll(conn);
        }
        return executou;
    }

    public boolean gravarInsercao(ModelPrimeiro primeiro) {
        Connection conn = null;
        Statement stmt = null;
        PreparedStatement pstmt = null;
        boolean executou = false;
        try {
            conn = Conexao.getConexao();
            stmt = conn.createStatement();
            pstmt = conn.prepareStatement(" insert into tbprimeiro ("
                    + " pricodigo, "
                    + " prinome, "
                    + " priidade"
                    + ") values("
                    + " ?, ?, ? "
                    + ")");
            pstmt.setInt(1, primeiro.getCodigo());
            pstmt.setString(2, primeiro.getNome());
            pstmt.setString(3, primeiro.getIdade());
            pstmt.executeUpdate();
            executou = true;
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro de conexão! " + erro);
        } finally {
            Conexao.closeAll(conn);
        }
        return executou;
    }
}
