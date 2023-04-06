import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class GerenciadorConexao {
    private static Connection conexao;
    // quer dizer que só vou ter uma conexão 

    public static Connection pegarConexao() throws ClassNotFoundException, SQLException {
        String url = "jdbc:mysql://127.0.0.1/bd_locadora?characterEnconding=latin1";
        String usuario = "gabriela";
        // em casa gabriela
        String senha = "daniela";
        // em casa daniela

        Class.forName("com.mysql.cj.jdbc.Driver");
        // caminho onde tá minha class driver
        conexao = DriverManager.getConnection(url, usuario, senha);
        return conexao;

    }
}

