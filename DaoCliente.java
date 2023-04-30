import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DaoCliente {
    private Connection conn;
    // faz a conexão com o banco de dados 
    private Statement st;
    // statement é uma interface que disponibliza métodos para executar as querys

    private void conectar(){
        try {
            this.conn = GerenciadorConexao.pegarConexao();
            // essa linha faz a conexão com o banco de dados
            st = conn.createStatement();
            // cria um objeto statement que vai executar os comandos querys
        } catch (ClassNotFoundException e1) {
            System.out.println(e1.getMessage());
        } catch (SQLException e2) {
            System.out.println(e2.getMessage());
        }
    }

    private void desconectar(){
        try {
            st.close();;
            conn.close();
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public boolean inserir(Cliente c) throws NumberFormatException{
        boolean resultado = false;
        try {
            this.conectar();
            String comando = "INSERT INTO tb_cliente VALUES (NULL, '" + c.getNome() + "', '" + c.getCpf() + "', '" + c.getEmail() + "', '" + c.getTelefone() + "', '" + c.getEndRua() + "', '" + c.getEndBairro() + "', " + c.getEndNroCasa() + ", '" + c.getEndComplemento() + "');";
            System.out.println(comando);
            st.executeUpdate(comando);
            resultado = true;
            // se foi inserido com sucesso vai retornar true
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            this.desconectar();
        }
        return resultado;
    }

    public ArrayList<Cliente> buscarTodos(){
        ArrayList<Cliente> resultado = new ArrayList<Cliente>();

        try {
            this.conectar();
            ResultSet rs = st.executeQuery("SELECT * FROM tb_cliente ORDER BY nome;");
            while (rs.next()) {
                Cliente c = new Cliente();
                c.setCodigo(rs.getInt("codigo"));
                c.setNome(rs.getString("nome"));
                c.setCpf(rs.getString("cpf"));
                c.setEmail(rs.getString("email"));
                c.setTelefone(rs.getString("telefone"));
                c.setEndRua(rs.getString("endRua"));
                c.setEndBairro(rs.getString("endBairro"));
                c.setEndNroCasa(rs.getInt("endNroCasa"));
                c.setEndComplemento(rs.getString("endComplemento"));
                resultado.add(c);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            this.desconectar();
        }
        return resultado;
        // método buscarTodos vai retornar uma arrayList 
    }

    public int excluir(int cod){
        int qtd = 0;
        try {
            this.conectar();
            String comando = "DELETE FROM tb_cliente WHERE codigo = " + cod + ";";
            st.executeUpdate(comando);
            qtd = st.getUpdateCount();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            this.desconectar();
        }
        return qtd;
    }

    public Cliente consultar(int cod){
        Cliente c = null;
        try {
            this.conectar();
            ResultSet rs = st.executeQuery("SELECT * FROM tb_cliente WHERE codigo = " + cod + ";");
            if(rs.next()){
                c = new Cliente();
                c.setCodigo(rs.getInt("codigo"));
                c.setNome(rs.getString("nome"));
                c.setCpf(rs.getString("cpf"));
                c.setEmail(rs.getString("email"));
                c.setTelefone(rs.getString("telefone"));
                c.setEndRua(rs.getString("endRua"));
                c.setEndBairro(rs.getString("endBairro"));
                c.setEndNroCasa(rs.getInt("endNroCasa"));
                c.setEndComplemento(rs.getString("endComplemento"));;
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            this.desconectar();
        }
        return c;
        // retorna o objeto que encontrou 
    }

    public int atualizar(Cliente c) throws NumberFormatException {
        int qtd = 0;
        try {
            this.conectar();
            String comando = "UPDATE tb_cliente SET nome = '" + c.getNome() + "', cpf = '" + c.getCpf() + "', email = '" + c.getEmail() + "', telefone = '" + c.getTelefone() + "', endRua = '" + c.getEndRua() + "', endBairro = '" + c.getEndBairro() + "', endNroCasa = " + c.getEndNroCasa() + ", endComplemento = '" + c.getEndComplemento() + "';";
            System.out.println(comando);
            st.executeUpdate(comando);
            qtd = st.getUpdateCount();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            this.desconectar();
        }
        return qtd;
    }
}
