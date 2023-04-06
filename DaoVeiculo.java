import java.sql.Statement;
import java.sql.Connection;
import java.sql.SQLException;

public class DaoVeiculo {
    private Connection conn;
    private Statement st;

    private void conectar() {
        try {
            this.conn = GerenciadorConexao.pegarConexao();
            // conectado no banco
            st = conn.createStatement();
        } catch (ClassNotFoundException e1) {
            System.out.println(e1.getMessage());
        } catch (SQLException e2) {
            System.out.println(e2.getMessage());
        }
    }

    private void desconectar() {
        try {
            st.close();
            conn.close();
        } catch (SQLException e1) {
            System.out.println(e1.getMessage());
        }

    }

    public boolean inserir(Veiculo v) {
        boolean resultado = false;
        try {
            this.conectar();
            String comando = "INSERT INTO tb_veiculos VALUES (NULL, '" + v.getMarca() + "', '" + v.getModelo() + "', '" + v.getChassi() + "', " + v.getAno() + ");";
            System.out.println(comando);
            st.executeUpdate(comando);
            resultado = true;
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            this.desconectar();
        }
        return resultado;
    }
}
