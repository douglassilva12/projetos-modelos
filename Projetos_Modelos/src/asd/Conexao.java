package asd;

import java.sql.*;
import javax.swing.JOptionPane;

public class Conexao {

//    private static final String DRIVER   = "org.postgresql.Driver";
//    private static final String BANCO    = "sistemacomercial";
//    private static final String CONEXAO  = "jdbc:postgresql://db.rtdeipolklruhepjiekz.supabase.co/" + BANCO;
//    private static final String USUARIO  = "postgres";
//    private static final String SENHA    = "LV683fPMyxLKQ13C";
    public static void closeAll(Connection conn) {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro ao fechar conexao! \n " + erro.getMessage());
        }
    }

    public static Connection getConexao() {
        // SUPABASE
        String driver = "org.postgresql.Driver";
        String url = "jdbc:postgresql://db.htwkmrtrfwkcpedwlcsh.supabase.co/postgres";
        String usuario = "postgres";
        String senha = "neLFsdsnz6Ugl4LJ";
        Connection conn = null;
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, usuario, senha);
        } catch (ClassNotFoundException erro) {
            JOptionPane.showMessageDialog(null, "Erro de driver! \n" + erro.getMessage());
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro de Conexao! \n" + erro.getMessage());
        }
        return conn;
    }
}
